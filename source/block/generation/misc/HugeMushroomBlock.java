package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

import net.minecraft.block.AbstractBlock;

public class HugeMushroomBlock extends net.minecraft.block.HugeMushroomBlock {
	public HugeMushroomBlock(MaterialColor mapColour) {
		super(generateBlockProperties(mapColour));
	}

	private static AbstractBlock.Properties generateBlockProperties(MaterialColor mapColor) {
		return AbstractBlock.Properties.of(Material.WOOD, mapColor)
				.strength(0.2f)
				.sound(SoundType.WOOD);
	}
}
