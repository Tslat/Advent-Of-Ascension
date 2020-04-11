package net.tslat.aoa3.item.weapon.shotgun;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class PurplePunisher extends BaseShotgun {
	public PurplePunisher(final double dmg, final int pellets, final int durability, final int fireDelayTicks, final float knockbackFactor, final float recoil) {
		super(dmg, pellets, durability, fireDelayTicks, knockbackFactor, recoil);
		setTranslationKey("PurplePunisher");
		setRegistryName("aoa3:purple_punisher");
	}
}
