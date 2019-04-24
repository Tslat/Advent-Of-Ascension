package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;

public class DualSight extends BaseSniper implements AdventWeapon {
	public DualSight(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		setUnlocalizedName("DualSight");
		setRegistryName("aoa3:dual_sight");
	}

	@Override
	public Enums.ScopeScreens getScreen() {
		return Enums.ScopeScreens.DOTTED;
	}
}
