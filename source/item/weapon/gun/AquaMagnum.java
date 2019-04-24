package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.item.weapon.AdventWeapon;

public class AquaMagnum extends BaseGun implements AdventWeapon {
	public AquaMagnum(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		setUnlocalizedName("AquaMagnum");
		setRegistryName("aoa3:aqua_magnum");
	}
}
