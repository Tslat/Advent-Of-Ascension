package net.tslat.aoa3.item.tool.pickaxe;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.misc.EntityOccultBlock;
import net.tslat.aoa3.item.SkillItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import java.util.List;

public class OccultPickaxe extends ItemPickaxe implements SkillItem {
	public OccultPickaxe(ToolMaterial material) {
		super(material);
		setUnlocalizedName("OccultPickaxe");
		setRegistryName("aoa3:occult_pickaxe");
		setCreativeTab(CreativeTabsRegister.toolsTab);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState blockState, BlockPos pos, EntityLivingBase entity) {
		super.onBlockDestroyed(stack, world, blockState, pos, entity);

		if (!world.isRemote && blockState.getBlock() instanceof BlockOre && blockState.getBlockHardness(world, pos) > 0) {
			if (entity instanceof EntityPlayer) {
				for (int i = (int)(entity.posX - 6.0); i < (int)(entity.posX + 6.0); ++i) {
					for (int j = (int)(entity.posY - 6.0); j < (int)(entity.posY + 6.0); ++j) {
						for (int k = (int)(entity.posZ - 6.0); k < (int)(entity.posZ + 6.0); ++k) {
							if (i == pos.getX() && j == pos.getY() && k == pos.getZ())
								continue;

							Block block = world.getBlockState(new BlockPos(i, j, k)).getBlock();

							if (block instanceof BlockOre) {
								world.spawnEntity(new EntityOccultBlock(world, new BlockPos(i, j, k)));
							}
						}
					}
				}
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
		return 75;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.ingotRosite), false) || super.getIsRepairable(stack, repairMaterial);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.OccultPickaxe.desc.1", TextFormatting.DARK_GREEN));

		if (AdventGuiTabPlayer.getSkillLevel(getSkill()) >= 75) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.GREEN, "75", StringUtil.getLocaleString("skills.extraction.name")));
		}
		else {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.RED, "75", StringUtil.getLocaleString("skills.extraction.name")));
		}
	}
}
