package net.tslat.aoa3.event.dimension;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.block.functional.portal.NowhereActivityPortal;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.PlayerUtil;

public final class NowhereEvents {
	public static void doPlayerTick(final TickEvent.PlayerTickEvent ev) {
		Player pl = ev.player;

		if (ev.phase == TickEvent.Phase.START) {
			if (pl instanceof ServerPlayer serverPl) {
				if (pl.getX() > 10000 && pl.getZ() > 10000) {
					if (PlayerUtil.shouldPlayerBeAffected(serverPl))
						PlayerUtil.getAdventPlayer(serverPl).setInAbilityLockRegion();
				}
				else {
					PlayerUtil.getAdventPlayer(serverPl).leaveAbilityLockRegion();
				}
			}
		}
		else {
			pl.getAbilities().mayBuild = pl.isCreative();

			if (PlayerUtil.shouldPlayerBeAffected(pl)) {
				if (pl.getY() < pl.level.getMinBuildHeight()) {
					pl.fallDistance = -1;

					if (pl instanceof ServerPlayer)
						NowhereActivityPortal.Activity.RETURN.teleport((ServerPlayer)pl);
				}

				if (pl.isFallFlying())
					pl.stopFallFlying();

				if (pl.getX() > 0 && pl.getZ() > 0) {
					FoodData foodData = pl.getFoodData();

					foodData.setFoodLevel(20);
					foodData.setExhaustion(0);
					foodData.setSaturation(20);
				}
			}
		}
	}

	public static void doDimensionChange(final PlayerEvent.PlayerChangedDimensionEvent ev) {
		ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayer)ev.getEntity());

		plData.returnItemStorage();
		plData.clearCheckpoint();

		ItemUtil.clearInventoryOfItems(ev.getEntity(), new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
		((ServerPlayer)ev.getEntity()).gameMode.getGameModeForPlayer().updatePlayerAbilities(ev.getEntity().getAbilities());
	}

	public static void doDeathPrevention(final LivingDamageEvent ev, ServerPlayerDataManager plData) {
		NowhereActivityPortal.Activity.RETURN.teleport(plData.player());
	}
}
