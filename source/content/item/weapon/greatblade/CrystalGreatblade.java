package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;

public class CrystalGreatblade extends AoAGreatblade {
	public CrystalGreatblade(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		for (Entity enemy : EntityRetrievalUtil.<Entity>getEntities(target, 2.5f, target2 -> target2 instanceof Enemy)) {
			DamageUtil.safelyDealDamage(DamageUtil.positionedEntityDamage(attacker instanceof Player ? DamageTypes.PLAYER_ATTACK : DamageTypes.MOB_ATTACK, attacker, enemy.position()), enemy, RandomUtil.valueUpTo(1.5f) * attackCooldown);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
