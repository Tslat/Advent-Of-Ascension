package net.tslat.aoa3.content.entity.monster.barathos;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.networking.AoANetworking;
import net.tslat.aoa3.common.networking.packets.EntityRidePlayerPacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.ai.movehelper.AirborneMoveControl;
import net.tslat.aoa3.content.entity.ai.temp.SetWalkTargetToAttackTarget;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.base.FlyingEntity;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomFlyingTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.smartbrainlib.api.core.navigation.SmoothFlyingPathNavigation;
import net.tslat.smartbrainlib.object.MemoryTest;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.animation.AnimationController;
import software.bernie.geckolib.animation.RawAnimation;
import software.bernie.geckolib.constant.DefaultAnimations;

import java.util.List;

public class TharaflyEntity extends AoAMeleeMob<TharaflyEntity> implements FlyingEntity {
	private static final RawAnimation LIFTOFF_ANIM = RawAnimation.begin().thenPlay("misc.fly.take_off");
	private static final RawAnimation LANDING_ANIM = RawAnimation.begin().thenPlay("misc.fly.land");
	private static final RawAnimation INJECT_ANIM = RawAnimation.begin().thenPlay("attack.inject");

    private final MoveControl groundMoveControl = new MoveControl(this);
    private final MoveControl airborneMoveControl;
    private PathNavigation flyingPathNavigation;
    private PathNavigation groundPathNavigation;

	public TharaflyEntity(EntityType<? extends TharaflyEntity> entityType, Level world) {
		super(entityType, world);

        this.airborneMoveControl = this.moveControl;
	}

    @Override
    protected MoveControl createMoveControl() {
        return new AirborneMoveControl(this);
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        this.flyingPathNavigation = new SmoothFlyingPathNavigation(this, level);

        this.flyingPathNavigation.setCanFloat(true);
        this.groundPathNavigation = super.createNavigation(level);

        return this.groundPathNavigation;
    }

