package net.tslat.aoa3.content.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public abstract class AbstractLavaFishEntity extends LavaMobEntity {
	public AbstractLavaFishEntity(EntityType<? extends AbstractLavaFishEntity> entityType, Level world) {
		super(entityType, world);

		this.moveControl = new MoveHelperController(this);
	}

	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return size.height * 0.65F;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 3.0D);
	}

	public static boolean checkFishSpawnRules(EntityType<? extends Mob> entityType, LevelAccessor world, MobSpawnType reason, BlockPos position, Random rand) {
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
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8f, 1.6d, 1.4d, EntitySelector.NO_SPECTATORS::test));
		this.goalSelector.addGoal(4, new FloatGoal(this));
	}

	protected PathNavigation createNavigation(Level pLevel) {
		return new WaterBoundPathNavigation(this, pLevel);
	}

	public void travel(Vec3 pTravelVector) {
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

	static class MoveHelperController extends MoveControl {
		private final AbstractLavaFishEntity fish;

		MoveHelperController(AbstractLavaFishEntity fish) {
			super(fish);

			this.fish = fish;
		}

		public void tick() {
			if (this.fish.isEyeInFluid(FluidTags.LAVA))
				this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0, 0.005d, 0));

			if (this.operation == MoveControl.Operation.MOVE_TO && !this.fish.getNavigation().isDone()) {
				float moveSpeed = (float)(this.speedModifier * this.fish.getAttributeValue(Attributes.MOVEMENT_SPEED));

				this.fish.setSpeed(Mth.lerp(0.125f, this.fish.getSpeed(), moveSpeed));

				double distX = this.wantedX - this.fish.getX();
				double distY = this.wantedY - this.fish.getY();
				double distZ = this.wantedZ - this.fish.getZ();

				if (distY != 0) {
					double dist = Math.sqrt((distX * distX + distY * distY + distZ * distZ));

					this.fish.setDeltaMovement(this.fish.getDeltaMovement().add(0, (double)this.fish.getSpeed() * (distY / dist) * 0.1d, 0));
				}

				if (distX != 0 || distZ != 0) {
					float rotation = (float)(Mth.atan2(distZ, distX) * (double)(180f / (float)Math.PI)) - 90;
					this.fish.setYRot(this.rotlerp(this.fish.getYRot(), rotation, 90));
					this.fish.yBodyRot = this.fish.getYRot();
				}

			}
			else {
				this.fish.setSpeed(0);
			}
		}
	}

	static class FloatGoal extends RandomSwimmingGoal {
		private final AbstractLavaFishEntity fish;

		public FloatGoal(AbstractLavaFishEntity fish) {
			super(fish, 1, 40);

			this.fish = fish;
		}

		public boolean canUse() {
			return this.fish.canRandomSwim() && super.canUse();
		}
	}
}
