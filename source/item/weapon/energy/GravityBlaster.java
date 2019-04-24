package net.nevermine.item.weapon.energy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.resource.energy.energyHelper;

import java.util.List;

public class GravityBlaster extends Item {
	public GravityBlaster() {
		setMaxDamage(200);
		setFull3D();
		setMaxStackSize(1);
		setCreativeTab(Weaponizer.AncientTab);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	public ItemStack onItemRightClick(final ItemStack par1ItemStack, final World par2World, final EntityPlayer par3EntityPlayer) {
		if (par3EntityPlayer.capabilities.isCreativeMode || energyHelper.getProperties(par3EntityPlayer).getBarValue() > 150.0f) {
			if (!par3EntityPlayer.capabilities.isCreativeMode)
				energyHelper.getProperties(par3EntityPlayer).useBar(150.0f);

			par3EntityPlayer.addVelocity(par3EntityPlayer.motionX * 1.0, 2.0, par3EntityPlayer.motionZ * 1.0);
			par3EntityPlayer.worldObj.playSoundAtEntity(par3EntityPlayer, "nevermine:GravityBlaster", 2.0f, 1.0f);
			par1ItemStack.damageItem(1, par3EntityPlayer);
			par3EntityPlayer.fallDistance = -0.5f;
		}
		return par1ItemStack;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.GravityBlaster.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.energy.cost", EnumChatFormatting.LIGHT_PURPLE, "150"));
	}
}
