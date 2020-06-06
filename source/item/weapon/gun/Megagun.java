package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class Megagun extends BaseGun {
	public Megagun(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("Megagun");
		setRegistryName("aoa3:megagun");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.MINIGUN_FIRE;
	}
}
