package net.tslat.aoa3.item.tool.shovel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.ToolRegister;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class OrnamyteShovel extends ItemSpade implements SkillItem, SpecialHarvestTool {
	public OrnamyteShovel(ToolMaterial material) {
		super(material);
		setUnlocalizedName("OrnamyteShovel");
		setRegistryName("aoa3:ornamyte_shovel");
		setCreativeTab(CreativeTabsRegister.toolsTab);
	}

	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		ItemStack heldItem = e.getHarvester().getHeldItem(EnumHand.MAIN_HAND);

		if (!e.isSilkTouching() && itemRand.nextBoolean() && ToolRegister.shovelOrnamyte.getDestroySpeed(e.getHarvester().getHeldItem(EnumHand.MAIN_HAND), e.getState()) > 1) {
			ItemStack smeltedStack = FurnaceRecipes.instance().getSmeltingResult(e.getDrops().get(0)).copy();

			if (smeltedStack != ItemStack.EMPTY)
				e.getDrops().set(0, smeltedStack);
		}
	}

	@Override
	public Enums.Skills getSkill() {
		return Enums.Skills.EXTRACTION;
	}

	@Override
	public int getLevelReq() {
		return 65;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.ingotRosite), false) || super.getIsRepairable(stack, repairMaterial);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.OrnamyteShovel.desc.1", TextFormatting.DARK_GREEN));

		if (AdventGuiTabPlayer.getSkillLevel(getSkill()) >= 65) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.GREEN, "65", StringUtil.getLocaleString("skills.extraction.name")));
		}
		else {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.RED, "65", StringUtil.getLocaleString("skills.extraction.name")));
		}
	}
}
