package net.tslat.aoa3.item.weapon.shotgun;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.item.weapon.AdventWeapon;

public class LongShot extends BaseShotgun implements AdventWeapon {
	public LongShot(final double dmg, final int pellets, final SoundEvent sound, final int durability, final int fireDelayTicks, final float recoil) {
		super(dmg, pellets, sound, durability, fireDelayTicks, recoil);
		setUnlocalizedName("LongShot");
		setRegistryName("aoa3:long_shot");
	}
}
