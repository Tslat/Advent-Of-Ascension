package net.tslat.aoa3.item.tool.axe;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Items;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.ToolRegister;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class OccultAxe extends ItemAxe implements SkillItem, SpecialHarvestTool {
	public OccultAxe(ToolMaterial material) {
		super(material, material.getAttackDamage(), -3.0f);
		setUnlocalizedName("OccultAxe");
		setRegistryName("aoa3:occult_axe");
		setCreativeTab(CreativeTabsRegister.toolsTab);
	}

	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		if (ToolRegister.axeOccult.getDestroySpeed(e.getHarvester().getHeldItem(EnumHand.MAIN_HAND), e.getState()) > 1 && AdventOfAscension.rand.nextInt(4) == 0)
			e.getDrops().add(new ItemStack(Items.COAL, 1));
	}

	@Override
	public Enums.Skills getSkill() {
		return Enums.Skills.EXTRACTION;
	}

	@Override
	public int getLevelReq() {
		return 75;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.ingotRosite), false) || super.getIsRepairable(stack, repairMaterial);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.OccultAxe.desc.1", TextFormatting.DARK_GREEN));

		if (AdventGuiTabPlayer.getSkillLevel(getSkill()) >= 75) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.GREEN, "75", StringUtil.getLocaleString("skills.extraction.name")));
		}
		else {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.RED, "75", StringUtil.getLocaleString("skills.extraction.name")));
		}
	}
}
