package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.item.weapon.AdventWeapon;

public class CrystalCarver extends BaseGun implements AdventWeapon {
	public CrystalCarver(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		setUnlocalizedName("CrystalCarver");
		setRegistryName("aoa3:crystal_carver");
	}
}
