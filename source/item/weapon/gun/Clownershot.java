package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.item.weapon.AdventWeapon;

public class Clownershot extends BaseGun implements AdventWeapon {
	public Clownershot(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		setUnlocalizedName("Clownershot");
		setRegistryName("aoa3:clownershot");
	}
}
