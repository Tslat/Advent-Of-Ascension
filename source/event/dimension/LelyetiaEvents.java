package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

public class LelyetiaEvents {
	public static void doPlayerTick(PlayerEntity pl) {
		if (pl.getPosY() < 54) {
			if (PlayerUtil.shouldPlayerBeAffected(pl)) {
				pl.fallDistance = -0.5f;
				Vec3d motion = pl.getMotion();

				if (ItemUtil.getStackFromHotbar(pl, AoAItems.DISTORTING_ARTIFACT.get()) == null) {
					if (motion.getY() < 0)
						pl.setNoGravity(true);

					pl.setMotion(motion.getX(), motion.getY() + 0.05d, motion.getZ());
					pl.velocityChanged = true;
				}
				else if (motion.getY() < 0) {
					pl.setMotion(motion.getX(), motion.getY() * 0.75f, motion.getZ());
				}
				else if (motion.getY() == 0) {
					pl.setMotion(motion.getX(), motion.getY() + 0.5f, motion.getZ());
				}
			}
		}
		else if (pl.getPosY() > 57) {
			if (pl.getMotion().getY() > 0 || pl.hasNoGravity()) {
				pl.setNoGravity(false);
			}
		}
		else if (pl.getMotion().getY() < 0.2 && pl.getMotion().getY() > -0.2) {
			pl.setNoGravity(false);
		}
	}
}
