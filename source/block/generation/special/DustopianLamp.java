package net.tslat.aoa3.block.generation.special;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class DustopianLamp extends Block {
	public DustopianLamp() {
		super(Material.ROCK);
		setTranslationKey("DustopianLamp");
		setRegistryName("aoa3:dustopian_lamp");
		setHardness(5.0f);
		setResistance(10.0f);
		setLightLevel(0.9f);
		setCreativeTab(null);
	}
}
