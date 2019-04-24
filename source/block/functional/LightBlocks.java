package net.nevermine.block.functional;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.nevermine.izer.Itemizer;

public class LightBlocks extends Block {
	private String name;
	private static Material Gls;
	private static Block.SoundType glass;

	public LightBlocks() {
		super(LightBlocks.Gls);
		setCreativeTab(Itemizer.LightingTab);
		setHardness(5.0f);
		setResistance(0.5f);
		setLightLevel(1.0f);
		setSoundType(LightBlocks.glass);
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

	public Block setSoundType(final Block.SoundType name) {
		return setStepSound(name);
	}

	static {
		LightBlocks.Gls = Material.glass;
		LightBlocks.glass = Block.soundTypeGlass;
	}
}
