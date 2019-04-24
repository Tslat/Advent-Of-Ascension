package net.nevermine.block.decoration;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.nevermine.izer.Itemizer;

public class BasicStaircase extends BlockStairs {
	private String name;

	public BasicStaircase(final Block b) {
		super(b, 0);
		setCreativeTab(Itemizer.DecorativeBlocksTab);
		setHardness(2.0f);
		setResistance(0.5f);
		useNeighborBrightness = true;
	}

	public Block setName(final String name) {
		setBlockName(this.name = name);
		GameRegistry.registerBlock(this, name);
		return this;
	}
}
