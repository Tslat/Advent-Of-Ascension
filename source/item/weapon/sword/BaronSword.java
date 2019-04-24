package net.nevermine.item.weapon.sword;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.nevermine.assist.StringUtil;
import net.nevermine.common.nevermine;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.List;

public class BaronSword extends ItemSword implements SwordInterface {

	public BaronSword(final Item.ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		setCreativeTab(Weaponizer.SwordsTab);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	public boolean hitEntity(final ItemStack stack, final EntityLivingBase live, final EntityLivingBase live2) {
		if (nevermine.rand.nextInt(5) == 2) {
			live.addPotionEffect(new PotionEffect(Potion.harm.id, 40, 1));
		}

		stack.damageItem(1, live2);
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.BaronSword.desc.1", EnumChatFormatting.DARK_GREEN));
	}
}
