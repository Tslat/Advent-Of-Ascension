package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class UpsideDownGenericPlant extends GenericPlantBlock {
	public UpsideDownGenericPlant(String name, String registryName, float hardness, Material... growthMaterials) {
		super(name, registryName, hardness, growthMaterials);
	}

	public UpsideDownGenericPlant(String name, String registryName, Material... growthMaterials) {
		super(name, registryName, growthMaterials);
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState targetState = world.getBlockState(pos.up());

		return targetState.isOpaqueCube() && (growthMaterials.isEmpty() || growthMaterials.contains(targetState.getMaterial()));
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0.1, 0.1, 0.1, 1, 1, 1);
	}
}
