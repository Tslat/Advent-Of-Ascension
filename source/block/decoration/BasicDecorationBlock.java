package net.tslat.aoa3.block.decoration;

import net.minecraft.block.material.Material;
import net.tslat.aoa3.block.BasicBlock;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;

public class BasicDecorationBlock extends BasicBlock {
	public BasicDecorationBlock(String name, String registryName, Material material, float hardness, float resistance) {
		super(name, registryName, material, hardness, resistance);
		setCreativeTab(CreativeTabsRegister.decorationBlocksTab);
	}

	public BasicDecorationBlock(String name, String registryName, Material material) {
		this(name, registryName, material, 1.5f, 10f);
	}
}
