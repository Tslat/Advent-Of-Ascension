package net.tslat.aoa3.event.dimension;

import net.minecraft.world.entity.player.Player;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.PlayerUtil;

public class LelyetiaEvents {
	public static void doPlayerTick(Player pl) {
		if (ItemUtil.hasItemInHotbar(pl, AoAItems.DISTORTING_ARTIFACT.get()) || ItemUtil.hasItemInOffhand(pl, AoAItems.DISTORTING_ARTIFACT.get()))
			return;

		if (pl.getY() <= -25 && PlayerUtil.shouldPlayerBeAffected(pl))
			pl.teleportTo(pl.getX(), pl.level.getMaxBuildHeight(), pl.getZ());
	}
}
