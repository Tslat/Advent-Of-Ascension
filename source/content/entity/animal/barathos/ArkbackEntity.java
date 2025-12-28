package net.tslat.aoa3.content.entity.animal.barathos;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.schedule.Activity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.Tags;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.custom.AoAWorldEvents;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.base.AoAAnimal;
import net.tslat.aoa3.content.world.event.AoAWorldEventManager;
import net.tslat.aoa3.content.world.event.BarathosSandstormEvent;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.aoa3.util.InventoryUtil;
import net.tslat.aoa3.util.LootUtil;
import net.tslat.aoa3.util.MathUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.HeldBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.registry.SBLMemoryTypes;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.util.EntityRetrievalUtil;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.List;
import java.util.Map;

public class ArkbackEntity extends AoAAnimal<ArkbackEntity> {
    private static final RawAnimation EAT_ANIM = RawAnimation.begin().thenPlay("misc.eat");
    private static final RawAnimation REST_START_ANIM = RawAnimation.begin().thenPlay("misc.rest.start");
    private static final RawAnimation REST_STOP_ANIM = RawAnimation.begin().thenPlay("misc.shake").thenPlay("misc.rest.stop");
    public static final int MAX_SAND = 29;
    public static final EntityDataAccessor<Boolean> RESTING = makeSynchedData(ArkbackEntity.class, EntityDataSerializers.BOOLEAN);
    public static final EntityDataAccessor<Integer> SAND_LEVEL = makeSynchedData(ArkbackEntity.class, EntityDataSerializers.INT);

    public ArkbackEntity(EntityType<? extends ArkbackEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(RESTING, false);
        builder.define(SAND_LEVEL, 0);
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
    public boolean canBeCollidedWith() {
        return true;
    }

    @Override
    protected float getStepWeight() {
        return 6f;
    }

    @Nullable
    @Override
    protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
        return AoASounds.ENTITY_GENERIC_HEAVY_STEP.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_ARKBACK_AMBIENT.get();
    }

    @Override
    public int getAmbientSoundInterval() {
        return 480;
    }

