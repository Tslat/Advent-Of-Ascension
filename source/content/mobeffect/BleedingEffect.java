package net.tslat.aoa3.content.mobeffect;

import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.tslat.aoa3.common.registration.AoAEntityEffects;
import net.tslat.aoa3.util.ColourUtil;

import javax.annotation.Nullable;
import java.util.List;

public class BleedingEffect extends MobEffect {
	public static final DamageSource DAMAGE_SOURCE = new DamageSource("bleeding").bypassArmor().bypassEnchantments().setScalesWithDifficulty();

	public BleedingEffect() {
		super(MobEffectCategory.HARMFUL, ColourUtil.RGB(128,5,0));
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		return List.of(new ItemStack(Items.ENCHANTED_GOLDEN_APPLE));
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplifier) {
		int interval = 100 >> amplifier;

		return interval <= 0 || duration % interval == 0;
	}

	@Override
	public void applyEffectTick(LivingEntity entity, int amplifier) {
		if (entity.getMobType() != MobType.UNDEAD) {
			entity.hurt(DAMAGE_SOURCE, 0.75f);

			if (entity instanceof Player player)
				player.causeFoodExhaustion(5);
		}
	}

	public static void apply(LivingEntity target, MobEffectInstance bleedEffect, @Nullable Entity applyingEntity) {
		MobEffectInstance existingBleed = target.getEffect(AoAEntityEffects.BLEEDING.get());

		if (existingBleed != null && existingBleed.getAmplifier() >= bleedEffect.getAmplifier())
			bleedEffect = new MobEffectInstance(bleedEffect.getEffect(), Math.max(bleedEffect.getDuration(), existingBleed.getDuration()), Mth.clamp(existingBleed.getAmplifier() + 1, 0, 127), bleedEffect.isAmbient(), bleedEffect.isVisible(), bleedEffect.showIcon(), bleedEffect.hiddenEffect, bleedEffect.getFactorData());

		target.addEffect(bleedEffect, applyingEntity);
	}
}
