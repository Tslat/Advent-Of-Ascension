package net.tslat.aoa3.block.functional.lights;

import net.minecraft.block.material.Material;

public class UnbreakableLightBlock extends LightBlock {
	public UnbreakableLightBlock(String name, String registryName, Material material, float luminosity) {
		super(name, registryName, material, luminosity, -1f, 999999999f);
		setCreativeTab(null);
	}
}
