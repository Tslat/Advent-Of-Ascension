package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.library.Enums;

public class Crystaneer extends BaseSniper implements AdventWeapon {
	public Crystaneer(double dmg, SoundEvent sound, int durability, int firingDelayTicks, float recoil) {
		super(dmg, sound, durability, firingDelayTicks, recoil);
		setUnlocalizedName("Crystaneer");
		setRegistryName("aoa3:crystaneer");
	}

	@Override
	public Enums.ScopeScreens getScreen() {
		return Enums.ScopeScreens.CRYSTAL;
	}
}
