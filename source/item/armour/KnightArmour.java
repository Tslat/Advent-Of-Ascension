package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.misc.AoAAttributes;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_KNIGHT;

public class KnightArmour extends AdventArmour {
	public KnightArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_KNIGHT, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.KNIGHT;
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots) {
		if (slots == null && EntityUtil.checkBelowHealthPercentThreshold(plData.player(), 20))
			plData.player().addPotionEffect(new PotionEffect(MobEffects.STRENGTH, -1, 1, false, true));
	}

	@Override
	public void onEquip(PlayerDataManager plData, @Nullable EntityEquipmentSlot slot) {
		if (slot != null) {
			switch (slot) {
				case FEET:
					EntityUtil.applyAttributeModifierSafely(plData.player(), SharedMonsterAttributes.MAX_HEALTH, AoAAttributes.KNIGHT_ARMOUR_BOOTS);
					break;
				case LEGS:
					EntityUtil.applyAttributeModifierSafely(plData.player(), SharedMonsterAttributes.MAX_HEALTH, AoAAttributes.KNIGHT_ARMOUR_LEGS);
					break;
				case CHEST:
					EntityUtil.applyAttributeModifierSafely(plData.player(), SharedMonsterAttributes.MAX_HEALTH, AoAAttributes.KNIGHT_ARMOUR_BODY);
					break;
				case HEAD:
					EntityUtil.applyAttributeModifierSafely(plData.player(), SharedMonsterAttributes.MAX_HEALTH, AoAAttributes.KNIGHT_ARMOUR_HELMET);
					break;
			}
		}
	}

	@Override
	public void onUnequip(PlayerDataManager plData, @Nullable EntityEquipmentSlot slot) {
		if (slot != null) {
			switch (slot) {
				case FEET:
					EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.MAX_HEALTH, AoAAttributes.KNIGHT_ARMOUR_BOOTS);
					break;
				case LEGS:
					EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.MAX_HEALTH, AoAAttributes.KNIGHT_ARMOUR_LEGS);
					break;
				case CHEST:
					EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.MAX_HEALTH, AoAAttributes.KNIGHT_ARMOUR_BODY);
					break;
				case HEAD:
					EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.MAX_HEALTH, AoAAttributes.KNIGHT_ARMOUR_HELMET);
					break;
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.KnightArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.KnightArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
	}
}
