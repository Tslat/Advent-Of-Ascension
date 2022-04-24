package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.PlayerUtil;

public final class NowhereEvents {
	public static void doPlayerTick(final TickEvent.PlayerTickEvent ev) {
		PlayerEntity pl = ev.player;

		pl.abilities.mayBuild = pl.isCreative();

		if (PlayerUtil.shouldPlayerBeAffected(pl)) {
			if (pl.getY() < 0) {
				pl.setPos(0, 202, 0);
				pl.fallDistance = -1;

				if (pl instanceof ServerPlayerEntity)
					PlayerUtil.getAdventPlayer((ServerPlayerEntity)pl).returnItemStorage();
			}

			if (pl.isFallFlying())
				pl.stopFallFlying();
		}
	}

	public static void doDimensionChange(final PlayerEvent.PlayerChangedDimensionEvent ev) {
		ServerPlayerEntity player = (ServerPlayerEntity)ev.getPlayer();

		ItemUtil.clearInventoryOfItems(player, new ItemStack(AoAItems.PROGRESS_TOKEN.get()), new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
		PlayerUtil.getAdventPlayer(player).returnItemStorage();
		player.gameMode.getGameModeForPlayer().updatePlayerAbilities(player.abilities);
	}
}
