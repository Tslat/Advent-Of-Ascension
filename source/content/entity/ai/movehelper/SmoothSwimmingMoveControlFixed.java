package net.tslat.aoa3.content.entity.ai.movehelper;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;

// Because Forge doesn't know how to patch things properly
public class SmoothSwimmingMoveControlFixed extends MoveControl {
    protected final int maxTurnX;
    protected final int maxTurnY;
    protected final float waterSpeedMod;
    protected final float landSpeedMod;
    protected final boolean hasGravity;

    public SmoothSwimmingMoveControlFixed(Mob mob, int maxTurnX, int maxTurnY, float waterSpeedMod, float landSpeedMod, boolean hasGravity) {
        super(mob);

        this.maxTurnX = maxTurnX;
        this.maxTurnY = maxTurnY;
        this.waterSpeedMod = waterSpeedMod;
        this.landSpeedMod = landSpeedMod;
        this.hasGravity = hasGravity;
    }

    public void tick() {
        if (this.hasGravity && this.mob.isInWater())
            this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(0, 0.005d, 0));

        if (this.operation == MoveControl.Operation.MOVE_TO && !this.mob.getNavigation().isDone()) {
            final Vec3 distance = new Vec3(this.wantedX, this.wantedY, this.wantedZ).subtract(this.mob.position());

            if (distance.lengthSqr() < 0.00000025f) {
                this.mob.setZza(0);

                return;
            }

            final float attemptedRot = (float)(Mth.atan2(distance.z, distance.x) * (double)Mth.RAD_TO_DEG) - 90;
            final float speed = (float)(this.speedModifier * this.mob.getAttributeValue(ForgeMod.SWIM_SPEED.get()));

            this.mob.setYRot(rotlerp(this.mob.getYRot(), attemptedRot, (float)this.maxTurnY));
            this.mob.yBodyRot = this.mob.getYRot();
            this.mob.yHeadRot = this.mob.getYRot();

            if (!this.mob.isInWater()) {
                this.mob.setSpeed(speed * this.landSpeedMod * getTurningSpeedFactor(Math.abs(Mth.wrapDegrees(this.mob.getYRot() - attemptedRot))));

                return;
            }

            final double lateralDist = distance.horizontalDistance();

            this.mob.setSpeed(speed * this.waterSpeedMod);

            if (Math.abs(distance.y) > 0.00005f || Math.abs(lateralDist) > 0.00005f) {
                final float rotX = -((float)(Mth.atan2(distance.y, lateralDist) * (double)(180F / (float)Math.PI)));

                this.mob.setXRot(this.rotlerp(this.mob.getXRot(), Mth.clamp(Mth.wrapDegrees(rotX), (float)(-this.maxTurnX), (float)this.maxTurnX), 50F));
            }

            float f6 = Mth.cos(this.mob.getXRot() * Mth.DEG_TO_RAD);
            float f4 = Mth.sin(this.mob.getXRot() * Mth.DEG_TO_RAD);
            this.mob.zza = f6 * speed;
            this.mob.yya = -f4 * speed;

            return;
        }

        this.mob.setSpeed(0);
        this.mob.setXxa(0);
        this.mob.setYya(0);
        this.mob.setZza(0);
    }

    protected static float getTurningSpeedFactor(float rot) {
        return 1 - Mth.clamp((rot - 10) / 50f, 0, 1);
    }
}
