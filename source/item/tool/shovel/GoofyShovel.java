package net.tslat.aoa3.item.tool.shovel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSpade;
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

public class GoofyShovel extends ItemSpade implements SkillItem, SpecialHarvestTool {
	public GoofyShovel(ToolMaterial material) {
		super(material);
		setUnlocalizedName("GoofyShovel");
		setRegistryName("aoa3:goofy_shovel");
		setCreativeTab(CreativeTabsRegister.toolsTab);
	}

	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		if (ToolRegister.shovelGoofy.getDestroySpeed(e.getHarvester().getHeldItem(EnumHand.MAIN_HAND), e.getState()) > 1) {
			if (AdventOfAscension.rand.nextBoolean()) {
				ItemStack loot = e.getDrops().get(0);

				if (loot.getItem() instanceof ItemBlock && ((ItemBlock)loot.getItem()).getBlock() == e.getState().getBlock()) {
					loot.setCount(loot.getCount() * 3);
					e.getDrops().set(0, loot);
				}
			}
			else {
				e.getDrops().clear();
			}
		}
	}

	@Override
	public Enums.Skills getSkill() {
		return Enums.Skills.EXTRACTION;
	}

	@Override
	public int getLevelReq() {
		return 85;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.ingotRosite), false) || super.getIsRepairable(stack, repairMaterial);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.GoofyShovel.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.GoofyShovel.desc.2", TextFormatting.DARK_GREEN));

		if (AdventGuiTabPlayer.getSkillLevel(getSkill()) >= 85) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.GREEN, "85", StringUtil.getLocaleString("skills.extraction.name")));
		}
		else {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.RED, "85", StringUtil.getLocaleString("skills.extraction.name")));
		}
	}
}
