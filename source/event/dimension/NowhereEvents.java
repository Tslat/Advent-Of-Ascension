package net.tslat.aoa3.event.dimension;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.PlayerUtil;

public final class NowhereEvents {
	public static void doPlayerTick(final TickEvent.PlayerTickEvent ev) {
		Player pl = ev.player;

		pl.getAbilities().mayBuild = pl.isCreative();

		if (PlayerUtil.shouldPlayerBeAffected(pl)) {
			if (pl.getY() < 0) {
				pl.setPos(0, 202, 0);
				pl.fallDistance = -1;

				if (pl instanceof ServerPlayer)
					PlayerUtil.getAdventPlayer((ServerPlayer)pl).returnItemStorage();
			}

			if (pl.isFallFlying())
				pl.stopFallFlying();
		}
	}

	public static void doDimensionChange(final PlayerEvent.PlayerChangedDimensionEvent ev) {
		ItemUtil.clearInventoryOfItems(ev.getPlayer(), new ItemStack(AoAItems.PROGRESS_TOKEN.get()), new ItemStack(AoAItems.RETURN_CRYSTAL.get()));
		PlayerUtil.getAdventPlayer((ServerPlayer)ev.getPlayer()).returnItemStorage();
	}
}
