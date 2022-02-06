package net.tslat.aoa3.object.entity.animal;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.*;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.ai.mob.RandomFlyingGoal;
import net.tslat.aoa3.object.entity.ai.movehelper.RoamingFlightMovementController;
import net.tslat.aoa3.object.entity.base.AoAAnimal;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

public class MeganeuropsisEntity extends AoAAnimal {
	private static final DataParameter<Boolean> LANDED = EntityDataManager.defineId(MeganeuropsisEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> START_LANDING_TICKS = EntityDataManager.defineId(MeganeuropsisEntity.class, DataSerializers.INT);
	private static final DataParameter<Direction> LANDING_DIRECTION = EntityDataManager.defineId(MeganeuropsisEntity.class, DataSerializers.DIRECTION);
	private static final DataParameter<Optional<UUID>> LANDED_PLAYER = EntityDataManager.defineId(MeganeuropsisEntity.class, DataSerializers.OPTIONAL_UUID);

	private PlayerEntity clientRidingPlayer = null;
	private int clientStartLandingTicks = 0;

	public MeganeuropsisEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);

		moveControl = new RoamingFlightMovementController(this);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		entityData.define(LANDED, false);
		entityData.define(START_LANDING_TICKS, 0);
		entityData.define(LANDING_DIRECTION, Direction.DOWN);
		entityData.define(LANDED_PLAYER, Optional.empty());
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new EntityAIMeganeuropsisLand(this));
		goalSelector.addGoal(1, new RandomFlyingGoal(this, true));
	}

	@Override
	protected PathNavigator createNavigation(World world) {
		return new FlyingPathNavigator(this, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 0.3125f;
	}

	public int getStartLandingTicks() {
		return level.isClientSide ? clientStartLandingTicks : entityData.get(START_LANDING_TICKS);
	}

	public boolean isLanded() {
		return entityData.get(LANDED);
	}

	public Direction getLandingDirection() {
		return entityData.get(LANDING_DIRECTION);
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
		return AoASounds.ENTITY_MEGANEUROPSIS_AMBIENT.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block) {}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier) {
		return false;
	}

	@Override
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {}

	@Override
	public boolean onClimbable() {
		return false;
	}

	@Override
	public void onSyncedDataUpdated(DataParameter<?> key) {
		super.onSyncedDataUpdated(key);

		if (level.isClientSide && key == START_LANDING_TICKS)
			clientStartLandingTicks = tickCount;
	}

	@Override
	public CreatureAttribute getMobType() {
		return CreatureAttribute.ARTHROPOD;
	}

	@Override
	public void travel(Vector3d motion) {
		if (isLanded()) {
			if (level.isClientSide && getLandingDirection() == Direction.DOWN && !isPassenger()) {
				if (entityData.get(LANDED_PLAYER).isPresent()) {
					PlayerEntity pl = level.getPlayerByUUID(entityData.get(LANDED_PLAYER).get());

					if (pl != null) {
						clientRidingPlayer = pl;
						startRiding(clientRidingPlayer, false);
					}
				}
			}

			return;
		}
		else if (level.isClientSide && isPassenger() && clientRidingPlayer != null) {
			clientRidingPlayer = null;
			stopRiding();
		}

		if (isInWater()) {
			moveRelative(0.02f, motion);
			move(MoverType.SELF, getDeltaMovement());

			setDeltaMovement(getDeltaMovement().scale(0.8d));
		}
		else if (isInLava()) {
			moveRelative(0.02f, motion);
			move(MoverType.SELF, getDeltaMovement());

			setDeltaMovement(getDeltaMovement().scale(0.5d));
		}
		else {
			float friction = 0.91F;

			if (onGround) {
				BlockPos underPos = new BlockPos(MathHelper.floor(getX()), MathHelper.floor(getBoundingBox().minY) - 1, MathHelper.floor(getZ()));
				BlockState underState = level.getBlockState(underPos);
				friction = underState.getBlock().getSlipperiness(underState, level, underPos, this) * 0.91F;
			}

			moveRelative(onGround ? 0.1F * (0.16277136F / (friction * friction * friction)) : 0.02F, motion);
			friction = 0.91F;

			if (onGround) {
				BlockPos underPos = new BlockPos(MathHelper.floor(getX()), MathHelper.floor(getBoundingBox().minY) - 1, MathHelper.floor(getZ()));
				BlockState underState = level.getBlockState(underPos);
				friction = underState.getBlock().getSlipperiness(underState, level, underPos, this) * 0.91F;
			}

			move(MoverType.SELF, getDeltaMovement());
			setDeltaMovement(getDeltaMovement().scale(friction));
		}

		animationSpeedOld = animationSpeed;
		double movedX = getZ() - xo;
		double movedZ = getZ() - zo;
		float totalMotion = MathHelper.sqrt(movedX * movedX + movedZ * movedZ) * 4.0F;

		if (totalMotion > 1.0F)
			totalMotion = 1.0F;

		animationSpeed += (totalMotion - animationSpeed) * 0.4F;
		animationPosition += animationSpeed;
	}

	private class EntityAIMeganeuropsisLand extends Goal {
		private final MeganeuropsisEntity taskOwner;
		private BlockPos landingPos = null;
		private Direction blockFace = null;
		private PlayerEntity landingPlayer = null;

		public EntityAIMeganeuropsisLand(MeganeuropsisEntity creature) {
			this.taskOwner = creature;

			setFlags(EnumSet.of(Flag.LOOK, Flag.MOVE, Flag.TARGET));
		}

		@Override
		public boolean canUse() {
			if (taskOwner.getLastHurtByMob() != null)
				return false;

			if (landingPos == null && landingPlayer == null && taskOwner.getRandom().nextFloat() <= 0.01f) {
				if (random.nextBoolean()) {
					PlayerEntity nearestPlayer = taskOwner.level.getNearestPlayer(getX(), getY(), getZ(), 10, false);

					if (nearestPlayer != null && !nearestPlayer.isVehicle()) {
						taskOwner.navigation.stop();
						landingPlayer = nearestPlayer;

						return true;
					}
				}
				else {
					for (int i = 0; i < 3; i ++) {
						int x = (int)(taskOwner.getX() + taskOwner.getRandom().nextGaussian() * 10);
						int z = (int)(taskOwner.getZ() + taskOwner.getRandom().nextGaussian() * 10);
						int y = taskOwner.level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, new BlockPos(x, taskOwner.getY(), z)).getY();

						if (taskOwner.getY() - y > 10)
							continue;

						if (y > taskOwner.getY()) {
							if (true) // TODO Alternate landing pattern
								return false;

							BlockRayTraceResult trace = taskOwner.level.clip(new RayTraceContext(position(), new Vector3d(x + 0.5d, y + 0.5d, z + 0.5d), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, null));

							if (trace.getType() == RayTraceResult.Type.MISS)
								return false;

							if (trace.getDirection() == Direction.DOWN)
								return false;

							taskOwner.navigation.stop();
							landingPos = trace.getBlockPos();
							blockFace = trace.getDirection();

							return true;
						}
						else {
							taskOwner.navigation.stop();
							landingPos = new BlockPos(x, y, z);
							blockFace = Direction.UP;

							return true;
						}
					}
				}
			}

			return false;
		}

		@Override
		public void start() {
			if (landingPos != null) {
				taskOwner.getNavigation().moveTo(landingPos.getX(), landingPos.getY() + 1, landingPos.getZ(), 1f);
			}
			else if (landingPlayer != null) {
				taskOwner.getNavigation().moveTo(landingPlayer, 1f);
			}
		}

		@Override
		public boolean canContinueToUse() {
			if ((landingPos == null && (landingPlayer == null || !landingPlayer.isAlive() || (landingPlayer.isVehicle() && !landingPlayer.hasPassenger(taskOwner))) || taskOwner.getLastHurtByMob() != null))
				return false;

			return !taskOwner.getNavigation().isDone() || (taskOwner.getStartLandingTicks() > 0 && taskOwner.tickCount - taskOwner.getStartLandingTicks() < 240);
		}

		@Override
		public void tick() {
			BlockPos targetPos = landingPos != null ? landingPos : landingPlayer.blockPosition().above();

			if (!taskOwner.isLanded()) {
				if (taskOwner.blockPosition().distSqr(targetPos.getX(), targetPos.getY(), targetPos.getZ(), true) <= 4) {
					taskOwner.getNavigation().stop();

					Vector3d actualLandingLocation = getActualLandingLocation();
					BlockPos actualLandingBlockPos = new BlockPos(actualLandingLocation);

					if (taskOwner.level.getBlockState(actualLandingBlockPos).getCollisionShape(taskOwner.level, actualLandingBlockPos) != VoxelShapes.empty()) {
						landingPos = null;
						landingPlayer = null;

						return;
					}

					if (taskOwner.getStartLandingTicks() == 0)
						taskOwner.entityData.set(START_LANDING_TICKS, taskOwner.tickCount);

					if (taskOwner.blockPosition().distSqr(targetPos.getX(), targetPos.getY(), targetPos.getZ(), true) <= 1d) {
						double vecX = actualLandingLocation.x - taskOwner.getX();
						double vecZ = actualLandingLocation.z - taskOwner.getZ();
						float rotYaw = (float)(MathHelper.atan2(vecZ, vecX) * (180 / Math.PI)) - 90f;

						taskOwner.moveTo(actualLandingLocation.x, actualLandingLocation.y, actualLandingLocation.z, rotYaw, 0);

						taskOwner.entityData.set(LANDED, true);

						if (landingPlayer != null) {
							taskOwner.entityData.set(LANDING_DIRECTION, Direction.DOWN);
							taskOwner.entityData.set(LANDED_PLAYER, Optional.of(landingPlayer.getUUID()));
							taskOwner.startRiding(landingPlayer, true);
						}
						else {
							taskOwner.entityData.set(LANDING_DIRECTION, blockFace);
						}
					}
					else {
						double vecX = (actualLandingLocation.x - taskOwner.getX()) * 0.5d;
						double vecZ = (actualLandingLocation.z - taskOwner.getZ()) * 0.5d;
						double vecY = (actualLandingLocation.y - taskOwner.getY()) * 0.5d;

						taskOwner.move(MoverType.SELF, new Vector3d(vecX, vecY, vecZ));
					}
				}
			}
			else {
				if (landingPlayer == null) {
					Vector3d actualLandingLocation = getActualLandingLocation();

					taskOwner.setDeltaMovement(new Vector3d(0, 0, 0));
					taskOwner.hurtMarked = true;

					taskOwner.teleportTo(actualLandingLocation.x, actualLandingLocation.y - 0.5d, actualLandingLocation.z);
				}
			}
		}

		@Override
		public void stop() {
			landingPos = null;
			blockFace = null;
			landingPlayer = null;
			taskOwner.entityData.set(START_LANDING_TICKS, 0);
			taskOwner.entityData.set(LANDED, false);
			taskOwner.entityData.set(LANDING_DIRECTION, Direction.DOWN);
			taskOwner.entityData.set(LANDED_PLAYER, Optional.empty());
			taskOwner.stopRiding();
		}

		private Vector3d getActualLandingLocation() {
			if (landingPlayer != null) {
				return new Vector3d(landingPlayer.getX(), landingPlayer.getY() + landingPlayer.getBbHeight() + taskOwner.getBbHeight() + 0.1d, landingPlayer.getZ());
			}
			else {
				switch (blockFace) {
					case NORTH:
						return new Vector3d(landingPos.getX() + 0.5d, landingPos.getY() + 0.5d, landingPos.getZ() - taskOwner.getBbWidth() / 2d);
					case SOUTH:
						return new Vector3d(landingPos.getX() + 0.5d, landingPos.getY() + 0.5d, landingPos.getZ() + 1 + taskOwner.getBbWidth() / 2d);
					case EAST:
						return new Vector3d(landingPos.getX() + 1 + taskOwner.getBbWidth() / 2d, landingPos.getY() + 0.5d, landingPos.getZ() + 0.5d);
					case WEST:
						return new Vector3d(landingPos.getX() - taskOwner.getBbWidth() / 2d, landingPos.getY() + 0.5d, landingPos.getZ() + 0.5d);
					default:
					case UP:
						return new Vector3d(landingPos.getX() + 0.5d, landingPos.getY() + 0.5d, landingPos.getZ() + 0.5d);
				}
			}
		}
	}
}
