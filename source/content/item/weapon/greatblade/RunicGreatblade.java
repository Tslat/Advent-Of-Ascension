package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class RunicGreatblade extends AoAGreatblade {
	public RunicGreatblade(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public float getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack greatblade, DamageSource source, float baseDamage) {
		return super.getDamageForAttack(target, attacker, greatblade, source, baseDamage * 0.15f);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (!target.level().isClientSide)
			DamageUtil.doMiscMagicAttack(attacker, target, getBaseDamage(stack) * attackCooldown * 0.85f, null);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
