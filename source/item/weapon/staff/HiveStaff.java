package net.nevermine.item.weapon.staff;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.item.ItemRune;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.minion.entity.EntityHiveSoldier;
import net.nevermine.resource.creation.creationHelper;

import java.util.HashMap;
import java.util.List;

public class HiveStaff extends BaseStaff {
	private static HashMap<ItemRune, Integer> runes = new HashMap<ItemRune, Integer>();

	public HiveStaff() {
		super(runes);
		setMaxDamage(200);
		setCreativeTab(Weaponizer.StaffTab);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.HiveStaff.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.runes.required", EnumChatFormatting.LIGHT_PURPLE));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(3), StringUtil.getLocaleString("item.KineticRune.name")));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(1), StringUtil.getLocaleString("item.EnergyRune.name")));
	}

	@Override
	public void fireStaff(final World world, final ItemStack stack, final EntityPlayer player) {
		player.worldObj.playSoundAtEntity(player, "nevermine:Shadowstaff", 1.0f, 1.0f);

		if (!world.isRemote && (player.capabilities.isCreativeMode || creationHelper.getProperties(player).useBar(600.0f))) {
			final EntityHiveSoldier var4 = new EntityHiveSoldier(player.worldObj);

			var4.setLocationAndAngles(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
			player.worldObj.spawnEntityInWorld(var4);
			stack.damageItem(1, player);
		}
	}

	static {
		runes.put((ItemRune)Itemizer.KineticRune, 3);
		runes.put((ItemRune)Itemizer.EnergyRune, 1);
	}
}
