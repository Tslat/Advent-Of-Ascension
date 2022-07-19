package net.tslat.aoa3.library.object;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public record PositionAndRotation(double x, double y, double z, float pitch, float yaw) {
	public BlockPos asBlockPos() {
		return new BlockPos(x, y, z);
	}

	public void applyToEntity(Entity entity) {
		entity.setDeltaMovement(Vec3.ZERO);
		entity.setYRot(yaw);
		entity.setXRot(pitch);
		entity.moveTo(x, y, z);
	}

	public static PositionAndRotation from(BlockPos pos, Entity entity) {
		return new PositionAndRotation(pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d, entity.getXRot(), entity.getYHeadRot());
	}
}
