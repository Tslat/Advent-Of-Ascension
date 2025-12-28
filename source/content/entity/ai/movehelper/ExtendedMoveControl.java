package net.tslat.aoa3.content.entity.ai.movehelper;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

/**
 * Extending wrapper class for {@link MoveControl} to allow for much easier custom movement handling
 */
public class ExtendedMoveControl extends MoveControl {
    public static final float MIN_MOVEMENT_SPEED = 2.5000003E-7F;

    protected float maxTurn = 90f;
    protected float strafeSpeedModifier = 1;

    public ExtendedMoveControl(Mob mob) {
        super(mob);
    }

    /**
     * Set the maximum rotation the mob can turn while moving in a single tick<br>
     * Default value is 90 degrees
     *
     */
    public ExtendedMoveControl withMaxTurn(float rotation) {
        this.maxTurn = rotation;

        return this;
    }

    /**
     * Set a speed multiplier to apply when strafing<br>
     * Allows for movement speed penalties when strafing
     */
    public ExtendedMoveControl strafeSpeedPenalty(float multiplier) {
        this.strafeSpeedModifier *= multiplier;

        return this;
    }

    /**
     * @return The current movement target X-coordinate
     */
    @Override
    public double getWantedX() {
        return this.wantedX;
    }

    /**
     * @return The current movement target Y-coordinate
     */
    @Override
    public double getWantedY() {
        return this.wantedY;
    }

    /**
     * @return The current movement target Z-coordinate
     */
    @Override
    public double getWantedZ() {
        return this.wantedZ;
    }

    /**
     * @return The current movement target position in {@link Vec3} form
     */
    public Vec3 getWantedPosition() {
        return new Vec3(this.wantedX, this.wantedY, this.wantedZ);
    }

    /**
     * @return The current movement speed modifier
     */
    @Override
    public double getSpeedModifier() {
        return this.speedModifier;
    }

    /**
     * @return Whether the MoveControl has a movement target
     */
    @Override
    public boolean hasWanted() {
        return this.operation == MoveControl.Operation.MOVE_TO;
    }

    /**
     * Compute the closest angle possible to the target angle, with a maximum change in angle allowed
     *
     * @param currentAngle The current angle of rotation
     * @param targetAngle The target angle of rotation
     * @param maxChange The maximum change in angle to allow
     * @return The angle between the source and target angles, clamped to the maximum if applicable
     */
    protected float rotClamped(float currentAngle, float targetAngle, float maxChange) {
        float rotDelta = Mth.clamp(Mth.wrapDegrees(targetAngle - currentAngle), -maxChange, maxChange);
        float newAngle = currentAngle + rotDelta;

        if (newAngle < 0f) {
            newAngle += 360f;
        }
        else if (newAngle > 360f) {
            newAngle -= 360f;
        }

        return newAngle;
    }

    /**
     * @param posX The target X position (on the same Y-level as the mob)
     * @param posZ The target Z position (on the same Y-level as the mob)
     * @return Whether the target position is considered walkable for the target mob
     */
    protected boolean isWalkable(double posX, double posZ) {
        return isWalkable(BlockPos.containing(posX, this.mob.getBlockY(), posZ));
    }

    /**
     * @return Whether the target position is considered walkable for this mob
     */
    protected boolean isWalkable(BlockPos pos) {
        return this.mob.getNavigation()
                       .getNodeEvaluator()
                       .getPathType(this.mob, pos) == PathType.WALKABLE;
    }

