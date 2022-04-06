package net.tslat.aoa3.content.item.weapon.blaster;

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
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.content.entity.projectile.blaster.SoulSparkEntity;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

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
		shooter.level.addFreshEntity(new SoulSparkEntity(shooter, this, 5));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		if (!EntityUtil.isImmuneToSpecialAttacks(target, shooter)) {
			if (shooter instanceof ServerPlayerEntity && !((ServerPlayerEntity)shooter).isCreative()) {
				ServerPlayerEntity player = (ServerPlayerEntity)shooter;
				AoAResource.Instance spirit = PlayerUtil.getResource(player, AoAResources.SPIRIT.get());

				if (!spirit.hasAmount(200)) {
					PlayerUtil.notifyPlayerOfInsufficientResources(player, AoAResources.SPIRIT.get(), 200);

					return false;
				}

				spirit.consume(200, false);

				Hand hand = player.getUsedItemHand();
				ItemStack stack =  player.getItemInHand(hand);

				if (stack.getItem() != this)
					stack = player.getItemInHand(hand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND);

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
		if (!player.level.isClientSide) {
			if (count + firingDelay <= 72000 && count % firingDelay == 0) {
				if (getFiringSound() != null)
					player.level.playSound(null, player.getX(), player.getY(), player.getZ(), getFiringSound(), SoundCategory.PLAYERS, 1.0f, 1.0f);

				fire(stack, player);
				((PlayerEntity)player).awardStat(Stats.ITEM_USED.get(this));
			}
		}
	}

	@Override
	public void releaseUsing(ItemStack stack, World world, LivingEntity player, int useTicksRemaining) {}


	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.SPEC_IMMUNE, LocaleUtil.ItemDescriptionType.HARMFUL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.AMMO_RESOURCE, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, new StringTextComponent("200"), AoAResources.SPIRIT.get().getName()));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.blaster.fire", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.blaster.effect", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.FIRING_SPEED, LocaleUtil.ItemDescriptionType.NEUTRAL, new StringTextComponent(Double.toString((2000 / firingDelay) / 100d))));
	}
}
