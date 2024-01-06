package net.tslat.aoa3.library.object;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public record PositionAndRotation(double x, double y, double z, float pitch, float yaw) {
	public PositionAndRotation(Vec3 position, float pitch, float yaw) {
		this(position.x, position.y, position.z, pitch, yaw);
	}

	public BlockPos asBlockPos() {
		return BlockPos.containing(this.x, this.y, this.z);
	}

	public void applyToEntity(Entity entity) {
		if (entity instanceof ServerPlayer pl) {
			pl.connection.teleport(this.x, this.y, this.z, this.yaw, this.pitch);
		}
		else {
			entity.setDeltaMovement(Vec3.ZERO);
			entity.setYRot(this.yaw);
			entity.setXRot(this.pitch);
			entity.moveTo(this.x, this.y, this.z);
		}
	}

	public Vec3 position() {
		return new Vec3(this.x, this.y, this.z);
	}

	public static PositionAndRotation from(BlockPos pos, Entity entity) {
		return new PositionAndRotation(pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d, entity.getXRot(), entity.getYHeadRot());
	}
}
