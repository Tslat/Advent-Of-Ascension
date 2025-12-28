package net.tslat.aoa3.content.item.tool.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.content.entity.misc.HaulingFishingBobberEntity;
import net.tslat.aoa3.content.entity.misc.ThermalFishingBobberEntity;
import net.tslat.aoa3.event.custom.AoAEvents;
import net.tslat.aoa3.event.custom.events.HaulingItemFishedEvent;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;

public class ThermallyInsulatedRod extends HaulingRod {
	public ThermallyInsulatedRod(Properties itemProperties) {
		super(itemProperties);
	}

	@Override
	protected void reelIn(Player player, HaulingFishingBobberEntity bobber, ItemStack stack, InteractionHand hand) {
		if (bobber.distanceToSqr(player) <= 9) {
			if (player instanceof ServerPlayer pl) {
				List<ItemStack> loot = landEntity(pl, stack, hand, bobber);
				int xp = RandomUtil.numberBetween(1, 6);
				HaulingItemFishedEvent event = AoAEvents.fireHaulingItemFished(bobber.getHookedIn(), stack, loot, xp, 1, bobber);

				if (!event.isCanceled()) {
					handleLureRetrieval(pl, stack, bobber, loot);

					for (ItemStack lootStack : loot) {
						ItemEntity entity = new ItemEntity(pl.level(), bobber.getX(), bobber.getY(), bobber.getZ(), lootStack) {
							@Override
							public boolean isInvulnerableTo(DamageSource source) {
								return source.is(DamageTypeTags.IS_FIRE) || super.isInvulnerableTo(source);
							}
						};

						double velX = pl.getX() - bobber.getX();
						double velY = pl.getY() - bobber.getY();
						double velZ = pl.getZ() - bobber.getZ();

						entity.setDeltaMovement(velX * 0.1d, velY * 0.1d + Math.sqrt(Math.sqrt(velX * velX + velY * velY + velZ * velZ)) * 0.1d, velZ * 0.1d);
						pl.level().addFreshEntity(entity);

						if (lootStack.is(ItemTags.FISHES))
							pl.awardStat(Stats.FISH_CAUGHT, 1);
					}
				}

				ItemUtil.damageItemForUser(pl, stack, event.getRodDamage(), hand);
				pl.level().addFreshEntity(new ExperienceOrb(pl.level(), pl.getX() + 0.5d, pl.getY() + 0.5d, pl.getZ() + 0.5d, event.getXp()));
				bobber.discard();
			}
		}
		else {
			Entity hookedEntity = bobber.getHookedIn();

			if (hookedEntity != null) {
				float pullStrength = getHaulStrengthMod(player, stack, bobber);

				EntityUtil.pullEntityIn(player, hookedEntity, 0.25f * pullStrength, true);

				hookedEntity.setDeltaMovement(hookedEntity.getDeltaMovement().multiply(1, 0.5f, 1));

				if (!player.onGround() && bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_IN_ENTITY)
					EntityUtil.pullEntityIn(hookedEntity, player, 0.25f * pullStrength, true);
			}
		}
	}

	@Override
	protected HaulingFishingBobberEntity getNewBobber(Player player, ItemStack stack, float lureMod, float luckMod) {
		return new ThermalFishingBobberEntity(player, player.level(), stack, luckMod, lureMod);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
