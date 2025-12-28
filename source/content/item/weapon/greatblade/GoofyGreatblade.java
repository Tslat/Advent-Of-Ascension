package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;

public class GoofyGreatblade extends AoAGreatblade {
	public GoofyGreatblade(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public float getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack greatblade, DamageSource source, float baseDamage) {
		return super.getDamageForAttack(target, attacker, greatblade, source, baseDamage + (float)RandomUtil.valueBetween(-5, 5));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.RANDOM_DAMAGE, LocaleUtil.ItemDescriptionType.BENEFICIAL, Component.literal(String.valueOf(getBaseDamage(stack) - 4)), Component.literal(String.valueOf(getBaseDamage(stack) + 6))));
	}
}
