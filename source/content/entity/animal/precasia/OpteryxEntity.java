package net.tslat.aoa3.content.entity.animal.precasia;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.base.AoAAnimal;
import net.tslat.aoa3.content.entity.base.AoAEntityPart;
import net.tslat.aoa3.content.entity.brain.task.temp.FixedFollowParent;
import net.tslat.aoa3.content.entity.brain.task.temp.SetRandomFlyingTarget;
import net.tslat.aoa3.library.object.EntityDataHolder;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.BreedWithPartner;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FollowTemptation;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import java.util.UUID;

public class OpteryxEntity extends AoAAnimal<OpteryxEntity> implements FlyingAnimal, OwnableEntity {
	protected static final AttributeModifier EGG_HEALTH_MOD = new AttributeModifier(UUID.fromString("b8ccb450-74b3-44a9-9490-c71b329cb5fd"), "Baby Health Mod", -0.75f, AttributeModifier.Operation.MULTIPLY_TOTAL);
	public static final EntityDataHolder<Boolean> IS_EGG = EntityDataHolder.register(OpteryxEntity.class, EntityDataSerializers.BOOLEAN, false, entity -> entity.isEgg, (entity, value) -> entity.isEgg = value);
	protected static final EntityDimensions EGG_DIMENSIONS = EntityDimensions.fixed(0.375f, 0.5f);
	private static final RawAnimation TAKEOFF_ANIM = RawAnimation.begin().thenPlay("move.fly_start");
	private static final RawAnimation LANDING_ANIM = RawAnimation.begin().thenPlay("move.fly_stop");
	protected boolean isFlying = false;
	protected final FlyingPathNavigation flightNavigator;
	protected final PathNavigation walkNavigator;
	protected final FlyingMoveControl flightMoveControl;
	protected final MoveControl groundMoveControl;
	@Nullable
	protected UUID ownerId = null;

	protected boolean isEgg = false;

	public OpteryxEntity(EntityType<? extends OpteryxEntity> entityType, Level world) {
		super(entityType, world);

		this.flightMoveControl = new FlyingMoveControl(this, 10, true);
		this.groundMoveControl = this.moveControl;
		this.flightNavigator = new FlyingPathNavigation(this, world);
		this.walkNavigator = this.navigation;

		this.walkNavigator.setCanFloat(true);

		setParts(
				new AoAEntityPart<>(this, 0.375f, 0.4375f, 0, 0.76f, getBbWidth() - 0.0625f),
				new AoAEntityPart<>(this, 0.25f, 0.25f, 0, 0.97f, getBbWidth() + 0.25f),
				new AoAEntityPart<>(this, 0.25f, 0.25f, 0, 0.97f, getBbWidth() + 0.5f),
				new AoAEntityPart<>(this, 0.375f, 0.4375f, 0, 0.76f, -getBbWidth() + 0.0625f)
		);
	}

	@Override
	public EntityDimensions getDimensions(Pose pPose) {
		return isEgg() ? EGG_DIMENSIONS : super.getDimensions(pPose);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		registerDataParams(IS_EGG);
	}

	@Override
	public PathNavigation getNavigation() {
		if (this.flightNavigator == null)
			return super.getNavigation();

		this.navigation = isFlying() ? this.flightNavigator : this.walkNavigator;

		return this.navigation;
	}

	@Override
	public MoveControl getMoveControl() {
		this.moveControl = this.navigation == walkNavigator ? this.groundMoveControl : this.flightMoveControl;

		return this.moveControl;
	}

