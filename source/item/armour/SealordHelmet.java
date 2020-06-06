package net.tslat.aoa3.item.armour;

import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
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
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_SEALORD_HELMET;

public class SealordHelmet extends AdventArmour {
	public SealordHelmet(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_SEALORD_HELMET, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.ALL;
	}

	@Override
	public void onEffectTick(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots) {
		EntityPlayer player = plData.player();

		if (player.isInsideOfMaterial(Material.WATER)) {
			EntityUtil.applyAttributeModifierSafely(plData.player(), SharedMonsterAttributes.ATTACK_SPEED, AoAAttributes.SEALORD_ATTACK_BUFF);
		}
		else {
			EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.ATTACK_SPEED, AoAAttributes.SEALORD_ATTACK_BUFF);
		}
	}

	@Override
	public void onUnequip(PlayerDataManager plData, @Nullable EntityEquipmentSlot slot) {
		EntityUtil.removeAttributeModifier(plData.player(), SharedMonsterAttributes.ATTACK_SPEED, AoAAttributes.SEALORD_ATTACK_BUFF);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SealordHelmet.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(anySetEffectHeader());
	}
}
