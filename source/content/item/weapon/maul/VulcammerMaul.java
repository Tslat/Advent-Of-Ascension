package net.tslat.aoa3.content.item.weapon.maul;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.explosion.StandardExplosion;
import net.tslat.tme.api.util.EntityRetrievalUtil;

import java.util.List;

public class VulcammerMaul extends AoAMaul {
	public VulcammerMaul(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, Entity target, LivingEntity attacker, float attackCooldown) {
		if (attackCooldown <= 0.85f || !(attacker.level() instanceof ServerLevel level))
			return;

		AoAExplosionBuilder.at(level, (attacker.getX() + target.getX()) / 2d, (attacker.getY(0.5f) + target.getY(0.5f)) / 2d, (attacker.getZ() + target.getZ()) / 2d,
							   AoAExplosions.VULCAMMER_MAUL, StandardExplosion::new).explode();

		for (LivingEntity entity : EntityRetrievalUtil.getEntities(target, 2, LivingEntity.class, entity2 -> EntityUtil.areProbablyEnemies(entity2, attacker))) {
			entity.igniteForSeconds(3);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, tooltipFlag);
	}
}
