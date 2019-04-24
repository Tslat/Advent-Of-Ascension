package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURROCKBONE;

public class RockboneArmour extends AdventArmour {
	public RockboneArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURROCKBONE, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.ROCKBONE;
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		EntityPlayer pl = cap.getPlayer();
		if (!pl.capabilities.isCreativeMode) {
			if (pl.isInWater()) {
				pl.motionY -= 0.0025;
			}
			else {
				pl.motionY -= 0.011;
			}
		}
	}

	@Override
	public void setUnequipEffect(AdventPlayerCapability cap) {
		cap.getPlayer().getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0d);
	}

	@Override
	public void setEquipEffect(AdventPlayerCapability cap) {
		cap.getPlayer().getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0d);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.RockboneArmour.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.RockboneArmour.desc.2", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.RockboneArmour.desc.3", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.RockboneArmour.desc.4", TextFormatting.DARK_GREEN));
	}
}
