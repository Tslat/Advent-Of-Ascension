package net.tslat.aoa3.content.item.weapon.maul;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.library.object.extension.MutableFloat;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

import java.util.List;

public class ElectronMaul extends AoAMaul {
	public ElectronMaul(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
		if (world.getGameTime() % 10 == 0 && entity instanceof LivingEntity livingEntity) {
			float damageScaling = stack.getOrDefault(AoADataComponents.DAMAGE_SCALING, 0f);

			if (isSelected) {
				float currentCalcBuff = getKnockbackMultiplier(entity);

				if (damageScaling != currentCalcBuff) {
					stack.set(AoADataComponents.DAMAGE_SCALING, currentCalcBuff);
					livingEntity.getAttribute(Attributes.ATTACK_KNOCKBACK).addOrUpdateTransientModifier(getKnockbackModifier(stack, damageScaling == 0 ? 1 : damageScaling));
				}
			}
			else if (damageScaling != 0 && livingEntity.getMainHandItem().isEmpty()) {
				livingEntity.getAttribute(Attributes.ATTACK_KNOCKBACK).addOrUpdateTransientModifier(getKnockbackModifier(stack, 1));
				stack.set(AoADataComponents.DAMAGE_SCALING, 0f);
			}
		}
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		float attackStr = player.getAttackStrengthScale(0);

		stack.get(AoADataComponents.MELEE_SWING_STRENGTH).setValue(attackStr);
		player.getAttribute(Attributes.ATTACK_KNOCKBACK).addOrUpdateTransientModifier(getKnockbackModifier(stack, attackStr * getKnockbackMultiplier(player)));

		return false;
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (attacker.level() instanceof ServerLevel level) {
			doMeleeEffect(stack, target, attacker, stack.getOrDefault(AoADataComponents.MELEE_SWING_STRENGTH, new MutableFloat(1f)).getValue());
			ItemUtil.damageItemForUser(level, stack, attacker, InteractionHand.MAIN_HAND);
			attacker.getAttribute(Attributes.ATTACK_KNOCKBACK).addOrUpdateTransientModifier(getKnockbackModifier(stack, 1 * getKnockbackMultiplier(attacker)));
		}

		return true;
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, Entity target, LivingEntity attacker, float attackCooldown) {
		if (stack.getOrDefault(AoADataComponents.DAMAGE_SCALING, 0f) > 0.75f)
			WorldUtil.spawnLightning((ServerLevel)attacker.level(), (ServerPlayer)attacker, target.getX(), target.getY(), target.getZ(), false, false);

		if (attacker instanceof ServerPlayer player) {
			AoAResource.Instance spirit = PlayerUtil.getResource(player, AoAResources.SPIRIT.get());

			spirit.consume(spirit.getCurrentValue(), true);
		}
	}

	private float getKnockbackMultiplier(Entity holder) {
		if (holder instanceof Player pl) {
			AoAResource.Instance spirit = PlayerUtil.getResource(pl, AoAResources.SPIRIT.get());

			return 1 + spirit.getCurrentValue() / spirit.getMaxValue();
		}

		return 1f;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, tooltipFlag);
	}
}
