package net.tslat.aoa3.item.weapon.archergun;

import net.minecraft.util.SoundEvent;

public class SkeletalArchergun extends BaseArchergun {
	public SkeletalArchergun(double dmg, SoundEvent sound, int durability, int fireDelayTicks, float recoil) {
		super(dmg, sound, durability, fireDelayTicks, recoil);
		setUnlocalizedName("SkeletalArchergun");
		setRegistryName("aoa3:skeletal_archergun");
	}
}
