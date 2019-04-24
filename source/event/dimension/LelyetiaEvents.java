package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.EntityPlayer;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;

public class LelyetiaEvents {
	public static void doPlayerTick(AdventPlayerCapability cap) {
		EntityPlayer pl = cap.getPlayer();

		if (!pl.capabilities.isCreativeMode && pl.posY < 54) {
			pl.fallDistance = -0.5f;

			if (pl.motionY < 0)
				pl.motionY *= 0.800000011920929;
		}
	}
}
