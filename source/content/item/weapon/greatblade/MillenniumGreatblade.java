package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;

public class MillenniumGreatblade extends AoAGreatblade {
	private final double maxDmg;

	public MillenniumGreatblade(Tier tier, Item.Properties properties) {
		super(tier, properties);

		this.maxDmg = getTier().getAttackDamageBonus() * 3;
	}

	@Override
	public float getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack greatblade, DamageSource source, float baseDamage) {
		return super.getDamageForAttack(target, attacker, greatblade, source, baseDamage + (float)RandomUtil.valueUpTo(this.maxDmg - Math.min(baseDamage, this.maxDmg)) * getSwingEffectiveness(greatblade));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.RANDOM_DAMAGE, LocaleUtil.ItemDescriptionType.ITEM_DAMAGE, Component.literal(NumberUtil.roundTo2Decimals(getBaseDamage(stack) + 1)), Component.literal(NumberUtil.roundTo2Decimals((float)this.maxDmg))));
	}
}
