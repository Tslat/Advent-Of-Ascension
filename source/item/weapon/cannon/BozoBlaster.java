package net.tslat.aoa3.item.weapon.cannon;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.cannon.BozoBallEntity;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.item.weapon.gun.BaseGun;

import javax.annotation.Nullable;

public class BozoBlaster extends BaseCannon {
	public BozoBlaster(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_CLOWNER_FIRE.get();
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, Hand hand) {
		return new BozoBallEntity(shooter, this, hand, 120, 0);
	}
}
