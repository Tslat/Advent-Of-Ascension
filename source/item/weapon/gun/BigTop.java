package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class BigTop extends BaseGun {
	public BigTop(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("BigTop");
		setRegistryName("aoa3:big_top");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.REVOLVER_FIRE;
	}
}
