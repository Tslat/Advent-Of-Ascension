package net.tslat.aoa3.content.entity.ai.movehelper;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;

/**
 * MoveControl wrapper utilising the ExtendedMoveControl base, specifically for flying/airborne entities
 */
public class AirborneMoveControl extends ExtendedMoveControl {
    public static final float MIN_Y_DELTA = 1E-5f;

    protected boolean canHover = false;

    public AirborneMoveControl(Mob mob) {
        super(mob);
    }

    /**
     * Set this Airborne MoveControl to hover when not moving
     */
    public AirborneMoveControl canHover() {
        return canHover(true);
    }

    /**
     * Set this Airborne MoveControl to hover when not moving
     */
    public AirborneMoveControl canHover(boolean hover) {
        this.canHover = hover;

        return this;
    }

    /**
     * @return The mob's effective movement speed, taking into account its movement attribute(s) and this MoveControl's speed modifier
     */
    @Override
    protected float getMoveSpeed() {
        return (float)(this.speedModifier * (this.mob.getAttributeValue(this.mob.onGround() || !this.mob.getAttributes().hasAttribute(Attributes.FLYING_SPEED) ? Attributes.MOVEMENT_SPEED : Attributes.FLYING_SPEED)));
    }

    /**
     * Tick the MoveControl while it is not currently moving
     */
    @Override
    protected void tickWait() {
        super.tickWait();
        this.mob.setYya(0);

        if (!this.canHover)
            this.mob.setNoGravity(false);
    }

    /**
     * Tick the MoveControl while it is in the strafing state<p>
     * This is normally only used for specific behaviours such as Skeletons or Piglins strafing evasively
     */
    @Override
    protected void tickStrafe() {
        this.operation = Operation.WAIT;
        float speed = getMoveSpeed() * this.strafeSpeedModifier;

        this.mob.setNoGravity(true);
        this.mob.setSpeed(speed);
        this.mob.setZza(this.strafeForwards);
        this.mob.setXxa(this.strafeRight);
    }

    /**
     * Tick the MoveControl while it is moving to a target position
     * <p>
     * This state is somewhat special in MoveControl; the state gets set to WAIT after ticking, every tick.<br>
     * This is done because the PathNavigator will continuously set the operation to MOVE_TO as it navigates
     */
    @Override
    protected void tickMoveTo() {
        this.operation = Operation.WAIT;
        double xDelta = this.wantedX - this.mob.getX();
        double yDelta = this.wantedY - this.mob.getY();
        double zDelta = this.wantedZ - this.mob.getZ();

        if (xDelta * xDelta + yDelta * yDelta + zDelta * zDelta < MIN_MOVEMENT_SPEED) {
            this.mob.setYya(0);
            this.mob.setZza(0);

            return;
        }

        float yRot = (float)(Mth.atan2(zDelta, xDelta) * Mth.RAD_TO_DEG) - 90;
        float moveSpeed = getMoveSpeed();

        this.mob.setNoGravity(true);
        this.mob.setSpeed(moveSpeed);
        this.mob.setYRot(rotClamped(this.mob.getYRot(), yRot, 90f));

        double lateralDist = Math.sqrt(xDelta * xDelta + zDelta * zDelta);

        if (Math.abs(yDelta) > MIN_Y_DELTA || Math.abs(lateralDist) > MIN_Y_DELTA) {
            double angle = Mth.atan2(yDelta, lateralDist) * -Mth.RAD_TO_DEG;

            this.mob.setXRot(rotClamped(this.mob.getXRot(), (float)angle, this.maxTurn));
            this.mob.setYya(yDelta > 0 ? moveSpeed : -moveSpeed);
        }
    }
}
