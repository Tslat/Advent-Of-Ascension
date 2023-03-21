package net.tslat.aoa3.util;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.tslat.smartbrainlib.util.RandomUtil;

public final class PositionAndMotionUtil {
	public static Vec3 accountForGravity(Vec3 origin, Vec3 velocity, Vec3 targetPos, double gravity) {
		double latDist = targetPos.subtract(origin).horizontalDistance();
		double ticksToTravel = latDist / velocity.horizontalDistance();

		return velocity.add(0, 0.5 * gravity * Math.ceil(ticksToTravel), 0);
	}

	public static void turnToFace(Entity entity, Vec3 targetPosition) {
		Vec3 position = entity.getEyePosition();
		double lengthX = targetPosition.x() - position.x();
		double lengthY = targetPosition.y() - position.y();
		double lengthZ = targetPosition.z() - position.z();

		entity.setXRot((float)Math.toDegrees(Mth.atan2(lengthZ, lengthX)) - 90f);
		entity.setYRot((float)Math.toDegrees(-Mth.atan2(lengthY, Math.sqrt(lengthX * lengthX + lengthZ * lengthZ))));

		entity.xRotO = entity.getXRot();
		entity.yRotO = entity.getYRot();
	}

	public static void faceTowardsMotion(Entity entity) {
		Vec3 velocity = entity.getDeltaMovement();

		entity.setYRot((float)Math.toDegrees(Mth.atan2(velocity.x(), velocity.z())));
		entity.setXRot((float)Math.toDegrees(Mth.atan2(velocity.y(), velocity.horizontalDistance())));

		entity.yRotO = entity.getYRot();
		entity.xRotO = entity.getXRot();
	}

	public static void moveTowards(Entity entity, Vec3 targetPosition, double blocksPerSecond, double inaccuracy) {
		moveTowards(entity, targetPosition, blocksPerSecond, 0.0075f * inaccuracy, 0.0075f * inaccuracy);
	}

	public static void moveTowards(Entity entity, Vec3 targetPosition, double blocksPerSecond, double lateralVariance, double verticalVariance) {
		Vec3 origin = entity.position();
		Vec3 velocity = new Vec3(targetPosition.x() - origin.x(), targetPosition.y() - origin.y(), targetPosition.z() - origin.z()).normalize();

		if (lateralVariance != 0 || verticalVariance != 0)
			velocity = velocity.add(RandomUtil.randomScaledGaussianValue(lateralVariance), RandomUtil.randomScaledGaussianValue(verticalVariance), RandomUtil.randomScaledGaussianValue(lateralVariance));

		entity.setDeltaMovement(velocity.scale(blocksPerSecond));
	}

	public static void moveRelativeToFacing(Entity entity, double moveLeft, double moveForward, double moveUp) {
		entity.setPos(moveRelativeToFacing(entity.position(), entity.getYRot(), moveLeft, moveForward, moveUp));
	}

	public static Vec3 moveRelativeToFacing(Vec3 position, float yRot, double moveLeft, double moveForward, double moveUp) {
		double x = 0;
		double z = 0;

		if (moveLeft != 0) {
			double radians = Math.toRadians(yRot - 180);
			x += moveLeft * -Math.cos(radians);
			z += moveLeft * -Math.sin(radians);
		}

		if (moveForward != 0) {
			double radians = Math.toRadians(yRot - 90);
			x += moveForward * -Math.cos(radians);
			z += moveForward * -Math.sin(radians);
		}


		return position.add(x, moveUp, z);
	}
}
