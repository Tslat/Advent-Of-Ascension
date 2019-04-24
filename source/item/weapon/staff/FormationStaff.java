package net.nevermine.item.weapon.staff;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.assist.StringUtil;
import net.nevermine.item.ItemRune;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.HashMap;
import java.util.List;

public class FormationStaff extends BaseStaff {
	private static HashMap<ItemRune, Integer> runes = new HashMap<ItemRune, Integer>();

	public FormationStaff() {
		super(runes);
		setMaxDamage(200);
		setCreativeTab(Weaponizer.StaffTab);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.MagicRepairDust == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.FormationStaff.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.FormationStaff.desc.2", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("item.FormationStaff.desc.3", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.runes.required", EnumChatFormatting.LIGHT_PURPLE));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(6), StringUtil.getLocaleString("item.KineticRune.name")));
	}

	@Override
	public void fireStaff(final World world, final ItemStack stack, final EntityPlayer player) {
		player.worldObj.playSoundAtEntity(player, "nevermine:FormationStaff", 1.0f, 1.0f);
		int count = 0;

		if (player.dimension != ConfigurationHelper.ancientcavern && player.dimension != ConfigurationHelper.immortallis && player.dimension != ConfigurationHelper.labricon) {
			for (int i = (int)(player.posX - 4.0); i < (int)(player.posX + 8.0); ++i) {
				for (int j = (int)(player.posY - 4.0); j < (int)(player.posY + 8.0); ++j) {
					for (int k = (int)(player.posZ - 4.0); k < (int)(player.posZ + 8.0); ++k) {
						Block bl = world.getBlock(i, j, k);

						if (!world.isRemote && !MinecraftForge.EVENT_BUS.post(new BlockEvent.BreakEvent(i, j, k, world, bl, world.getBlockMetadata(i, j, k), player))) {
							world.setBlock(i, j, k, Blocks.air);
							count++;
						}
					}
				}
			}
		}

		if (count > 0)
			stack.damageItem(1 + (count / 100), player);
	}

	static {
		runes.put((ItemRune)Itemizer.KineticRune, 6);
	}
}
