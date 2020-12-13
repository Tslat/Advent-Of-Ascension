package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.SoundType;
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

public class TeaSink extends Block {
	public static final BooleanProperty FILLED = BooleanProperty.create("filled");
	private static final VoxelShape BOTTOM_SHAPE = makeCuboidShape(0, 0, 0, 16, 7, 16);
	private static final VoxelShape TOP_SHAPE = makeCuboidShape(6, 7, 0, 10, 16, 10);
	private static final VoxelShape SHAPE = VoxelShapes.or(BOTTOM_SHAPE, TOP_SHAPE);

	public TeaSink() {
		super(BlockUtil.generateBlockProperties(Material.WOOD, MaterialColor.LIGHT_GRAY, 5, 3, SoundType.WOOD));

		setDefaultState(getDefaultState().with(FILLED, false).with(HorizontalBlock.HORIZONTAL_FACING, Direction.NORTH));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPE;
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
		if (state.get(FILLED)) {
			if (player.getHeldItem(hand).getItem() == AoAItems.CUP.get() && player.inventory.hasItemStack(new ItemStack(AoAItems.TEA_SHREDDINGS.get()))) {
				if (!world.isRemote()) {
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
							player.getHeldItem(hand).shrink(1);

						world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), AoASounds.BLOCK_TEA_SINK_USE.get(), SoundCategory.BLOCKS, 1.0f, 1.0f);

						if (RandomUtil.oneInNChance(7))
							world.setBlockState(pos, AoABlocks.TEA_SINK.get().getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, state.get(HorizontalBlock.HORIZONTAL_FACING)));
					}
				}
			}

			return ActionResultType.SUCCESS;
		}
		else {
			if (player.getHeldItem(hand).getItem() == Items.WATER_BUCKET) {
				if (!world.isRemote()) {
					if (!player.isCreative()) {
						player.getHeldItem(hand).shrink(1);
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(Items.BUCKET));
					}

					world.setBlockState(pos, getDefaultState().with(HorizontalBlock.HORIZONTAL_FACING, state.get(HorizontalBlock.HORIZONTAL_FACING)).with(FILLED, true));
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
		return getDefaultState().with(FILLED, false).with(HorizontalBlock.HORIZONTAL_FACING, EntityUtil.getDirectionFacing(context.getPlayer(), true));
	}

	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return false;
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FILLED, HorizontalBlock.HORIZONTAL_FACING);
	}
}