    @Override
    public BrainActivityGroup<? extends ArkbackEntity> getIdleTasks() {
        return BrainActivityGroup.idleTasks(
                new FirstApplicableBehaviour<>(
                        new RestBehaviour(),
                        new OneRandomBehaviour<>(
                                Pair.of(new SetRandomWalkTarget<>()
                                        .setRadius(30, 10)
                                        .speedModifier(0.9f), 20),
                                Pair.of(new Idle<>()
                                        .runFor(entity -> entity.getRandom().nextInt(60, 240)), 20),
                                Pair.of(new EatBehaviour(), 1))
                                .stopIf(entity -> BrainUtils.hasMemory(entity, MemoryModuleType.HURT_BY_ENTITY) && !BrainUtils.hasMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get()))));
    }

    @Override
    public Map<Activity, BrainActivityGroup<? extends ArkbackEntity>> getAdditionalTasks() {
        return new Object2ObjectOpenHashMap<>(0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        compound.putInt("SandLevel", getSynchedData(SAND_LEVEL));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("SandLevel", Tag.TAG_INT))
            setSandLevel(compound.getInt("SandLevel"));
    }

    @Override
    public InteractionResult interactAt(Player player, Vec3 pos, InteractionHand hand) {
        if (getSandLevel() <= 0 || !getSynchedData(RESTING))
            return InteractionResult.PASS;

        if (!player.getItemInHand(hand).canPerformAction(ItemAbilities.SHOVEL_DIG))
            return InteractionResult.PASS;

        if (pos.y < getBbHeight())
            return InteractionResult.PASS;

        if (player instanceof ServerPlayer pl)
            InventoryUtil.giveItemsTo(pl, LootUtil.generateLoot(AdventOfAscension.id("misc/arkback_harvest"), new LootParams.Builder(pl.serverLevel()).withParameter(LootContextParams.ORIGIN, pos).withOptionalParameter(LootContextParams.THIS_ENTITY, pl).create(LootContextParamSets.ARCHAEOLOGY)));

        if (!level().isClientSide)
            setSandLevel(getSandLevel() - 5);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void tick() {
        super.tick();

        if (!getSynchedData(RESTING)) {
            for (Entity entity : EntityRetrievalUtil.<Entity>getEntities(level(), AABB.ofSize(position().add(0, getBbHeight(), 0), getBbWidth(), 1.5f * getScale(), getBbWidth()), entity -> entity != this && entity.onGround() && entity.getY() >= getY() + getBbHeight())) {
                entity.setDeltaMovement(MathUtil.getBodyForward(this).scale(-0.4f));
                entity.hurtMarked = true;
            }
        }
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();

        if (getSandLevel() < MAX_SAND && RandomUtil.oneInNChance(210) && AoAWorldEventManager.getEventById(level(), AoAWorldEvents.BARATHOS_SANDSTORM.getId()) instanceof BarathosSandstormEvent sandstorm && sandstorm.isActive() && getY() >= 90 && level().getBrightness(LightLayer.SKY, blockPosition()) == 15)
            setSandLevel(getSandLevel() + 1);

        if (onGround() && getDeltaMovement().horizontalDistanceSqr() > 0) {
            for (LivingEntity entity : EntityRetrievalUtil.getEntities(level(), getBoundingBox().inflate(0.5f).move(getDeltaMovement()), LivingEntity.class, entity -> entity != this && entity.getBbHeight() < getBbHeight() && entity.getBbWidth() < getBbWidth())) {
                entity.hurt(damageSources().cramming(), 3 * level().getDifficulty().getId());
            }
        }
    }

    public void setSandLevel(int sandLevel) {
        setSynchedData(SAND_LEVEL, Mth.clamp(sandLevel, 0, MAX_SAND));
    }

    public int getSandLevel() {
        return getSynchedData(SAND_LEVEL);
    }

    public static AoAEntityStats.AttributeBuilder entityStats(EntityType<ArkbackEntity> entityType) {
        return AoAEntityStats.AttributeBuilder.create(entityType)
                .health(130)
                .knockbackResist(1)
                .armour(50, 75)
                .moveSpeed(0.22f);
    }

    public static SpawnPlacements.SpawnPredicate<ArkbackEntity> spawnRules(EntityType<ArkbackEntity> entityType) {
        return EntitySpawnConditions.create(entityType).minLightLevel(8).onlySpawnOn(Tags.Blocks.SANDS).betweenYLevels(AoADimensions.BARATHOS, 85, 110).spawnChance(1 / 1000f);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Main", 0, state -> {
            if (getSynchedData(RESTING))
                return state.setAndContinue(DefaultAnimations.REST);

            return state.setAndContinue(state.isMoving() ? DefaultAnimations.WALK : DefaultAnimations.IDLE);
        })
                .triggerableAnim("rest_start", REST_START_ANIM)
                .triggerableAnim("rest_stop", REST_STOP_ANIM));
        controllers.add(new AnimationController<GeoAnimatable>(this, "Eating", 0, state -> level().getGameTime() - this.lastDamageStamp < 40L ? PlayState.STOP : PlayState.CONTINUE)
                .receiveTriggeredAnimations()
                .triggerableAnim("Eat", EAT_ANIM));
    }

    private static class RestBehaviour extends HeldBehaviour<ArkbackEntity> {
        private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = List.of(Pair.of(MemoryModuleType.HURT_BY_ENTITY, MemoryStatus.VALUE_PRESENT));

        private int shakeTick = -1;

        public RestBehaviour() {
            runFor(entity -> 180 + entity.random.nextInt(100));
        }

        @Override
        public List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
            return MEMORY_REQUIREMENTS;
        }

        @Override
        protected boolean checkExtraStartConditions(ServerLevel level, ArkbackEntity entity) {
            return !entity.getCombatTracker().entries.isEmpty() && entity.getCombatTracker().entries.getLast().damage() > 4;
        }

        @Override
        protected void start(ArkbackEntity entity) {
            entity.setImmobile(true);
            entity.triggerAnim("Main", "rest_start");
        }

        @Override
        protected void stop(ArkbackEntity entity) {
            entity.setImmobile(false);
            this.shakeTick = -1;
            BrainUtils.setSpecialCooldown(entity, 600);
        }

        @Override
        protected void tick(ArkbackEntity entity) {
            if (this.runningTime == 25) {
                entity.setSynchedData(RESTING, true);
            }
            else if (this.shakeTick >= 0) {
                if (this.shakeTick++ > 16) {
                    entity.setSynchedData(RESTING, false);
                }
                else {
                    Vec3 shakeDirection = MathUtil.getBodyRight(entity).scale(Mth.floor(Mth.sin(45 * this.shakeTick) * 2)).add(0, 1, 0);
                    AABB plateBounds = AABB.ofSize(entity.position().add(0, entity.getBbHeight(), 0), entity.getBbWidth(), 1.5f * entity.getScale(), entity.getBbWidth());

                    for (Entity rider : EntityRetrievalUtil.<Entity>getEntities(entity.level(), plateBounds, rider -> rider != entity)) {
                        rider.setDeltaMovement(shakeDirection);
                        rider.hurtMarked = true;
                    }

                    if (this.shakeTick < 10 && entity.getSandLevel() > 0) {
                        float sine = Mth.sin(45 * this.shakeTick);

                        if (Mth.abs(sine) >= 0.9f) {
                            TMEParticlePacket packet = new TMEParticlePacket();
                            shakeDirection = MathUtil.getBodyRight(entity).scale(sine * 0.25f).add(0, 0.25f, 0);
                            plateBounds = plateBounds.move(0, 0.5f, 0);

                            for (int i = 0; i < 100; i++) {
                                packet.particle(ParticleBuilder.forRandomPosInBounds(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.SAND.defaultBlockState()), plateBounds)
                                        .velocity(shakeDirection.add(entity.random.nextGaussian() * 0.05f, entity.random.nextGaussian() * 0.05f, entity.random.nextGaussian() * 0.05f))
                                        .scaleMod((float)entity.random.nextGaussian() * 0.4f + 0.8f));
                                packet.particle(ParticleBuilder.forRandomPosInBounds(new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.SAND.defaultBlockState()), plateBounds)
                                        .velocity(shakeDirection.scale(0.1f).add(entity.random.nextGaussian() * 0.05f, entity.random.nextGaussian() * 0.05f, entity.random.nextGaussian() * 0.05f))
                                        .scaleMod(entity.random.nextFloat() * 0.4f + 0.3f));
                            }

                            packet.sendToAllPlayersTrackingEntity(entity);
                            entity.setSandLevel(entity.getSandLevel() - 10);
                        }
                    }
                }
            }

            if (this.shakeTick == -1 && timedOut(entity.level().getGameTime() + 37)) {
                this.shakeTick = 0;
                entity.triggerAnim("Main", "rest_stop");
            }
        }
    }

    private static class EatBehaviour extends HeldBehaviour<ArkbackEntity> {
        private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = List.of(Pair.of(MemoryModuleType.HURT_BY, MemoryStatus.VALUE_ABSENT));

        public EatBehaviour() {
            runFor(entity -> 103);
            stopIf(arkback -> arkback.level().getGameTime() - arkback.lastDamageStamp < 40);
        }

        @Override
        protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
            return MEMORY_REQUIREMENTS;
        }

        @Override
        protected boolean checkExtraStartConditions(ServerLevel level, ArkbackEntity entity) {
            return !entity.getSynchedData(RESTING) && level.getBlockState(BlockPos.containing(entity.position().add(MathUtil.getBodyForward(entity).scale(entity.getBbWidth())).subtract(0, 1, 0))).isSolid();
        }

        @Override
        protected void start(ArkbackEntity entity) {
            entity.setImmobile(true);
            entity.triggerAnim("Eating", "Eat");
        }

        @Override
        protected void stop(ArkbackEntity entity) {
            entity.setImmobile(false);
        }

        @Override
        protected void tick(ArkbackEntity entity) {
            if (this.runningTime == 75 || this.runningTime == 90) {
                SoundBuilder.following(SoundEvents.CAMEL_EAT, entity)
                        .varyPitch(0.2f)
                        .play();
                ParticleBuilder.forPositions(new ItemParticleOption(ParticleTypes.ITEM, AoABlocks.BARON_SAND.toStack()), entity.position().add(MathUtil.getBodyForward(entity).scale(2)).add(0, entity.getBbHeight() / 2f, 0))
                        .spawnNTimes(Mth.ceil(RandomUtil.valueBetween(10, 20)))
                        .sendToAllPlayersTrackingEntity(entity);

                if (entity.getHealth() < entity.getMaxHealth()) {
                    float heal = Math.min(entity.getMaxHealth() - entity.getHealth(), (float)RandomUtil.valueBetween(7, 12));

                    entity.heal(heal);
                    ParticleBuilder.forRandomPosInEntity(ParticleTypes.HEART, entity)
                            .spawnNTimes(Mth.ceil(heal))
                            .sendToAllPlayersTrackingEntity(entity);
                }
            }
        }
    }
}
