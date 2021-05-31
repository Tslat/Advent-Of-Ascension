package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.BlockUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;

public class TeaSink extends HorizontalBlock {
	public static final BooleanProperty FILLED = BooleanProperty.create("filled");
	private static final VoxelShape BOTTOM_SHAPE = box(0, 0, 0, 16, 7, 16);
	private static final VoxelShape NORTH_SHAPE = VoxelShapes.or(BOTTOM_SHAPE, box(6, 7, 0, 10, 16, 10));
	private static final VoxelShape SOUTH_SHAPE = VoxelShapes.or(BOTTOM_SHAPE, box(6, 7, 6, 10, 16, 16));
	private static final VoxelShape WEST_SHAPE = VoxelShapes.or(BOTTOM_SHAPE, box(0, 7, 6, 10, 16, 10));
	private static final VoxelShape EAST_SHAPE = VoxelShapes.or(BOTTOM_SHAPE, box(6, 7, 6, 16, 16, 10));

	public TeaSink() {
		super(new BlockUtil.CompactProperties(Material.WOOD, MaterialColor.COLOR_LIGHT_GRAY).stats(5f, 3f).get());

		registerDefaultState(defaultBlockState().setValue(FILLED, false).setValue(FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		switch (state.getValue(FACING)) {
			case EAST:
				return EAST_SHAPE;
			case SOUTH:
				return SOUTH_SHAPE;
			case WEST:
				return WEST_SHAPE;
			case NORTH:
			default:
				return NORTH_SHAPE;
		}
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (state.getValue(FILLED)) {
			if (player.getItemInHand(hand).getItem() == AoAItems.CUP.get() && player.inventory.contains(new ItemStack(AoAItems.TEA_SHREDDINGS.get()))) {
				if (!world.isClientSide()) {
					boolean success = false;

					if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.MYSTIC_SHROOMS.get()), true, 1)) {
						ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.TEA_SHREDDINGS.get()), true, 1);
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.FUNGAL_TEA.get()));

						success = true;
					}
					else if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.NATURE_MELON_SLICE.get()), true, 1)) {
						ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.TEA_SHREDDINGS.get()), true, 1);
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.NATURAL_TEA.get()));

						success = true;
					}
					else if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.TEA_SHREDDINGS.get()), true, 1)) {
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.TEA.get()));

						success = true;
					}

					if (success) {
						if (!player.isCreative())
							player.getItemInHand(hand).shrink(1);

						world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_TEA_SINK_USE.get(), SoundCategory.BLOCKS, 1.0f, 1.0f);

						if (RandomUtil.oneInNChance(7))
							world.setBlockAndUpdate(pos, AoABlocks.TEA_SINK.get().defaultBlockState().setValue(HorizontalBlock.FACING, state.getValue(HorizontalBlock.FACING)));
					}
				}
			}

			return ActionResultType.SUCCESS;
		}
		else {
			if (player.getItemInHand(hand).getItem() == Items.WATER_BUCKET) {
				if (!world.isClientSide()) {
					if (!player.isCreative()) {
						player.getItemInHand(hand).shrink(1);
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(Items.BUCKET));
					}

					world.setBlockAndUpdate(pos, defaultBlockState().setValue(HorizontalBlock.FACING, state.getValue(HorizontalBlock.FACING)).setValue(FILLED, true));
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_TEA_SINK_FILL.get(), SoundCategory.BLOCKS, 1.0f, 1.0f);
				}

				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.PASS;
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return defaultBlockState().setValue(FILLED, false).setValue(HorizontalBlock.FACING, EntityUtil.getDirectionFacing(context.getPlayer(), true));
	}

	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FILLED, HorizontalBlock.FACING);
	}
}
