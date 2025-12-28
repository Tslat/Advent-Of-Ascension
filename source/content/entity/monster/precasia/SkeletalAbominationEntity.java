package net.tslat.aoa3.content.entity.monster.precasia;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidType;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.block.AoAFluidTypes;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.builder.MultipartBuilder;
import net.tslat.aoa3.util.DamageUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DefaultAnimations;

import static net.tslat.aoa3.library.builder.MultipartBuilder.Part;

public class SkeletalAbominationEntity extends AoAMeleeMob<SkeletalAbominationEntity> {
    public static final EntityDataAccessor<Boolean> STANDING = makeSynchedData(SkeletalAbominationEntity.class, EntityDataSerializers.BOOLEAN);

    private static final RawAnimation POSE_ANIM_STAND = RawAnimation.begin().thenPlay("misc.stand").thenLoop("misc.stand.hold");
    private static final RawAnimation POSE_ANIM_RELEASE = RawAnimation.begin().thenPlay("misc.stand.release");
    private static final RawAnimation WALK_ANIM_BIPED = RawAnimation.begin().thenPlay("move.walk.biped");

    private static final int ATTACK_BITE = 0;
    private static final int ATTACK_THROW = 1;
    private static final int ATTACK_SWING = 2;
    private static final int ATTACK_SLAM = 3;

    public SkeletalAbominationEntity(EntityType<? extends SkeletalAbominationEntity> entityType, Level world) {
        super(entityType, world);

        /*setParts(new AoAEntityPart<>(this, getBbWidth() * 0.75f, getBbHeight() * 0.75f, 0, getBbHeight() * 0.65f, getBbWidth() * 0.875f),
                new AoAEntityPart<>(this, getBbWidth() * 0.75f, getBbHeight() * 0.5f, 0, getBbHeight(), getBbWidth() * 1.625f).setDamageMultiplier(1.25f),
                new AoAEntityPart<>(this, getBbWidth(), getBbHeight() * 0.9f, 0, 0, -getBbWidth()).setDamageMultiplier(0.75f)
        );*/
    }

    @Nullable
    @Override
    public MultipartBuilder<? extends SkeletalAbominationEntity> definePartEntities() {
        return MultipartBuilder.ofDynamic(this,
                                          Part.sized(getBbWidth() * 0.75f, getBbWidth() * 0.75f).adjacentAbove().adjacentForward().then(
                                                  Part.sized(getBbWidth() * 0.75f, getBbHeight() * 0.5f).adjacentForward().damageMod(1.25f)),
                                          Part.sized(getBbWidth(), getBbHeight() * 0.9f).adjacentBehind().damageMod(0.75f));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(STANDING, false);
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        super.onSyncedDataUpdated(key);

        if (key.equals(STANDING))
            setStanding(isStanding());
    }

    public boolean isStanding() {
        return getSynchedData(STANDING);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        compound.putBoolean("StandingUp", isStanding());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("StandingUp"))
            setSynchedData(STANDING, compound.getBoolean("StandingUp"));
    }

    @Override
    public void makeStuckInBlock(BlockState state, Vec3 pMotionMultiplier) {
        if (!state.is(AoABlocks.TAR.getBlock()))
            super.makeStuckInBlock(state, pMotionMultiplier);
    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }

    @Override
    public void jumpInFluid(FluidType type) {
        if (getNavigation().canFloat() && type == AoAFluidTypes.TAR.get()) {
            self().setDeltaMovement(self().getDeltaMovement().add(0.0D, (double)0.4F * self().getAttributeValue(NeoForgeMod.SWIM_SPEED), 0.0D));
        }
        else {
            super.jumpInFluid(type);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (DamageUtil.isRangedDamage(source) || DamageUtil.isGunDamage(source))
            amount *= 0.5f;

        return super.hurt(source, amount);
    }

    protected void setStanding(boolean standing) {
        if (standing == isStanding())
            return;

        if (!level().isClientSide)
            setSynchedData(STANDING, standing);

        toggleMultipart(!standing);
        refreshDimensions();
    }/*

    @Override
    public float getEyeHeightAccess(Pose pose) {
        return (isStanding() ? 1.9047619f : 1.2619047f) * getDefaultDimensions(pose).height();
    }*/

    @Override
    public EntityDimensions getDefaultDimensions(Pose pose) {
        if (isStanding())
            return new EntityDimensions(1, 2.6875f, 5.1190476f, getType().getDimensions().attachments(), getType().getDimensions().fixed()).scale(getScale());

        return super.getDefaultDimensions(pose);
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();

        if (isInFluidType(AoAFluidTypes.TAR.get()) && getHealth() < getMaxHealth())
            heal(2);
    }

    @Override
    public int getCurrentSwingDuration() {
        return switch (getAttackState()) {
            case ATTACK_BITE -> 9;
            case ATTACK_THROW -> 18;
            case ATTACK_SWING -> 15;
            case ATTACK_SLAM -> 20;
            default -> 9;
        };
    }

    @Override
    protected int getPreAttackTime() {
        return switch (getAttackState()) {
            case ATTACK_BITE -> 4;
            case ATTACK_THROW -> 7;
            case ATTACK_SWING -> 6;
            case ATTACK_SLAM -> 17;
            default -> 4;
        };
    }

    @Override
    protected double getAttackReach() {
        return 1.5625;
    }

    public static AoAEntityStats.AttributeBuilder entityStats(EntityType<SkeletalAbominationEntity> entityType) {
        return AoAEntityStats.AttributeBuilder.createMonster(entityType)
                .health(64)
                .meleeStrength(9.5f)
                .moveSpeed(0.35f)
                .aggroRange(32)
                .followRange(48)
                .knockbackResist(0.15f);
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
}
