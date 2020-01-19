package net.tslat.aoa3.item.weapon.cannon;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.weapon.AdventWeapon;

import javax.annotation.Nullable;

public class ShyreBlaster extends BaseCannon implements AdventWeapon {
	public ShyreBlaster(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("ShyreBlaster");
		setRegistryName("aoa3:shyre_blaster");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunBigBlast;
	}
}
