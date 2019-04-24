package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUREXPEDITION;

public class ExpeditionArmour extends AdventArmour implements SkillItem {
	public ExpeditionArmour(String name, String registryName, int renderIndex, EntityEquipmentSlot slot) {
		super(ARMOUREXPEDITION, name, registryName, renderIndex, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.EXPEDITION;
	}

	@Override
	public Enums.Skills getSkill() {
		return Enums.Skills.EXPEDITION;
	}

	@Override
	public int getLevelReq() {
		return 100;
	}

	@Override
	public void setTickEffect(AdventPlayerCapability cap) {
		cap.getPlayer().addPotionEffect(new PotionEffect(MobEffects.SPEED, -1, 8));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("items.description.fullSetBonus", TextFormatting.GOLD));
		tooltip.add(StringUtil.getColourLocaleString("item.ExpeditionArmour.desc.1", TextFormatting.DARK_GREEN));

		if (AdventGuiTabPlayer.getSkillLevel(getSkill()) >= 100) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.GREEN, Integer.toString(100), StringUtil.getLocaleString("skills.expedition.name")));
		}
		else {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.RED, Integer.toString(100), StringUtil.getLocaleString("skills.expedition.name")));
		}
	}
}
