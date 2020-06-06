package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
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
import net.tslat.aoa3.capabilities.handlers.AdventGunCapability;
import net.tslat.aoa3.capabilities.providers.AdventGunProvider;
import net.tslat.aoa3.common.registration.CreativeTabsRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.event.GlobalEvents;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.EnergyProjectileWeapon;
import net.tslat.aoa3.item.weapon.blaster.BaseBlaster;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseStaff extends Item implements AdventWeapon, EnergyProjectileWeapon {
	protected final HashMap<RuneItem, Integer> runes = new HashMap<RuneItem, Integer>(2);

	public BaseStaff(int durability) {
		setMaxDamage(durability);
		setMaxStackSize(1);
		setFull3D();
		setCreativeTab(CreativeTabsRegister.STAVES);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		AdventGunCapability cap = (AdventGunCapability)stack.getCapability(AdventGunProvider.ADVENT_GUN, null);

		if (cap == null)
			return ActionResult.newResult(EnumActionResult.FAIL, stack);

		if (hand == EnumHand.OFF_HAND) {
			Item mainItem = player.getHeldItem(EnumHand.MAIN_HAND).getItem();

			if (mainItem instanceof BaseGun || mainItem instanceof BaseBlaster)
				return ActionResult.newResult(EnumActionResult.FAIL, stack);
		}

		if (cap.getNextFireTime() < GlobalEvents.tick) {
			if (!world.isRemote) {
				Object preconditionResult = checkPreconditions(player, stack);

				if (preconditionResult == null)
					return ActionResult.newResult(EnumActionResult.FAIL, stack);

				if (!findAndConsumeRunes(getRunes(), player, true, stack))
					return ActionResult.newResult(EnumActionResult.FAIL, stack);

				if (getCastingSound() != null)
					world.playSound(null, player.posX, player.posY, player.posZ, getCastingSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);

				cap.setNextShotDelay(12);
				player.addStat(StatList.getObjectUseStats(this));
				stack.damageItem(1, player);
				cast(world, stack, player, preconditionResult);
			}

			return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
		}
		else if (cap.getNextFireTime() > GlobalEvents.tick + 20) {
			cap.setNextShotDelay(0);
		}

		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}

	public boolean findAndConsumeRunes(HashMap<RuneItem, Integer> runes, EntityPlayer player, boolean allowBuffs, ItemStack staff) {
		return ItemUtil.findAndConsumeRunes(runes, player, allowBuffs, staff);
	}

	@Nullable
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		return new Object();
	}

	public HashMap<RuneItem, Integer> getRunes() {
		if (runes.isEmpty())
			populateRunes(runes);

		return runes;
	}

	@Nullable
	public SoundEvent getCastingSound() {
		return null;
	}

	protected abstract void populateRunes(HashMap<RuneItem, Integer> runes);

	public abstract void cast(World world, ItemStack staff, EntityLivingBase caster, Object args);

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return repairMaterial.getItem() != Items.ENCHANTED_BOOK && OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.MAGIC_REPAIR_DUST), false);
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, BlockPos block, EntityLivingBase shooter) {}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {return true;}

	@Override
	public EnumHand getWeaponHand() {
		return EnumHand.MAIN_HAND;
	}

	@Override
	public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
		return oldStack.getItem() != newStack.getItem();
	}

	public float getDmg() {
		return 0;
	}

	@Override
	public int getItemEnchantability() {
		return 8;
	}

	@Nullable
	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
		return new AdventGunProvider();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		if (getDmg() > 0)
			tooltip.add(1, ItemUtil.getFormattedDescriptionText("items.description.damage.magic", Enums.ItemDescriptionType.ITEM_DAMAGE, Float.toString(getDmg())));

		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.staff.runesRequired", Enums.ItemDescriptionType.ITEM_AMMO_COST));

		for (Map.Entry<RuneItem, Integer> runeEntry : getRunes().entrySet()) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.staff.runesRequired.specific", TextFormatting.WHITE, Integer.toString(runeEntry.getValue()), StringUtil.getLocaleString(runeEntry.getKey().getTranslationKey() + ".name")));
		}
	}
}
