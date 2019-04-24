package net.nevermine.block.generation.structure;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLadder;
import net.minecraft.block.material.Material;
import net.nevermine.izer.Itemizer;

import java.util.Random;

public class BlockUnbreakableLadder extends BlockLadder {
	private String name;

	public BlockUnbreakableLadder(final Material Rck) {
		setCreativeTab(Itemizer.GenerationTab);
		setHardness(-1.0f);
		setResistance(999999999f);
	}

	public int quantityDropped(final Random p_149745_1_) {
		return 0;
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
