package net.tslat.aoa3.content.item.weapon.sniper;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;

public class HeadHunter extends BaseSniper {
	public HeadHunter(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_SNIPER_MEDIUM_FIRE_LONG.get();
	}

	@Override
	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target instanceof LivingEntity && target.level instanceof ServerLevel) {
			Vec3 preciseImpactSpot = EntityUtil.preciseEntityInterceptCalculation(target, bullet, 20);

			if (preciseImpactSpot != null) {
				double headMinRange = (target.getBoundingBox().minY + target.getEyeHeight()) - target.getBbHeight() * 0.105f;
				double headMaxRange = headMinRange + target.getBbHeight() * 0.225f;

				if (preciseImpactSpot.y > headMinRange && preciseImpactSpot.y < headMaxRange) {
					for (int i = 0; i < 5; i++) {
						((ServerLevel)target.level).sendParticles(ParticleTypes.DAMAGE_INDICATOR, preciseImpactSpot.x + RandomUtil.randomValueBetween(-0.5d, 0.5d), preciseImpactSpot.y + RandomUtil.randomValueBetween(-0.5d, 0.5d), preciseImpactSpot.z + RandomUtil.randomValueBetween(-0.5d, 0.5d), 3, 0, 0, 0, 0);
					}

					if (shooter.getItemInHand(InteractionHand.MAIN_HAND).getItem() != this && shooter.getItemInHand(InteractionHand.OFF_HAND).getItem() != this)
						return;

					if (shooter instanceof Player)
						((Player)shooter).getCooldowns().addCooldown(this, (int)(getFiringDelay() / 2f));
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
