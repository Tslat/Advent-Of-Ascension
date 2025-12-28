package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class PrimordialGreatblade extends AoAGreatblade {
	public PrimordialGreatblade(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public float getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack greatblade, DamageSource source, float baseDamage) {
		float extraDmg;
		float maxHealth = target.getMaxHealth();

		if (maxHealth <= 100) {
			extraDmg = maxHealth / 50f;
		}
		else if (maxHealth <= 300) {
			extraDmg = maxHealth / 100f;
		}
		else if (maxHealth <= 1000) {
			extraDmg = maxHealth / 250f;
		}
		else {
			extraDmg = Math.min(maxHealth / 300f, 5);
		}

		return super.getDamageForAttack(target, attacker, greatblade, source, (float)baseDamage + extraDmg);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
