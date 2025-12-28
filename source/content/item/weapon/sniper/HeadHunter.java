package net.tslat.aoa3.content.item.weapon.sniper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.projectile.base.WeaponProjectile;
import net.tslat.tme.api.object.RayTrace;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;

public class HeadHunter extends AoASniper {
	public HeadHunter(Item.Properties properties) {
		super(properties);
	}

	@Override
	protected void onDamageEntity(Level level, WeaponProjectile projectile, RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		super.onDamageEntity(level, projectile, rayTrace, hitEntity, damage);

		if (EntityUtil.getPartOrPartOwner(hitEntity) instanceof LivingEntity target && level instanceof ServerLevel serverLevel) {
			Vec3 pos = rayTrace.hitPos();
			double headMinRange = (target.getBoundingBox().minY + target.getEyeHeight()) - target.getBbHeight() * 0.105f;
			double headMaxRange = headMinRange + target.getBbHeight() * 0.225f;

			if (pos.y > headMinRange && pos.y < headMaxRange) {
				TMEParticlePacket packet = new TMEParticlePacket();

				for (int i = 0; i < 5; i++) {
					packet.particle(ParticleBuilder.forPositions(ParticleTypes.DAMAGE_INDICATOR, pos.add(RandomUtil.valueBetween(-0.5d, 0.5d), RandomUtil.valueBetween(-0.5d, 0.5d), RandomUtil.valueBetween(-0.5d, 0.5d)))
											.ignoreDistanceAndLimits());
				}

				packet.sendToAllPlayersTrackingBlock(serverLevel, BlockPos.containing(pos));

				if (projectile.getShooter() instanceof ServerPlayer pl) {
					SoundBuilder.localAmbience(SoundEvents.EXPERIENCE_ORB_PICKUP, level).onlyFor(pl).play();

					if (pl.isHolding(stack -> stack == projectile.getShotContext().weaponStack()))
						pl.getCooldowns().addCooldown(this, getTicksBetweenShots(projectile.getShotContext().weaponStack()) / 2);
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
