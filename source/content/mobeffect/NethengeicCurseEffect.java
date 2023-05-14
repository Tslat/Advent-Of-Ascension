package net.tslat.aoa3.content.mobeffect;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.DamageUtil;
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
		if (source.is(DamageTypeTags.IS_FIRE) && entity.hasEffect(MobEffects.FIRE_RESISTANCE)) {
			Vec3 position = source.getSourcePosition();

			DamageUtil.safelyDealDamage(DamageUtil.miscPositionedDamage(DamageTypes.MAGIC, entity.level, position != null ? position : entity.position()), entity, amount * (Mth.clamp(effectInstance.getAmplifier(), 1, 8) * 0.5f + 1f));
		}

		return true;
	}
}
