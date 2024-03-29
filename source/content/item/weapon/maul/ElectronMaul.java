package net.tslat.aoa3.content.item.weapon.maul;

import com.google.common.collect.Multimap;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoADataAttachments;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.library.constant.AttackSpeed;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.*;
import org.jetbrains.annotations.Nullable;

import java.util.ConcurrentModificationException;
import java.util.List;

public class ElectronMaul extends BaseMaul {
	public ElectronMaul() {
		super(25.0f, AttackSpeed.THIRD, 2.5d, 1500);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int itemSlot, boolean isSelected) {
		if (world.getGameTime() % 10 == 0 && entity instanceof LivingEntity livingEntity) {
			float damageScaling = stack.getData(AoADataAttachments.DAMAGE_SCALING);

			try {
				if (isSelected) {
					float currentCalcBuff = getKnockbackMultiplier(entity);

					if (damageScaling != currentCalcBuff) {
						livingEntity.getAttributes().removeAttributeModifiers(getAttributeModifiers(EquipmentSlot.MAINHAND, stack));
						stack.setData(AoADataAttachments.DAMAGE_SCALING, currentCalcBuff);
						livingEntity.getAttributes().addTransientAttributeModifiers(getAttributeModifiers(EquipmentSlot.MAINHAND, stack));
					}
				}
				else if (damageScaling != 0 && livingEntity.getMainHandItem().isEmpty()) {
					livingEntity.getAttributes().removeAttributeModifiers(getAttributeModifiers(EquipmentSlot.MAINHAND, stack));
					stack.setData(AoADataAttachments.DAMAGE_SCALING, 0f);
				}
			}
			catch (ConcurrentModificationException ex) {
				// Don't really have a way of pre-empting this issue, and I hate this solution but idk what else I can do
			}
		}
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
		float attackStr = player.getAttackStrengthScale(0.0f);

		stack.setData(AoADataAttachments.MELEE_SWING_STRENGTH, attackStr);
		EntityUtil.reapplyAttributeModifier(player, Attributes.ATTACK_KNOCKBACK, getKnockbackModifier(attackStr * getKnockbackMultiplier(player)), false);

		return false;
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		doMeleeEffect(stack, target, attacker, stack.getData(AoADataAttachments.MELEE_SWING_STRENGTH));
		ItemUtil.damageItem(stack, attacker, InteractionHand.MAIN_HAND);
		EntityUtil.reapplyAttributeModifier(attacker, Attributes.ATTACK_KNOCKBACK, getKnockbackModifier(1 * getKnockbackMultiplier(attacker)), false);

		return true;
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, Entity target, LivingEntity attacker, float attackCooldown) {
		if (stack.getData(AoADataAttachments.DAMAGE_SCALING) > 0.75f)
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
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifierMap = super.getAttributeModifiers(slot, stack);

		if (slot == EquipmentSlot.MAINHAND) {
			float damageScaling = stack.getData(AoADataAttachments.DAMAGE_SCALING);

			ItemUtil.setAttribute(modifierMap, Attributes.ATTACK_KNOCKBACK, KNOCKBACK_MODIFIER_UUID, getBaseKnockback() * (damageScaling == 0 ? 1 : damageScaling));
		}

		return modifierMap;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
	}
}
