package net.tslat.aoa3.item.weapon.blaster;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.blaster.SoulSparkEntity;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class SoulSpark extends BaseBlaster {
	public SoulSpark(double dmg, int durability, int fireDelayTicks, float energyCost) {
		super(dmg, durability, fireDelayTicks, energyCost);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_SOUL_SPARK_FIRE.get();
	}

	@Override
	public void fire(ItemStack blaster, LivingEntity shooter) {
		shooter.world.addEntity(new SoulSparkEntity(shooter, this, 5));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		if (!EntityUtil.isImmuneToSpecialAttacks(target, shooter)) {
			if (shooter instanceof ServerPlayerEntity && !((ServerPlayerEntity)shooter).isCreative()) {
				ServerPlayerEntity player = (ServerPlayerEntity)shooter;
				PlayerDataManager.PlayerStats stats = PlayerUtil.getAdventPlayer(player).stats();

				if (stats.getResourceValue(Resources.ENERGY) < 200) {
					PlayerUtil.notifyPlayerOfInsufficientResources((ServerPlayerEntity)player, Resources.ENERGY, 200);

					return false;
				}

				if (stats.getResourceValue(Resources.SOUL) < 50) {
					PlayerUtil.notifyPlayerOfInsufficientResources((ServerPlayerEntity)player, Resources.SOUL, 50);

					return false;
				}
				stats.consumeResource(Resources.ENERGY, 200, false);
				stats.consumeResource(Resources.SOUL, 50, false);

				Hand hand = player.getActiveHand();
				ItemStack stack =  player.getHeldItem(hand);

				if (stack.getItem() != this)
					stack = player.getHeldItem(hand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND);

				if (stack.getItem() != this)
					return false;

				ItemUtil.damageItem(stack, shooter, hand);
			}

			target.remove();

			return true;
		}

		return false;
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
		if (!player.world.isRemote) {
			if (count + firingDelay <= 72000 && count % firingDelay == 0) {
				if (getFiringSound() != null)
					player.world.playSound(null, player.getPosX(), player.getPosY(), player.getPosZ(), getFiringSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);

				fire(stack, player);
				((PlayerEntity)player).addStat(Stats.ITEM_USED.get(this));
			}
		}
	}

	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, LivingEntity player, int useTicksRemaining) {}


	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.SPEC_IMMUNE, LocaleUtil.ItemDescriptionType.HARMFUL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.AMMO_RESOURCE, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, "200", LocaleUtil.getLocaleString(LocaleUtil.Constants.ENERGY_RESOURCE)));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.AMMO_RESOURCE, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, "50", LocaleUtil.getLocaleString(LocaleUtil.Constants.SOUL_RESOURCE)));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.blaster.fire", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.blaster.slowing", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.blaster.effect", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.FIRING_SPEED, LocaleUtil.ItemDescriptionType.NEUTRAL, Double.toString((2000 / firingDelay) / 100d)));
	}
}
