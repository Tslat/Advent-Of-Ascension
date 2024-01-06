package net.tslat.aoa3.content.entity.boss.skeletron;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.base.AoAEntityPart;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import net.tslat.aoa3.library.object.EntityDataHolder;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;

public class SkeletronEntity extends AoABoss {
    public static final EntityDataHolder<Boolean> WOUNDED = EntityDataHolder.register(SkeletronEntity.class, EntityDataSerializers.BOOLEAN, false, entity -> entity.wounded, (entity, value) -> entity.wounded = value);

    private boolean wounded = false;

    public SkeletronEntity(EntityType<? extends SkeletronEntity> entityType, Level level) {
        super(entityType, level);

        setParts(new AoAEntityPart<>(this, 14 / 16f, getBbHeight() - 0.625f, 0, 0.5f, -getBbWidth() * 0.5f - 7 / 16f),
                new AoAEntityPart<>(this, 9 / 16f, 0.5625f, 0, 0.4375f, -getBbWidth() * 1.35f).setDamageMultiplier(0.85f),
                new AoAEntityPart<>(this, 9 / 16f, 0.5625f, 0, 0.375f, -getBbWidth() * 1.75f).setDamageMultiplier(0.85f),
                new AoAEntityPart<>(this, 14 / 16f, getBbHeight() - 0.625f, 0, 0.5f, getBbWidth() * 0.5f + 7 / 16f),
                new AoAEntityPart<>(this, 11/ 16f, 1, 0, 0.5f, getBbWidth() * 1.4f));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        registerDataParams(WOUNDED);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return 1.0625f;
    }

    @Override
    public @Nullable SoundEvent getMusic() {
        return null;
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        compound.putBoolean("Wounded", WOUNDED.get(this));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("Wounded", CompoundTag.TAG_BYTE))
            WOUNDED.set(this, compound.getBoolean("Wounded"));
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource damageSource, int lootingLevel, boolean recentlyHit) {
        super.dropCustomDeathLoot(damageSource, lootingLevel, recentlyHit);

        if (WOUNDED.is(this, true))
            spawnAtLocation(AoAItems.WARPED_HORN.get());
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Walk/Run/Idle", 0, state -> {
            if (state.isMoving()) {
                if (isSprinting())
                    return state.setAndContinue(DefaultAnimations.RUN);

                return state.setAndContinue(WOUNDED.is(this, true) ? RawAnimation.begin().thenLoop("move.walk.wounded") : DefaultAnimations.WALK);
            }

            return state.setAndContinue(WOUNDED.is(this, true) ? RawAnimation.begin().thenLoop("misc.idle.wounded") : DefaultAnimations.IDLE);
        }));
    }
}
