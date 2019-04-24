package net.tslat.aoa3.item.tool.axe;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class OrnamyteAxe extends ItemAxe implements SkillItem {
	public OrnamyteAxe(ToolMaterial material) {
		super(material, material.getAttackDamage(), -3.0f);
		setUnlocalizedName("OrnamyteAxe");
		setRegistryName("aoa3:ornamyte_axe");
		setCreativeTab(CreativeTabsRegister.toolsTab);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState blockState, BlockPos pos, EntityLivingBase entity) {
		super.onBlockDestroyed(stack, world, blockState, pos, entity);

		if (!world.isRemote) {
			if (itemRand.nextInt(20) == 0) {
				pos = pos.up();
				IBlockState block = world.getBlockState(pos);
				int i = 0;

				while (block.getBlock() == blockState.getBlock()) {
					if (!MinecraftForge.EVENT_BUS.post(new BlockEvent.BreakEvent(world, pos, block, (EntityPlayer)entity))) {
						block.getBlock().dropBlockAsItem(world, pos, block, 0);
						world.setBlockToAir(pos);
					}
					else {
						break;
					}
					pos = pos.up();
					block = world.getBlockState(pos);
					i++;
				}

				stack.damageItem(i, entity);
			}
		}

		return true;
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
		tooltip.add(StringUtil.getColourLocaleString("item.OrnamyteAxe.desc.1", TextFormatting.DARK_GREEN));

		if (AdventGuiTabPlayer.getSkillLevel(getSkill()) >= 65) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.GREEN, "65", StringUtil.getLocaleString("skills.extraction.name")));
		}
		else {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.RED, "65", StringUtil.getLocaleString("skills.extraction.name")));
		}
	}
}
