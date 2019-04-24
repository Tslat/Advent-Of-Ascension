package net.tslat.aoa3.block.decoration.fences;

import net.minecraft.block.material.Material;

public class TwinklestoneFence extends FenceBlock {
	public TwinklestoneFence() {
		super("TwinklestoneFence", "twinklestone_fence", Material.GLASS);
		setLightOpacity(0);
		setLightLevel(0.6f);
	}
}
