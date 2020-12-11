package net.tslat.aoa3.block.decoration.fence;

import net.minecraft.block.FenceBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class TwinklestoneFence extends FenceBlock {
	public TwinklestoneFence() {
		super(generateBlockProperties());
	}

	private static Properties generateBlockProperties() {
		return Properties.create(Material.GLASS, MaterialColor.MAGENTA)
				.hardnessAndResistance(0.3f)
				.sound(SoundType.GLASS)
				.lightValue(15);
	}
}
