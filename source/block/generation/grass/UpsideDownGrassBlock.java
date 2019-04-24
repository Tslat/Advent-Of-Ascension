package net.tslat.aoa3.block.generation.grass;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class UpsideDownGrassBlock extends GrassBlock {
	public UpsideDownGrassBlock(String name, String registryName, Block dirtBlock) {
		super(name, registryName, dirtBlock);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
		if (!world.isRemote) {
			if (!world.isAreaLoaded(pos, 3))
				return;

			if (world.getLightFromNeighbors(pos.down()) < 4 && world.getBlockState(pos.down()).getBlock().getLightOpacity(world.getBlockState(pos.down()), world, pos.down()) > 2) {
				world.setBlockState(pos, dirtBlock.getDefaultState());
			}
			else {
				for (int i = 0; i < 4; i++) {
					BlockPos newPos = pos.add(rand.nextInt(3) - 1, rand.nextInt(5) - 3, rand.nextInt(3) - 1);

					if (newPos.getY() <= 0 || newPos.getY() >= 256 || !world.isBlockLoaded(newPos))
						return;

					IBlockState targetBlockState = world.getBlockState(newPos);

					if (targetBlockState.getBlock() == dirtBlock && world.getLightFromNeighbors(newPos.down()) >= 4 && world.getBlockState(newPos.down()).getLightOpacity(world, pos.down()) <= 2)
						world.setBlockState(newPos, getDefaultState());
				}
			}
		}
	}
}
