package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.particleoption.EntityTrackingParticleOptions;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.content.entity.projectile.base.WeaponRayTrace;
import net.tslat.aoa3.content.item.ProjectileFiringWeapon;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.RayTrace;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;

import java.util.List;

public class BlastChiller extends AoABlaster<WeaponRayTrace> {
	public BlastChiller(Item.Properties properties) {
		super(properties);
	}

	@Override
	public float getForwardOffset(ProjectileFiringWeapon weapon, Entity shooter) {
		return 1f;
	}

	@Override
	public float getEyeHeightOffset(ProjectileFiringWeapon weapon, Entity shooter) {
		return -0.3f;
	}

	@Override
	void fireBlaster(ServerLevel level, WeaponFiringContext context) {
		fireRayTrace(level, context);
	}

	@Override
	protected void onDamageEntity(Level level, WeaponRayTrace effect, WeaponFiringContext context, RayTrace<?> rayTrace, Entity hitEntity, float damage) {
		EntityUtil.applyPotions(hitEntity, context.getShooter(), new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 40).level(5));
		hitEntity.setTicksFrozen(Math.min(300, hitEntity.getTicksFrozen() + 15));
	}

	@Override
	protected void doFiringEffects(ServerLevel level, WeaponRayTrace effect, Vec3 pos, WeaponFiringContext context) {
		super.doFiringEffects(level, effect, pos, context);

		TMEParticlePacket packet = new TMEParticlePacket();
		Vec3 originPos = effect.visualStartPos();
		Vec3 hitPos = effect.endPos();

		packet.particle(ParticleBuilder.forPositionsInLine(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, originPos, hitPos, 6)
								.lifespan(10)
								.ignoreDistanceAndLimits()
								.scaleMod(0.25f)
								.colourTint(0, 168, 162, 120));
		packet.particle(ParticleBuilder.forPositionsInLine(EntityTrackingParticleOptions.ambient(AoAParticleTypes.FREEZING_SNOWFLAKE), originPos, hitPos, 6)
								.lifespan(RandomUtil.numberBetween(20, 50))
								.ignoreDistanceAndLimits()
								.velocity(RandomUtil.scaledGaussianValue(0.05f), RandomUtil.scaledGaussianValue(0.05f), RandomUtil.scaledGaussianValue(0.05f))
								.gravityOverride(0.001f)
								.scaleMod(0.4f));
		packet.particle(ParticleBuilder.forPositionsInLine(ParticleTypes.WARPED_SPORE, originPos, hitPos, 6)
								.lifespan(RandomUtil.numberBetween(12, 25))
								.ignoreDistanceAndLimits()
								.colourTint(0, 168, 162, 255)
								.spawnNTimes(2));

		packet.sendToAllPlayersTrackingEntity(context.getShooter());
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.SLOWS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.FREEZES_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
