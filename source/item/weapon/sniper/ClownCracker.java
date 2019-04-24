package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;

public class ClownCracker extends BaseSniper implements AdventWeapon {
	public ClownCracker(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		setUnlocalizedName("ClownCracker");
		setRegistryName("aoa3:clown_cracker");
	}

	@Override
	public Enums.ScopeScreens getScreen() {
		return Enums.ScopeScreens.DOTTED;
	}
}
