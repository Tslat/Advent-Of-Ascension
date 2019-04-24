package net.tslat.aoa3.item.tool.pickaxe;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class OrnamytePickaxe extends ItemPickaxe implements SkillItem, SpecialHarvestTool {
	public OrnamytePickaxe(ToolMaterial material) {
		super(material);
		setUnlocalizedName("OrnamytePickaxe");
		setRegistryName("aoa3:ornamyte_pickaxe");
		setCreativeTab(CreativeTabsRegister.toolsTab);
	}

	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		if (e.getDrops().get(0).getItem() == Item.getItemFromBlock(Blocks.OBSIDIAN))
			e.getDrops().remove(0);
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
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		return state.getBlock() == Blocks.OBSIDIAN ? 100.0f : super.getDestroySpeed(stack, state);
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.ingotRosite), false) || super.getIsRepairable(stack, repairMaterial);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.OrnamytePickaxe.desc.1", TextFormatting.DARK_GREEN));

		if (AdventGuiTabPlayer.getSkillLevel(getSkill()) >= 65) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.GREEN, "65", StringUtil.getLocaleString("skills.extraction.name")));
		}
		else {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.RED, "65", StringUtil.getLocaleString("skills.extraction.name")));
		}
	}
}
