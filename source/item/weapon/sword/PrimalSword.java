package net.tslat.aoa3.item.weapon.sword;

import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.volatilestack.VolatileStackCapabilityHandles;
import net.tslat.aoa3.capabilities.volatilestack.VolatileStackCapabilityProvider;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class PrimalSword extends BaseSword {
	public PrimalSword() {
		super(ItemUtil.customItemTier(1960, AttackSpeed.NORMAL, 13.0f, 4, 10, null));
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
		if (world.getGameTime() % 10 == 0 && entity instanceof LivingEntity) {
			VolatileStackCapabilityHandles cap = VolatileStackCapabilityProvider.getOrDefault(stack, null);

			if (isSelected) {
				float currentDamageMod = cap.getValue();
				float currentCalcBuff = getCurrentDamageBuff(entity);

				if (currentDamageMod != currentCalcBuff) {
					((LivingEntity)entity).getAttributes().removeAttributeModifiers(getAttributeModifiers(EquipmentSlotType.MAINHAND, stack));
					cap.setValue(currentCalcBuff);
					((LivingEntity)entity).getAttributes().addTransientAttributeModifiers(getAttributeModifiers(EquipmentSlotType.MAINHAND, stack));
				}
			}
			else if (cap.getValue() != 0) {
				((LivingEntity)entity).getAttributes().removeAttributeModifiers(getAttributeModifiers(EquipmentSlotType.MAINHAND, stack));
				cap.setValue(0);
			}
		}
	}

	private float getCurrentDamageBuff(Entity holder) {
		if (holder instanceof LivingEntity) {
			float armour = (float)((LivingEntity)holder).getAttribute(Attributes.ARMOR).getValue();

			if (armour > 15) {
				return 15 / armour;
			}
			else {
				return 1.5f - (armour / 30f);
			}
		}

		return 1;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlotType slot, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifierMap =  super.getAttributeModifiers(slot, stack);

		if (slot == EquipmentSlotType.MAINHAND) {
			VolatileStackCapabilityHandles cap = VolatileStackCapabilityProvider.getOrDefault(stack, null);

			ItemUtil.setAttribute(modifierMap, Attributes.ATTACK_DAMAGE, BASE_ATTACK_DAMAGE_UUID, getDamage() * (cap.getValue() == 0 ? 1 : cap.getValue()));
		}

		return modifierMap;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
