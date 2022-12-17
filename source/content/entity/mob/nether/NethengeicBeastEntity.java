package net.tslat.aoa3.content.entity.mob.nether;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMonster;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nullable;

public class NethengeicBeastEntity extends AoAMonster<NethengeicBeastEntity> implements AoARangedAttacker {
    private static final EntityDataAccessor<Boolean> FLAME_AURA = SynchedEntityData.defineId(NethengeicBeastEntity.class, EntityDataSerializers.BOOLEAN);
    private static final RawAnimation FLAMETHROWER_ANIM = RawAnimation.begin().thenPlay("attack.flamethrower.start").thenPlay("attack.flamethrower.hold");
    private static final RawAnimation FLAMETHROWER_RELEASE_ANIM = RawAnimation.begin().thenPlay("attack.flamethrower.end");
    private static final RawAnimation FIRE_AURA_ANIM = RawAnimation.begin().thenPlay("misc.fire_aura");
    private static final int FIRE_SPIN = 0;
    private static final int FLAMETHROWER_OPEN = 1;
    private static final int FLAMETHROWER = 2;

    public NethengeicBeastEntity(EntityType<? extends NethengeicBeastEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public int calculateKillXp() {
        int xp = super.calculateKillXp();

        return xp > 0 ? xp + 25 : 0;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        getEntityData().define(FLAME_AURA, false);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 3.375f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_NETHENGEIC_BEAST_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_NETHENGEIC_BEAST_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_NETHENGEIC_BEAST_HURT.get();
    }

    @Override
    protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
        return null;
    }

    public boolean hasAura() {
        return getEntityData().get(FLAME_AURA);
    }

    public void toggleAura() {
        getEntityData().set(FLAME_AURA, !getEntityData().get(FLAME_AURA));
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {}

    @Override
    public void doRangedAttackEntity(@org.jetbrains.annotations.Nullable BaseMobProjectile projectile, Entity target) {}

    @Override
    public void doRangedAttackBlock(@org.jetbrains.annotations.Nullable BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {}

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericWalkIdleController(this));
        controllers.add(new AnimationController<>(this, "living", 0, event -> {
            if (getAttackState() != FLAMETHROWER) {
                event.getController().setAnimationSpeed(1 + (1 - (getHealth() / getMaxHealth())) * 5);
                event.getController().setAnimation(DefaultAnimations.LIVING);

                return PlayState.CONTINUE;
            }

            return PlayState.STOP;
        }));
        controllers.add(AoAAnimations.genericHeldPoseController(this, FLAMETHROWER_ANIM, FLAMETHROWER_RELEASE_ANIM, entity -> entity.getAttackState() == FLAMETHROWER_OPEN || entity.getAttackState() == FLAMETHROWER)
                .triggerableAnim("fire_aura", FIRE_AURA_ANIM));
    }
}
