package net.tslat.aoa3.content.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAAnimals;
import net.tslat.aoa3.content.entity.ai.movehelper.RoamingSwimmingMovementController;
import net.tslat.aoa3.content.entity.base.AoAAnimal;
import net.tslat.aoa3.util.EntityUtil;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class CorateeEntity extends AoAAnimal {
	public CorateeEntity(EntityType<? extends Animal> entityType, Level world) {
		super(entityType, world);

		setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
		this.moveControl = new RoamingSwimmingMovementController(this);
	}

	protected void registerGoals() {
		goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
		goalSelector.addGoal(5, new RandomSwimmingGoal(this, 1, 70));
		goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
		goalSelector.addGoal(7, new RandomLookAroundGoal(this));

		if (isBreedable()) {
			goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
			goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		}
	}

	@Override
	protected PathNavigation createNavigation(Level world) {
		return new WaterBoundPathNavigation(this, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 0.6875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CORATEE_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CORATEE_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CORATEE_HURT.get();
	}

	@Override
	protected boolean isBreedable() {
		return true;
	}

	@Nullable
	@Override
	protected Item getTemptItem() {
		return Item.byBlock(Blocks.LILY_PAD);
	}

	@Nullable
	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob partner) {
		return new CorateeEntity(AoAAnimals.CORATEE.get(), this.level);
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public MobType getMobType() {
		return MobType.WATER;
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader world) {
		return world.isUnobstructed(this);
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor world, MobSpawnType reason) {
		if (!EntityUtil.isNaturalSpawnReason(reason))
			return true;

		return world.getBlockState(blockPosition()).getFluidState().is(FluidTags.WATER);
	}

	@Override
	public void baseTick() {
		super.baseTick();

		setAirSupply(300);
	}

	@Override
	public boolean isPushedByFluid() {
		return false;
	}

	@Override
	public boolean canBeLeashed(Player player) {
		return false;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block) {}

	@Override
	public void travel(Vec3 motion) {
		if (isEffectiveAi() && isInWater()) {
			moveRelative(0.01F, motion);
			move(MoverType.SELF, getDeltaMovement());
			setDeltaMovement(getDeltaMovement().scale(0.9D));

			if (getTarget() == null) {
				setDeltaMovement(getDeltaMovement().add(0.0D, -0.005D, 0.0D));
			}
		}
		else {
			super.travel(motion);
		}
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(AoAAnimations.genericSwimIdleController(this));
	}
}
