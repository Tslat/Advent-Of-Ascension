package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.weapon.AdventWeapon;

import javax.annotation.Nullable;

public class Stampede extends BaseGun implements AdventWeapon {
	public Stampede(final double dmg, final int durability, final int firingDelayTicks, final float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("Stampede");
		setRegistryName("aoa3:stampede");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunStampede;
	}
}
