package net.tslat.aoa3.content.item.tool.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.content.entity.misc.HaulingFishingBobberEntity;
import net.tslat.aoa3.content.entity.misc.ThermalFishingBobberEntity;
import net.tslat.aoa3.event.custom.AoAEvents;
import net.tslat.aoa3.event.custom.events.HaulingItemFishedEvent;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ThermallyInsulatedRod extends HaulingRod {
	public ThermallyInsulatedRod(Properties itemProperties) {
		super(itemProperties);
	}

	@Override
	protected void reelIn(ServerPlayer player, HaulingFishingBobberEntity bobber, ItemStack stack, InteractionHand hand) {
		if (bobber.distanceToSqr(player) <= 9) {
			List<ItemStack> loot = landEntity(player, stack, hand, bobber);
			int xp = RandomUtil.randomNumberBetween(1, 6);
			HaulingItemFishedEvent event = AoAEvents.haulingItemFished(bobber.getHookedIn(), stack, loot, xp, 1, bobber);

			if (!event.isCanceled()) {
				handleLureRetrieval(player, stack, bobber, loot);

				for (ItemStack lootStack : loot) {
					ItemEntity entity = new ItemEntity(player.level, bobber.getX(), bobber.getY(), bobber.getZ(), lootStack) {
						@Override
						public boolean isInvulnerableTo(DamageSource source) {
							return source == DamageSource.LAVA || source == DamageSource.ON_FIRE && super.isInvulnerableTo(source);
						}
					};

					double velX = player.getX() - bobber.getX();
					double velY = player.getY() - bobber.getY();
					double velZ = player.getZ() - bobber.getZ();

					entity.setDeltaMovement(velX * 0.1d, velY * 0.1d + Math.sqrt(Math.sqrt(velX * velX + velY * velY + velZ * velZ)) * 0.1d, velZ * 0.1d);
					player.level.addFreshEntity(entity);

					if (lootStack.is(ItemTags.FISHES))
						player.awardStat(Stats.FISH_CAUGHT, 1);
				}
			}

			if (!player.isCreative())
				ItemUtil.damageItem(stack, player, hand, event.getRodDamage());

			player.level.addFreshEntity(new ExperienceOrb(player.level, player.getX() + 0.5d, player.getY() + 0.5d, player.getZ() + 0.5d, event.getXp()));
			bobber.discard();
		}
		else {
			Entity hookedEntity = bobber.getHookedIn();

			if (hookedEntity != null) {
				float pullStrength = getHaulStrengthMod(player, stack, bobber);

				EntityUtil.pullEntityIn(player, hookedEntity, 0.25f * pullStrength, true);

				hookedEntity.setDeltaMovement(hookedEntity.getDeltaMovement().multiply(1, 0.5f, 1));

				if (!player.isOnGround() && bobber.getState() == HaulingFishingBobberEntity.State.HOOKED_IN_ENTITY)
					EntityUtil.pullEntityIn(hookedEntity, player, 0.25f * pullStrength, true);
			}
		}
	}

	@Override
	protected HaulingFishingBobberEntity getNewBobber(Player player, ItemStack stack, int lureMod, int luckMod) {
		return new ThermalFishingBobberEntity(player, player.level, stack, luckMod, lureMod);
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
