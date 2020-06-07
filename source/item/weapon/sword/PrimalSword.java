package net.tslat.aoa3.item.weapon.sword;

import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.handlers.AdventMiscStackCapability;
import net.tslat.aoa3.capabilities.providers.AdventMiscStackProvider;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nullable;
import java.util.List;

public class PrimalSword extends BaseSword {
	public PrimalSword(final ToolMaterial material, final double speed) {
		super(material, speed);
		setTranslationKey("PrimalSword");
		setRegistryName("aoa3:primal_sword");
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (world.getWorldTime() % 10 == 0 && entity instanceof EntityLivingBase) {
			AdventMiscStackCapability cap = (AdventMiscStackCapability)stack.getCapability(AdventMiscStackProvider.MISC_STACK, null);

			if (cap != null) {
				if (isSelected) {
					float currentDamageMod = cap.getValue();
					float currentCalcBuff = getCurrentDamageBuff(entity);

					if (currentDamageMod != currentCalcBuff) {
						((EntityLivingBase)entity).getAttributeMap().removeAttributeModifiers(getAttributeModifiers(EntityEquipmentSlot.MAINHAND, stack));
						cap.setValue(currentCalcBuff);
						((EntityLivingBase)entity).getAttributeMap().applyAttributeModifiers(getAttributeModifiers(EntityEquipmentSlot.MAINHAND, stack));
					}
				}
				else if (cap.getValue() != 0) {
					((EntityLivingBase)entity).getAttributeMap().removeAttributeModifiers(getAttributeModifiers(EntityEquipmentSlot.MAINHAND, stack));
					cap.setValue(0);
				}
			}
		}
	}

	private float getCurrentDamageBuff(Entity holder) {
		if (holder instanceof EntityLivingBase) {
			float armour = (float)((EntityLivingBase)holder).getEntityAttribute(SharedMonsterAttributes.ARMOR).getAttributeValue();

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
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
		Multimap<String, AttributeModifier> modifierMap =  super.getItemAttributeModifiers(slot);

		if (slot == EntityEquipmentSlot.MAINHAND) {
			AdventMiscStackCapability cap = (AdventMiscStackCapability)stack.getCapability(AdventMiscStackProvider.MISC_STACK, null);

			if (cap != null)
				ItemUtil.setAttribute(modifierMap, SharedMonsterAttributes.ATTACK_DAMAGE, ATTACK_DAMAGE_MODIFIER, dmg * (cap.getValue() == 0 ? 1 : cap.getValue()));
		}

		return modifierMap;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.PrimalSword.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
