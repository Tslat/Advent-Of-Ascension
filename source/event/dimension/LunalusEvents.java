package net.tslat.aoa3.event.dimension;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

public class LunalusEvents {
	public static void doPlayerTick(PlayerEntity pl) {
		boolean hasDistortingArtifact = ItemUtil.hasItemInHotbar(pl, AoAItems.DISTORTING_ARTIFACT.get()) || ItemUtil.hasItemInOffhand(pl, AoAItems.DISTORTING_ARTIFACT.get());

		if (!pl.level.isClientSide() && PlayerUtil.shouldPlayerBeAffected(pl)) {
			if (pl.getY() <= -25 && !hasDistortingArtifact)
				pl.teleportTo(pl.getX(), 350, pl.getZ());

			if (!DamageUtil.isPlayerEnvironmentallyProtected((ServerPlayerEntity)pl))
				pl.hurt(new DamageSource("suffocation").bypassArmor().bypassMagic(), 1f);
		}

		if (hasDistortingArtifact)
			return;

		if (pl.flyingSpeed < 0.05f)
			pl.flyingSpeed = Math.min(0.05f, pl.flyingSpeed + 0.05f);

		Vector3d motion = pl.getDeltaMovement();

		if (motion.y() < -0.01)
			pl.setDeltaMovement(motion.multiply(1, 0.85f, 1));
	}

	public static void doPlayerJump(PlayerEntity pl) {
		if (ItemUtil.getStackFromHotbar(pl, AoAItems.DISTORTING_ARTIFACT.get()) != null)
			return;

		pl.setDeltaMovement(pl.getDeltaMovement().add(0, 0.5f, 0));
	}

	public static void doPlayerLanding(PlayerEntity pl, LivingFallEvent ev) {
		if (ItemUtil.getStackFromHotbar(pl, AoAItems.DISTORTING_ARTIFACT.get()) != null && !ItemUtil.hasItemInOffhand(pl, AoAItems.DISTORTING_ARTIFACT.get()))
			return;

		ev.setDistance(Math.min(10, ev.getDistance()));
	}
}
