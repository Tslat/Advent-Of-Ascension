package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.content.entity.projectile.misc.TidalWaveEntity;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class TidalGreatblade extends AoAGreatblade {
	public TidalGreatblade(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (attackCooldown < 0.95f)
			return;

		double xOffset = Mth.cos(attacker.getYRot() / 180.0F * (float)Math.PI) * 0.7F;
		double zOffset = Mth.sin(attacker.getYRot() / 180.0F * (float)Math.PI) * 0.7F;

		attacker.level().addFreshEntity(new TidalWaveEntity(attacker.level(), attacker, xOffset, zOffset));
		attacker.level().addFreshEntity(new TidalWaveEntity(attacker.level(), attacker, 0, 0));
		attacker.level().addFreshEntity(new TidalWaveEntity(attacker.level(), attacker, -xOffset, -zOffset));
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
