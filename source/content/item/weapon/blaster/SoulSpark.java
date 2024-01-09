package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.AoASoundBuilderPacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;
import net.tslat.aoa3.library.builder.SoundBuilder;
import net.tslat.aoa3.util.*;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.effectslib.api.particle.transitionworker.PositionParticleTransition;
import net.tslat.effectslib.api.particle.transitionworker.ScaleParticleTransition;
import net.tslat.effectslib.networking.packet.TELParticlePacket;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoulSpark extends BaseBlaster {
	public SoulSpark(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, 20, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_BLASTER_ENERGY_PULSE_FIRE.get();
	}

	@Override
	public float getBlasterHeightOffset(EnergyProjectileWeapon weapon, LivingEntity shooter) {
		return -0.3f;
	}

	@Override
	public float getBeamDistance(ItemStack stack, @Nullable LivingEntity shooter) {
		return 2;
	}

	@Override
	public float getChamberLength(EnergyProjectileWeapon weapon, LivingEntity shooter) {
		return 1f;
	}

	@Override
	public float getDistToBlasterArm(EnergyProjectileWeapon weapon, LivingEntity shooter) {
		return 0.6f;
	}

	@Override
	public float getEnergyCost(ItemStack stack, @Nullable LivingEntity shooter, boolean forDisplay) {
		if (forDisplay)
			return 200;

		return super.getEnergyCost(stack, shooter, forDisplay);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (hand != getWeaponHand(player))
			return InteractionResults.ItemUse.denyUsage(stack);

		if (player.getAttackStrengthScale(0.0f) < 1)
			return InteractionResults.ItemUse.denyUsage(stack);

		final float energyCost = getEnergyCost(stack, player, true);

		if (player.getAbilities().instabuild || PlayerUtil.getResourceValue(player, AoAResources.SPIRIT.get()) >= energyCost) {
			player.startUsingItem(hand);

			return InteractionResults.ItemUse.noActionTaken(stack);
		}
		else if (!player.getAbilities().instabuild) {
			return InteractionResults.ItemUse.denyUsage(stack);
		}

		return InteractionResults.ItemUse.noActionTaken(stack);
	}

	// TODO Remove
	@Override
	protected ShotInfo fireBlaster(ServerLevel level, LivingEntity shooter, ItemStack blaster, boolean temp) {
		return super.fireBlaster(level, shooter, blaster, false);
	}

	@Override
	public boolean doEntityImpact(ServerLevel level, LivingEntity shooter, ItemStack stack, ShotInfo shotInfo, EntityHitResult rayTrace) {
		if (!EntityUtil.isImmuneToSpecialAttacks(rayTrace.getEntity(), shooter)) {
			if (shooter instanceof ServerPlayer player && !player.getAbilities().instabuild) {
				if (PlayerUtil.consumeResource(player, AoAResources.SPIRIT.get(), 200, false)) {
					InteractionHand hand = player.getUsedItemHand();

					if (stack.getItem() != this)
						stack = player.getItemInHand(hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);

					if (stack.getItem() != this)
						return false;

					ItemUtil.damageItem(stack, shooter, hand);
				}
			}

			if (shooter.level() instanceof ServerLevel serverLevel) {
				Entity entity = rayTrace.getEntity();
				RandomSource rand = shooter.getRandom();
				Vec3 center = entity.position().add(0, entity.getBbHeight() * 0.5f, 0);

				ParticleBuilder.forRandomPosInSphere(ParticleTypes.ELECTRIC_SPARK, center, Math.max(entity.getBbHeight(), entity.getBbWidth()) * 1.1f)
						.spawnNTimes(1000)
						.scaleMod(1.5f)
						.lifespan(15)
						.colourOverride(0, 100 + rand.nextInt(130), 230, 255)
						.ignoreDistanceAndLimits()
						.addTransition(ScaleParticleTransition.create(0.1f, 10))
						.spawnParticles(serverLevel);
				ParticleBuilder.forPositionsInSphere(ParticleTypes.END_ROD, center, Math.max(entity.getBbHeight(), entity.getBbWidth()) * 1.25f, 32)
						.colourOverride(0, 230, 230, 255)
						.spawnNTimes(4096)
						.lifespan(20)
						.ignoreDistanceAndLimits()
						.addTransition(PositionParticleTransition.create(center, 10))
						.spawnParticles(serverLevel);

				AoANetworking.sendToAllPlayersTrackingEntity(new AoASoundBuilderPacket(new SoundBuilder(AoASounds.ITEM_SOUL_SPARK_FIRE).atEntity(rayTrace.getEntity())), shooter);
				rayTrace.getEntity().discard();
			}

			return true;
		}

		return false;
	}

	@Override
	public void onUseTick(Level level, LivingEntity shooter, ItemStack stack, int count) {
		if (getUseDuration(stack) - count < getChargeTime(stack) - 2)
			return;

		if (level instanceof ServerLevel serverLevel) {
			ServerPlayer player = shooter instanceof ServerPlayer ? (ServerPlayer)shooter : null;

			if (player == null || player.getCooldowns().getCooldownPercent(this, 0) == 0) {
				if (tryFireBlaster(serverLevel, shooter, stack, player)) {
					if (player != null) {
						player.awardStat(Stats.ITEM_USED.get(this));

						if (getFiringDelay() > 1)
							player.getCooldowns().addCooldown(this, getFiringDelay());
					}
				}
				else {
					shooter.releaseUsingItem();
				}
			}
		}
	}

	@Override
	protected void doFiringEffects(ServerLevel level, LivingEntity shooter, ItemStack stack, ShotInfo shotInfo) {
		Vec3 originPos = shotInfo.shotOrBarrelPosForVfx();
		Vec3 hitPos = shotInfo.getHitPos().orElse(originPos);
		TELParticlePacket packet = new TELParticlePacket();
		RandomSource rand = shooter.getRandom();

		packet.particle(ParticleBuilder.forPositionsInLine(ParticleTypes.ELECTRIC_SPARK, originPos, hitPos, 6)
				.colourOverride(0, 100 + rand.nextInt(130), 230, 255)
				.lifespan(1)
				.scaleMod(0.25f + rand.nextFloat() * 0.75f));

		for (int i = 0; i < 3; i++) {
			packet.particle(ParticleBuilder.forPositionsInLine(ParticleTypes.ELECTRIC_SPARK, originPos.add(rand.nextGaussian() * 0.1f, rand.nextGaussian() * 0.1f, rand.nextGaussian() * 0.1f), hitPos, 6)
					.colourOverride(0, 100 + rand.nextInt(130), 230, 255)
					.lifespan(1)
					.scaleMod(0.25f + rand.nextFloat() * 0.75f));
		}

		packet.send(level);
	}

	@Override
	public void releaseUsing(ItemStack stack, Level world, LivingEntity player, int useTicksRemaining) {}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.SPEC_IMMUNE, LocaleUtil.ItemDescriptionType.HARMFUL));

		super.appendHoverText(stack, world, tooltip, flag);
	}
}
