package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoADataComponents;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class PrimalSword extends AoASword {
	public PrimalSword(Tier tier, Item.Properties properties) {
		super(tier, properties);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean isSelected) {
		if (world.getGameTime() % 10 == 0 && entity instanceof LivingEntity livingEntity) {
			float damageScaling = stack.getOrDefault(AoADataComponents.DAMAGE_SCALING, 0f);

			if (isSelected) {
				float currentCalcBuff = getCurrentDamageBuff(entity);

				if (damageScaling != currentCalcBuff) {
					stack.set(AoADataComponents.DAMAGE_SCALING, currentCalcBuff);
					stack.update(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY, modifiers -> modifiers.withModifierAdded(Attributes.ATTACK_DAMAGE, getAttackDamageModifier(stack), EquipmentSlotGroup.MAINHAND));
				}
			}
			else if (damageScaling != 0 && livingEntity.getMainHandItem().isEmpty()) {
				stack.update(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.EMPTY, modifiers -> modifiers.withModifierAdded(Attributes.ATTACK_DAMAGE, getAttackDamageModifier(stack), EquipmentSlotGroup.MAINHAND));
				stack.set(AoADataComponents.DAMAGE_SCALING, 0f);
			}
		}
	}

	private float getCurrentDamageBuff(Entity holder) {
		if (!(holder instanceof LivingEntity entity) || !entity.getAttributes().hasAttribute(Attributes.ARMOR))
			return 1;

		float armour = (float)entity.getAttributeValue(Attributes.ARMOR);

		if (armour > 15)
			return 15 / armour;

		return 1.5f - (armour / 30f);
	}

	protected AttributeModifier getAttackDamageModifier(ItemStack stack) {
		float damageScaling = stack.getOrDefault(AoADataComponents.DAMAGE_SCALING, 0f);

		return new AttributeModifier(BASE_ATTACK_DAMAGE_ID, getTier().getAttackDamageBonus() * (damageScaling == 0 ? 1 : damageScaling), AttributeModifier.Operation.ADD_VALUE);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
