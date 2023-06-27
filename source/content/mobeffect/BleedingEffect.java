package net.tslat.aoa3.content.mobeffect;

import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.effectslib.api.ExtendedMobEffect;
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
	public boolean shouldTickEffect(@org.jetbrains.annotations.Nullable MobEffectInstance effectInstance, @org.jetbrains.annotations.Nullable LivingEntity entity, int ticksRemaining, int amplifier) {
		int interval = 100 >> amplifier;

		return interval == 0 || ticksRemaining % interval == 0;
	}

	@Override
	public void tick(LivingEntity entity, @org.jetbrains.annotations.Nullable MobEffectInstance effectInstance, int amplifier) {
		if (entity.getMobType() != MobType.UNDEAD) {
			entity.hurt(DamageUtil.miscDamage(AoADamageTypes.BLEED, entity.level()), 0.75f);

			if (entity instanceof Player player)
				player.causeFoodExhaustion(5);
		}
	}

	@Override
	public void onApplication(@Nullable MobEffectInstance effectInstance, @Nullable Entity source, LivingEntity entity, int amplifier) {
		if (effectInstance != null)
			effectInstance.visible = false;
	}

	@Override
	public MobEffectInstance onReapplication(MobEffectInstance existingEffectInstance, MobEffectInstance newEffectInstance, LivingEntity entity) {
		if (existingEffectInstance.getAmplifier() >= newEffectInstance.getAmplifier())
			return new MobEffectInstance(newEffectInstance.getEffect(), Math.max(newEffectInstance.getDuration(), existingEffectInstance.getDuration()), Mth.clamp(existingEffectInstance.getAmplifier() + 1, 0, 127), newEffectInstance.isAmbient(), newEffectInstance.isVisible(), newEffectInstance.showIcon(), newEffectInstance.hiddenEffect, newEffectInstance.getFactorData());

		return newEffectInstance;
	}
}
