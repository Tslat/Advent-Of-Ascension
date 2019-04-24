package net.nevermine.item.weapon.energy;

import com.google.common.collect.Multimap;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.nevermine.assist.ArmorUtil;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.resource.energy.energyHelper;

import java.util.Random;

public abstract class BaseEnergyRapid extends ItemSword {
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

	public BaseEnergyRapid(final int consumeChance, final String effect, final int uses, final int fireRate, final int cost) {
		super(Weaponizer.Sprayer);
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

	@Override
	public boolean hitEntity(ItemStack p_77644_1_, EntityLivingBase p_77644_2_, EntityLivingBase p_77644_3_) {
		return true;
	}

	public void onUsingTick(final ItemStack stack, final EntityPlayer player, final int count) {
		final int ticksInUse = 72000 - count;
		if (ticksInUse % rof == 0) {
			if (ArmorUtil.isGhoulishArmor(player)) {
				newcost = (int)(Energycost / 2 + Energycost % 2 * multi);
			}
			else {
				newcost = (int)(Energycost * multi);
			}
			if (!player.worldObj.isRemote && (player.capabilities.isCreativeMode || energyHelper.getProperties(player).useBar(newcost))) {
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
				if (sound != null) {
					player.worldObj.playSoundAtEntity(player, "nevermine:" + sound, 1.0f, 1.0f);
				}
				if (!player.worldObj.isRemote) {
					fireAncient(player.worldObj, stack, player);
					stack.damageItem(1 + destruct, player);
				}
			}
			if (player instanceof EntityPlayerMP) {
				((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
			}
		}
	}

	public Multimap getItemAttributeModifiers() {
		final Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(BaseEnergyRapid.field_111210_e, "Weapon modifier", 0, 0));
		return multimap;
	}

	public abstract void fireAncient(final World p0, final ItemStack p1, final EntityPlayer p2);
}
