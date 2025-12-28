package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponRayTrace;
import net.tslat.aoa3.content.item.ProjectileFiringWeapon;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.object.RayTrace;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import net.tslat.tme.internal.particle.transition.ToPositionParticleTransition;
import net.tslat.tme.internal.particle.transition.ToScaleParticleTransition;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LightSpark extends AoABlaster<WeaponRayTrace> {
	public LightSpark(Item.Properties properties) {
		super(properties);
	}

	@Override
	public float getEyeHeightOffset(ProjectileFiringWeapon weapon, Entity shooter) {
		return -0.3f;
	}

	@Override
	public float getForwardOffset(ProjectileFiringWeapon weapon, Entity shooter) {
		return 1f;
	}

	@Override
	public float getRightOffset(ProjectileFiringWeapon weapon, Entity shooter) {
		return 0.6f;
	}

	@Override
	public float getSpiritCost(ItemStack stack, @Nullable Entity shooter, boolean forShotConsumption) {
		if (forShotConsumption)
			return 0;

		return super.getSpiritCost(stack, shooter, forShotConsumption);
	}

	@Override
	void fireBlaster(ServerLevel level, WeaponFiringContext context) {
		fireRayTrace(level, context);
	}

	@Override
	protected boolean tryFireBlaster(ServerLevel level, WeaponFiringContext context) {
		final Entity shooter = context.getShooter();
		final float spiritCost = getSpiritCost(context.weaponStack(), shooter, false);

		if (spiritCost == 0 || !(shooter instanceof ServerPlayer pl) || PlayerUtil.hasResourceAmount(pl, AoAResources.SPIRIT.get(), spiritCost)) {
			fireBlaster(level, context);

			return true;
		}

		PlayerUtil.notifyPlayerOfInsufficientResources(pl, AoAResources.SPIRIT.get(), spiritCost);

		return false;
	}

	@Override
	public void onUseTick(Level level, LivingEntity shooter, ItemStack stack, int count) {
		if (getUseDuration(stack, shooter) - count < getChargeTime(stack) - 2)
			return;

		if (level instanceof ServerLevel serverLevel) {
			ServerPlayer player = shooter instanceof ServerPlayer pl ? pl : null;

			if (player == null || player.getCooldowns().getCooldownPercent(this, 0) == 0) {
				if (tryFireBlaster(serverLevel, createFiringContext(stack, shooter, shooter.getUsedItemHand()).build())) {
					if (player != null) {
						int cooldown = getTicksBetweenShots(stack);

						if (cooldown > 1)
							player.getCooldowns().addCooldown(this, cooldown);
					}
				}
				else {
					shooter.releaseUsingItem();
				}
			}
		}
	}

	@Override
	protected boolean doEntityImpact(Level level, WeaponRayTrace effect, WeaponFiringContext context, RayTrace<?> rayTrace, Entity hitEntity) {
		if (!EntityUtil.isImmuneToSpecialAttacks(hitEntity)) {
			Entity shooter = context.getShooter();
			float spiritCost = getSpiritCost(context.weaponStack(), shooter, false);

			if (!(shooter instanceof Player pl) || pl.hasInfiniteMaterials() || PlayerUtil.hasResourceAmount(pl, AoAResources.SPIRIT.get(), spiritCost)) {
				if (level instanceof ServerLevel serverLevel && (!(shooter instanceof ServerPlayer serverPlayer) || PlayerUtil.consumeResource(serverPlayer, AoAResources.SPIRIT.get(), spiritCost, false))) {
					Vec3 center = hitEntity.position().add(0, hitEntity.getBbHeight() * 0.5f, 0);
					float colourMod = RandomUtil.valueUpTo(1f);

					ParticleBuilder.forRandomPosInSphere(ParticleTypes.ELECTRIC_SPARK, center, Math.max(hitEntity.getBbHeight(), hitEntity.getBbWidth()) * 1.1f)
							.spawnNTimes(1000)
							.scaleMod(1.5f)
							.lifespan(15)
							.colourTint(0.3137255f + colourMod * 0.6862745f, 0.9019608f + colourMod * 0.09803922f, colourMod, 1.0f)
							.ignoreDistanceAndLimits()
							.addTransition(ToScaleParticleTransition.create(0.1f, 10))
							.sendToAllPlayersTrackingEntity(shooter);
					ParticleBuilder.forPositionsInSphere(ParticleTypes.END_ROD, center, Math.max(hitEntity.getBbHeight(), hitEntity.getBbWidth()) * 1.25f, 32)
							.spawnNTimes(4096)
							.lifespan(20)
							.ignoreDistanceAndLimits()
							.addTransition(ToPositionParticleTransition.create(center, 10))
							.sendToAllPlayersTrackingEntity(shooter);

					SoundBuilder.following(AoASounds.ITEM_SOUL_SPARK_FIRE, hitEntity).category(shooter.getSoundSource()).play();

					hitEntity.discard();

					if (shooter instanceof ServerPlayer pl)
						ItemUtil.damageItemForUser(serverLevel, context.weaponStack(), 1, pl, context.weaponHand());
				}

				return true;
			}
		}

		return false;
	}

	@Override
	protected void doFiringEffects(ServerLevel level, WeaponRayTrace effect, Vec3 pos, WeaponFiringContext context) {
		super.doFiringEffects(level, effect, pos, context);

		Vec3 originPos = effect.visualStartPos();
		Vec3 hitPos = effect.endPos();
		TMEParticlePacket packet = new TMEParticlePacket();

		packet.particle(ParticleBuilder.forPositionsInLine(ParticleTypes.ELECTRIC_SPARK, originPos, hitPos, 6)
								.colourTint(80, 229, 0, 255)
								.lifespan(1)
								.scaleMod((float)RandomUtil.valueBetween(0.25f, 1f)));

		for (int i = 0; i < 3; i++) {
			float colourMod = RandomUtil.valueUpTo(1f);

			packet.particle(ParticleBuilder.forPositionsInLine(ParticleTypes.ELECTRIC_SPARK, originPos.add(RandomUtil.scaledGaussianValue(0.1f), RandomUtil.scaledGaussianValue(0.1f), RandomUtil.scaledGaussianValue(0.1f)), hitPos, 6)
									.colourTint(0.3137255f + colourMod * 0.6862745f, 0.9019608f + colourMod * 0.09803922f, colourMod, 1.0f)
									.lifespan(1)
									.scaleMod((float)RandomUtil.valueBetween(0.25f, 1f)));
		}

		packet.sendToAllPlayersTrackingEntity(context.getShooter());
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.SPEC_IMMUNE, LocaleUtil.ItemDescriptionType.HARMFUL));

		super.appendHoverText(stack, context, tooltip, flag);
	}
}