    /**
     * @return The mob's effective movement speed, taking into account its movement attribute(s) and this MoveControl's speed modifier
     */
    protected float getMoveSpeed() {
        return (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
    }

    /**
     * Determine whether the mob can jump over the given block<br>
     * This is typically called when inside the same cuboid region as a block (such as a fence), and the block itself can be collided with
     *
     * @param pos The position of the block to potentially jump over
     * @param state The block to potentially jump over
     * @return Whether the mob can jump over the given block
     */
    protected boolean canJumpOver(BlockPos pos, BlockState state) {
        return !state.is(BlockTags.DOORS) && !state.is(BlockTags.FENCES);
    }

    /**
     * Tick the MoveControl while it is moving to a target position
     * <p>
     * This state is somewhat special in MoveControl; the state gets set to WAIT or JUMPING after ticking, every tick.<br>
     * This is done because the PathNavigator will continuously set the operation to MOVE_TO as it navigates
     */
    protected void tickMoveTo() {
        this.operation = Operation.WAIT;
        double xDelta = this.wantedX - this.mob.getX();
        double yDelta = this.wantedY - this.mob.getY();
        double zDelta = this.wantedZ - this.mob.getZ();

        if (xDelta * xDelta + yDelta * yDelta + zDelta * zDelta < MIN_MOVEMENT_SPEED) {
            this.mob.setZza(0);

            return;
        }

        BlockPos blockPos = this.mob.blockPosition();
        BlockState blockState = this.mob.level().getBlockState(blockPos);
        VoxelShape blockShape = blockState.getCollisionShape(this.mob.level(), blockPos);
        float yRot = (float)(Mth.atan2(zDelta, xDelta) * Mth.RAD_TO_DEG) - 90;

        this.mob.setYRot(rotClamped(this.mob.getYRot(), yRot, 90f));
        this.mob.setSpeed(getMoveSpeed());

        if (yDelta > (double)this.mob.maxUpStep() && xDelta * xDelta + zDelta * zDelta < Math.max(1d, this.mob.getBbWidth())
            || (!blockShape.isEmpty() && this.mob.getY() < blockPos.getY() + blockShape.max(Direction.Axis.Y) && canJumpOver(blockPos, blockState))) {
            this.mob.getJumpControl().jump();
            this.operation = Operation.JUMPING;
        }
    }

    /**
     * Tick the MoveControl while it is in the strafing state<p>
     * This is normally only used for specific behaviours such as Skeletons or Piglins strafing evasively
     */
    protected void tickStrafe() {
        this.operation = Operation.WAIT;
        float speed = getMoveSpeed() * this.strafeSpeedModifier;
        float strafeForward = this.strafeForwards;
        float strafeRight = this.strafeRight;
        float strafe = Math.max(Mth.sqrt(strafeForward * strafeForward + strafeRight * strafeRight), 1);

        strafe = speed / strafe;
        strafeForward *= strafe;
        strafeRight *= strafe;
        float forwardScale = Mth.cos(this.mob.getYRot() * Mth.DEG_TO_RAD);
        float rightScale = Mth.sin(this.mob.getYRot() * Mth.DEG_TO_RAD);
        float xDelta = strafeForward * forwardScale - strafeRight * rightScale;
        float zDelta = strafeRight * forwardScale + strafeForward * rightScale;

        if (!isWalkable(this.mob.getX() + xDelta, this.mob.getZ() + zDelta)) {
            this.strafeForwards = 1;
            this.strafeRight = 0;
        }

        this.mob.setSpeed(speed);
        this.mob.setZza(this.strafeForwards);
        this.mob.setXxa(this.strafeRight);
    }

    /**
     * Tick the MoveControl while it is in the jumping state<p>
     * This state is usually set when attempting to climb blocks that it can't automatically step up
     */
    protected void tickJump() {
        this.mob.setSpeed((float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED)));

        if (this.mob.onGround())
            this.operation = Operation.WAIT;
    }

    /**
     * Tick the MoveControl while it is not currently moving
     */
    protected void tickWait() {
        this.mob.setZza(0);
    }

    /**
     * Tick the MoveControl<br>
     * Ideally, you'd handle the actual movement in one of the other tick methods and only do state-management in this method
     */
    @Override
    public void tick() {
        switch (this.operation) {
            case MOVE_TO -> tickMoveTo();
            case STRAFE -> tickStrafe();
            case JUMPING -> tickJump();
            default -> tickWait();
        }
    }
}
