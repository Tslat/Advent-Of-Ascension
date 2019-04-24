package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;

public class Mineral extends BaseSniper implements AdventWeapon {
	public Mineral(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		setUnlocalizedName("Mineral");
		setRegistryName("aoa3:mineral");
	}

	@Override
	public Enums.ScopeScreens getScreen() {
		return Enums.ScopeScreens.BOULDER;
	}
}
