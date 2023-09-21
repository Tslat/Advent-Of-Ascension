package net.tslat.aoa3.content.entity.animal.precasia;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.base.AoAAnimal;
import net.tslat.aoa3.content.entity.base.AoAEntityPart;
import net.tslat.aoa3.content.entity.brain.task.temp.SetRandomFlyingTarget;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.FirstApplicableBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.OneRandomBehaviour;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.BreedWithPartner;
import net.tslat.smartbrainlib.api.core.behaviour.custom.misc.Idle;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FollowParent;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FollowTemptation;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomWalkTarget;
import net.tslat.smartbrainlib.util.BrainUtils;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;

import javax.annotation.Nullable;

public class OpteryxEntity extends AoAAnimal<OpteryxEntity> implements FlyingAnimal {
	private static final RawAnimation TAKEOFF_ANIM = RawAnimation.begin().thenPlay("move.fly_start");
	private static final RawAnimation LANDING_ANIM = RawAnimation.begin().thenPlay("move.fly_stop");
	protected boolean isFlying = false;
	protected final FlyingPathNavigation flightNavigator;
	protected final PathNavigation walkNavigator;
	protected final FlyingMoveControl flightMoveControl;
	protected final MoveControl groundMoveControl;

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
				new BreedWithPartner<>().startCondition(entity -> canBreed()),
				new FirstApplicableBehaviour<>(
						new FollowParent<>(),
						new FollowTemptation<>().startCondition(entity -> getTemptItem() != null),
						new OneRandomBehaviour<>(
								new FirstApplicableBehaviour<>(
										new SetRandomWalkTarget<OpteryxEntity>()
												.speedModifier(0.9f)
												.startCondition(entity -> !entity.isFlying()),
										new SetRandomFlyingTarget<OpteryxEntity>()
												/*.verticalWeight(entity -> -1)*/
												.speedModifier(0.9f)
												.startCondition(OpteryxEntity::isFlying)),
								new Idle<>().runFor(entity -> entity.getRandom().nextInt(30, 60)))));
	}

	@Override
	protected @org.jetbrains.annotations.Nullable Item getTemptItem() {
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
		return AoASounds.ENTITY_CHARGER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_OPTERYX_HURT.get();
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource damageSource) {
		return false;
	}

	@Override
	public void aiStep() {
		super.aiStep();

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
		else {
			if (wasFlying) {
				triggerAnim("Movement", "Land");
				setNoGravity(false);
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
	public @org.jetbrains.annotations.Nullable AgeableMob getBreedOffspring(ServerLevel level, AgeableMob partner) {
		return new OpteryxEntity(AoAAnimals.OPTERYX.get(), level);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(new AnimationController<>(this, "Movement", 0, state -> {
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
}
