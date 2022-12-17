package net.tslat.aoa3.content.item.weapon.gun;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.content.entity.projectile.thrown.GrenadeEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Dustometer extends BaseGun {
	public Dustometer(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_GENERIC_FIRE_2.get();
	}

	@Override
	protected boolean fireGun(LivingEntity shooter, ItemStack stack, InteractionHand hand) {
		if (super.fireGun(shooter, stack, hand)) {
			if (!shooter.level.isClientSide() && RandomUtil.oneInNChance(3)) {
				shooter.level.addFreshEntity(new GrenadeEntity(shooter, this, hand, 120, 0));
				shooter.level.playSound(null, shooter.getX(), shooter.getY(), shooter.getZ(), AoASounds.ITEM_GUN_AIR_CANNON_FIRE.get(), SoundSource.PLAYERS, 1.0f, getFiringSoundPitchAdjust() + (float)RandomUtil.randomScaledGaussianValue(0.075f));
			}

			return true;
		}

		return false;
	}

	@Override
	public void doImpactDamage(Entity target, LivingEntity shooter, BaseBullet bullet, Vec3 impactPosition, float bulletDmgMultiplier) {
		if (!(bullet instanceof GrenadeEntity))
			super.doImpactDamage(target, shooter, bullet, impactPosition, bulletDmgMultiplier);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
