package net.nevermine.block.functional;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.nevermine.izer.Itemizer;

public class BlockLightFence extends BlockFence {
	private String name;

	public BlockLightFence(final String str) {
		super("nevermine:" + str, Material.glass);
		setCreativeTab(Itemizer.GenerationTab);
		setLightLevel(0.9f);
	}

	public Block setName(final String name) {
		setBlockName(this.name = name);
		GameRegistry.registerBlock(this, name);
		return this;
	}
}
