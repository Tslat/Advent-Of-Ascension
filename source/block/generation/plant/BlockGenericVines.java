package net.nevermine.block.generation.plant;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.nevermine.izer.Itemizer;

public class BlockGenericVines extends BlockVine {
	private String name;

	public BlockGenericVines(final Material Rck) {
		setCreativeTab(Itemizer.GenerationTab);
		setStepSound(BlockGenericPlant.soundTypeGrass);
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
