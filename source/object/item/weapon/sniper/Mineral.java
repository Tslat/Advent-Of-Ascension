package net.tslat.aoa3.object.item.weapon.sniper;

import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.AoASounds;

import javax.annotation.Nullable;

public class Mineral extends BaseSniper {
	public Mineral(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_SNIPER_METALLIC_FIRE_LONG.get();
	}

	@Override
	protected float getFiringSoundPitchAdjust() {
		return 0.1f;
	}
}
