package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.weapon.AdventWeapon;

import javax.annotation.Nullable;

public class Skullette extends BaseGun implements AdventWeapon {
	public Skullette(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("Skullette");
		setRegistryName("aoa3:skullette");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunRoulette;
	}
}
