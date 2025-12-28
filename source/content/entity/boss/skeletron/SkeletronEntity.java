package net.tslat.aoa3.content.entity.boss.skeletron;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DefaultAnimations;

public class SkeletronEntity extends AoABoss {
    public static final EntityDataAccessor<Boolean> WOUNDED = makeSynchedData(SkeletronEntity.class, EntityDataSerializers.BOOLEAN);

    public SkeletronEntity(EntityType<? extends SkeletronEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(WOUNDED, false);
    }

    @Override
    public @Nullable SoundEvent getMusic() {
        return null;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        compound.putBoolean("Wounded", getSynchedData(WOUNDED));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("Wounded", CompoundTag.TAG_BYTE))
            setSynchedData(WOUNDED, compound.getBoolean("Wounded"));
    }

    @Override
    protected void dropCustomDeathLoot(ServerLevel level, DamageSource damageSource, boolean killedByPlayer) {
        super.dropCustomDeathLoot(level, damageSource, killedByPlayer);

        if (getSynchedData(WOUNDED))
            spawnAtLocation(AoAItems.WARPED_HORN.get());
    }

    public static AoAEntityStats.AttributeBuilder entityStats(EntityType<SkeletronEntity> entityType) {
        return AoAEntityStats.AttributeBuilder.createMonster(entityType)
                .health(275)
                .moveSpeed(0.31)
                .meleeStrength(15)
                .knockbackResist(0.9)
                .followRange(128)
                .aggroRange(128)
                .armour(10, 10)
                .knockback(1f)
                .stepHeight(1.25f);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Walk/Run/Idle", 0, state -> {
            if (state.isMoving()) {
                if (isSprinting())
                    return state.setAndContinue(DefaultAnimations.RUN);

                return state.setAndContinue(getSynchedData(WOUNDED) ? RawAnimation.begin().thenLoop("move.walk.wounded") : DefaultAnimations.WALK);
            }

            return state.setAndContinue(getSynchedData(WOUNDED) ? RawAnimation.begin().thenLoop("misc.idle.wounded") : DefaultAnimations.IDLE);
        }));
    }
}
