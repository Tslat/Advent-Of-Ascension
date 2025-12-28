package net.tslat.aoa3.content.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.content.entity.ai.movehelper.AirborneMoveControl;
import net.tslat.aoa3.content.entity.ai.temp.SetWalkTargetToAttackTarget;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.navigation.SmoothFlyingPathNavigation;

public class AoAFlyingMeleeMob<T extends AoAFlyingMeleeMob<T>> extends AoAMeleeMob<T> implements FlyingEntity {
    protected AoAFlyingMeleeMob(EntityType<? extends AoAMeleeMob> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected MoveControl createMoveControl() {
        return new AirborneMoveControl(this);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        final FlyingPathNavigation navigation = new SmoothFlyingPathNavigation(this, level);

        navigation.setCanFloat(true);

        return navigation;
    }

    @Override
    public BrainActivityGroup<? extends T> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>().invalidateIf((entity, target) -> !DamageUtil.isAttackable(target) || distanceToSqr(target.position()) > Mth.square(getAttributeValue(Attributes.FOLLOW_RANGE))),
                new SetWalkTargetToAttackTarget<>(),
                new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration() + 2));
    }

    @Override
    protected void spawnSprintParticle() {}

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {}

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {}

    @Override
    public boolean onClimbable() {
        return false;
    }

    @Override
    protected float getFlyingSpeed() {
        return getSpeed();
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (onGround()) {
            super.travel(travelVector);
        }
        else {
            travelFlying(this, travelVector);
        }
    }
}
