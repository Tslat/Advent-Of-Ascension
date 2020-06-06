package net.tslat.aoa3.item.weapon.blaster;

import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.misc.AoAAttributes;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public abstract class BaseBlaster extends Item implements EnergyProjectileWeapon, AdventWeapon {
	protected final double baseDmg;
	protected final int firingDelay;
	protected final float energyCost;

	public BaseBlaster(final double dmg, final int durability, final int fireDelayTicks, final float energyCost) {
		this.baseDmg = dmg;
		this.firingDelay = fireDelayTicks;
		this.energyCost = energyCost;
		setMaxDamage(durability);
		setCreativeTab(CreativeTabsRegister.BLASTERS);
		setMaxStackSize(1);
		setFull3D();
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

	@Nullable
	public SoundEvent getFiringSound() {
		return null;
	}

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return false;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return slotChanged || oldStack.getItem() != newStack.getItem();
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

		if (!world.isRemote) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);
			int recharge = EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.RECHARGE, stack);
			int greed = EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.GREED, stack);
			float energyConsumption = (1 + (0.3f * greed)) * energyCost * Math.max(0, (1 - 0.07f * recharge));

			if (plData.equipment().getCurrentFullArmourSet() == Enums.ArmourSets.GHOULISH)
				energyConsumption *= 0.7f;

			if (plData.stats().getResourceValue(Enums.Resources.ENERGY) < energyConsumption)
				return ActionResult.newResult(EnumActionResult.FAIL, stack);

			player.setActiveHand(hand);
		}

		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		if (!player.world.isRemote) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((EntityPlayer)player);
			int recharge = EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.RECHARGE, stack);
			int greed = EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.GREED, stack);
			float energyConsumption = ((EntityPlayer)player).capabilities.isCreativeMode ? 0 : (1 + (0.3f * greed)) * energyCost * Math.max(0, (1 - 0.07f * recharge));

			if (plData.equipment().getCurrentFullArmourSet() == Enums.ArmourSets.GHOULISH)
				energyConsumption *= 0.7f;

			if (plData.stats().getResourceValue(Enums.Resources.ENERGY) >= energyConsumption) {
				if (count + firingDelay <= 72000 && count % firingDelay == 0) {
					if (consumeEnergy(plData, stack, energyConsumption)) {
						if (getFiringSound() != null)
							player.world.playSound(null, player.posX, player.posY, player.posZ, getFiringSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);

						fire(stack, player);
						((EntityPlayer)player).addStat(StatList.getObjectUseStats(this));

						if ((72000 - count) / firingDelay >= getMaxDamage(stack) - stack.getItemDamage())
							stack.damageItem((72000 - count) / firingDelay, player);
					}
					else {
						player.stopActiveHand();
					}
				}
			}
			else {
				if (player instanceof EntityPlayerMP)
					PlayerUtil.notifyPlayerOfInsufficientResources((EntityPlayerMP)player, Enums.Resources.ENERGY, energyConsumption);

				player.stopActiveHand();
			}
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase player, int useTicksRemaining) {
		stack.damageItem((72000 - useTicksRemaining - 1) / firingDelay, player);
	}

	public abstract void fire(ItemStack blaster, EntityLivingBase shooter);

	public boolean consumeEnergy(PlayerDataManager plData, ItemStack stack, float cost) {
		return plData.stats().consumeResource(Enums.Resources.ENERGY, cost, false);
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
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		if (EntityUtil.dealBlasterDamage(shooter, target, shot, (float)baseDmg, false)) {
			doImpactEffect(shot, target, shooter);

			return true;
		}

		return false;
	}

	protected void doImpactEffect(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		if (baseDmg > 0)
			tooltip.add(1, StringUtil.getColourLocaleStringWithArguments("items.description.damage.blaster", TextFormatting.DARK_RED, StringUtil.roundToNthDecimalPlace((float)baseDmg, 1)));

		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.blaster.fire", Enums.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.blaster.slowing", Enums.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.blaster.effect", Enums.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / 100d)));

		float energyConsumption = (1 + (0.3f * EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.GREED, stack)) * getEnergyCost() * Math.max(0, (1 - 0.07f * EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.RECHARGE, stack))));

		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.ammo.resource", Enums.ItemDescriptionType.ITEM_AMMO_COST, StringUtil.roundToNthDecimalPlace(energyConsumption, 1), StringUtil.getLocaleString("resources.energy.name")));
	}

	@Override
	public int getItemEnchantability() {
		return 8;
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

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
			multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), AoAAttributes.vanillaWeaponSpeedModifier(firingDelay < 20 ? Enums.WeaponSpeed.THIRD.value : Enums.WeaponSpeed.QUARTER.value));

		return multimap;
	}
}
