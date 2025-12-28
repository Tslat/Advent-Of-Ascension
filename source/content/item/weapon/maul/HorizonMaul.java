package net.tslat.aoa3.content.item.weapon.maul;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.List;

public class HorizonMaul extends AoAMaul {
	public HorizonMaul(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, Entity target, LivingEntity attacker, float attackCooldown) {
		if (target instanceof LivingEntity livingEntity && attackCooldown == 1f && !EntityUtil.isImmuneToSpecialAttacks(livingEntity))
			EntityUtil.applyPotions(livingEntity, attacker, new EffectBuilder(MobEffects.LEVITATION, 35).level(1).isAmbient().hideParticles());
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, tooltipFlag);
	}
}
