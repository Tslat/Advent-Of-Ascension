package net.tslat.aoa3.item.weapon.archergun;

public class TrollsArchergun extends BaseArchergun {
	public TrollsArchergun(double dmg, int durability, int fireDelayTicks, float recoil) {
		super(dmg, durability, fireDelayTicks, recoil);
		setTranslationKey("TrollsArchergun");
		setRegistryName("aoa3:trolls_archergun");
	}
}
