package net.tslat.aoa3.content.entity.animal.barathos;

import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.content.entity.base.AoAAnimal;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DefaultAnimations;

import javax.annotation.Nullable;
import java.util.Optional;

public class MasonBeetleEntity extends AoAAnimal<MasonBeetleEntity> {
    public static final EntityDataAccessor<Optional<BlockState>> BLOCKSTATE = makeSynchedData(MasonBeetleEntity.class, EntityDataSerializers.OPTIONAL_BLOCK_STATE);
    private static final RawAnimation WALK_PUSHING_ANIM = RawAnimation.begin().thenPlay("move.walk.push");
    private static final RawAnimation PUSH_POSE_ANIM = RawAnimation.begin().thenPlay("pose.push");

    public MasonBeetleEntity(EntityType<? extends MasonBeetleEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(BLOCKSTATE, Optional.empty());
    }

    @Override
    protected float getStepWeight() {
        return 0.1f;
    }

    @Override
    protected boolean isQuadruped() {
        return true;
    }

    @Override
    protected float nextStep() {
        return this.moveDist + 0.25f;
    }

    public static AoAEntityStats.AttributeBuilder entityStats(EntityType<MasonBeetleEntity> entityType) {
        return AoAEntityStats.AttributeBuilder.create(entityType)
                .health(8)
                .moveSpeed(0.2375f);
    }

    public void setCarriedBlock(@Nullable BlockState state) {
        if (state != null && (state.getBlock() instanceof LiquidBlock || state.isAir()))
            state = null;

        setSynchedData(BLOCKSTATE, Optional.ofNullable(state));
    }

    public Optional<BlockState> getCarriedBlock() {
        return getSynchedData(BLOCKSTATE);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        getCarriedBlock().ifPresent(blockState -> compound.put("carriedBlockState", NbtUtils.writeBlockState(blockState)));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("carriedBlockState", Tag.TAG_COMPOUND))
            setCarriedBlock(NbtUtils.readBlockState(level().holderLookup(Registries.BLOCK), compound.getCompound("carriedBlockState")));
    }

    @Override
    public int getMaxHeadYRot() {
        return getCarriedBlock().isPresent() ? 1 : super.getMaxHeadYRot();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "PushingPose", 0, state ->
                getCarriedBlock().isPresent() ? state.setAndContinue(PUSH_POSE_ANIM) : PlayState.STOP));
        controllers.add(new AnimationController<>(this, "Walk/Idle", 0, state -> {
            if (state.isMoving())
                return state.setAndContinue(getCarriedBlock().isPresent() ? WALK_PUSHING_ANIM : DefaultAnimations.WALK);

            return state.setAndContinue(DefaultAnimations.IDLE);
        }));
    }
}
