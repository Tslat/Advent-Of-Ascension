package net.tslat.aoa3.item.weapon.archergun;

public class SkeletalArchergun extends BaseArchergun {
	public SkeletalArchergun(double dmg, int durability, int fireDelayTicks, float recoil) {
		super(dmg, durability, fireDelayTicks, recoil);
		setTranslationKey("SkeletalArchergun");
		setRegistryName("aoa3:skeletal_archergun");
	}
}
