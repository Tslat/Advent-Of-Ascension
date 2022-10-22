package net.tslat.aoa3.content.mobeffect;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.effectslib.api.ExtendedMobEffect;
import org.jetbrains.annotations.Nullable;

public class BurnedEffect extends ExtendedMobEffect {
	public BurnedEffect() {
		super(MobEffectCategory.HARMFUL, ColourUtil.RGB(255,63,0));
	}

	@Override
	public boolean shouldCureEffect(MobEffectInstance effectInstance, ItemStack stack, LivingEntity entity) {
		return stack.getItem() == Items.ENCHANTED_GOLDEN_APPLE;
	}

	@Override
	public float modifyIncomingAttackDamage(LivingEntity entity, MobEffectInstance effectInstance, DamageSource source, float baseAmount) {
		return baseAmount * (1 + (effectInstance.getAmplifier() + 1) * 0.1f);
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
