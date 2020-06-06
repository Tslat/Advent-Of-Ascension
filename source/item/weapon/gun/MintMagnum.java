package net.tslat.aoa3.item.weapon.gun;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class MintMagnum extends BaseGun {
	public MintMagnum(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("MintMagnum");
		setRegistryName("aoa3:mint_magnum");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.FAST_RIFLE_FIRE;
	}
}
