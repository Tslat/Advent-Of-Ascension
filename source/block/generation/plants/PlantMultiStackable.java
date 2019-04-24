package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlantMultiStackable extends PlantStackable {
	protected List<PlantStackable> stemBlocks = new ArrayList<PlantStackable>();

	public PlantMultiStackable(String name, String registryName, Material material, float hardness, Material... growthMaterial) {
		super(name, registryName, material, hardness, growthMaterial);
		stemBlocks.add(this);
	}

	public PlantMultiStackable(String name, String registryName, Material... growthMaterials) {
		super(name, registryName, growthMaterials);
		stemBlocks.add(this);
	}

	public PlantMultiStackable addStemBlock(PlantMultiStackable... blocks) {
		this.stemBlocks.addAll(Arrays.asList(blocks));

		for (PlantMultiStackable bl : blocks) {
			bl.stemBlocks.add(this);
		}

		return this;
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		IBlockState targetState = world.getBlockState(pos.down());

		return ((growthMaterials.isEmpty() || growthMaterials.contains(targetState.getMaterial())) && targetState.isOpaqueCube()) || stemBlocks.contains(targetState.getBlock());
	}

	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state) {
		Block block = world.getBlockState(pos = pos.up()).getBlock();

		while (stemBlocks.contains(block) || block == hatBlock) {
			world.setBlockToAir(pos);
			block = world.getBlockState(pos = pos.up()).getBlock();
		}
	}
}
