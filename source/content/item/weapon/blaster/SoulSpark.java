package net.tslat.aoa3.content.item.weapon.blaster;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
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
			if (shooter instanceof ServerPlayer && !((ServerPlayer)shooter).isCreative()) {
				ServerPlayer player = (ServerPlayer)shooter;
				AoAResource.Instance spirit = PlayerUtil.getResource(player, AoAResources.SPIRIT.get());

				if (!spirit.hasAmount(200)) {
					PlayerUtil.notifyPlayerOfInsufficientResources(player, AoAResources.SPIRIT.get(), 200);

					return false;
				}

				spirit.consume(200, false);

				InteractionHand hand = player.getUsedItemHand();
				ItemStack stack =  player.getItemInHand(hand);

				if (stack.getItem() != this)
					stack = player.getItemInHand(hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);

				if (stack.getItem() != this)
					return false;

				ItemUtil.damageItem(stack, shooter, hand);
			}

			target.discard();

			return true;
		}

		return false;
	}

	@Override
	public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
		if (!player.level.isClientSide) {
			if (count + firingDelay <= 72000 && count % firingDelay == 0) {
				if (getFiringSound() != null)
					player.level.playSound(null, player.getX(), player.getY(), player.getZ(), getFiringSound(), SoundSource.PLAYERS, 1.0f, 1.0f);

				fire(stack, player);
				((Player)player).awardStat(Stats.ITEM_USED.get(this));
			}
		}
	}

	@Override
	public void releaseUsing(ItemStack stack, Level world, LivingEntity player, int useTicksRemaining) {}


	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.SPEC_IMMUNE, LocaleUtil.ItemDescriptionType.HARMFUL));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.AMMO_RESOURCE, LocaleUtil.ItemDescriptionType.ITEM_AMMO_COST, new TextComponent("200"), AoAResources.SPIRIT.get().getName()));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.blaster.fire", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.blaster.effect", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.FIRING_SPEED, LocaleUtil.ItemDescriptionType.NEUTRAL, new TextComponent(Double.toString((2000 / firingDelay) / 100d))));
	}
}
