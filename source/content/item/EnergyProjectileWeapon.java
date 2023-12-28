package net.tslat.aoa3.content.item;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.Optional;

public interface EnergyProjectileWeapon {
	InteractionHand getWeaponHand(LivingEntity holder);

	@Deprecated(forRemoval = true)
	void doBlockImpact(BaseEnergyShot shot, Vec3 hitPos, LivingEntity shooter);

	@Deprecated(forRemoval = true)
	boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter);

	default boolean doEntityImpact(ServerLevel level, LivingEntity shooter, ItemStack stack, ShotInfo shotInfo, EntityHitResult rayTrace) {
		return true;
	}

	default boolean doBlockImpact(ServerLevel level, LivingEntity shooter, ItemStack stack, ShotInfo shotInfo, BlockHitResult rayTrace) {
		return true;
	}

	default ShotInfo getPosAndRotForShot(EnergyProjectileWeapon weapon, LivingEntity shooter, float inaccuracy, float beamDistance) {
		final RandomSource random = shooter.getRandom();
		final boolean rightSide = shooter.getMainArm() == HumanoidArm.RIGHT ? weapon.getWeaponHand(shooter) == InteractionHand.MAIN_HAND : weapon.getWeaponHand(shooter) == InteractionHand.OFF_HAND;
		final Vec3 shooterAngle = shooter.getLookAngle();
		final float yaw = (float)Mth.atan2(shooterAngle.x, shooterAngle.z);
		final float armDist = getDistToBlasterArm(weapon, shooter);
		final float xOffset = Mth.cos(yaw) * armDist;
		final float zOffset = Mth.sin(yaw) * armDist;
		final Vec3 rayStart = shooter.getEyePosition().add(xOffset * (rightSide ? -1 : 1), getBlasterHeightOffset(weapon, shooter), zOffset * (rightSide ? 1 : -1));
		final Vec3 angle = rayStart.vectorTo(shooter.getEyePosition().add(shooterAngle.scale(beamDistance))).normalize().add(random.triangle(0, 0.0172275D * inaccuracy), random.triangle(0, 0.0172275D * inaccuracy), random.triangle(0, 0.0172275D * inaccuracy));

		return new ShotInfo(rayStart, rayStart.add(angle.scale(getChamberLength(weapon, shooter))), angle);
	}

	default float getChamberLength(EnergyProjectileWeapon weapon, LivingEntity shooter) {
		return 1.5f;
	}

	default float getDistToBlasterArm(EnergyProjectileWeapon weapon, LivingEntity shooter) {
		return 0.4f;
	}

	default float getBlasterHeightOffset(EnergyProjectileWeapon weapon, LivingEntity shooter) {
		return -0.2f;
	}

	record ShotInfo(Vec3 position, Vec3 barrelPos, Vec3 angle, MutableObject<Vec3> hitPos, MutableBoolean effectiveHit) {
		public ShotInfo(Vec3 position, Vec3 barrelPos, Vec3 angle) {
			this(position, barrelPos, angle, new MutableObject<>(), new MutableBoolean(false));
		}

		public float getPitch() {
			return (float)Mth.atan2(this.angle.y, this.angle.horizontalDistance()) * Mth.RAD_TO_DEG;
		}

		public float getYaw() {
			return (float)Mth.atan2(this.angle.x, this.angle.z) * Mth.RAD_TO_DEG;
		}

		public Vec3 shotOrBarrelPosForVfx() {
			return getHitPos().map(hitPos -> position().distanceToSqr(hitPos) < position().distanceToSqr(this.barrelPos) ? position() : barrelPos()).orElse(this.position);
		}

		public Optional<Vec3> getHitPos() {
			return Optional.ofNullable(hitPos.getValue());
		}

		public boolean wasEffectiveHit() {
			return this.effectiveHit.getValue();
		}

		public void setEffectiveHit(boolean effective) {
			this.effectiveHit.setValue(effective);
		}

		public void setHitPos(Vec3 hitPos) {
			this.hitPos.setValue(hitPos);
		}
	}
}
