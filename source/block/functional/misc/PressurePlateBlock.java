package net.tslat.aoa3.block.functional.misc;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.tslat.aoa3.util.BlockUtil;

public class PressurePlateBlock extends net.minecraft.block.PressurePlateBlock {
	public PressurePlateBlock(Sensitivity sensitivity, MaterialColor mapColour) {
		super(sensitivity, BlockUtil.generateBlockProperties(Material.WOOD, mapColour, 0.5f, 0.5f, SoundType.WOOD).doesNotBlockMovement());
	}
}