    @Override
	public BrainActivityGroup<? extends TharaflyEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new TargetOrRetaliate<>()
						.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
						.attackablePredicate(target -> DamageUtil.isAttackable(target) && !isAlliedTo(target)),
                new FirstApplicableBehaviour<>(
                        new OneRandomBehaviour<>(
                                new SetRandomFlyingTarget<>()
                                        .verticalWeight(entity -> 0)
                                        .setRadius(20, 8)
                                        .speedModifier(0.9f),
                                new Idle<>()
                                        .startCondition(Entity::isNoGravity)
                                        .whenStarting(entity -> entity.setNoGravity(true))
                                        .runFor(entity -> rand().numberBetween(30, 60))
                                        .whenStopping(entity -> entity.setNoGravity(false))
                                        .stopIf(Entity::onGround)
                        ).startCondition(entity -> this.navigation == this.flyingPathNavigation),
                        new OneRandomBehaviour<>(
                                new SetRandomWalkTarget<>()
                                        .speedModifier(0.9f),
                                new Idle<>()
                                        .runFor(entity -> entity.getRandom().nextInt(30, 60))
                                        .stopIf(entity -> !entity.onGround())
                        ).startCondition(entity -> this.navigation == this.groundPathNavigation)
                ));
	}

    @Override
    public BrainActivityGroup<? extends TharaflyEntity> getFightTasks() {
        return BrainActivityGroup.fightTasks(
                new InvalidateAttackTarget<>().invalidateIf((entity, target) -> !DamageUtil.isAttackable(target) || distanceToSqr(target.position()) > Mth.square(getAttributeValue(Attributes.FOLLOW_RANGE))),
                new SetWalkTargetToAttackTarget<>(),
                new OneRandomBehaviour<>(
                        new AnimatableMeleeAttack<>(getPreAttackTime())
                                .attackInterval(entity -> getAttackSwingDuration() + 2),
                        new TharaflyAttachBehaviour()
                                .startCondition(tharafly -> this.navigation == this.flyingPathNavigation)
                                .runFor(tharafly -> rand().numberBetween(100, 200))
                                .cooldownFor(tharafly -> rand().numberBetween(100, 200))
                ));
    }

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return AoASounds.ENTITY_MEGANEUROPSIS_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_MEGANEUROPSIS_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return onGround() ? null : AoASounds.ENTITY_MEGANEUROPSIS_AMBIENT.get();
	}

    @Override
    protected int getAttackSwingDuration() {
        return 9 + 5;
    }

    @Override
    protected int getPreAttackTime() {
        return 9;
    }

    public static SpawnPlacements.SpawnPredicate<TharaflyEntity> spawnRules(EntityType<TharaflyEntity> entityType) {
		return EntitySpawnConditions.createDayNightMonster(entityType).noLowerThanY(AoADimensions.BARATHOS, 65).difficultyBasedSpawnChance(0.05f);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<TharaflyEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(26)
				.meleeStrength(4)
				.moveSpeed(0.25f)
				.flyingSpeed(0.33f)
				.aggroRange(32)
				.armour(3)
                .jumpStrength(0.75f)
				.followRange(64);
	}

    @Override
    protected boolean isQuadruped() {
        return true;
    }

    @Override
    protected float getStepWeight() {
        return 0.1f;
    }

    @Override
    protected float nextStep() {
        return this.moveDist + 0.25f;
    }

    @Override
    public boolean onClimbable() {
        if (this.navigation == this.flyingPathNavigation)
            return false;

        return super.onClimbable();
    }

    @Override
    protected void spawnSprintParticle() {
        if (this.navigation == this.groundPathNavigation)
            super.spawnSprintParticle();
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {
        if (this.navigation == this.groundPathNavigation)
            super.playStepSound(pos, block);
    }

    @Override
    protected float getFlyingSpeed() {
        return getSpeed();
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
        return false;
    }

    @Override
    protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {}

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();

        if (this.onGround()) {
            if (this.moveControl != this.groundMoveControl) {
                triggerAnim("Movement", "Landing");
            }
            else if ((getTarget() == null || distanceToSqr(getTarget()) > 4) && RandomUtil.oneInNChance(100)) {
                jumpFromGround();
            }

            this.moveControl = this.groundMoveControl;
            this.navigation = this.groundPathNavigation;
        }
        else {
            if (this.moveControl != this.airborneMoveControl)
                triggerAnim("Movement", "Liftoff");

            this.moveControl = this.airborneMoveControl;
            this.navigation = this.flyingPathNavigation;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (getVehicle() != null)
            setYRot(getVehicle().getYRot());
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (onGround()) {
            super.travel(travelVector);
        }
        else {
            travelFlying(this, travelVector);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Movement", 0, state -> {
            if (getVehicle() != null)
                return state.setAndContinue(INJECT_ANIM);

            if (onGround() || (isNoAi() && !getBlockStateOn().isFaceSturdy(level(), getOnPos(), Direction.UP)))
                return state.setAndContinue(state.isMoving() ? DefaultAnimations.WALK : DefaultAnimations.IDLE);

            return state.setAndContinue(DefaultAnimations.FLY);
        }).triggerableAnim("Liftoff", LIFTOFF_ANIM).triggerableAnim("Landing", LANDING_ANIM));
        controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_STRIKE));
    }

    public static class TharaflyAttachBehaviour extends ExtendedBehaviour<TharaflyEntity> {
        private static final MemoryTest MEMORY_REQUIREMENTS = MemoryTest.builder(2).hasMemory(MemoryModuleType.ATTACK_TARGET).noMemory(MemoryModuleType.ATTACK_COOLING_DOWN);

        @Nullable
        protected Player target = null;
        protected int shakes = 0;
        protected boolean wasCrouching = false;

        @Override
        protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
            return MEMORY_REQUIREMENTS;
        }

        @Override
        protected boolean checkExtraStartConditions(ServerLevel level, TharaflyEntity entity) {
            LivingEntity target = BrainUtils.getTargetOfEntity(entity);

            if (!(target instanceof Player pl))
                return false;

            this.target = pl;

            return !this.target.isVehicle() && entity.getSensing().hasLineOfSight(this.target) && entity.isWithinMeleeAttackRange(this.target);
        }

        @Override
        protected void start(TharaflyEntity entity) {
            this.wasCrouching = this.target.isCrouching();

            entity.startRiding(this.target, true);
            AoANetworking.sendToAllPlayersTrackingEntity(new EntityRidePlayerPacket(entity.getId(), this.target.getUUID(), EntityRidePlayerPacket.Operation.MOUNT), entity);
        }

        @Override
        protected boolean shouldKeepRunning(TharaflyEntity entity) {
            return this.target != null && this.target.getFirstPassenger() == entity;
        }

        @Override
        protected void tick(TharaflyEntity entity) {
            if (entity.tickCount % 10 == 0)
                entity.doHurtTarget(this.target);

            if (this.target.isCrouching() != this.wasCrouching) {
                this.wasCrouching = this.target.isCrouching();

                if (this.shakes++ >= 10)
                    doStop((ServerLevel)entity.level(), entity, entity.level().getGameTime());
            }
        }

        @Override
        protected void stop(TharaflyEntity entity) {
            if (this.target != null)
                AoANetworking.sendToAllPlayersTrackingEntity(new EntityRidePlayerPacket(entity.getId(), this.target.getUUID(), EntityRidePlayerPacket.Operation.DISMOUNT), entity);

            if (this.target == null || this.target == entity.getVehicle())
                entity.stopRiding();

            BrainUtils.setForgettableMemory(entity, MemoryModuleType.ATTACK_COOLING_DOWN, true, 40);

            this.target = null;
            this.shakes = 0;
            this.wasCrouching = false;
        }
    }
}
