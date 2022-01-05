package net.tslat.aoa3.object.item.weapon.cannon;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.projectile.cannon.SmileBlasterEntity;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;

import javax.annotation.Nullable;

public class SmileBlaster extends BaseCannon {
	public SmileBlaster(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_SHADOW_BLASTER_FIRE.get();
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, Hand hand) {
		return new SmileBlasterEntity(shooter, this, hand, 120, 0);
	}
}
