package net.tslat.aoa3.content.entity.boss.tyrosaur;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.ScreenShakePacket;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.MathUtil;
import net.tslat.smartbrainlib.api.core.behaviour.HeldBehaviour;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.tme.internal.particle.transition.AwayFromPositionParticleTransition;

import java.util.List;

class TyrosaurRoarBehaviour<T extends AoABoss> extends HeldBehaviour<T> {
    private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = ObjectArrayList.of(Pair.of(MemoryModuleType.ATTACK_TARGET, MemoryStatus.VALUE_PRESENT), Pair.of(MemoryModuleType.ATTACK_COOLING_DOWN, MemoryStatus.VALUE_ABSENT));

    private final float radiusSq;
    private int roarSoundDelay = 0;

    public TyrosaurRoarBehaviour(float radius) {
        this.radiusSq = radius * radius;
    }

    @Override
    protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
        return MEMORY_REQUIREMENTS;
    }

    @Override
    protected void start(T entity) {
        SoundBuilder.following(AoASounds.ENTITY_TYROSAUR_ROAR_START, entity.getParts()[4]).radius(32).play();
        this.roarSoundDelay = RandomUtil.numberBetween(4, 8);
        entity.setAttackState(TyrosaurEntity.ROAR);
        entity.setImmobile(true);
        entity.triggerAnim("Main", "roar_start");
    }

    @Override
    protected boolean shouldKeepRunning(T entity) {
        return BrainUtils.getTargetOfEntity(entity) != null;
    }

    @Override
    protected void tick(T entity) {
        if (this.runningTime > 30 && this.runningTime % this.roarSoundDelay == 0) {
            this.roarSoundDelay = RandomUtil.numberBetween(5, 7);
            SoundBuilder.following(AoASounds.ENTITY_TYROSAUR_ROAR_LOOP, entity.getParts()[4]).radius(32).play();
        }

        if (this.runningTime > 33 && entity.tickCount % 2 == 0) {
            doParticles(entity);

            if (entity.tickCount % 10 == 0)
                doDamage(entity);
        }
    }

    private void doDamage(T entity) {
        for (LivingEntity target : EntityRetrievalUtil.getEntities(entity, 32, LivingEntity.class)) {
            float strength = Math.max(0.2f, 1 - (float)entity.distanceToSqr(target) / this.radiusSq);
            float hearingMod = (float)(target.getViewVector(1).normalize().dot(target.getEyePosition().vectorTo(entity.getEyePosition()).normalize()) + 1) * 0.5f;

            if (target.isCrouching())
                hearingMod -= 0.25f;

            strength *= 1 + hearingMod;

            if (DamageUtil.doMiscEnergyAttack(entity, target, strength * 2f, entity.position())) {
                if (hearingMod > 0.5f) {
                    EntityUtil.applyPotions(target, entity, new EffectBuilder(MobEffects.DIG_SLOWDOWN, 200).isAmbient());

                    if (hearingMod > 0.8f && !target.hasEffect(MobEffects.WEAKNESS))
                        EntityUtil.applyPotions(target, entity, new EffectBuilder(MobEffects.WEAKNESS, 200).isAmbient());
                }

                if (target instanceof ServerPlayer pl)
                    AoANetworking.sendToPlayer(pl, new ScreenShakePacket(20, strength * 1.5f, 0.98f));
            }
        }
    }

    private void doParticles(T entity) {
        Vec3 forward = MathUtil.getBodyForward(entity);
        Vec3 pos = entity.getEyePosition().add(forward.scale(entity.getBbWidth() * 1.4f - 0.3f));

        if (entity.tickCount % 6 == 0) {
            ParticleBuilder.forPositionsInCircle(AoAParticleTypes.ORB.get(), pos, forward, 0.25f, 32)
                    .lifespan(20)
                    .scaleMod(0.4f)
                    .colourTint(1, 1, 1, 0.5f)
                    .addTransition(AwayFromPositionParticleTransition.create(pos.subtract(forward.scale(0.25f)), 3))
                    .sendToAllPlayersTrackingEntity(entity);
        }
        else if (entity.tickCount % 8 == 0) {
            ParticleBuilder.forPositionsInCircle(AoAParticleTypes.ORB.get(), pos, forward, 0.25f, 32)
                    .lifespan(20)
                    .scaleMod(0.4f)
                    .colourTint(1, 1, 1, 0.5f)
                    .addTransition(AwayFromPositionParticleTransition.create(pos.subtract(forward.scale(0.1f)), 3))
                    .sendToAllPlayersTrackingEntity(entity);
        }
        else {
            ParticleBuilder.forRandomPosInSphere(ParticleTypes.DUST_PLUME, entity.getEyePosition(), 0.05f)
                    .lifespan(20)
                    .spawnNTimes(20)
                    .addTransition(AwayFromPositionParticleTransition.create(entity.getEyePosition(), 10))
                    .sendToAllPlayersTrackingEntity(entity);
        }
    }

    @Override
    protected void stop(T entity) {
        AoAScheduler.schedule(3, tick -> SoundBuilder.following(AoASounds.ENTITY_TYROSAUR_ROAR_STOP, entity).play());
        entity.setAttackState(TyrosaurEntity.BITE);
        entity.setImmobile(false);
        entity.triggerAnim("Main", "roar_stop");
    }
}