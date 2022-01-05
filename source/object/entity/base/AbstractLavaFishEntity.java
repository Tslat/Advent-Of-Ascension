package net.tslat.aoa3.object.entity.base;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

public abstract class AbstractLavaFishEntity extends LavaMobEntity {
	public AbstractLavaFishEntity(EntityType<? extends AbstractLavaFishEntity> entityType, World world) {
		super(entityType, world);

		this.moveControl = new AbstractLavaFishEntity.MoveHelperController(this);
	}

	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return size.height * 0.65F;
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D);
	}

	public static boolean checkFishSpawnRules(EntityType<? extends MobEntity> entityType, IWorld world, SpawnReason reason, BlockPos position, Random rand) {
		return world.getBlockState(position).is(Blocks.LAVA) && world.getBlockState(position.above()).is(Blocks.LAVA);
	}

	public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
		return !this.hasCustomName();
	}

	public int getMaxSpawnClusterSize() {
		return 8;
	}

	protected void registerGoals() {
		super.registerGoals();

		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PlayerEntity.class, 8f, 1.6d, 1.4d, EntityPredicates.NO_SPECTATORS::test));
		this.goalSelector.addGoal(4, new AbstractLavaFishEntity.SwimGoal(this));
	}

	protected PathNavigator createNavigation(World pLevel) {
		return new SwimmerPathNavigator(this, pLevel);
	}

	public void travel(Vector3d pTravelVector) {
		if (this.isEffectiveAi() && this.isInLava()) {
			this.moveRelative(0.01f, pTravelVector);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9d));

			if (this.getTarget() == null)
				this.setDeltaMovement(this.getDeltaMovement().add(0, -0.005d, 0));
		}
		else {
			super.travel(pTravelVector);
		}

	}

	public void aiStep() {
		if (!this.isInLava() && this.onGround && this.verticalCollision) {
			this.setDeltaMovement(this.getDeltaMovement().add((this.random.nextFloat() * 2 - 1) * 0.05f, 0.4f, (this.random.nextFloat() * 2 - 1) * 0.05f));

			this.onGround = false;
			this.hasImpulse = true;

			this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());
		}

		super.aiStep();
	}

	protected boolean canRandomSwim() {
		return true;
	}

	protected abstract SoundEvent getFlopSound();

	protected SoundEvent getSwimSound() {
		return SoundEvents.FISH_SWIM;
	}

	protected void playStepSound(BlockPos pPos, BlockState pBlock) {}

	static class MoveHelperController extends MovementController {
		private final AbstractLavaFishEntity fish;

		MoveHelperController(AbstractLavaFishEntity fish) {
			super(fish);

			this.fish = fish;
		}

		public void tick() {
			if (this.fish.isEyeInFluid(FluidTags.LAVA))
				this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0, 0.005d, 0));

			if (this.operation == MovementController.Action.MOVE_TO && !this.fish.getNavigation().isDone()) {
				float moveSpeed = (float)(this.speedModifier * this.fish.getAttributeValue(Attributes.MOVEMENT_SPEED));

				this.fish.setSpeed(MathHelper.lerp(0.125f, this.fish.getSpeed(), moveSpeed));

				double distX = this.wantedX - this.fish.getX();
				double distY = this.wantedY - this.fish.getY();
				double distZ = this.wantedZ - this.fish.getZ();

				if (distY != 0) {
					double dist = MathHelper.sqrt(distX * distX + distY * distY + distZ * distZ);

					this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0, (double)this.fish.getSpeed() * (distY / dist) * 0.1d, 0));
				}

				if (distX != 0 || distZ != 0) {
					float rotation = (float)(MathHelper.atan2(distZ, distX) * (double)(180f / (float)Math.PI)) - 90;
					this.fish.yRot = this.rotlerp(this.fish.yRot, rotation, 90);
					this.fish.yBodyRot = this.fish.yRot;
				}

			}
			else {
				this.fish.setSpeed(0);
			}
		}
	}

	static class SwimGoal extends RandomSwimmingGoal {
		private final AbstractLavaFishEntity fish;

		public SwimGoal(AbstractLavaFishEntity fish) {
			super(fish, 1, 40);

			this.fish = fish;
		}

		public boolean canUse() {
			return this.fish.canRandomSwim() && super.canUse();
		}
	}
}
