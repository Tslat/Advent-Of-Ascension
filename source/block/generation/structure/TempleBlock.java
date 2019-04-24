package net.nevermine.block.generation.structure;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.nevermine.izer.Itemizer;

public class TempleBlock extends Block {
	private String name;

	public TempleBlock(final Material Rck) {
		super(Rck);
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(-1.0f);
		setResistance(999999999f);
		setBlockUnbreakable();
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
}
