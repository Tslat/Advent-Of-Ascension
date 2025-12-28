package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.List;

public class NethengeicSword extends AoASword {
	public NethengeicSword(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public float getDamageForAttack(LivingEntity target, LivingEntity attacker, ItemStack swordStack, DamageSource source, float baseDamage) {
		if (baseDamage > 0 && !attacker.level().isClientSide && !target.fireImmune() && !target.isInvulnerableTo(target.level().damageSources().onFire()))
			target.igniteForSeconds((int)(4 * baseDamage / getBaseDamage(swordStack)));

		return super.getDamageForAttack(target, attacker, swordStack, source, baseDamage);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (!attacker.level().isClientSide && (target.fireImmune() || target.isInvulnerableTo(target.level().damageSources().onFire())))
			EntityUtil.applyPotions(target, attacker, new EffectBuilder(MobEffects.WITHER, (int)(80 * attackCooldown)).level(3));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.BURNS_TARGETS, LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
