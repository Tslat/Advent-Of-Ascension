package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.library.builder.AoAExplosionBuilder;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.explosion.StandardExplosion;

import java.util.List;

public class CreepoidGreatblade extends AoAGreatblade {
	public CreepoidGreatblade(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (attackCooldown > 0.85f && target.level() instanceof ServerLevel level) {
			double offset = target.getBbWidth() / 1.99d;
			double offsetX = Mth.clamp(attacker.getX() - target.getX(), -offset, offset);
			double offsetY = Mth.clamp(attacker.getY() + attacker.getEyeHeight() - target.getY(), -0.1, target.getBbHeight() + 0.1);
			double offsetZ = Mth.clamp(attacker.getZ() - target.getZ(), -offset, offset);

			AoAExplosionBuilder.at(level, target.position().add(offsetX, offsetY, offsetZ), AoAExplosions.CREEPOID_GREATBLADE, StandardExplosion::new).explodingEntity(attacker).explode();
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Keys.EXPLODES_ON_HIT, LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
