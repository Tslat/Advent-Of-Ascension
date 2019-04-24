package net.nevermine.block.decoration;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.nevermine.izer.Itemizer;

public class BasicDecoration extends Block {
	private String name;

	public BasicDecoration(final Material DecorBasic) {
		super(DecorBasic);
		setCreativeTab(Itemizer.DecorativeBlocksTab);
		setHardness(2.0f);
		setResistance(0.5f);

		if (DecorBasic == Material.wood || DecorBasic == Material.gourd)
			setStepSound(Block.soundTypeWood);
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
