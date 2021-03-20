package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.PlayerEntity;
import net.tslat.aoa3.util.player.PlayerUtil;

public class LelyetiaEvents {
	public static void doPlayerTick(PlayerEntity pl) {
		if (pl.getY() <= -25 && PlayerUtil.shouldPlayerBeAffected(pl))
			pl.teleportTo(pl.getX(), pl.level.getMaxBuildHeight(), pl.getZ());
	}
}
