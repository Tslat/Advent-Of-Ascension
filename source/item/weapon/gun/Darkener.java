package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.item.weapon.AdventWeapon;

public class Darkener extends BaseGun implements AdventWeapon {
	public Darkener(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		setUnlocalizedName("Darkener");
		setRegistryName("aoa3:darkener");
	}
}
