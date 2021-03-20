package net.tslat.aoa3.block.decoration.fence;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

import net.minecraft.block.AbstractBlock.Properties;

public class TwinklestoneFence extends FenceBlock {
	public TwinklestoneFence() {
		super(generateBlockProperties());
	}

	private static Properties generateBlockProperties() {
		return AbstractBlock.Properties.of(Material.GLASS, MaterialColor.COLOR_MAGENTA)
				.strength(0.3f)
				.sound(SoundType.GLASS)
				.lightLevel(state -> 15);
	}
}
