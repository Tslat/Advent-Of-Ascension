package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class CarvedRunicBlock extends Block {
	public CarvedRunicBlock(String name, String registryName) {
		super(Material.ROCK);
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(3.0f);
		setResistance(10.0f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.FUNCTIONAL_BLOCKS);
	}
}
