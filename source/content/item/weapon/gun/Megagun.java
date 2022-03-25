package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;

import javax.annotation.Nullable;

public class Megagun extends BaseGun {
	public Megagun(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(AoAItemGroups.GUNS, dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_MINIGUN_AUTOMATIC_FIRE.get();
	}

	@Override
	protected float getFiringSoundPitchAdjust() {
		return 0.85f;
	}

	@Override
	public void releaseUsing(ItemStack stack, World level, LivingEntity shooter, int timeCharged) {
		shooter.level.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), AoASounds.ITEM_GUN_MINIGUN_WINDDOWN.get(), SoundCategory.PLAYERS, 1.0f, 1f);
	}

	@Override
	protected void doFiringSound(LivingEntity shooter, BaseBullet bullet, ItemStack stack, Hand hand) {
		shooter.level.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), getFiringSound(), SoundCategory.PLAYERS, 1.0f, getFiringSoundPitchAdjust());
	}
}
