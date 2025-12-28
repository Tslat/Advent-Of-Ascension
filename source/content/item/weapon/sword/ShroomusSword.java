package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ShroomusSword extends AoASword {
	public ShroomusSword(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (attackCooldown > 0.75) {
			Collection<MobEffectInstance> effects = attacker.getActiveEffects();

			if (!effects.isEmpty()) {
				ArrayList<MobEffectInstance> removableEffects = new ArrayList<MobEffectInstance>(effects.size());

				for (MobEffectInstance effect : effects) {
					if (!effect.getEffect().value().isBeneficial())
						removableEffects.add(effect);
				}

				for (MobEffectInstance effect : removableEffects) {
					target.addEffect(effect);
					attacker.removeEffect(effect.getEffect());
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
