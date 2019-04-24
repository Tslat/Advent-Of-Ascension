package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;

public class BaronSSR extends BaseSniper implements AdventWeapon {
	public BaronSSR(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		setUnlocalizedName("BaronSSR");
		setRegistryName("aoa3:baron_ssr");
	}

	@Override
	public Enums.ScopeScreens getScreen() {
		return Enums.ScopeScreens.REDLIGHT;
	}
}
