package net.tslat.aoa3.content.entity.mob.precasia;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.fluids.FluidType;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.entity.base.AoAEntityPart;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.object.EntityDataHolder;
import net.tslat.aoa3.util.DamageUtil;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class SkeletalAbominationEntity extends AoAMeleeMob<SkeletalAbominationEntity> {
    public static final EntityDataHolder<Boolean> STANDING = EntityDataHolder.register(SkeletalAbominationEntity.class, EntityDataSerializers.BOOLEAN, false, entity -> entity.standing, SkeletalAbominationEntity::setStanding);

    private static final RawAnimation POSE_ANIM_STAND = RawAnimation.begin().thenPlay("misc.stand").thenLoop("misc.stand.hold");
    private static final RawAnimation POSE_ANIM_RELEASE = RawAnimation.begin().thenPlay("misc.stand.release");
    private static final RawAnimation WALK_ANIM_BIPED = RawAnimation.begin().thenPlay("move.walk.biped");

    private static final int ATTACK_BITE = 0;
    private static final int ATTACK_THROW = 1;
    private static final int ATTACK_SWING = 2;
    private static final int ATTACK_SLAM = 3;

    private boolean standing = false;

    public SkeletalAbominationEntity(EntityType<? extends SkeletalAbominationEntity> entityType, Level world) {
        super(entityType, world);

        setParts(new AoAEntityPart<>(this, getBbWidth() * 0.75f, getBbHeight() * 0.75f, 0, getBbHeight() * 0.65f, getBbWidth() * 0.875f),
                new AoAEntityPart<>(this, getBbWidth() * 0.75f, getBbHeight() * 0.5f, 0, getBbHeight(), getBbWidth() * 1.625f).setDamageMultiplier(1.25f),
                new AoAEntityPart<>(this, getBbWidth(), getBbHeight() * 0.9f, 0, 0, -getBbWidth()).setDamageMultiplier(0.75f)
        );
    }

    @Override
    public boolean isInFluidType(FluidType type) {
        return super.isInFluidType(type);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        registerDataParams(STANDING);
    }

    public boolean isStanding() {
        return this.standing;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        compound.putBoolean("StandingUp", this.standing);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("StandingUp"))
            STANDING.set(this, compound.getBoolean("StandingUp"));
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return isStanding() ? 2.5f : 1.65625f;
    }

    @Override
    public void makeStuckInBlock(BlockState state, Vec3 pMotionMultiplier) {
        if (!state.is(AoABlocks.TAR.getBlock()))
            super.makeStuckInBlock(state, pMotionMultiplier);
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (DamageUtil.isRangedDamage(source, this, amount) || DamageUtil.isGunDamage(source))
            amount *= 0.5f;

        return super.hurt(source, amount);
    }

    protected void setStanding(boolean standing) {
        if (standing == this.standing)
            return;

        this.standing = standing;

        if (!level().isClientSide)
            STANDING.set(this, standing);

        toggleMultipart(!standing);
        refreshDimensions();
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        if (isStanding())
            return EntityDimensions.scalable(1, 2.6875f).scale(getScale());

        return super.getDimensions(pose);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();

        if (tickCount % 100 == 0) {
            setStanding((tickCount / 100) % 2 == 0);
        }
    }

    @Override
    public int getCurrentSwingDuration() {
        return switch (ATTACK_STATE.get(this)) {
            case ATTACK_BITE -> 9;
            case ATTACK_THROW -> 18;
            case ATTACK_SWING -> 15;
            case ATTACK_SLAM -> 20;
            default -> 9;
        };
    }

    @Override
    protected int getPreAttackTime() {
        return switch (ATTACK_STATE.get(this)) {
            case ATTACK_BITE -> 4;
            case ATTACK_THROW -> 7;
            case ATTACK_SWING -> 6;
            case ATTACK_SLAM -> 17;
            default -> 4;
        };
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<GeoAnimatable>(this, "Pose", 0, state -> {
            if (isStanding()) {
                return state.setAndContinue(POSE_ANIM_STAND);
            }
            else if (state.isCurrentAnimation(POSE_ANIM_STAND) || (state.isCurrentAnimation(POSE_ANIM_RELEASE) && !state.getController().hasAnimationFinished())) {
                return state.setAndContinue(POSE_ANIM_RELEASE);
            }

            state.resetCurrentAnimation();

            return PlayState.STOP;
        }));
        controllers.add(new AnimationController<GeoAnimatable>(this, "Walk/Idle", 3, state -> {
            if (state.isMoving())
                return state.setAndContinue(isStanding() ? WALK_ANIM_BIPED : DefaultAnimations.WALK);

            if (!isStanding())
                return state.setAndContinue(DefaultAnimations.IDLE);

            return PlayState.STOP;
        }));
    }

    @Override
    protected float getAttackVectorPositionOffset() {
        return 1.5625f;
    }
}
