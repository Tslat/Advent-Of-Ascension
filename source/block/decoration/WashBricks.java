package net.nevermine.block.decoration;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.nevermine.izer.Itemizer;

public class WashBricks extends Block {
	private String name;
	private static Material WshBricks;

	public WashBricks() {
		super(WashBricks.WshBricks);
		setCreativeTab(Itemizer.DecorativeBlocksTab);
		setHardness(25.0f);
		setResistance(0.5f);
		setLightLevel(0.0f);
	}

	public Block setTextureName(final String name) {
		return setBlockTextureName("nevermine:" + name);
	}

	public Block setName(final String name) {
		setBlockName(this.name = name);
		setTextureName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}

	static {
		WashBricks.WshBricks = Material.rock;
	}
}
