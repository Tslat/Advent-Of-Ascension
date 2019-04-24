package net.tslat.aoa3.item.tool.pickaxe;

import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayer;
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

public class Pickmax extends ItemPickaxe implements SkillItem {
	public Pickmax(ToolMaterial material) {
		super(material);
		setUnlocalizedName("Pickmax");
		setRegistryName("aoa3:pickmax");
		setCreativeTab(CreativeTabsRegister.toolsTab);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState blockState, BlockPos pos, EntityLivingBase entity) {
		super.onBlockDestroyed(stack, world, blockState, pos, entity);

		float targetHardness = blockState.getBlockHardness(world, pos);

		if (!world.isRemote && entity instanceof EntityPlayer && !(entity instanceof FakePlayer) && blockState.getMaterial() == Material.ROCK && blockState.isOpaqueCube()) {
			for (int i = pos.getX() - 1; i < pos.getX() + 2; i++) {
				for (int j = pos.getY() - 1; j < pos.getY() + 2; j++) {
					for (int k = pos.getZ() - 1; k < pos.getZ() + 2; k++) {
						BlockPos newPos = new BlockPos(i, j, k);
						IBlockState state = world.getBlockState(newPos);
						float hardness = state.getBlockHardness(world, newPos);

						if (state.getMaterial() == Material.ROCK && state.isOpaqueCube() && !(state.getBlock() instanceof BlockOre) && hardness > 0 && hardness <= targetHardness){
							if (!MinecraftForge.EVENT_BUS.post(new BlockEvent.BreakEvent(world, newPos, state, (EntityPlayer)entity))) {
								world.setBlockToAir(newPos);
								state.getBlock().dropBlockAsItem(world, newPos, state, 0);
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
		return 90;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.ingotRosite), false) || super.getIsRepairable(stack, repairMaterial);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(StringUtil.getColourLocaleString("item.Pickmax.desc.1", TextFormatting.DARK_GREEN));

		if (AdventGuiTabPlayer.getSkillLevel(getSkill()) >= 90) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.GREEN, "90", StringUtil.getLocaleString("skills.extraction.name")));
		}
		else {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.skillRequirement", TextFormatting.RED, "90", StringUtil.getLocaleString("skills.extraction.name")));
		}
	}
}
