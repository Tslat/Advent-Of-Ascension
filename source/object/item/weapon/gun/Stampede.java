package net.tslat.aoa3.object.item.weapon.gun;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoASounds;

import javax.annotation.Nullable;

public class Stampede extends BaseGun {
	public Stampede(final double dmg, final int durability, final int firingDelayTicks, final float recoil) {
		super(AoAItemGroups.GUNS, dmg, durability, firingDelayTicks, recoil);
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
