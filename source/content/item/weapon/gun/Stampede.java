package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.sounds.SoundEvent;
import net.tslat.aoa3.common.registration.AoASounds;

import javax.annotation.Nullable;

public class Stampede extends BaseGun {
	public Stampede(final float dmg, final int durability, final int firingDelayTicks, final float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_GENERIC_FIRE_5.get();
	}

	@Override
	protected float getFiringSoundPitchAdjust() {
		return 0.8f;
	}

	@Override
	public boolean isFullAutomatic() {
		return false;
	}
}
