package net.tslat.aoa3.item.weapon.shotgun;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class Gimmick extends BaseShotgun {
	public Gimmick(final double dmg, final int pellets, final int durability, final int fireDelayTicks, final float knockbackFactor, final float recoil) {
		super(dmg, pellets, durability, fireDelayTicks, knockbackFactor, recoil);
		setTranslationKey("Gimmick");
		setRegistryName("aoa3:gimmick");
	}
}
