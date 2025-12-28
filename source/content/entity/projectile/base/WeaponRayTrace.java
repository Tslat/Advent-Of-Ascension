package net.tslat.aoa3.content.entity.projectile.base;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.item.ProjectileFiringWeapon;
import net.tslat.tme.api.object.RayTrace;

public record WeaponRayTrace(Vec3 startPos, Vec3 visualStartPos, Vec3 angle, RayTrace<?> result) {
    public static WeaponRayTrace create(ProjectileFiringWeapon weapon, WeaponFiringContext context, Entity shooter, float maxDist) {
        return create(weapon, context, shooter, maxDist, (startPos, endPos, level, shooter2) ->
                              RayTrace.createWithSource(shooter).between(startPos, endPos).blockType(ClipContext.Block.OUTLINE).fluidType(ClipContext.Fluid.ANY).forEntities(LivingEntity.class).run());
    }

    public static WeaponRayTrace create(ProjectileFiringWeapon weapon, WeaponFiringContext context, Entity shooter, float maxDist, TraceFunction traceFunction) {
        final RandomSource random = shooter.getRandom();
        final int side = shooter instanceof LivingEntity livingShooter ? livingShooter.getMainArm() == HumanoidArm.RIGHT ?
                                                                         context.weaponHand() == InteractionHand.MAIN_HAND ? 1 : 0 :
                                                                         context.weaponHand() == InteractionHand.OFF_HAND ? 1 : 0 : 0;
        final Vec3 shooterAngle = shooter.getLookAngle();
        final float yaw = (float) Mth.atan2(shooterAngle.x, shooterAngle.z);
        final float armDist = weapon.getRightOffset(weapon, shooter);
        final float xOffset = Mth.cos(yaw) * armDist;
        final float zOffset = Mth.sin(yaw) * armDist;
        final Vec3 rayStart = shooter.getEyePosition().add(xOffset * -side, weapon.getEyeHeightOffset(weapon, shooter), zOffset * side);
        final Vec3 angle = rayStart.vectorTo(shooter.getEyePosition().add(shooterAngle.scale(maxDist))).normalize().add(random.triangle(0, 0.0172275D * context.inaccuracy()), random.triangle(0, 0.0172275D * context.inaccuracy()), random.triangle(0, 0.0172275D * context.inaccuracy()));
        Vec3 rayEnd = rayStart.add(angle.scale(maxDist));
        final RayTrace<?> rayTrace = traceFunction.doTrace(rayStart, rayEnd, shooter.level(), shooter);
        rayEnd = rayTrace.hitPos();
        final float forwardOffset = weapon.getForwardOffset(weapon, shooter);

        return new WeaponRayTrace(rayStart, rayStart.distanceToSqr(rayEnd) <= forwardOffset ? rayStart : rayStart.add(shooterAngle.scale(forwardOffset)), angle, rayTrace);
    }

    public Vec3 endPos() {
        return this.result.hitPos();
    }

    public boolean missed() {
        return this.result.result() == HitResult.Type.MISS;
    }

    public interface TraceFunction {
        RayTrace<?> doTrace(Vec3 startPos, Vec3 endPos, Level level, Entity shooter);
    }
}
