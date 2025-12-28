package net.tslat.aoa3.content.entity.boss.tyrosaur;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.neoforged.neoforge.common.ItemAbilities;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.entity.AoAMobEffects;
import net.tslat.aoa3.content.entity.ai.mob.AnimatableMeleeAttack;
import net.tslat.aoa3.content.entity.boss.AoABoss;
import net.tslat.aoa3.content.entity.boss.ArmouredBoss;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyLivingEntitySensor;
import net.tslat.aoa3.content.entity.brain.sensor.AggroBasedNearbyPlayersSensor;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.integration.tes.TESIntegration;
import net.tslat.aoa3.library.builder.MultipartBuilder;
import net.tslat.aoa3.util.AttributeUtil;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.MathUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.ReactToUnreachableTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import net.tslat.smartbrainlib.api.core.sensor.custom.UnreachableTargetSensor;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.HurtBySensor;
import net.tslat.smartbrainlib.registry.SBLMemoryTypes;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.object.builder.EffectBuilder;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.sound.SoundBuilder;
import net.tslat.tme.api.util.RandomUtil;
import net.tslat.tme.internal.particle.transition.AwayFromPositionParticleTransition;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.PlayState;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.List;

public class TyrosaurEntity extends AoABoss implements ArmouredBoss {
    private static final float ARMOUR_MODIFIER = 60;
    private static final float MAX_ARMOUR = 600f;
    public static final EntityDataAccessor<Float> ARMOUR = makeSynchedData(TyrosaurEntity.class, EntityDataSerializers.FLOAT);
    private static final RawAnimation ROAR_START_ANIM = RawAnimation.begin().thenPlay("misc.roar.start");
    private static final RawAnimation ROAR_ANIM = RawAnimation.begin().thenPlay("misc.roar.hold");
    private static final RawAnimation ROAR_STOP_ANIM = RawAnimation.begin().thenPlay("misc.roar.stop");
    static final int BITE = 0;
    static final int HORN = 1;
    static final int SLAM = 2;
    static final int ROAR = 3;

    public TyrosaurEntity(EntityType<? extends TyrosaurEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Nullable
    @Override
    public MultipartBuilder<? extends TyrosaurEntity> definePartEntities() {
        return MultipartBuilder.of(this,
                                   MultipartBuilder.Part.sized(0.875f, getBbHeight() - 0.625f).up(0.5f).adjacentBehind().then(
                                           MultipartBuilder.Part.sized(0.5625f, 0.5625f).down(0.0625f).adjacentBehind().damageMod(0.85f).then(
                                                   MultipartBuilder.Part.sized(0.5625f, 0.5625f).down(0.0625f).adjacentBehind().damageMod(0.5f))),
                                   MultipartBuilder.Part.sized(0.875f, getBbHeight() - 0.625f).up(0.5f).adjacentForward().then(
                                           MultipartBuilder.Part.<TyrosaurEntity>sized(1, getBbHeight() - 0.75f).adjacentAbove().constructor(SkullPlateMultipart::new)).then(
                                                   MultipartBuilder.Part.sized(0.6875f, 1).adjacentForward()));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);

        builder.define(ARMOUR, MAX_ARMOUR);
    }

    @Override
    protected void addSwingData(SwingData swings) {
        swings.put(BITE, new SwingData.Swing(11, 5, DefaultAnimations.ATTACK_BITE));
        swings.put(HORN, new SwingData.Swing(13, 6, DefaultAnimations.ATTACK_THROW));
    }

    @Nullable
    @Override
    public SoundEvent getMusic() {
        return AoASounds.TYROSAUR_MUSIC.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_TYROSAUR_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_TYROSAUR_HURT.get();
    }

    @Override
    protected float getStepWeight() {
        return 4f;
    }

    @Override
    protected boolean isQuadruped() {
        return true;
    }

    @Override
    protected int getPreAttackTime() {
        return getSwingWarmupTicks();
    }

    @Override
    protected int getAttackSwingDuration() {
        return getSwingDurationTicks();
    }

