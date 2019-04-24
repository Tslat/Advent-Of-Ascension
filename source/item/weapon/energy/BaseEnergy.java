package net.nevermine.item.weapon.energy;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.nevermine.assist.ArmorUtil;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.event.player.Ticker;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.resource.energy.energyHelper;

import java.util.Random;

public abstract class BaseEnergy extends Item {
	private Random rand;
	private int canShootTick;
	private int chance;
	private String sound;
	private int rof;
	private int Energycost;
	private int newcost;
	private float multi;
	private float modify;
	private int destruct;

	public BaseEnergy(final int consumeChance, final String effect, final int uses, final int fireRate, final int cost) {
		rand = new Random();
		canShootTick = 0;
		multi = 1.0f;
		modify = 1.0f;
		destruct = 0;
		chance = consumeChance;
		sound = effect;
		rof = fireRate;
		Energycost = cost;
		setFull3D();
		setMaxDamage(uses);
		setCreativeTab(Weaponizer.AncientTab);
		maxStackSize = 1;
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	public EnumAction getItemUseAction(final ItemStack par1ItemStack) {
		return EnumAction.none;
	}

	public ItemStack onItemRightClick(final ItemStack stack, final World world, final EntityPlayer player) {
		if (!stack.hasTagCompound()) {
			stack.setTagCompound(new NBTTagCompound());
		}
		if (Ticker.tick >= stack.getTagCompound().getLong("lastShotTime")) {
			if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.erecharge, stack) == 2) {
				multi = 0.6f;
			}
			else if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.erecharge, stack) == 1) {
				multi = 0.8f;
			}
			else {
				multi = 1.0f;
			}
			if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.eoverpower, stack) == 2) {
				modify = 0.6f;
				destruct = 2;
			}
			else if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.eoverpower, stack) == 1) {
				modify = 0.8f;
				destruct = 1;
			}
			else {
				modify = 1.0f;
				destruct = 0;
			}
			if (ArmorUtil.isGhoulishArmor(player)) {
				newcost = (int)(Energycost / 2 + Energycost % 2 * multi);
			}
			else {
				newcost = (int)(Energycost * multi);
			}
			if (player.capabilities.isCreativeMode || energyHelper.getProperties(player).useBar(newcost)) {
				if (sound != null) {
					player.worldObj.playSoundAtEntity(player, "nevermine:" + sound, 1.0f, 1.0f);
				}
				if (!player.worldObj.isRemote) {
					fireAncient(world, stack, player);
					stack.damageItem(1 + destruct, player);
					stack.getTagCompound().setLong("lastShotTime", Ticker.tick + (long)(rof * modify));
				}
			}
		}
		if (player instanceof EntityPlayerMP) {
			((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
		}
		if (stack.getTagCompound().getLong("lastShotTime") >= 100000L || stack.getTagCompound().getLong("lastShotTime") > Ticker.tick + 141) {
			stack.getTagCompound().setLong("lastShotTime", 0L);
		}
		return stack;
	}

	public abstract void fireAncient(final World p0, final ItemStack p1, final EntityPlayer p2);
}
