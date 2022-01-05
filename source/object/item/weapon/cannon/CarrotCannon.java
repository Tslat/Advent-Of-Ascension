package net.tslat.aoa3.object.item.weapon.cannon;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.projectile.cannon.CarrotBallEntity;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;

import javax.annotation.Nullable;

public class CarrotCannon extends BaseCannon {
	public CarrotCannon(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_CARROT_CANNON_FIRE.get();
	}

	@Override
	public Item getAmmoItem() {
		return Items.CARROT;
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, Hand hand) {
		return new CarrotBallEntity(shooter, this, hand, 120, 0);
	}
}
