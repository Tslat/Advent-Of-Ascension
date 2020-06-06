package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.EntityPlayer;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class LelyetiaEvents {
	public static void doPlayerTick(EntityPlayer pl) {
		if (pl.posY < 54) {
			if (PlayerUtil.shouldPlayerBeAffected(pl)) {
				pl.fallDistance = -0.5f;

				if (ItemUtil.getStackFromHotbar(pl, ItemRegister.DISTORTING_ARTIFACT) == null) {
					if (pl.motionY < 0)
						pl.setNoGravity(true);

					pl.motionY += 0.05d;
					pl.velocityChanged = true;
				}
				else if (pl.motionY < 0) {
					pl.motionY *= 0.75;
				}
				else if (pl.motionY == 0) {
					pl.motionY += 0.5f;
				}
			}
		}
		else if (pl.posY > 57) {
			if (pl.motionY > 0 || pl.hasNoGravity()) {
				pl.setNoGravity(false);
			}
		}
		else if (pl.motionY < 0.2 && pl.motionY > -0.2) {
			pl.setNoGravity(false);
		}
	}
}
