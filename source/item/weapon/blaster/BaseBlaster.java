package net.tslat.aoa3.item.weapon.blaster;

import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseBlaster extends Item implements EnergyProjectileWeapon, AdventWeapon {
	protected final double baseDmg;
	protected final SoundEvent sound;
	protected final int firingDelay;
	protected final float energyCost;

	public BaseBlaster(final double dmg, final SoundEvent sound, final int durability, final int fireDelayTicks, final float energyCost) {
		this.baseDmg = dmg;
		this.sound = sound;
		this.firingDelay = fireDelayTicks;
		this.energyCost = energyCost;
		setMaxDamage(durability);
		setCreativeTab(CreativeTabsRegister.blastersTab);
		setMaxStackSize(1);
		setFull3D();
		setNoRepair();
	}

	public double getDamage() {
		return baseDmg;
	}

	public int getFiringDelay() {
		return firingDelay;
	}

	public float getEnergyCost() {
		return energyCost;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return repairMaterial.getItem() != Items.ENCHANTED_BOOK && OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.ingotRosite), false) || super.getIsRepairable(stack, repairMaterial);
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return oldStack.getItem() != newStack.getItem();
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.NONE;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 72000;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);

		if (hand != getWeaponHand())
			return ActionResult.newResult(EnumActionResult.FAIL, stack);

		if (player.getCooledAttackStrength(0.0f) < 1)
			return ActionResult.newResult(EnumActionResult.FAIL, stack);

		player.setActiveHand(hand);

		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		if (count + firingDelay <= 72000 && count % firingDelay == 0) {
			AdventPlayerCapability cap = (AdventPlayerCapability)player.getCapability(AdventPlayerProvider.ADVENT_PLAYER, null);
			int recharge = EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.recharge, stack);
			int greed = EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.greed, stack);

			if (!player.world.isRemote) {
				if (consumeEnergy(cap, stack, (1 + (0.3f * greed)) * energyCost * (1 - 0.07f * recharge))) {
					if (sound != null)
						player.world.playSound(null, player.posX, player.posY, player.posZ, sound, SoundCategory.PLAYERS, 1.0f, 1.0f);

					fire(stack, player);
					((EntityPlayer)player).addStat(StatList.getObjectUseStats(this));

					if ((72000 - count) / firingDelay >= getMaxDamage(stack))
						stack.damageItem((72000 - count) / firingDelay, player);
				}
				else {
					player.stopActiveHand();
				}
			}
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase player, int useTicksRemaining) {
		stack.damageItem((72000 - useTicksRemaining) / firingDelay, player);
	}

	public abstract void fire(ItemStack blaster, EntityLivingBase shooter);

	public boolean consumeEnergy(AdventPlayerCapability cap, ItemStack stack, float cost) {
		return cap.consumeResource(Enums.Resources.ENERGY, (float)(cap.getArmourSetType() == Enums.ArmourSets.GHOULISH ? cost * 0.7 : cost), false);
	}

	@Override
	public boolean isFull3D() {
		return true;
	}

	@Override
	public EnumHand getWeaponHand() {
		return EnumHand.MAIN_HAND;
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, BlockPos block, EntityLivingBase shooter) {}

	@Override
	public void doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		if (target != null)
			EntityUtil.dealBlasterDamage(shooter, target, shot, (float)baseDmg, false);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		if (baseDmg > 0)
			tooltip.add(1, StringUtil.getColourLocaleStringWithArguments("items.description.damage.blaster", TextFormatting.DARK_RED, Double.toString(baseDmg)));

		tooltip.add(StringUtil.getColourLocaleString("items.description.blaster.fire", TextFormatting.AQUA));
		tooltip.add(StringUtil.getColourLocaleString("items.description.blaster.slowing", TextFormatting.AQUA));
		tooltip.add(StringUtil.getColourLocaleString("items.description.blaster.effect", TextFormatting.AQUA));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / (double)100)));
		tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.ammo.energy", TextFormatting.LIGHT_PURPLE, Float.toString(energyCost)));
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Nullable
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());

		stack.getTagCompound().setByte("HideFlags", (byte)2);

		return super.initCapabilities(stack, nbt);
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot equipmentSlot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot, stack);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND) {
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", firingDelay < 20 ? Enums.WeaponSpeed.THIRD.value : Enums.WeaponSpeed.QUARTER.value, 0));
		}

		return multimap;
	}
}
