package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.material.Material;

import java.util.Random;

public class FlowerBlock extends GenericPlantBlock {
	public FlowerBlock(String name, String registryName, float hardness, Material... growthMaterials) {
		super(name, registryName, hardness, growthMaterials);
	}

	public FlowerBlock(String name, String registryName, Material... growthMaterials) {
		super(name, registryName, growthMaterials);
	}

	@Override
	public int quantityDropped(Random random) {
		return 1;
	}
}
