package net.tslat.aoa3.content.mobeffect;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.effectslib.api.ExtendedMobEffect;

public class BleedingEffect extends ExtendedMobEffect {
	public static final DamageSource DAMAGE_SOURCE = new DamageSource("bleeding").bypassArmor().bypassEnchantments().setScalesWithDifficulty();

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
			entity.hurt(DAMAGE_SOURCE, 0.75f);

			if (entity instanceof Player player)
				player.causeFoodExhaustion(5);
		}
	}

	@Override
	public MobEffectInstance onReapplication(MobEffectInstance existingEffectInstance, MobEffectInstance newEffectInstance, LivingEntity entity) {
		if (existingEffectInstance.getAmplifier() >= newEffectInstance.getAmplifier())
			return new MobEffectInstance(newEffectInstance.getEffect(), Math.max(newEffectInstance.getDuration(), existingEffectInstance.getDuration()), Mth.clamp(existingEffectInstance.getAmplifier() + 1, 0, 127), newEffectInstance.isAmbient(), newEffectInstance.isVisible(), newEffectInstance.showIcon(), newEffectInstance.hiddenEffect, newEffectInstance.getFactorData());

		return newEffectInstance;
	}
}
