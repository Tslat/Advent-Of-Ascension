package net.tslat.aoa3.content.block.functional.plant;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
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
	public void playerDestroy(Level world, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity pTe, ItemStack pStack) {
		super.playerDestroy(world, player, pos, state, pTe, pStack);

		if (!player.isCreative() && state.getValue(AGE) == 7)
			world.setBlock(pos, state.setValue(AGE, 0), Block.UPDATE_ALL);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return SHAPES[state.getValue(AGE)];
	}
}
