package net.tslat.aoa3.content.mobeffect;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.effectslib.api.ExtendedMobEffect;

public class NethengeicCurseEffect extends ExtendedMobEffect {
	public NethengeicCurseEffect() {
		super(MobEffectCategory.HARMFUL, ColourUtil.RGB(43,0,0));
	}

	@Override
	public boolean shouldCureEffect(MobEffectInstance effectInstance, ItemStack stack, LivingEntity entity) {
		return stack.getItem() == Items.ENCHANTED_GOLDEN_APPLE || super.shouldCureEffect(effectInstance, stack, entity);
	}

	@Override
	public boolean beforeIncomingAttack(LivingEntity entity, MobEffectInstance effectInstance, DamageSource source, float amount) {
		if (source.isFire() && entity.hasEffect(MobEffects.FIRE_RESISTANCE))
			entity.hurt(DamageSource.MAGIC, amount * (float)(Math.min(effectInstance.getAmplifier(), 8) * 0.5 + 0.5f));

		return true;
	}
}
