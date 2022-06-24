package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.sounds.SoundEvent;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;
import net.tslat.aoa3.common.registration.AoASounds;

import javax.annotation.Nullable;

public class HuntersRifle extends BaseGun {
	public HuntersRifle(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(AoACreativeModeTabs.GUNS, dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_RIFLE_MEDIUM_FIRE_LONG.get();
	}

	@Override
	public boolean isFullAutomatic() {
		return false;
	}
}
