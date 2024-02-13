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
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ReturnCrystal extends Item {
	public ReturnCrystal() {
		super(new Item.Properties());
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.SPYGLASS;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
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
				if (NowhereEvents.isInBossRegion(pl.blockPosition()) && !pl.getAdvancements().getOrStartProgress(AdvancementUtil.getAdvancement(pl.serverLevel(), AdventOfAscension.id("nowhere/root"))).isDone()) {
					AoAScheduler.scheduleSyncronisedTask(() -> {
						PlayerUtil.resetToDefaultStatus(pl);
						pl.connection.teleport(17.5d, 452.5d, 3.5d, 0, pl.getXRot());
						ItemUtil.clearInventoryOfItems(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
						PlayerUtil.getAdventPlayer(pl).returnItemStorage();
					}, 1);
				}
				else {
					AoAScheduler.scheduleSyncronisedTask(() -> {
						PlayerUtil.resetToDefaultStatus(pl);
						pl.connection.teleport(16.5d, 1501.5d, 16.5d, 180, pl.getXRot());
						ItemUtil.clearInventoryOfItems(pl, new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
						PlayerUtil.getAdventPlayer(pl).returnItemStorage();
					}, 1);
				}
			}
		}

		return stack;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
