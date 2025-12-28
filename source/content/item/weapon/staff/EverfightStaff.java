package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

import java.util.List;
import java.util.Optional;

public class EverfightStaff extends AoAStaff<Float> {
	public EverfightStaff(Item.Properties properties) {
		super(properties);
	}

	public Optional<Float> checkPreconditions(LivingEntity caster, ItemStack staff) {
		float healthPercent = EntityUtil.getHealthPercent(caster);

		return Optional.ofNullable(healthPercent < 1 && healthPercent > 0 ? healthPercent : null);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, Float args) {
		EntityUtil.applyPotions(caster, caster, new EffectBuilder(MobEffects.DAMAGE_RESISTANCE, (int)(1200f * (1 - args))));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
