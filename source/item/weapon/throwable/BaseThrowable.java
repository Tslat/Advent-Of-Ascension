package net.nevermine.item.weapon.throwable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.nevermine.event.player.Ticker;

import java.util.Random;

public abstract class BaseThrowable extends Item {
	private Random rand;
	private int canShootTick;

	public BaseThrowable() {
		rand = new Random();
		canShootTick = 0;
		setMaxStackSize(64);
	}

	public EnumAction getItemUseAction(final ItemStack par1ItemStack) {
		return EnumAction.none;
	}

	public ItemStack onItemRightClick(final ItemStack stack, final World world, final EntityPlayer player) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		if (Ticker.tick >= stack.getTagCompound().getLong("lastShotTime")) {
			if (!player.capabilities.isCreativeMode) {
				player.inventory.consumeInventoryItem(this);
			}

			player.worldObj.playSoundAtEntity(player, "random.bow", 0.5f, 0.4f / (BaseThrowable.itemRand.nextFloat() * 0.4f + 0.8f));

			if (!player.worldObj.isRemote) {
				fireGun(world, stack, player);
				stack.getTagCompound().setLong("lastShotTime", Ticker.tick + 10L);
			}
		}
		if (player instanceof EntityPlayerMP) {
			((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
		}
		if (stack.getTagCompound().getLong("lastShotTime") >= 100000L || stack.getTagCompound().getLong("lastShotTime") > Ticker.tick + 141) {
			stack.setTagCompound(new NBTTagCompound());
		}
		return stack;
	}

	public abstract void fireGun(final World p0, final ItemStack p1, final EntityPlayer p2);
}
