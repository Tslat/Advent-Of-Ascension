package net.tslat.aoa3.content.item.misc;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.event.dimension.NowhereEvents;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.*;

import java.util.List;
import java.util.Set;

public class ReturnCrystal extends Item {
	public ReturnCrystal() {
		super(new Item.Properties());
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.SPYGLASS;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity user) {
		return 20;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if (WorldUtil.isWorld(world, AoADimensions.NOWHERE))
			return ItemUtils.startUsingInstantly(world, player, hand);

		return InteractionResultHolder.pass(player.getItemInHand(hand));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		if (entity instanceof ServerPlayer pl) {
			if (!WorldUtil.isWorld(world, AoADimensions.NOWHERE)) {
				PlayerUtil.notifyPlayer(pl, LocaleUtil.getLocaleMessage("message.feedback.item.returnCrystal.wrongDim"));

				return stack;
			}

			if (!pl.isCreative())
				stack.shrink(1);

			if (!NowhereEvents.isInLobbyRegion(pl.blockPosition())) {
				if (NowhereEvents.isInBossRegion(pl.blockPosition()) && !AdvancementUtil.isAdvancementCompleted(pl, AdventOfAscension.id("nowhere/root"))) {
					AoAScheduler.schedule(1, tick -> {
						PlayerUtil.resetToDefaultStatus(pl);
						pl.placePortalTicket(pl.blockPosition());
						pl.teleportTo(pl.serverLevel(), 17.5d, 452.5d, 3.5d, Set.of(), 0, pl.getXRot());
						InventoryUtil.clearItems(pl, AoAItems.RETURN_CRYSTAL);
						PlayerUtil.getAdventPlayer(pl).storage.returnStoredItems();
					});
				}
				else {
					AoAScheduler.schedule(1, tick -> {
						PlayerUtil.resetToDefaultStatus(pl);
						pl.placePortalTicket(pl.blockPosition());
						pl.teleportTo(pl.serverLevel(), 16.5d, 1501.5d, 16.5d, Set.of(), 180, pl.getXRot());
						InventoryUtil.clearItems(pl, AoAItems.RETURN_CRYSTAL);
						PlayerUtil.getAdventPlayer(pl).storage.returnStoredItems();
					});
				}
			}
		}

		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
