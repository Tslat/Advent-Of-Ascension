package net.tslat.aoa3.util;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

/**
 * You're welcome to copy/use this code in its entirety as needed!<br>
 * Just credit me (Tslat) if you do :)
 */
public final class MathUtil {
	public static Iterable<Vec3> inLateralCircle(Vec3 center, double radius, double granularity) {
		return new Iterable<>() {
			@NotNull
			@Override
			public Iterator<Vec3> iterator() {
				return new Iterator<>() {
					private final double increment = Mth.TWO_PI / granularity;
					private double theta = 0;

					@Override
					public boolean hasNext() {
						return this.theta < Mth.TWO_PI;
					}

					@Override
					public Vec3 next() {
						double angle = this.theta;
						this.theta += this.increment;

						return center.add(Math.cos(angle) * radius, 0, Math.sin(angle) * radius);
					}
				};
			}
		};
	}

	public static Iterable<Vec3> inCircleAtAngle(Vec3 center, Vec3 angle, double radius, double granularity) {
		return new Iterable<>() {
			@NotNull
			@Override
			public Iterator<Vec3> iterator() {
				return new Iterator<>() {
					private final double increment = Mth.TWO_PI / granularity;
					private final double yawLength = angle.horizontalDistance();
					private double theta = 0;

					@Override
					public boolean hasNext() {
						return this.theta < Mth.TWO_PI;
					}

					@Override
					public Vec3 next() {
						double circleAngle = this.theta;
						this.theta += this.increment;

						double yaw = Math.cos(circleAngle) * radius;
						double pitch = Math.sin(circleAngle) * radius;

						return center.add(
								(yaw * (Math.sinh(-angle.z) / this.yawLength)) - (pitch * angle.y * (angle.x / this.yawLength)),
								pitch * this.yawLength,
								(yaw * (Math.sinh(angle.x) / this.yawLength)) - (pitch * angle.y * (angle.z / this.yawLength)));
					}
				};
			}
		};
	}

	public static Iterable<Vec3> inLine(Vec3 start, Vec3 end, double granularity) {
		return new Iterable<>() {
			@NotNull
			@Override
			public Iterator<Vec3> iterator() {
				return new Iterator<>() {
					private final Vec3 angle = start.vectorTo(end).normalize();
					private final double length = start.distanceTo(end);
					private final double increment = this.length / granularity;
					private double step = 0;

					@Override
					public boolean hasNext() {
						return this.step <= this.length;
					}

					@Override
					public Vec3 next() {
						double scale = this.step;
						this.step += this.increment;

						return start.add(this.angle.multiply(scale, scale, scale));
					}
				};
			}
		};
	}

	public static Iterable<Vec3> inSphere(Vec3 center, double radius, double granularity) {
		return new Iterable<>() {
			@NotNull
			@Override
			public Iterator<Vec3> iterator() {
				return new Iterator<>() {
					private final double increment = Mth.PI / granularity;
					private double theta = -Mth.HALF_PI;
					private double phi = 0;

					@Override
					public boolean hasNext() {
						return this.theta < Mth.HALF_PI || this.phi < Mth.TWO_PI;
					}

					@Override
					public Vec3 next() {
						double currentTheta = this.theta;
						double currentPhi = this.phi;

						if (this.phi >= Mth.TWO_PI) {
							this.theta += this.increment;
							this.phi = 0;
						}

						this.phi += this.increment;

						return center.add(Math.cos(currentTheta) * Math.sin(currentPhi) * radius, Math.cos(currentTheta) * Math.cos(currentPhi) * radius, Math.sin(currentTheta) * radius);
					}
				};
			}
		};
	}

	public static Vec3 getPositionRelativeToAngle(Vec3 position, Vec3 relativePosition, Vec3 angle) {
		final double angleLateralLength = angle.horizontalDistance();

		return position.add(new Vec3(relativePosition.x, relativePosition.y, relativePosition.x)
				.multiply(Math.sinh(-angle.z) / angleLateralLength, angleLateralLength, Math.sinh(angle.x) / angleLateralLength)
				.subtract(relativePosition.y * angle.y * (angle.x / angleLateralLength), 0, relativePosition.y * angle.y * (angle.z / angleLateralLength))
				.add(angle.scale(relativePosition.z)));
	}

	public static Vec3 clampVec(Vec3 vec, Vec3 min, Vec3 max) {
		return new Vec3(Mth.clamp(vec.x, min.x, max.x), Mth.clamp(vec.y, min.y, max.y), Mth.clamp(vec.z, min.z, max.z));
	}

	public static float getYawForVector(Vec3 origin, Vec3 target) {
		Vec3 angle = origin.vectorTo(target);

		return (float)Mth.atan2(angle.z, angle.x) - (90 * Mth.DEG_TO_RAD);
	}

	public static float getPitchForVector(Vec3 origin, Vec3 target) {
		Vec3 angle = origin.vectorTo(target);

		return -(float)Mth.atan2(angle.y, angle.horizontalDistance());
	}

	public static Vec3 getEyelineForward(Entity entity) {
		return Vec3.directionFromRotation(0, entity.getYRot());
	}

	public static Vec3 getEyelineRight(Entity entity) {
		return Vec3.directionFromRotation(0, entity.getYRot() + 90);
	}

	public static Vec3 getBodyForward(LivingEntity entity) {
		return Vec3.directionFromRotation(0, entity.yBodyRot);
	}

	public static Vec3 getBodyRight(LivingEntity entity) {
		return Vec3.directionFromRotation(0, entity.yBodyRot + 90);
	}
}
