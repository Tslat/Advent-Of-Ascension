package net.tslat.aoa3.item.weapon.archergun;

import net.minecraft.util.SoundEvent;

public class TrollsArchergun extends BaseArchergun {
	public TrollsArchergun(double dmg, SoundEvent sound, int durability, int fireDelayTicks, float recoil) {
		super(dmg, sound, durability, fireDelayTicks, recoil);
		setUnlocalizedName("TrollsArchergun");
		setRegistryName("aoa3:trolls_archergun");
	}
}
