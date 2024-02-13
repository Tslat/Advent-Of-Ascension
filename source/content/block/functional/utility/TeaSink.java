package net.tslat.aoa3.content.block.functional.utility;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
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
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
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
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		if (state.getValue(FILLED)) {
			if (player.getItemInHand(hand).getItem() == AoAItems.CUP.get() && player.getInventory().contains(new ItemStack(AoAItems.TEA_SHREDDINGS.get()))) {
				if (!world.isClientSide()) {
					boolean success = false;

					if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.MYSTIC_SHROOMS.get()), true, 1, false)) {
						ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.TEA_SHREDDINGS.get()), true, 1, false);
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.FUNGAL_TEA.get()));

						success = true;
					}
					else if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.NATURE_MELON_SLICE.get()), true, 1, false)) {
						ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.TEA_SHREDDINGS.get()), true, 1, false);
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.NATURAL_TEA.get()));

						success = true;
					}
					else if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.TEA_SHREDDINGS.get()), true, 1, false)) {
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.TEA.get()));

						success = true;
					}

					if (success) {
						if (!player.isCreative())
							player.getItemInHand(hand).shrink(1);

						world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_TEA_SINK_USE.get(), SoundSource.BLOCKS, 1.0f, 1.0f);

						if (RandomUtil.oneInNChance(7))
							world.setBlockAndUpdate(pos, AoABlocks.TEA_SINK.get().defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING)));
					}
				}
			}

			return InteractionResult.SUCCESS;
		}
		else {
			if (player.getItemInHand(hand).getItem() == Items.WATER_BUCKET) {
				if (!world.isClientSide()) {
					if (!player.isCreative()) {
						player.getItemInHand(hand).shrink(1);
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(Items.BUCKET));
					}

					world.setBlockAndUpdate(pos, defaultBlockState().setValue(HorizontalDirectionalBlock.FACING, state.getValue(HorizontalDirectionalBlock.FACING)).setValue(FILLED, true));
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_TEA_SINK_FILL.get(), SoundSource.BLOCKS, 1.0f, 1.0f);
				}

				return InteractionResult.SUCCESS;
			}
		}

		return InteractionResult.PASS;
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
