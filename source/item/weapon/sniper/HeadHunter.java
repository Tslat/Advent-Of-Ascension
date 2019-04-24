package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;

public class HeadHunter extends BaseSniper implements AdventWeapon {
	public HeadHunter(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		setUnlocalizedName("HeadHunter");
		setRegistryName("aoa3:head_hunter");
	}

	@Override
	public Enums.ScopeScreens getScreen() {
		return Enums.ScopeScreens.DOTTED;
	}
}
