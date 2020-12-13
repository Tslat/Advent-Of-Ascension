package net.tslat.aoa3.block.generation.misc;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class HugeMushroomBlock extends net.minecraft.block.HugeMushroomBlock {
	public HugeMushroomBlock(MaterialColor mapColour) {
		super(generateBlockProperties(mapColour));
	}

	private static Block.Properties generateBlockProperties(MaterialColor mapColor) {
		return Block.Properties.create(Material.WOOD, mapColor)
				.hardnessAndResistance(0.2f)
				.sound(SoundType.WOOD);
	}
}