	@Override
	public BrainActivityGroup<? extends OpteryxEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new BreedWithPartner<>().startCondition(entity -> canBreed() && !isVehicle()),
				new FirstApplicableBehaviour<>(
						new FixedFollowParent<>(),
						new FollowTemptation<>().startCondition(entity -> getTemptItem() != null),
						new OneRandomBehaviour<>(
								new FirstApplicableBehaviour<>(
										new SetRandomWalkTarget<OpteryxEntity>()
												.speedModifier(0.9f)
												.startCondition(entity -> !entity.isFlying()),
										new SetRandomFlyingTarget<OpteryxEntity>()
												.verticalWeight(entity -> -1)
												.speedModifier(0.9f)
												.startCondition(OpteryxEntity::isFlying)),
								new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60)))).startCondition(entity -> !isVehicle()));
	}

	@Override
	public void checkDespawn() {
		if (isEggAge())
			return;

		super.checkDespawn();
	}

	@Override
	public boolean isPushable() {
		if (isEggAge())
			return false;

		return super.isPushable();
	}

	@Override
	public boolean isPushedByFluid() {
		if (isEggAge())
			return false;

		return super.isPushedByFluid();
	}

	@Override
	protected void pushEntities() {
		if (!isEggAge())
			super.pushEntities();
	}

	@Override
	protected @Nullable Item getTemptItem() {
		return AoAItems.RAW_GIANT_LIZARD_MEAT.get();
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return stack.isEdible() && stack.getFoodProperties(this).isMeat();
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return dimensions.height * 0.9f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		if (isEgg())
			return null;

		return AoASounds.ENTITY_CHARGER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		if (isEgg())
			return SoundEvents.TURTLE_EGG_BREAK;

		return AoASounds.ENTITY_OPTERYX_HURT.get();
	}

	@Override
	protected @Nullable SoundEvent getDeathSound() {
		if (isEgg())
			return SoundEvents.TURTLE_EGG_BREAK;

		return AoASounds.ENTITY_OPTERYX_HURT.get();
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource damageSource) {
		return false;
	}

	@Override
	public boolean isEffectiveAi() {
		return !isEgg() && super.isEffectiveAi();
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);

		tag.putBoolean("IsEgg", IS_EGG.get(this));

		if (getOwnerUUID() != null)
			tag.putUUID("Owner", getOwnerUUID());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);

		if (tag.contains("IsEgg", Tag.TAG_BYTE))
			IS_EGG.set(this, tag.getBoolean("IsEgg"));

		if (tag.contains("Owner", Tag.TAG_INT_ARRAY))
			this.ownerId = tag.getUUID("Owner");
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (isEgg()) {
			if (getParts()[0].isEnabled()) {
				toggleMultipart(false);
				refreshDimensions();
			}
		}
		else if (!getParts()[0].isEnabled()) {
			toggleMultipart(true);
			refreshDimensions();
		}

		if (level().isClientSide) {
			this.isFlying = !onGround();
		}
		else {
			if (isEgg()) {
				if (getAge() > -120000) {
					if (getAge() == -24000) {
						level().playSound(null, blockPosition(), SoundEvents.TURTLE_EGG_HATCH, SoundSource.NEUTRAL, 0.7f, (float)rand().randomValueBetween(0.2f, 1.1f));
					}
					else if (getAge() % 12000 == 0) {
						level().playSound(null, blockPosition(), SoundEvents.TURTLE_EGG_CRACK, SoundSource.NEUTRAL, 0.7f, (float)rand().randomValueBetween(0.2f, 1.1f));
					}
				}

				setDeltaMovement(getDeltaMovement().multiply(0.25f, 1, 0.25f).subtract(0, getAttributeValue(NeoForgeMod.ENTITY_GRAVITY.value()), 0));
				move(MoverType.SELF, getDeltaMovement());

				if (!EntityUtil.hasAttributeModifier(this, Attributes.MAX_HEALTH, EGG_HEALTH_MOD))
					EntityUtil.applyAttributeModifierSafely(this, Attributes.MAX_HEALTH, EGG_HEALTH_MOD, true);
			}
			else if (EntityUtil.hasAttributeModifier(this, Attributes.MAX_HEALTH, EGG_HEALTH_MOD)) {
				EntityUtil.removeAttributeModifier(this, Attributes.MAX_HEALTH, EGG_HEALTH_MOD);
			}

			if (isVehicle() && this.isFlying) {
				setDeltaMovement(getDeltaMovement().subtract(0, getAttributeValue(NeoForgeMod.ENTITY_GRAVITY.value()) * 0.5f, 0));
			}
		}
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		if (IS_EGG.is(this, true))
			IS_EGG.set(this, false);

		boolean wasFlying = isFlying();

		if (isInLove())
			setDeltaMovement(getDeltaMovement().subtract(0, 0.1f, 0));

		this.isFlying = !onGround();

		if (wasFlying != isFlying())
			updatePathControllers();

		if (isFlying()) {
			if (!isBaby())
				setNoGravity(true);
		}
		else if (!isVehicle()) {
			if (wasFlying) {
				triggerAnim("Movement", "Land");
				setNoGravity(false);
				getNavigation().stop();
				setDeltaMovement(0, -0.1f, 0);
			}
			else if (rand().oneInNChance(2000) && !BrainUtils.hasMemory(this, MemoryModuleType.BREED_TARGET) && !isInLove()) {
				getNavigation().stop();
				setDeltaMovement(0, 0.2f, 0);
				triggerAnim("Movement", "Takeoff");
			}
		}
	}

	protected void updatePathControllers() {
		getNavigation();
		getMoveControl();
	}

	@Override
	public @Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
		return new OpteryxEntity(AoAAnimals.OPTERYX.get(), level);
	}

	@Override
	public void finalizeSpawnChildFromBreeding(ServerLevel level, Animal partner, @Nullable AgeableMob baby) {
		super.finalizeSpawnChildFromBreeding(level, partner, baby);

		if (baby instanceof OpteryxEntity babyOpteryx) {
			restrictTo(partner.blockPosition(), 5);
			partner.restrictTo(blockPosition(), 5);
			babyOpteryx.setAge(random.nextInt(-252000, -204000));

			if (EntityRetrievalUtil.getNearestPlayer(this, 15, pl -> true) instanceof ServerPlayer pl)
				babyOpteryx.ownerId = pl.getUUID();
		}
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "Movement", 2, state -> {
			if (isEggAge())
				return PlayState.STOP;

			if (isFlying())
				return state.setAndContinue(DefaultAnimations.FLY);

			if (state.isMoving()) {
				return state.setAndContinue(isSprinting() ? DefaultAnimations.RUN : DefaultAnimations.WALK);
			}
			else {
				return state.setAndContinue(DefaultAnimations.IDLE);
			}
		})
				.triggerableAnim("Takeoff", TAKEOFF_ANIM)
				.triggerableAnim("Land", LANDING_ANIM));
	}

	@Override
	public boolean isFlying() {
		return this.isFlying;
	}

	public boolean isEggAge() {
		return getAge() < -24000;
	}

	public boolean isEgg() {
		return IS_EGG.get(this);
	}

	@Override
	public void setAge(int age) {
		super.setAge(age);

		if (!level().isClientSide()) {
			IS_EGG.set(this, isEggAge());

			if ((age == 0 || age == -24000) && getOwner() instanceof ServerPlayer pl)
				CriteriaTriggers.TAME_ANIMAL.trigger(pl, this);
		}
	}

	@Nullable
	@Override
	public UUID getOwnerUUID() {
		return this.ownerId;
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		if (player.getUUID().equals(getOwnerUUID())) {
			ItemStack stack = player.getItemInHand(hand);

			if (isFood(stack) && getHealth() < getMaxHealth()) {
				heal(stack.getFoodProperties(this).getNutrition());

				if (!player.getAbilities().instabuild)
					stack.shrink(1);

				gameEvent(GameEvent.EAT, this);

				return InteractionResult.SUCCESS;
			}
		}

		final InteractionResult result = super.mobInteract(player, hand);

		if (result != InteractionResult.PASS)
			return result;

		if (!isBaby() && player.getUUID().equals(getOwnerUUID()) && !isVehicle() && !player.isSecondaryUseActive()) {
			if (!level().isClientSide)
				player.startRiding(this);

			return InteractionResult.sidedSuccess(level().isClientSide);
		}

		return InteractionResult.PASS;
	}

	@Override
	public Vec3 getDismountLocationForPassenger(LivingEntity passenger) {
		final Direction movingDirection = getMotionDirection();

		if (movingDirection.getAxis() == Direction.Axis.Y)
			return super.getDismountLocationForPassenger(passenger);

		final BlockPos pos = blockPosition();
		final BlockPos.MutableBlockPos testPos = new BlockPos.MutableBlockPos();

		for(int[] dismountOffset : DismountHelper.offsetsForDirection(movingDirection)) {
			double floorHeight = level().getBlockFloorHeight(testPos.set(pos.getX() + dismountOffset[0], pos.getY(), pos.getZ() + dismountOffset[1]));

			if (DismountHelper.isBlockFloorValid(floorHeight)) {
				Vec3 floorPos = Vec3.upFromBottomCenterOf(testPos, floorHeight);

				for (Pose pose : passenger.getDismountPoses()) {
					AABB dismountBoundingBox = passenger.getLocalBoundsForPose(pose);

					if (DismountHelper.canDismountTo(level(), passenger, dismountBoundingBox.move(floorPos))) {
						passenger.setPose(pose);

						return floorPos;
					}
				}
			}
		}

		return super.getDismountLocationForPassenger(passenger);
	}

	@Override
	protected void tickRidden(Player player, Vec3 travelVector) {
		super.tickRidden(player, travelVector);

		if (!level().isClientSide() && !player.getUUID().equals(getOwnerUUID()))
			player.stopRiding();

		float yRot = Mth.rotLerp(0.1f, getYRot(), player.getYRot());

		setRot(yRot, player.getXRot() * 0.5F);
		this.yRotO = this.yBodyRot = this.yHeadRot = getYRot();
	}

	@Nullable
	@Override
	public LivingEntity getControllingPassenger() {
		final Entity passenger = this.getFirstPassenger();

		if (passenger instanceof Player pl)
			return pl;

		return null;
	}

	@Override
	protected Vec3 getRiddenInput(Player passenger, Vec3 travelVector) {
		float forward = passenger.zza;

		if (forward <= 0)
			forward *= 0.25f;

		return new Vec3(0, passenger.jumping ? 0.25f : -0.25, forward);
	}

	@Override
	protected float getRiddenSpeed(Player player) {
		return (float)getAttributeValue(Attributes.MOVEMENT_SPEED) * (this.isFlying ? 2 : 0.5f);
	}

	@Override
	public void die(DamageSource cause) {
		Component deathMessage = getCombatTracker().getDeathMessage();

		super.die(cause);

		if (this.dead && !level().isClientSide() && level().getGameRules().getBoolean(GameRules.RULE_SHOWDEATHMESSAGES) && getOwner() instanceof ServerPlayer)
			getOwner().sendSystemMessage(deathMessage);
	}
}
