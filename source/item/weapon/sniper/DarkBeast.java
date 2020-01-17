package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class DarkBeast extends BaseSniper implements AdventWeapon {
	public DarkBeast(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
		setTranslationKey("DarkBeast");
		setRegistryName("aoa3:dark_beast");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunMonster;
	}

	@Override
	public Enums.ScopeScreens getScreen() {
		return Enums.ScopeScreens.MONSTER;
	}
}