    @Override
    public float getArmourPercent() {
        return Mth.clamp(getSynchedData(ARMOUR) / MAX_ARMOUR, 0, 1);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        updateArmour(getArmourPercent() * MAX_ARMOUR);

        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    public List<ExtendedSensor<? extends AoABoss>> getSensors() {
        return ObjectArrayList.of(
                new AggroBasedNearbyPlayersSensor<>(),
                new AggroBasedNearbyLivingEntitySensor<AoABoss>().setPredicate((target, entity) -> target instanceof OwnableEntity tamedEntity && tamedEntity.getOwnerUUID() != null).setScanRate(entity -> 40),
                new HurtBySensor<>(),
                new UnreachableTargetSensor<>()
        );
    }

    @Override
    public BrainActivityGroup<? extends AoABoss> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>().invalidateIf((entity, target) -> !DamageUtil.isAttackable(target) || distanceToSqr(target.position()) > Mth.square(getAttributeValue(Attributes.FOLLOW_RANGE))),
                new SetWalkTargetToAttackTarget<>().speedMod((entity, target) -> 1.25f),
                new FirstApplicableBehaviour<>(
                        new ReactToUnreachableTarget<>()
                                .timeBeforeReacting(entity -> 80)
                                .reaction((entity, isTowering) -> BrainUtils.clearMemory(entity, SBLMemoryTypes.SPECIAL_ATTACK_COOLDOWN.get()))
                                .cooldownFor(entity -> 80),
                        new OneRandomBehaviour<>(
                                Pair.of(new AnimatableMeleeAttack<>(getSwingWarmupTicks(BITE))
                                                .attackInterval(entity -> getSwingDurationTicks(BITE) + 2)
                                                .whenStarting(entity -> setAttackState(BITE)), 30),
                                Pair.of(new AnimatableMeleeAttack<>(getSwingWarmupTicks(HORN))
                                                .attackEffect((entity, target) -> {
                                                    target.setDeltaMovement(MathUtil.getEyelineForward(entity).scale(-0.75f).add(0, 1.5f, 0));
                                                    EntityUtil.applyPotions(target, entity, new EffectBuilder(AoAMobEffects.BLEEDING, 200));
                                                })
                                                .attackInterval(entity -> getSwingDurationTicks(HORN) + 2)
                                                .whenStarting(entity -> setAttackState(HORN)), 5),
                                Pair.of(new TyrosaurRoarBehaviour<>(17)
                                                .startCondition(entity -> !BrainUtils.isOnSpecialCooldown(entity))
                                                .runFor(entity -> BrainUtils.hasMemory(entity, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE) ? RandomUtil.numberBetween(160, 200) : RandomUtil.numberBetween(100, 200))
                                                .cooldownFor(entity -> BrainUtils.hasMemory(entity, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE) ? RandomUtil.numberBetween(80, 140) : RandomUtil.numberBetween(400, 800))
                                                .whenStopping(entity -> BrainUtils.setSpecialCooldown(entity, 150)), 1),
                                Pair.of(new TyrosaurEarthquakeBehaviour<>(8)
                                                .startCondition(entity -> !BrainUtils.isOnSpecialCooldown(entity) && !BrainUtils.hasMemory(entity, SBLMemoryTypes.TARGET_UNREACHABLE.get()))
                                                .cooldownFor(entity -> RandomUtil.numberBetween(200, 400))
                                                .whenStopping(entity -> BrainUtils.setSpecialCooldown(entity, 100)), 3))
                ));
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (getSynchedData(ARMOUR) > 0 && !level().isClientSide && !DamageUtil.isEnvironmentalDamage(source)) {
            float armourDamage = amount;

            if (source.is(DamageTypeTags.IS_PROJECTILE) && !DamageUtil.isMagicDamage(source) && !source.is(AoADamageTypes.HEAVY_GUN))
                armourDamage *= 0.25f;

            if (!source.is(DamageTypeTags.BYPASSES_ARMOR)) {
                if (DamageUtil.isMeleeDamage(source) && source.getDirectEntity() instanceof LivingEntity attacker) {
                    ItemStack weapon = attacker.getMainHandItem();

                    if (weapon.canPerformAction(ItemAbilities.PICKAXE_DIG)) {
                        armourDamage *= 1 + (weapon.getDestroySpeed(Blocks.STONE.defaultBlockState()) * 0.075f) + (float)attacker.getAttributeValue(Attributes.MINING_EFFICIENCY) * 0.04f;
                    }
                    else {
                        armourDamage *= 0.5f;
                    }
                }
                else if (source.is(DamageTypeTags.IS_EXPLOSION)) {
                    armourDamage *= 1.25f;
                }

                if (IntegrationManager.isTESActive())
                    TESIntegration.sendParticle(this, armourDamage, 0xFFAAAAAA);

                ParticleBuilder.forRandomPosInEntity(ParticleTypes.CRIT, this)
                        .spawnNTimes(50)
                        .sendToAllPlayersTrackingEntity(this);

                updateArmour(getSynchedData(ARMOUR) - armourDamage);
            }
        }

