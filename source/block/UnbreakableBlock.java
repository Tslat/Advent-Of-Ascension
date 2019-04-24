package net.tslat.aoa3.block;

import net.minecraft.block.material.Material;

public class UnbreakableBlock extends BasicBlock {
	public UnbreakableBlock(String name, String registryName, Material material) {
		super(name, registryName, material, -1f, 999999999f);
		setCreativeTab(null);
	}
}
