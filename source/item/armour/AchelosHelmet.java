package net.tslat.aoa3.item.armour;

import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURACHELOS;

public class AchelosHelmet extends AdventArmour {
	public AchelosHelmet(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURACHELOS, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.ALL;
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		if (cap.getPlayer().isInsideOfMaterial(Material.WATER))
			EntityUtil.healEntity(cap.getPlayer(), 0.03f);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.AchelosHelmet.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.AchelosHelmet.desc.2", TextFormatting.DARK_GREEN));
	}
}
