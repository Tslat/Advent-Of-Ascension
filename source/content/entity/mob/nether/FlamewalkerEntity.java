package net.tslat.aoa3.content.entity.mob.nether;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidType;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.common.registration.entity.AoAMobEffects;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.effectslib.api.util.EffectBuilder;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableRangedAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.StayWithinDistanceOfAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.util.BrainUtils;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

import javax.annotation.Nullable;

public class FlamewalkerEntity extends AoARangedMob<FlamewalkerEntity> {
    public FlamewalkerEntity(EntityType<? extends FlamewalkerEntity> entityType, Level world) {
        super(entityType, world);

        setPathfindingMalus(BlockPathTypes.LAVA, 4);
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0);
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0);
        setPathfindingMalus(BlockPathTypes.WATER_BORDER, 8);
        setPathfindingMalus(BlockPathTypes.WATER, -1);
    }

    @Override
    public BrainActivityGroup<FlamewalkerEntity> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>(),
                new StayWithinDistanceOfAttackTarget<>(),
                new FlameWalkerAttack(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration()));
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 2.03125f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_FLAMEWALKER_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_FLAMEWALKER_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_FLAMEWALKER_HURT.get();
    }

    @org.jetbrains.annotations.Nullable
    @Override
    protected SoundEvent getShootSound() {
        return null;
    }

    @Override
    public boolean canSwimInFluidType(FluidType type) {
        return type == ForgeMod.LAVA_TYPE.get();
    }

    protected int getAttackSwingDuration() {
        return 32;
    }

    protected int getPreAttackTime() {
        return 16;
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {}

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
        return null;
    }

    @Override
    protected void onHurt(DamageSource source, float amount) {
        if (DamageUtil.isMeleeDamage(source)) {
            Entity target = source.getEntity();

            target.setSecondsOnFire(3);

            if (DamageUtil.safelyDealDamage(DamageUtil.entityDamage(AoADamageTypes.BURN, this), target, 3) && rand().oneInNChance(15))
                EntityUtil.applyPotions(target, new EffectBuilder(AoAMobEffects.BURNED.get(), 600));
        }
    }

    @Override
    public void doRangedAttackEntity(@org.jetbrains.annotations.Nullable BaseMobProjectile projectile, Entity target) {
        target.setSecondsOnFire((int)Math.ceil(target.getRemainingFireTicks() / 20f) + 1);

       if (DamageUtil.safelyDealDamage(DamageUtil.indirectEntityDamage(AoADamageTypes.BURN, this, null), target, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get())) && rand().oneInNChance(10))
           EntityUtil.applyPotions(target, new EffectBuilder(AoAMobEffects.BURNED.get(), 600));
    }

    @Override
    protected void onInsideBlock(BlockState state) {
        if (!level.isClientSide() && state.getFluidState().is(FluidTags.WATER))
            hurt(level.damageSources().drown(), 0.25f);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericLivingController(this),
                DefaultAnimations.genericWalkIdleController(this),
                AoAAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_STRIKE));
    }

    private static class FlameWalkerAttack extends AnimatableRangedAttack<FlamewalkerEntity> {
        private Vec3 targetingPosition = null;

        public FlameWalkerAttack(int delayTicks) {
            super(delayTicks);
        }

        @Override
        protected void start(FlamewalkerEntity entity) {
            super.start(entity);

            this.targetingPosition = this.target.position().add(this.target.getBbWidth() * 0.5f, 0, this.target.getBbWidth() * 0.5f);
            ServerParticlePacket packet = new ServerParticlePacket();

            for (int i = 0; i < 10; i++) {
                packet.particle(ParticleTypes.SMALL_FLAME, targetingPosition.x + entity.rand().randomValueBetween(-1, 1f), targetingPosition.y + 0.1f, targetingPosition.z + entity.rand().randomValueBetween(-1, 1));
            }

            entity.level.playSound(null, this.targetingPosition.x, this.targetingPosition.y, this.targetingPosition.z, AoASounds.ENTITY_FLAMEWALKER_FLARE.get(), SoundSource.HOSTILE, 1, 1);
            AoAPackets.messageAllPlayersTrackingEntity(packet, entity);
        }

        @Override
        protected void doDelayedAction(FlamewalkerEntity entity) {
            if (this.target == null)
                return;

            if (!BrainUtils.canSee(entity, this.target) || entity.distanceToSqr(this.target) > this.attackRadius)
                return;

            ServerParticlePacket packet = new ServerParticlePacket();

            for (int i = 0; i < 15; i++) {
                packet.particle(new CustomisableParticleType.Data(AoAParticleTypes.BURNING_FLAME.get(), 0.5f, 2, 1, 1, 1, 1, entity.getId()), targetingPosition.x + entity.rand().randomValueBetween(-1, 1f), targetingPosition.y, targetingPosition.z + entity.rand().randomValueBetween(-1, 1), entity.rand().randomValueBetween(-0.05f, 0.05f), 0.5, entity.rand().randomValueBetween(-0.05f, 0.05f));
            }

            AoAPackets.messageAllPlayersTrackingEntity(packet, entity);
            BrainUtils.setForgettableMemory(entity, MemoryModuleType.ATTACK_COOLING_DOWN, true, this.attackIntervalSupplier.apply(entity));
        }

        @Override
        protected void stop(FlamewalkerEntity entity) {
            super.stop(entity);

            this.targetingPosition = null;
        }
    }
}
