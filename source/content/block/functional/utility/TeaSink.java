package net.tslat.aoa3.content.block.functional.utility;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.InventoryUtil;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;


public class TeaSink extends HorizontalDirectionalBlock {
	public static final BooleanProperty FILLED = BooleanProperty.create("filled");
	private static final VoxelShape BOTTOM_SHAPE = box(0, 0, 0, 16, 7, 16);
	private static final VoxelShape NORTH_SHAPE = Shapes.or(BOTTOM_SHAPE, box(6, 7, 0, 10, 16, 10));
	private static final VoxelShape SOUTH_SHAPE = Shapes.or(BOTTOM_SHAPE, box(6, 7, 6, 10, 16, 16));
	private static final VoxelShape WEST_SHAPE = Shapes.or(BOTTOM_SHAPE, box(0, 7, 6, 10, 16, 10));
	private static final VoxelShape EAST_SHAPE = Shapes.or(BOTTOM_SHAPE, box(6, 7, 6, 16, 16, 10));

	public TeaSink(BlockBehaviour.Properties properties) {
		super(properties);

		registerDefaultState(defaultBlockState().setValue(FILLED, false).setValue(FACING, Direction.NORTH));
	}

	@Override
	protected MapCodec<? extends TeaSink> codec() {
		return simpleCodec(TeaSink::new);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
		return switch (state.getValue(FACING)) {
			case EAST -> EAST_SHAPE;
			case SOUTH -> SOUTH_SHAPE;
			case WEST -> WEST_SHAPE;
			default -> NORTH_SHAPE;
		};
	}

	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (state.getValue(FILLED)) {
			if (!stack.is(AoAItems.CUP))
				return ItemInteractionResult.FAIL;

			return InventoryUtil.findItem(player, AoAItems.TEA_SHREDDINGS).map(shreddings -> {
				if (player instanceof ServerPlayer pl) {
					ItemLike tea = AoAItems.TEA;

					if (InventoryUtil.findItemForConsumption(pl, AoAItems.MYSTIC_SHROOMS, pl.getAbilities().instabuild ? 0 : 1, true)) {
						tea = AoAItems.FUNGAL_TEA;
					}
					else if (InventoryUtil.findItemForConsumption(pl, AoAItems.NATURE_MELON_SLICE, pl.getAbilities().instabuild ? 0 : 1, true)) {
						tea = AoAItems.NATURE_MELON_SLICE;
					}

					if (!pl.getAbilities().instabuild) {
						shreddings.right().shrink(1);
						stack.shrink(1);
					}

					InventoryUtil.giveItemTo(pl, tea);
					level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_TEA_SINK_USE.get(), SoundSource.BLOCKS, 1.0f, 1.0f);

					if (RandomUtil.oneInNChance(7))
						level.setBlockAndUpdate(pos, AoABlocks.TEA_SINK.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING)));
				}

				return ItemInteractionResult.sidedSuccess(level.isClientSide);
			}).orElse(ItemInteractionResult.FAIL);
		}
		else {
			if (stack.getItem() == Items.WATER_BUCKET) {
				if (player instanceof ServerPlayer pl) {
					if (!pl.getAbilities().instabuild) {
						stack.shrink(1);
						InventoryUtil.giveItemTo(pl, Items.BUCKET);
					}

					level.setBlockAndUpdate(pos, defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING)).setValue(FILLED, true));
					level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_TEA_SINK_FILL.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
				}

				return ItemInteractionResult.sidedSuccess(level.isClientSide);
			}
		}

		return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(FILLED, false).setValue(HorizontalDirectionalBlock.FACING, EntityUtil.getDirectionFacing(context.getPlayer(), true));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FILLED, HorizontalDirectionalBlock.FACING);
	}
}
