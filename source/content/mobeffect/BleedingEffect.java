package net.tslat.aoa3.content.mobeffect;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.tme.api.mobeffect.ExtendedMobEffect;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import org.jetbrains.annotations.Nullable;

public class BleedingEffect extends ExtendedMobEffect {
	public BleedingEffect() {
		super(MobEffectCategory.HARMFUL, ColourUtil.RGB(128,5,0));
	}

	@Override
	public boolean shouldCureEffect(MobEffectInstance effectInstance, ItemStack stack, LivingEntity entity) {
		return stack.getItem() == Items.ENCHANTED_GOLDEN_APPLE;
	}

	@Override
	public boolean shouldTickEffect(@Nullable MobEffectInstance effectInstance, @Nullable LivingEntity entity, int ticksRemaining, int amplifier) {
		int interval = 100 >> amplifier;

		return interval == 0 || ticksRemaining % interval == 0;
	}

	@Override
	public boolean tick(LivingEntity entity, @Nullable MobEffectInstance effectInstance, int amplifier) {
		if (!entity.level().isClientSide && !entity.getType().is(EntityTypeTags.UNDEAD)) {
			entity.hurt(DamageUtil.miscDamage(AoADamageTypes.BLEED, entity.level()), 0.75f);

			TMEParticlePacket packet = new TMEParticlePacket(20);

			for (int i = 0; i < 20; i++) {
				packet.particle(ParticleBuilder.forRandomPosInEntity(ParticleTypes.SPLASH, entity)
						.colourTint(0.75f, 0, 0, 1f)
						.spawnNTimes(5)
						.velocity(entity.getRandom().nextGaussian() * 0.01f, entity.getRandom().nextGaussian() * 0.01f, entity.getRandom().nextGaussian() * 0.01f));
			}

			packet.sendToAllPlayersTrackingEntity(entity);

			if (entity instanceof Player player)
				player.causeFoodExhaustion(5);
		}

		return true;
	}

	@Override
	public void onApplication(@Nullable MobEffectInstance effectInstance, @Nullable Entity source, LivingEntity entity, int amplifier) {
		if (effectInstance != null)
			effectInstance.visible = false;
	}

	@Override
	public MobEffectInstance onReapplication(MobEffectInstance existingEffectInstance, MobEffectInstance newEffectInstance, LivingEntity entity) {
		if (existingEffectInstance.getAmplifier() >= newEffectInstance.getAmplifier())
			return new MobEffectInstance(newEffectInstance.getEffect(), Math.max(newEffectInstance.getDuration(), existingEffectInstance.getDuration()), Mth.clamp(existingEffectInstance.getAmplifier() + 1, 0, 127), newEffectInstance.isAmbient(), newEffectInstance.isVisible(), newEffectInstance.showIcon(), newEffectInstance.hiddenEffect);

		return newEffectInstance;
	}
}
