package net.nevermine.item.weapon.scythe;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.nevermine.event.player.Ticker;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.resource.energy.energyHelper;

import java.util.Random;

public abstract class BaseScythe extends Item {
	private Random rand;
	private float dmg;
	private int canShootTick;

	public BaseScythe(final int uses, final float dealDamage) {
		rand = new Random();
		canShootTick = 0;
		setCreativeTab(Weaponizer.ScythesTab);
		dmg = dealDamage;
		setMaxDamage(uses);
		setMaxStackSize(1);
		setFull3D();
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	public EnumAction getItemUseAction(final ItemStack par1ItemStack) {
		return EnumAction.block;
	}

	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity entity) {
		return true;
	}

	public boolean onEntitySwing(final EntityLivingBase player, final ItemStack stack) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		if (!player.worldObj.isRemote && Ticker.tick >= stack.getTagCompound().getLong("lastShotTime") && player instanceof EntityPlayer && energyHelper.getProperties((EntityPlayer)player).getBarValue() >= 15.0f) {
			fireScythe(player.worldObj, stack, (EntityPlayer)player);
			stack.getTagCompound().setLong("lastShotTime", (long)(Ticker.tick + 14));
		}
		if (player instanceof EntityPlayerMP) {
			((EntityPlayerMP)player).sendContainerToPlayer(((EntityPlayerMP)player).inventoryContainer);
		}
		if (stack.getTagCompound().getLong("lastShotTime") >= 100000L || stack.getTagCompound().getLong("lastShotTime") > Ticker.tick + 141) {
			stack.getTagCompound().setLong("lastShotTime", 0L);
		}
		return false;
	}

	public boolean hitEntity(final ItemStack p_77644_1_, final EntityLivingBase p_77644_2_, final EntityLivingBase p_77644_3_) {
		return false;
	}

	public abstract void fireScythe(final World p0, final ItemStack p1, final EntityPlayer p2);
}
