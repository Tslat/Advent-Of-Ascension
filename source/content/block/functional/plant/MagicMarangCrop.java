package net.tslat.aoa3.content.block.functional.plant;

import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.tslat.aoa3.util.BlockUtil;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class MagicMarangCrop extends HangingCropBlock {
	private static final VoxelShape[] SHAPES = new VoxelShape[] {
			BlockUtil.pixelBasedCube(0, 12, 0, 16, 16, 16),
			BlockUtil.pixelBasedCube(0, 11, 0, 16, 16, 16),
			BlockUtil.pixelBasedCube(0, 9, 0, 16, 16, 16),
			BlockUtil.pixelBasedCube(0, 8, 0, 16, 16, 16),
			BlockUtil.pixelBasedCube(0, 6, 0, 16, 16, 16),
			BlockUtil.pixelBasedCube(0, 5, 0, 16, 16, 16),
			BlockUtil.pixelBasedCube(0, 4, 0, 16, 16, 16),
			BlockUtil.pixelBasedCube(0, 2, 0, 16, 16, 16)};

	public MagicMarangCrop(MaterialColor colour, Supplier<Item> seedItem) {
		super(colour, seedItem);
	}

	@Override
	public void playerDestroy(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable TileEntity pTe, ItemStack pStack) {
		super.playerDestroy(world, player, pos, state, pTe, pStack);

		if (!player.isCreative() && state.getValue(AGE) == 7)
			world.setBlock(pos, state.setValue(AGE, 0), Constants.BlockFlags.DEFAULT);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return SHAPES[state.getValue(AGE)];
	}
}
