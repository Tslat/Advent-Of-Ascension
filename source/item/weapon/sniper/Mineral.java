package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class Mineral extends BaseSniper {
	public Mineral(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("Mineral");
		setRegistryName("aoa3:mineral");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.SNIPER_FIRE;
	}

	@Override
	public Enums.ScopeScreens getScreen() {
		return Enums.ScopeScreens.BOULDER;
	}
}
