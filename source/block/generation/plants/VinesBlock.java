package net.tslat.aoa3.block.generation.plants;

import net.minecraft.block.BlockVine;
import net.minecraft.block.SoundType;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class VinesBlock extends BlockVine {
	public VinesBlock(String name, String registryName) {
		super();
		setTranslationKey(name);
		setRegistryName("aoa3:" + registryName);
		setHardness(0.2f);
		setSoundType(SoundType.PLANT);
		setCreativeTab(CreativeTabsRegister.generationBlocksTab);
	}
}
