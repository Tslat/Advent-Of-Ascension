package net.tslat.aoa3.item.tool.pickaxe;

import net.minecraft.block.BlockOre;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.item.tool.SpecialHarvestTool;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class GoofyPickaxe extends ItemPickaxe implements SkillItem, SpecialHarvestTool {
	public GoofyPickaxe(ToolMaterial material) {
		super(material);
		setUnlocalizedName("GoofyPickaxe");
		setRegistryName("aoa3:goofy_pickaxe");
		setCreativeTab(CreativeTabsRegister.toolsTab);
	}

	public void doHarvestEffect(BlockEvent.HarvestDropsEvent e) {
		if (e.getState().getBlock() instanceof BlockOre) {
			if (AdventOfAscension.rand.nextBoolean()) {
				ItemStack smeltedStack = FurnaceRecipes.instance().getSmeltingResult(e.getDrops().get(0)).copy();

				if (smeltedStack != ItemStack.EMPTY) {
					smeltedStack.setCount(smeltedStack.getCount() * 3);
					e.getDrops().set(0, smeltedStack);
				}
				else {
					ItemStack dropStack = e.getDrops().get(0);

					dropStack.setCount(3);
					e.getDrops().set(0, dropStack);
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
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		return enchantment != Enchantments.SILK_TOUCH;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		NBTTagList enchList = ItemEnchantedBook.getEnchantments(book);

		for (int i = 0; i < enchList.tagCount(); i++) {
			NBTTagCompound enchTag = enchList.getCompoundTagAt(i);

			if (Enchantment.getEnchantmentByID(enchTag.getShort("id")) == Enchantments.SILK_TOUCH)
				return false;
		}

		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.GoofyPickaxe.desc.1", TextFormatting.DARK_GREEN));
		tooltip.add(StringUtil.getColourLocaleString("item.GoofyPickaxe.desc.2", TextFormatting.DARK_GREEN));

		if (AdventGuiTabPlayer.getSkillLevel(getSkill()) >= 85) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.GREEN, "85", StringUtil.getLocaleString("skills.extraction.name")));
		}
		else {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.RED, "85", StringUtil.getLocaleString("skills.extraction.name")));
		}
	}
}
