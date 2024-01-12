package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.effectslib.api.util.EffectBuilder;
import net.tslat.effectslib.networking.packet.TELParticlePacket;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BlastChiller extends BaseBlaster {
	public BlastChiller(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, 20, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_MAGIC_GUN_FIRE.get();
	}

	@Override
	public float getChamberLength(EnergyProjectileWeapon weapon, LivingEntity shooter) {
		return 1f;
	}

	@Override
	public float getBlasterHeightOffset(EnergyProjectileWeapon weapon, LivingEntity shooter) {
		return -0.3f;
	}

	@Override
	protected void doImpactEffect(ServerLevel level, LivingEntity shooter, ItemStack stack, ShotInfo shotInfo, HitResult rayTrace, boolean affectedTarget) {
		if (affectedTarget && rayTrace instanceof EntityHitResult entityHit && entityHit.getEntity() instanceof LivingEntity target) {
			EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 40).level(5));
			target.setTicksFrozen(Math.min(300, target.getTicksFrozen() + 15));
		}
	}

	// TODO Remove
	@Override
	protected ShotInfo fireBlaster(ServerLevel level, LivingEntity shooter, ItemStack blaster, boolean temp) {
		return super.fireBlaster(level, shooter, blaster, false);
	}

	@Override
	protected void doFiringEffects(ServerLevel level, LivingEntity shooter, ItemStack stack, ShotInfo shotInfo) {
		final RandomSource rand = RandomUtil.RANDOM;
		TELParticlePacket packet = new TELParticlePacket();
		Vec3 originPos = shotInfo.shotOrBarrelPosForVfx();
		Vec3 hitPos = shotInfo.getHitPos().orElse(originPos);

		packet.particle(ParticleBuilder.forPositionsInLine(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, originPos, hitPos, 6)
				.lifespan(10)
				.ignoreDistanceAndLimits()
				.scaleMod(0.25f)
				.colourOverride(0, 168, 162, 120));
		packet.particle(ParticleBuilder.forPositionsInLine(new CustomisableParticleType.Data(AoAParticleTypes.FREEZING_SNOWFLAKE.get(), ColourUtil.WHITE), originPos, hitPos, 6)
				.lifespan(rand.nextInt(20, 50))
				.ignoreDistanceAndLimits()
				.velocity(rand.nextGaussian() * 0.05f, rand.nextGaussian() * 0.05f, rand.nextGaussian() * 0.05f)
				.gravityOverride(0.001f)
				.scaleMod(0.4f));
		packet.particle(ParticleBuilder.forPositionsInLine(ParticleTypes.WARPED_SPORE, originPos, hitPos, 6)
				.lifespan(rand.nextInt(12, 25))
				.ignoreDistanceAndLimits()
				.colourOverride(0, 168, 162, 255)
				.spawnNTimes(2));

		packet.sendToAllPlayersTrackingEntity(level, shooter);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.SLOWS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.FREEZES_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
