package net.nevermine.block.generation;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.nevermine.izer.Itemizer;

public class GeneratedBlock extends Block {
	private String name;

	public GeneratedBlock(final Material Mtr) {
		super(Mtr);
		setCreativeTab(Itemizer.GenerationTab);
		if (Mtr == Material.rock) {
			setHardness(1.6f);
			setResistance(25.0f);
		}
		else {
			setHarvestLevel("shovel", 1);
			setHardness(0.6f);
			setResistance(2.2f);
		}

		if (Mtr == Material.ground) {
			setStepSound(GeneratedBlock.soundTypeGravel);
		}
		else if (Mtr == Material.cloth) {
			setStepSound(GeneratedBlock.soundTypeCloth);
		}
		else if (Mtr == Material.sand) {
			setStepSound(GeneratedBlock.soundTypeSand);
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
