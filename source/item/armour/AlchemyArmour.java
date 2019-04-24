package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOURALCHEMY;

public class AlchemyArmour extends AdventArmour implements SkillItem {
	public AlchemyArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOURALCHEMY, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.ALCHEMY;
	}

	@Override
	public Enums.Skills getSkill() {
		return Enums.Skills.ALCHEMY;
	}

	@Override
	public int getLevelReq() {
		return 100;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		//tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		//tooltip.add(StringUtil.getColourLocaleString("item.AlchemyArmour.desc.1", TextFormatting.DARK_GREEN));
		//TODO ARMOUR DESCRIPTION ALCHEMY
		if (AdventGuiTabPlayer.getSkillLevel(getSkill()) >= 100) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.GREEN, Integer.toString(100), StringUtil.getLocaleString("skills.alchemy.name")));
		}
		else {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.RED, Integer.toString(100), StringUtil.getLocaleString("skills.alchemy.name")));
		}
	}
}
