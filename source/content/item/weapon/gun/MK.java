package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.sounds.SoundEvent;
import net.tslat.aoa3.common.registration.AoASounds;
import org.jetbrains.annotations.Nullable;


public class MK extends BaseGun {
	public MK(float dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_GENERIC_FIRE_5.get();
	}
}
