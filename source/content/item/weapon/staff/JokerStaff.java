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
import net.tslat.tme.api.util.EntityRetrievalUtil;

import java.util.List;
import java.util.Optional;

public class JokerStaff extends AoAStaff<List<LivingEntity>> {
	public JokerStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public Optional<List<LivingEntity>> checkPreconditions(LivingEntity caster, ItemStack staff) {
		List<LivingEntity> targets = EntityRetrievalUtil.getEntities(caster, 10, LivingEntity.class, entity -> EntityUtil.areProbablyEnemies(entity, caster) && !EntityUtil.isImmuneToSpecialAttacks(entity));

		return Optional.ofNullable(targets.isEmpty() ? null : targets);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, List<LivingEntity> args) {
		EntityUtil.applyPotions(args, caster, new EffectBuilder(MobEffects.LEVITATION, 100).level(6), new EffectBuilder(MobEffects.JUMP, 140).level(21));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.SPEC_IMMUNE, LocaleUtil.ItemDescriptionType.NEUTRAL));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
