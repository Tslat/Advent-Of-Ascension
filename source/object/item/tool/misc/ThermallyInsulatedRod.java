package net.tslat.aoa3.object.item.tool.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.event.custom.AoAEvents;
import net.tslat.aoa3.event.custom.events.HaulingItemFishedEvent;
import net.tslat.aoa3.object.entity.misc.HaulingFishingBobberEntity;
import net.tslat.aoa3.object.entity.misc.ThermalFishingBobberEntity;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ThermallyInsulatedRod extends HaulingRod {
	public ThermallyInsulatedRod(Properties itemProperties) {
		super(itemProperties);
	}

	@Override
	protected void reelIn(ServerPlayerEntity player, HaulingFishingBobberEntity bobber, ItemStack stack, Hand hand) {
		if (bobber.distanceToSqr(player) <= 9) {
			List<ItemStack> loot = landEntity(player, stack, hand, bobber);
			int xp = random.nextInt(6) + 1;
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

					if (lootStack.getItem().is(ItemTags.FISHES))
						player.awardStat(Stats.FISH_CAUGHT, 1);
				}
			}

			if (!player.isCreative())
				ItemUtil.damageItem(stack, player, hand, event.getRodDamage());

			player.level.addFreshEntity(new ExperienceOrbEntity(player.level, player.getX() + 0.5d, player.getY() + 0.5d, player.getZ() + 0.5d, event.getXp()));
			bobber.remove();
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
	protected HaulingFishingBobberEntity getNewBobber(PlayerEntity player, ItemStack stack, int lureMod, int luckMod) {
		return new ThermalFishingBobberEntity(player, player.level, stack, luckMod, lureMod);
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable World pLevel, List<ITextComponent> tooltip, ITooltipFlag pFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.GENERAL_INFO, 1));
	}
}
