package net.nevermine.item.weapon.staff;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.assist.StringUtil;
import net.nevermine.event.tasks.ReefStaffTask;
import net.nevermine.item.ItemRune;
import net.nevermine.izer.Blockizer;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ReefStaff extends BaseStaff {
	private static HashMap<ItemRune, Integer> runes = new HashMap<ItemRune, Integer>();

	public ReefStaff() {
		super(runes);
		setMaxDamage(70);
		setCreativeTab(Weaponizer.StaffTab);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.ReefStaff.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.runes.required", EnumChatFormatting.LIGHT_PURPLE));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(2), StringUtil.getLocaleString("item.EnergyRune.name")));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(8), StringUtil.getLocaleString("item.KineticRune.name")));
	}

	@Override
	public void fireStaff(final World world, final ItemStack stack, final EntityPlayer player) {
		if (player.dimension == ConfigurationHelper.ancientcavern || player.dimension == ConfigurationHelper.immortallis) {
			player.addChatMessage(StringUtil.getLocale("message.feedback.item.reefStaff.fail"));
		}
		else {
			player.worldObj.playSoundAtEntity(player, "nevermine:ReefStaff", 1.0f, 1.0f);

			if (!world.isRemote) {
				for (int x = (int)player.posX - 2; x < (int)player.posX + 2; x++) {
					for (int z = (int)player.posZ - 2; z < (int)player.posZ + 3; z++) {
						if (world.getBlock(x, (int)player.posY - 2, z).getMaterial() == Material.air) {
							world.setBlock(x, (int)player.posY - 2, z, Blockizer.CoralOrange);
						}

						if (world.getBlock(x, (int)player.posY + 2, z).getMaterial() == Material.air) {
							world.setBlock(x, (int)player.posY + 2, z, Blockizer.CoralOrange);
						}
					}

					for (int y = (int)player.posY - 2; y < (int)player.posY + 3; y++) {
						if (world.getBlock(x, y, (int)player.posZ - 2).getMaterial() == Material.air) {
							world.setBlock(x, y, (int)player.posZ - 2, Blockizer.CoralOrange);
						}

						if (world.getBlock(x, y, (int)player.posZ + 2).getMaterial() == Material.air) {
							world.setBlock(x, y, (int)player.posZ + 2, Blockizer.CoralOrange);
						}
					}
				}

				for (int z = (int)player.posZ - 2; z < (int)player.posZ + 3; z++) {
					for (int y = (int)player.posY - 2; y < (int)player.posY + 3; y++) {
						if (world.getBlock((int)player.posX - 2, y, z).getMaterial() == Material.air) {
							world.setBlock((int)player.posX - 2, y, z, Blockizer.CoralOrange);
						}

						if (world.getBlock((int)player.posX + 2, y, z).getMaterial() == Material.air) {
							world.setBlock((int)player.posX + 2, y, z, Blockizer.CoralOrange);
						}
					}
				}

				new ReefStaffTask(player.worldObj, player.posX, player.posY, player.posZ).schedule(1, TimeUnit.MINUTES);
			}
		}
	}

	static {
		runes.put((ItemRune)Itemizer.EnergyRune, 2);
		runes.put((ItemRune)Itemizer.KineticRune, 8);
	}
}
