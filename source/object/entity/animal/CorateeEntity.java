package net.tslat.aoa3.object.entity.animal;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.ai.movehelper.RoamingSwimmingMovementController;
import net.tslat.aoa3.object.entity.base.AoAAnimal;
import net.tslat.aoa3.util.EntityUtil;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class CorateeEntity extends AoAAnimal {
	public CorateeEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);

		setPathfindingMalus(PathNodeType.WATER, 0.0F);
		this.moveControl = new RoamingSwimmingMovementController(this);
	}

	protected void registerGoals() {
		goalSelector.addGoal(1, new PanicGoal(this, 1.5D));
		goalSelector.addGoal(5, new RandomSwimmingGoal(this, 1, 70));
		goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		goalSelector.addGoal(7, new LookRandomlyGoal(this));

		if (isBreedable()) {
			goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
			goalSelector.addGoal(4, new FollowParentGoal(this, 1.25D));
		}
	}

	@Override
	protected PathNavigator createNavigation(World world) {
		return new SwimmerPathNavigator(this, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
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
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity partner) {
		return new CorateeEntity(AoAEntities.Animals.CORATEE.get(), this.level);
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public CreatureAttribute getMobType() {
		return CreatureAttribute.WATER;
	}

	@Override
	public boolean checkSpawnObstruction(IWorldReader world) {
		return world.isUnobstructed(this);
	}

	@Override
	public boolean checkSpawnRules(IWorld world, SpawnReason reason) {
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
	public boolean canBeLeashed(PlayerEntity player) {
		return false;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block) {}

	@Override
	public void travel(Vector3d motion) {
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