        return super.hurt(source, amount);
    }

    private void updateArmour(float newArmour) {
        newArmour = Mth.clamp(newArmour, 0, MAX_ARMOUR);
        int oldArmourStage = Mth.ceil(getSynchedData(ARMOUR) / MAX_ARMOUR * 4);
        int newArmourStage = Mth.ceil(newArmour / MAX_ARMOUR * 4);

        setSynchedData(ARMOUR, newArmour);

        float armourPercent = getArmourPercent();

        AttributeUtil.applyTransientModifier(this, Attributes.ARMOR, getArmourMod(armourPercent));
        AttributeUtil.applyTransientModifier(this, Attributes.ARMOR_TOUGHNESS, getArmourToughnessMod(armourPercent));

        if (oldArmourStage != newArmourStage) {
            SoundBuilder.following(AoASounds.STONE_CRUMBLE, this)
                    .play();
            ParticleBuilder.forRandomPosInEntity(ParticleTypes.DUST_PLUME, this)
                    .lifespan(20)
                    .colourTint(0.5f, 0.5f, 0.5f, 1f)
                    .spawnNTimes(20)
                    .addTransition(AwayFromPositionParticleTransition.create(position().add(0, getBbHeight() * 0.5f, 0), 10))
                    .sendToAllPlayersTrackingEntity(this);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);

        compound.putFloat("ArmourPercent", getArmourPercent());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);

        if (compound.contains("ArmourPercent", Tag.TAG_FLOAT))
            updateArmour(compound.getFloat("ArmourPercent") * MAX_ARMOUR);
    }

    @Override
    protected AABB getAttackBoundingBox() {
        AABB boundingBox = getBoundingBox();

        if (getVehicle() != null) {
            AABB vehicleBounds = getVehicle().getBoundingBox();
            boundingBox = new AABB(
                    Math.min(boundingBox.minX, vehicleBounds.minX),
                    boundingBox.minY,
                    Math.min(boundingBox.minZ, vehicleBounds.minZ),
                    Math.max(boundingBox.maxX, vehicleBounds.maxX),
                    boundingBox.maxY,
                    Math.max(boundingBox.maxZ, vehicleBounds.maxZ)
            );
        }

        return boundingBox.inflate(1.65f, 0, 1.65f);
    }

    public static AoAEntityStats.AttributeBuilder entityStats(EntityType<TyrosaurEntity> entityType) {
        return AoAEntityStats.AttributeBuilder.createMonster(entityType)
                .health(635)
                .moveSpeed(0.2875f)
                .meleeStrength(15)
                .knockbackResist(0.9)
                .followRange(128)
                .aggroRange(128)
                .armour(10, 15)
                .knockback(1f)
                .stepHeight(1.25f);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Main", 0, state -> {
            if (isAttackState(ROAR))
                return state.setAndContinue(ROAR_ANIM);

            if (state.isMoving())
                return state.setAndContinue(isSprinting() ? DefaultAnimations.RUN : DefaultAnimations.WALK);

            return state.setAndContinue(DefaultAnimations.IDLE);
        }).triggerableAnim("roar_start", ROAR_START_ANIM)
                                .triggerableAnim("roar_stop", ROAR_STOP_ANIM)
                                .triggerableAnim("slam", DefaultAnimations.ATTACK_SLAM));
        controllers.add(new AnimationController<GeoAnimatable>(this, "Attack", 0, state -> {
            if (this.swinging)
                return state.setAndContinue(getSwingAnimation());

            state.resetCurrentAnimation();

            return PlayState.STOP;
        }));
    }

    private static AttributeModifier getArmourMod(float percent) {
        return new AttributeModifier(AdventOfAscension.id("tyrosaur_armour"), ARMOUR_MODIFIER * percent, AttributeModifier.Operation.ADD_VALUE);
    }

    private static AttributeModifier getArmourToughnessMod(float percent) {
        return new AttributeModifier(AdventOfAscension.id("tyrosaur_armour_toughness"), ARMOUR_MODIFIER * percent, AttributeModifier.Operation.ADD_VALUE);
    }
}