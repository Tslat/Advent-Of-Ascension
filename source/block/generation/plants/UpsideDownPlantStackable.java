package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class UpsideDownPlantStackable extends PlantStackable {
	public UpsideDownPlantStackable(String name, String registryName, Material... growthMaterial) {
		super(name, registryName, growthMaterial);
	}

	@Override
	public PlantStackable setStemBlock(PlantStackable block) {
		this.stemBlock = block;

		if (block instanceof BidirectionalPlantStackable) {
			((BidirectionalPlantStackable)block).bottomHatBlock = this;
		}
		else {
			block.hatBlock = this;
		}

		return this;
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState targetState = world.getBlockState(pos.up());

		return ((growthMaterials.isEmpty() || growthMaterials.contains(targetState.getMaterial())) && targetState.isOpaqueCube()) || targetState.getBlock() == stemBlock;
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		Block block = world.getBlockState(pos = pos.down()).getBlock();

		while (block == stemBlock || block == hatBlock) {
			world.setBlockToAir(pos);
			block = world.getBlockState(pos = pos.down()).getBlock();
		}
	}
}
