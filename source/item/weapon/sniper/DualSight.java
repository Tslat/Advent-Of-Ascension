package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class DualSight extends BaseSniper {
	public DualSight(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("DualSight");
		setRegistryName("aoa3:dual_sight");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.SNIPER_FIRE;
	}

	@Override
	public Enums.ScopeScreens getScreen() {
		return Enums.ScopeScreens.DOTTED;
	}
}
