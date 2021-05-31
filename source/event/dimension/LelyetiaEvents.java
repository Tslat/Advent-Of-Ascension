package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.PlayerEntity;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

public class LelyetiaEvents {
	public static void doPlayerTick(PlayerEntity pl) {
		if (ItemUtil.hasItemInHotbar(pl, AoAItems.DISTORTING_ARTIFACT.get()) || ItemUtil.hasItemInOffhand(pl, AoAItems.DISTORTING_ARTIFACT.get()))
			return;

		if (pl.getY() <= -25 && PlayerUtil.shouldPlayerBeAffected(pl))
			pl.teleportTo(pl.getX(), pl.level.getMaxBuildHeight(), pl.getZ());
	}
}
