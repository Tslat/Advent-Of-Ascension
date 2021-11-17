package net.tslat.aoa3.item.weapon.cannon;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.entity.projectile.thrown.GrenadeEntity;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

import javax.annotation.Nullable;

public class BoomCannon extends BaseCannon {
	public BoomCannon(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_BOOM_CANNON_FIRE.get();
	}

	@Override
	public Item getAmmoItem() {
		return AoAWeapons.GRENADE.get();
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, Hand hand) {
		return new GrenadeEntity(shooter, this, hand, 120, 0);
	}
}
