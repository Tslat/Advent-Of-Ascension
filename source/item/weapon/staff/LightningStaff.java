package net.nevermine.item.weapon.staff;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.item.ItemRune;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.HashMap;
import java.util.List;

public class LightningStaff extends BaseStaff {
	private static HashMap<ItemRune, Integer> runes = new HashMap<ItemRune, Integer>();

	public LightningStaff() {
		super(runes);
		setCreativeTab(Weaponizer.StaffTab);
		setMaxDamage(400);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	public void createLightning(final World par2World, final EntityPlayer par3EntityPlayer) {
		for (int i = 2; i < 5; i += 2) {
			for (double var4 = 0.0; var4 < 6.283185307179586; var4 += 0.39269908169872414) {
				final int var5 = (int)Math.round(Math.sin(var4) * i);
				final int var6 = (int)Math.round(Math.cos(var4) * i);
				par2World.addWeatherEffect(new EntityLightningBolt(par2World, par3EntityPlayer.posX + var5, par3EntityPlayer.posY, par3EntityPlayer.posZ + var6));
			}
		}
	}

	public EnumAction getItemUseAction(final ItemStack par1ItemStack) {
		return EnumAction.none;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.LightningStaff.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.runes.required", EnumChatFormatting.LIGHT_PURPLE));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(2), StringUtil.getLocaleString("item.WindRune.name")));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(1), StringUtil.getLocaleString("item.StrikeRune.name")));
	}

	@Override
	public void fireStaff(final World world, final ItemStack stack, final EntityPlayer player) {
		if (!world.isRemote) {
			createLightning(world, player);
			stack.damageItem(1, player);
		}
	}

	static {
		runes.put((ItemRune)Itemizer.WindRune, 2);
		runes.put((ItemRune)Itemizer.StrikeRune, 1);
	}
}
