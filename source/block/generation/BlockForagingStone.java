package net.nevermine.block.generation;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.nevermine.izer.Itemizer;

public class BlockForagingStone extends Block {
	private String name;

	public BlockForagingStone(final Material Mtr) {
		super(Mtr);
		setCreativeTab(Itemizer.GenerationTab);
		if (Mtr == Material.rock) {
			setHardness(1.3f);
		}
		else {
			setHarvestLevel("shovel", 1);
			setHardness(0.5f);
		}
		setResistance(8.0f);
		if (Mtr == Material.ground) {
			setStepSound(BlockForagingStone.soundTypeGravel);
		}
		if (Mtr == Material.cloth) {
			setStepSound(BlockForagingStone.soundTypeCloth);
		}
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
