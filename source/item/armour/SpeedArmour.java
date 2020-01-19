package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.misc.AoAAttributes;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_SPEED;

public class SpeedArmour extends AdventArmour {
	public SpeedArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_SPEED, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.SPEED;
	}

	@Override
	public void onEquip(PlayerDataManager plData, @Nullable EntityEquipmentSlot slot) {
		if (slot == null) {
			EntityUtil.applyAttributeModifierSafely(plData.player(), SharedMonsterAttributes.MOVEMENT_SPEED, AoAAttributes.SPEED_ARMOUR_SET);
		}
		else {
			switch (slot) {
				case FEET:
					EntityUtil.applyAttributeModifierSafely(plData.player(), SharedMonsterAttributes.MOVEMENT_SPEED, AoAAttributes.SPEED_ARMOUR_BOOTS);
					break;
				case LEGS:
					EntityUtil.applyAttributeModifierSafely(plData.player(), SharedMonsterAttributes.MOVEMENT_SPEED, AoAAttributes.SPEED_ARMOUR_LEGS);
					break;
				case CHEST:
					EntityUtil.applyAttributeModifierSafely(plData.player(), SharedMonsterAttributes.MOVEMENT_SPEED, AoAAttributes.SPEED_ARMOUR_BODY);
					break;
				case HEAD:
					EntityUtil.applyAttributeModifierSafely(plData.player(), SharedMonsterAttributes.MOVEMENT_SPEED, AoAAttributes.SPEED_ARMOUR_HELMET);
					break;
				default:
					break;
			}
		}
	}

	@Override
	public void onUnequip(PlayerDataManager plData, @Nullable EntityEquipmentSlot slot) {
		if (slot == null) {
			EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.MOVEMENT_SPEED, AoAAttributes.SPEED_ARMOUR_SET);
		}
		else {
			switch (slot) {
				case FEET:
					EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.MOVEMENT_SPEED, AoAAttributes.SPEED_ARMOUR_BOOTS);
					break;
				case LEGS:
					EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.MOVEMENT_SPEED, AoAAttributes.SPEED_ARMOUR_LEGS);
					break;
				case CHEST:
					EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.MOVEMENT_SPEED, AoAAttributes.SPEED_ARMOUR_BODY);
					break;
				case HEAD:
					EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.MOVEMENT_SPEED, AoAAttributes.SPEED_ARMOUR_HELMET);
					break;
				default:
					break;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SpeedArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SpeedArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
