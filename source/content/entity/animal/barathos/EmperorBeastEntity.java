package net.tslat.aoa3.content.entity.animal.barathos;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoAAnimal;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.library.builder.MultipartBuilder;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.MathUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.DelayedBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.object.MemoryTest;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.internal.particle.transition.AwayFromPositionParticleTransition;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.List;

import static net.tslat.aoa3.library.builder.MultipartBuilder.Part;

public class EmperorBeastEntity extends AoAAnimal<EmperorBeastEntity> {
    public EmperorBeastEntity(EntityType<? extends EmperorBeastEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public MultipartBuilder<? extends EmperorBeastEntity> definePartEntities() {
        return MultipartBuilder.of(this,
                                   Part.sized(3, 6).adjacentAbove().forward(1).damageMod(1.1f),
                                   Part.sized(getBbWidth(), getBbHeight()).forward(0.01f),
                                   Part.sized(getBbWidth(), getBbHeight()).back(0.01f));
    }

    @Override
    public BrainActivityGroup<? extends EmperorBeastEntity> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<>(
                        new EmperorBeastStomp(100)
                                .cooldownFor(entity -> 40),
                        new OneRandomBehaviour<>(
                                new SetRandomWalkTarget<>()
                                        .setRadius(30, 10)
                                        .speedModifier(0.9f),
                                new Idle<>()
                                        .runFor(entity -> entity.getRandom().nextInt(60, 240)))));
    }

    @Override
    protected boolean isQuadruped() {
        return true;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockState) {
        if (!blockState.liquid()) {
            BlockState state = level().getBlockState(pos.above());
            SoundType blockSound = state.getBlock() == Blocks.SNOW ? state.getSoundType(level(), pos, this) : blockState.getSoundType(level(), pos, this);
            SoundEvent stepSound = blockSound.getStepSound();
            SoundEvent stepSoundOverlay = getStepSound(pos, blockState);

            playStepSounds(stepSound, stepSoundOverlay);
        }
    }

    @Override
    protected float nextStep() {
        return this.moveDist + 1.85f;
    }

    @Override
    protected float getStepWeight() {
        return 6f;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isPushedByFluid() {
        return false;
    }

    @Override
    public int getHeadRotSpeed() {
        return 1;
    }

    @Nullable
    @Override
    protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
        return AoASounds.ENTITY_GENERIC_HEAVY_STEP.get();
    }

    @Override
    protected void pushEntities() {}

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();

        if (RandomUtil.oneInNChance(1000))
            triggerAnim("Living", "look_around");
    }

    public static AoAEntityStats.AttributeBuilder entityStats(EntityType<EmperorBeastEntity> entityType) {
        return AoAEntityStats.AttributeBuilder.create(entityType)
                .health(205)
                .knockbackResist(1)
                .armour(10, 40)
                .moveSpeed(0.22f)
                .stepHeight(1.9f);
    }

    public static SpawnPlacements.SpawnPredicate<EmperorBeastEntity> spawnRules(EntityType<EmperorBeastEntity> entityType) {
        return EntitySpawnConditions.create(entityType).minLightLevel(8).onlySpawnOn(Tags.Blocks.SANDS).betweenYLevels(AoADimensions.BARATHOS, 85, 110).spawnChance(1 / 1000f);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericWalkIdleController(this));
        controllers.add(new AnimationController<>(this, "Living", 0, state -> PlayState.STOP)
                                .triggerableAnim("look_around", DefaultAnimations.LIVING));
        controllers.add(new AnimationController<>(this, "Stomp", 0, state -> PlayState.STOP)
                                .triggerableAnim("stomp", DefaultAnimations.ATTACK_STOMP));
    }

    public static class EmperorBeastStomp extends DelayedBehaviour<EmperorBeastEntity> {
        private static final MemoryTest MEMORY_REQUIREMENTS = MemoryTest.builder().hasMemory(MemoryModuleType.NEAREST_LIVING_ENTITIES);

        public EmperorBeastStomp(int delayTicks) {
            super(delayTicks);
        }

        @Override
        protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
            return MEMORY_REQUIREMENTS;
        }

        @Override
        protected boolean shouldKeepRunning(EmperorBeastEntity entity) {
            return super.shouldKeepRunning(entity) && hasFeetTarget(entity);
        }

        @Override
        protected void doDelayedAction(EmperorBeastEntity entity) {
            entity.getNavigation().stop();
            entity.setImmobile(true);
            entity.triggerAnim("Stomp", "stomp");
            AoAScheduler.schedule(30, tick -> doStomp(entity));
        }

        @Override
        protected void stop(EmperorBeastEntity entity) {
            entity.setImmobile(false);
        }

        protected void doStomp(EmperorBeastEntity entity) {
            final float scale = entity.getScale();
            final Vec3 stompPos = entity.position().add(MathUtil.getEyelineForward(entity).scale(2.43f * scale).add(MathUtil.getEyelineRight(entity).scale(1.5f * scale)));
            final BlockState feetState = entity.level().getBlockState(BlockPos.containing(stompPos.subtract(0, 0.25f, 0)));

            SoundBuilder.at(AoASounds.ENTITY_TYROSAUR_STOMP, entity.level(), stompPos).varyPitch((float) RandomUtil.scaledGaussianValue(0.1f)).play();
            ParticleBuilder.forRandomPosInCircleRadius(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, stompPos, 0.2f)
                    .spawnNTimes(35)
                    .addBulkProperty(builder -> builder.scaleMod(RandomUtil.valueBetween(1, 1.25f)))
                    .addBulkProperty(builder -> builder.lifespan(RandomUtil.numberBetween(40, 60)))
                    .addTransition(AwayFromPositionParticleTransition.create(stompPos, 20))
                    .sendToAllPlayersTrackingEntity(entity);

            if (!feetState.isAir()) {
                ParticleBuilder.forRandomPosInCircleRadius(new BlockParticleOption(ParticleTypes.BLOCK, feetState), stompPos, 0.2f)
                        .spawnNTimes(50)
                        .scaleMod(2f)
                        .addBulkProperty(builder -> builder.velocity(RandomUtil.scaledGaussianValue(0.1f), RandomUtil.valueBetween(0.2f, 0.35f), RandomUtil.scaledGaussianValue(0.1f)))
                        .sendToAllPlayersTrackingEntity(entity);
            }

            for (LivingEntity nearby : EntityRetrievalUtil.getEntities(entity.level(), stompPos, entity.getBbWidth(), LivingEntity.class, target -> target != entity)) {
                DamageUtil.safelyDealDamage(DamageUtil.positionedEntityDamage(DamageTypes.MOB_ATTACK_NO_AGGRO, entity, stompPos), nearby, 10);
            }
        }

        protected boolean hasFeetTarget(EmperorBeastEntity entity) {
            final List<LivingEntity> nearbyEntities = BrainUtils.getMemory(entity, MemoryModuleType.NEAREST_LIVING_ENTITIES);

            if (nearbyEntities == null || nearbyEntities.isEmpty())
                return false;

            final float scale = entity.getScale();
            final AABB bounds = entity.getBoundingBox().inflate(1.5d * scale, 0.5d, 1.5d * scale);

            for (LivingEntity nearbyEntity : nearbyEntities) {
                if (!bounds.intersects(nearbyEntity.getBoundingBox()) && !bounds.contains(nearbyEntity.getEyePosition()))
                    continue;

                if (nearbyEntity.isInvisible())
                    continue;

                if (nearbyEntity instanceof OwnableEntity ownable) {
                    if (ownable.getOwnerUUID() == null)
                        continue;

                    if (ownable.getOwner() instanceof Player pl && pl.getAbilities().invulnerable)
                        continue;
                }
                else if (nearbyEntity instanceof Player pl && pl.getAbilities().invulnerable) {
                    continue;
                }

                return true;
            }

            return false;
        }
    }
}
