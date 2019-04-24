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
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseStaff extends Item implements AdventWeapon, EnergyProjectileWeapon {
	private final SoundEvent castSound;

	public BaseStaff(SoundEvent sound, int durability) {
		this.castSound = sound;
		setMaxDamage(durability);
		setMaxStackSize(1);
		setFull3D();
		setCreativeTab(CreativeTabsRegister.stavesTab);
		setNoRepair();
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
			EnumActionResult result = EnumActionResult.SUCCESS;

			if (!world.isRemote) {
				Object preconditionResult = checkPreconditions(player, stack);

				if (preconditionResult == null)
					return ActionResult.newResult(EnumActionResult.FAIL, stack);

				if (!findAndConsumeRunes(getRunes(), player, true, stack))
					return ActionResult.newResult(EnumActionResult.FAIL, stack);

				cap.setNextFireTime(10);
				player.addStat(StatList.getObjectUseStats(this));
				world.playSound(null, player.posX, player.posY, player.posZ, castSound, SoundCategory.PLAYERS, 1.0f, 1.0f);
				stack.damageItem(1, player);
				cast(world, stack, player, preconditionResult);
			}

			return ActionResult.newResult(result, stack);
		}
		else if (cap.getNextFireTime() > GlobalEvents.tick + 20) {
			cap.setNextFireTime(-GlobalEvents.tick);
		}

		return ActionResult.newResult(EnumActionResult.FAIL, stack);
	}

	public boolean findAndConsumeRunes(HashMap<RuneItem, Integer> runes, EntityPlayer player, boolean allowBuffs, ItemStack staff) {
		return ItemUtil.findAndConsumeRunes(runes, player, allowBuffs, staff);
	}

	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		return new Object();
	}

	public abstract HashMap<RuneItem, Integer> getRunes();

	public abstract void cast(World world, ItemStack staff, EntityLivingBase caster, Object args);

	@Override
	public boolean getIsRepairable(ItemStack stack, ItemStack repairMaterial) {
		return repairMaterial.getItem() != Items.ENCHANTED_BOOK && OreDictionary.itemMatches(repairMaterial, new ItemStack(ItemRegister.magicRepairDust), false) || super.getIsRepairable(stack, repairMaterial);
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, BlockPos block, EntityLivingBase shooter) {}

	@Override
	public void doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {}

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
		return 0;
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
			tooltip.add(1, StringUtil.getColourLocaleStringWithArguments("items.description.damage.magic", TextFormatting.DARK_RED, Float.toString(getDmg())));

		tooltip.add(StringUtil.getColourLocaleString("items.description.staff.runesRequired", TextFormatting.LIGHT_PURPLE));

		for (Map.Entry<RuneItem, Integer> runeEntry : getRunes().entrySet()) {
			tooltip.add(StringUtil.getColourLocaleStringWithArguments("items.description.staff.runesRequired.specific", TextFormatting.WHITE, Integer.toString(runeEntry.getValue()), StringUtil.getLocaleString(runeEntry.getKey().getUnlocalizedName() + ".name")));
		}
	}
}
