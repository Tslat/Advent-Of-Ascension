package net.nevermine.block.modelblocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMod extends Block {
	protected static Block.SoundType stone;
	protected static Block.SoundType grass;
	protected static Block.SoundType cloth;
	protected static Block.SoundType wood;
	public static Material rock;
	public static Material wool;

	public BlockMod(final Material mat) {
		super(mat);
	}

	public Block setTextureName(final String name) {
		return setBlockTextureName("nevermine:" + name);
	}

	public Block setName(final String name) {
		setTextureName(name);
		setBlockName(name);
		GameRegistry.registerBlock(this, name);
		return this;
	}

	static {
		BlockMod.stone = Block.soundTypeStone;
		BlockMod.grass = Block.soundTypeGrass;
		BlockMod.cloth = Block.soundTypeCloth;
		BlockMod.wood = Block.soundTypeWood;
		BlockMod.rock = Material.rock;
		BlockMod.wool = Material.cloth;
	}
}
