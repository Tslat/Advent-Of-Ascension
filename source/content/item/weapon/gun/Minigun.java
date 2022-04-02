package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;

import javax.annotation.Nullable;

public class Minigun extends BaseGun {
	public Minigun(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(AoAItemGroups.GUNS, dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_MINIGUN_AUTOMATIC_FIRE.get();
	}

	@Override
	protected float getFiringSoundPitchAdjust() {
		return 1f;
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity shooter, int timeCharged) {
		shooter.level.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), AoASounds.ITEM_GUN_MINIGUN_WINDDOWN.get(), SoundSource.PLAYERS, 1.0f, 1f);
	}

	@Override
	protected void doFiringSound(LivingEntity shooter, BaseBullet bullet, ItemStack stack, InteractionHand hand) {
		shooter.level.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), getFiringSound(), SoundSource.PLAYERS, 1.0f, getFiringSoundPitchAdjust());
	}
}
