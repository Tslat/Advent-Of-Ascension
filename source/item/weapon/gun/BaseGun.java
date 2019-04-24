package net.nevermine.item.weapon.gun;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.nevermine.assist.AddPackets;
import net.nevermine.assist.ArmorUtil;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.event.player.Ticker;
import net.nevermine.event.recoil.RecoilMessage;
import net.nevermine.izer.Itemizer;

import java.util.Random;

public abstract class BaseGun extends Item {
	private Random rand;
	private int canShootTick;
	private int chance;
	private String sound;
	private int rof;
	private Item ammo;
	private float recoil;
	private float mult;
	private float modify;

	public BaseGun(final int consumeChance, final String effect, final int uses, final int fireRate, final Item item) {
		rand = new Random();
		canShootTick = 0;
		mult = 1.0f;
		chance = consumeChance;
		sound = effect;
		rof = fireRate;
		ammo = item;
		setFull3D();
		setMaxDamage(uses);
		maxStackSize = 1;
		recoil = 0.0f;
	}

	public BaseGun(final int consumeChance, final String effect, final int uses, final int fireRate, final Item item, final float rec) {
		rand = new Random();
		canShootTick = 0;
		mult = 1.0f;
		chance = consumeChance;
		sound = effect;
		rof = fireRate;
		ammo = item;
		setFull3D();
		setMaxDamage(uses);
		maxStackSize = 1;
		recoil = rec;
	}

	public Item setRecoil(final float rec) {
		recoil = rec;
		return this;
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

		boolean consumeAmmo = true;

		if (Ticker.tick >= stack.getTagCompound().getLong("lastShotTime") && (player.capabilities.isCreativeMode || player.inventory.hasItem(ammo))) {
			if (!player.capabilities.isCreativeMode && rand.nextInt(100) < chance) {
				player.inventory.consumeInventoryItem(ammo);
			}
			else {
				consumeAmmo = false;
			}
			if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.eshell, stack) == 3) {
				mult = 1.6f;
			}
			else if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.eshell, stack) == 2) {
				mult = 1.4f;
			}
			else if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.eshell, stack) == 1) {
				mult = 1.2f;
			}
			else {
				mult = 1.0f;
			}

			if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.econtrol, stack) == 2) {
				modify = 0.5f;
			}
			else if (EnchantmentHelper.getEnchantmentLevel(ConfigurationHelper.econtrol, stack) == 1) {
				modify = 0.75f;
			}
			else {
				modify = 1.0f;
			}
			if (ArmorUtil.isPurityArmor(player)) {
				mult *= 1.25f;
			}
			player.worldObj.playSoundAtEntity(player, "nevermine:" + sound, 1.0f, 1.0f);
			if (!player.worldObj.isRemote) {
				fireGun(world, stack, player, mult, consumeAmmo);
				stack.damageItem(1, player);
				stack.getTagCompound().setLong("lastShotTime", Ticker.tick + (long)(rof * mult));
			}
			if (player instanceof EntityPlayerMP) {
				AddPackets.network.sendTo(new RecoilMessage(recoil * modify), (EntityPlayerMP)player);
			}
		}
		if (player instanceof EntityPlayerMP) {
			((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
		}
		if (stack.getTagCompound().getLong("lastShotTime") >= 100000L || stack.getTagCompound().getLong("lastShotTime") > Ticker.tick + 250) {
			stack.getTagCompound().setLong("lastShotTime", 0L);
		}
		return stack;
	}

	public abstract void fireGun(final World p0, final ItemStack p1, final EntityPlayer p2, final float p3, boolean p4);
}
