package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.explosion.StandardExplosion;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.util.EntityRetrievalUtil;

import java.util.List;
import java.util.Optional;

public class ConcussionStaff extends AoAStaff<List<LivingEntity>> {
	public ConcussionStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public Optional<List<LivingEntity>> checkPreconditions(LivingEntity caster, ItemStack staff) {
		List<LivingEntity> targets = EntityRetrievalUtil.getEntities(caster, 8, LivingEntity.class, entity -> EntityUtil.areProbablyEnemies(entity, caster));

		return Optional.ofNullable(targets.isEmpty() ? null : targets);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, List<LivingEntity> args) {
		EffectBuilder effect = new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 25).level(10);

		for (LivingEntity nearby : args) {
			EntityUtil.pushEntityAway(caster, nearby, 3f);
			AoAExplosionBuilder.at(nearby, AoAExplosions.CONCUSSION_STAFF, StandardExplosion::new).explodingEntity(caster).explode();
			EntityUtil.applyPotions(nearby, caster, effect);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
