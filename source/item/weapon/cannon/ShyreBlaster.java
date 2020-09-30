package net.tslat.aoa3.item.weapon.cannon;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class ShyreBlaster extends BaseCannon {
	public ShyreBlaster(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("ShyreBlaster");
		setRegistryName("aoa3:shyre_blaster");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.BIG_BLAST_FIRE;
	}
}
