package net.tslat.aoa3.content.item.weapon.sniper;

import net.minecraft.sounds.SoundEvent;
import net.tslat.aoa3.common.registration.AoASounds;

import javax.annotation.Nullable;

public class Duster extends BaseSniper {
	public Duster(float dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_SNIPER_METALLIC_FIRE_LONG.get();
	}
}
