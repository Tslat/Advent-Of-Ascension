package net.tslat.aoa3.item.weapon.shotgun;

import net.tslat.aoa3.item.weapon.AdventWeapon;

public class RedRocket extends BaseShotgun implements AdventWeapon {
	public RedRocket(final double dmg, final int pellets, final int durability, final int fireDelayTicks, final float knockbackFactor, final float recoil) {
		super(dmg, pellets, durability, fireDelayTicks, knockbackFactor, recoil);
		setTranslationKey("RedRocket");
		setRegistryName("aoa3:red_rocket");
	}
}
