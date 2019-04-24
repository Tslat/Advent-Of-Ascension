package net.nevermine.item.weapon.sword;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumChatFormatting;
import net.nevermine.assist.StringUtil;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;

import java.util.List;
import java.util.Random;

public class CreepifiedSword extends ItemSword implements SwordInterface {
	private Random rand;

	public CreepifiedSword(final Item.ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		rand = new Random();
		setCreativeTab(Weaponizer.SwordsTab);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	public boolean hitEntity(final ItemStack stack, final EntityLivingBase live, final EntityLivingBase live2) {
		if (rand.nextInt(10) == 3 && !live2.worldObj.isRemote) {
			final EntityCreeper var2 = new EntityCreeper(live2.worldObj);
			var2.setLocationAndAngles(live.posX, live.posY, live.posZ, rand.nextFloat() * 360.0f, 0.0f);
			live2.worldObj.spawnEntityInWorld(var2);
		}
		stack.damageItem(1, live2);
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.CreepifiedSword.desc.1", EnumChatFormatting.DARK_GREEN));
	}
}
