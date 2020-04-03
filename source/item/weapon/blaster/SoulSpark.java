package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.EnchantmentsRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.projectiles.blaster.EntitySoulSpark;
import net.tslat.aoa3.entity.projectiles.staff.BaseEnergyShot;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class SoulSpark extends BaseBlaster {
	public SoulSpark(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
		setTranslationKey("SoulSpark");
		setRegistryName("aoa3:soul_spark");
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return SoundsRegister.gunSoulSpark;
	}

	@Override
	public void fire(ItemStack blaster, EntityLivingBase shooter) {
		shooter.world.spawnEntity(new EntitySoulSpark(shooter, this, 5));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, EntityLivingBase shooter) {
		if (!EntityUtil.isSpecExempt(target, shooter) && !EntityUtil.isTypeImmune(target, Enums.MobProperties.BLASTER_IMMUNE)) {
			if (shooter instanceof EntityPlayerMP && !((EntityPlayerMP)shooter).capabilities.isCreativeMode) {
				EntityPlayer player = (EntityPlayer)shooter;
				PlayerDataManager.PlayerStats stats = PlayerUtil.getAdventPlayer(player).stats();

				if (stats.getResourceValue(Enums.Resources.ENERGY) < 200) {
					PlayerUtil.notifyPlayerOfInsufficientResources((EntityPlayerMP)player, Enums.Resources.ENERGY, 200);

					return false;
				}

				if (stats.getResourceValue(Enums.Resources.SOUL) < 50) {
					PlayerUtil.notifyPlayerOfInsufficientResources((EntityPlayerMP)player, Enums.Resources.SOUL, 50);

					return false;
				}
				stats.consumeResource(Enums.Resources.ENERGY, 200, false);
				stats.consumeResource(Enums.Resources.SOUL, 50, false);

				EnumHand hand = player.getActiveHand();
				ItemStack stack =  player.getHeldItem(hand);

				if (stack.getItem() != this)
					stack = player.getHeldItem(hand == EnumHand.MAIN_HAND ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);

				if (stack.getItem() != this)
					return false;

				stack.damageItem(1, shooter);
			}

			target.setDead();

			return true;
		}

		return false;
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
		if (!player.world.isRemote) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer((EntityPlayer)player);
			int recharge = EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.recharge, stack);
			int greed = EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.greed, stack);
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
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase player, int useTicksRemaining) {}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.SoulSpark.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.damage.specImmune", Enums.ItemDescriptionType.NEGATIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.ammo.resource", Enums.ItemDescriptionType.ITEM_AMMO_COST, "50", StringUtil.getLocaleString("resources.soul.name")));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.blaster.fire", Enums.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.blaster.slowing", Enums.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.blaster.effect", Enums.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(StringUtil.getLocaleStringWithArguments("items.description.gun.speed", Double.toString((2000 / firingDelay) / 100d)));
	}
}
