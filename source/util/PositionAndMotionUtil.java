package net.tslat.aoa3.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.*;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Predicate;

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

	public static boolean isNonVoidPosition(Level level, Vec3 pos) {
		int x = (int)Math.floor(pos.x);
		int z = (int)Math.floor(pos.z);

		if (x < -30000000 || z < -30000000 || x >= 30000000 || z >= 30000000)
			return false;

		return level.getHeight(Heightmap.Types.MOTION_BLOCKING, x, z) > level.getMinBuildHeight();
	}

	public static Vec3 moveDownToGround(Level level, Vec3 pos) {
		BlockPos.MutableBlockPos testPos = new BlockPos.MutableBlockPos(pos.x, pos.y, pos.z);
		LevelChunk chunk = level.getChunk(SectionPos.blockToSectionCoord(pos.x), SectionPos.blockToSectionCoord(pos.z));

		while (!chunk.getBlockState(testPos.move(Direction.DOWN)).blocksMotion() && testPos.getY() > level.getMinBuildHeight()) {}

		return new Vec3(pos.x, testPos.getY(), pos.z);
	}

	public static Vec3 moveUpToSurface(Level level, Vec3 pos) {
		BlockPos.MutableBlockPos testPos = new BlockPos.MutableBlockPos(pos.x, pos.y, pos.z);
		LevelChunk chunk = level.getChunk(SectionPos.blockToSectionCoord(pos.x), SectionPos.blockToSectionCoord(pos.z));

		while (chunk.getBlockState(testPos.move(Direction.UP)).blocksMotion() && testPos.getY() < level.getMaxBuildHeight()) {}

		return new Vec3(pos.x, testPos.getY(), pos.z);
	}

	public static Optional<Vec3> getNearestOnGroundPosition(Level level, Vec3 pos) {
		if (!isNonVoidPosition(level, pos))
			return Optional.empty();

		pos = moveDownToGround(level, pos);
		pos = moveUpToSurface(level, pos);

		return Optional.of(pos);
	}

	public static HitResult rayTrace(Entity entity, double distance, boolean doEntities, @Nullable Predicate<Entity> entityFilter) {
		return rayTrace(entity.level(), entity.getEyePosition(), entity.getEyePosition().add(entity.getLookAngle().scale(distance)), ClipContext.Block.COLLIDER, false, doEntities, entityFilter);
	}

	public static HitResult rayTrace(Level level, Vec3 start, Vec3 end, ClipContext.Block blockhitType, boolean includeFluids, boolean doEntities, @Nullable Predicate<Entity> entityFilter) {
		BlockHitResult blockHitResult = level.clip(new ClipContext(start, end, blockhitType, includeFluids ? ClipContext.Fluid.ANY : ClipContext.Fluid.NONE, null));

		if (!doEntities)
			return blockHitResult;

		if (blockHitResult.getType() == HitResult.Type.BLOCK)
			end = blockHitResult.getLocation();

		double distance = start.distanceTo(end);
		distance *= distance;
		Entity closestTarget = null;
		Vec3 clipPos = null;

		for (Entity entity : level.getEntities((Entity)null, new AABB(start, end).inflate(1), entityFilter == null ? entity -> true : entityFilter)) {
			AABB entityPickBounds = entity.getBoundingBox().inflate(entity.getPickRadius());

			if (entityPickBounds.contains(start)) {
				if (distance > 0) {
					closestTarget = entity;
					distance = 0;
					clipPos = entityPickBounds.clip(start, end).orElse(start);
				}
			}
			else {
				Optional<Vec3> entityClip = entityPickBounds.clip(start, end);

				if (entityClip.isPresent()) {
					Vec3 clipPoint = entityClip.get();
					double entityDist = start.distanceToSqr(clipPoint);

					if (entityDist < distance || distance == 0) {
						closestTarget = entity;
						clipPos = clipPoint;
						distance = entityDist;
					}
				}
			}
		}

		return closestTarget != null ? new EntityHitResult(closestTarget, clipPos) : blockHitResult;
	}
}
