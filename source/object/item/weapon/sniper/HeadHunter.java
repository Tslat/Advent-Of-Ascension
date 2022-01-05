package net.tslat.aoa3.object.item.weapon.sniper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class HeadHunter extends BaseSniper {
	public HeadHunter(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_SNIPER_FIRE.get();
	}

	@Override
	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (target instanceof LivingEntity && target.level instanceof ServerWorld) {
			Vector3d preciseImpactSpot = EntityUtil.preciseEntityInterceptCalculation(target, bullet, 20);

			if (preciseImpactSpot != null) {
				double headMinRange = (target.getBoundingBox().minY + target.getEyeHeight()) - target.getBbHeight() * 0.105f;
				double headMaxRange = headMinRange + target.getBbHeight() * 0.225f;

				if (preciseImpactSpot.y > headMinRange && preciseImpactSpot.y < headMaxRange) {
					for (int i = 0; i < 5; i++) {
						((ServerWorld)target.level).sendParticles(ParticleTypes.DAMAGE_INDICATOR, preciseImpactSpot.x + Item.random.nextDouble() - 0.5d, preciseImpactSpot.y + Item.random.nextDouble() - 0.5d, preciseImpactSpot.z + Item.random.nextDouble() - 0.5d, 3, 0, 0, 0, 0);
					}

					if (shooter.getItemInHand(Hand.MAIN_HAND).getItem() != this && shooter.getItemInHand(Hand.OFF_HAND).getItem() != this)
						return;

					if (shooter instanceof PlayerEntity) {
						if (shooter instanceof ServerPlayerEntity)
							PlayerUtil.playSoundForPlayer((ServerPlayerEntity)shooter, AoASounds.SKILL_LOOT.get(), SoundCategory.PLAYERS, shooter.getX(), shooter.getY(), shooter.getZ(), 0.3f, 1.0f);

						((PlayerEntity)shooter).getCooldowns().addCooldown(this, (int)(getFiringDelay() / 2f));
					}
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
