package net.tslat.aoa3.content.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.ai.mob.RandomFlyingGoal;
import net.tslat.aoa3.content.entity.ai.movehelper.RoamingFlightMovementController;
import net.tslat.aoa3.content.entity.base.AoAAnimal;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

public class MeganeuropsisEntity extends AoAAnimal {
	private static final EntityDataAccessor<Boolean> LANDED = SynchedEntityData.defineId(MeganeuropsisEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> START_LANDING_TICKS = SynchedEntityData.defineId(MeganeuropsisEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Direction> LANDING_DIRECTION = SynchedEntityData.defineId(MeganeuropsisEntity.class, EntityDataSerializers.DIRECTION);
	private static final EntityDataAccessor<Optional<UUID>> LANDED_PLAYER = SynchedEntityData.defineId(MeganeuropsisEntity.class, EntityDataSerializers.OPTIONAL_UUID);

	private Player clientRidingPlayer = null;
	private int clientStartLandingTicks = 0;

	public MeganeuropsisEntity(EntityType<? extends Animal> entityType, Level world) {
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
	protected PathNavigation createNavigation(Level world) {
		return new FlyingPathNavigation(this, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
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
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource source) {
		return false;
	}

	@Override
	protected void checkFallDamage(double y, boolean onGroundIn, BlockState state, BlockPos pos) {}

	@Override
	public boolean onClimbable() {
		return false;
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		super.onSyncedDataUpdated(key);

		if (level.isClientSide && key.equals(START_LANDING_TICKS))
			clientStartLandingTicks = tickCount;
	}

	@Override
	public MobType getMobType() {
		return MobType.ARTHROPOD;
	}

	@Override
	public void travel(Vec3 motion) {
		if (isLanded()) {
			if (level.isClientSide && getLandingDirection() == Direction.DOWN && !isPassenger()) {
				if (entityData.get(LANDED_PLAYER).isPresent()) {
					Player pl = level.getPlayerByUUID(entityData.get(LANDED_PLAYER).get());

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
				BlockPos underPos = new BlockPos(Mth.floor(getX()), Mth.floor(getBoundingBox().minY) - 1, Mth.floor(getZ()));
				BlockState underState = level.getBlockState(underPos);
				friction = underState.getBlock().getFriction(underState, level, underPos, this) * 0.91F;
			}

			moveRelative(onGround ? 0.1F * (0.16277136F / (friction * friction * friction)) : 0.02F, motion);
			friction = 0.91F;

			if (onGround) {
				BlockPos underPos = new BlockPos(Mth.floor(getX()), Mth.floor(getBoundingBox().minY) - 1, Mth.floor(getZ()));
				BlockState underState = level.getBlockState(underPos);
				friction = underState.getBlock().getFriction(underState, level, underPos, this) * 0.91F;
			}

			move(MoverType.SELF, getDeltaMovement());
			setDeltaMovement(getDeltaMovement().scale(friction));
		}

		double movedX = getZ() - xo;
		double movedZ = getZ() - zo;

		this.walkAnimation.update((float)Math.min(1, Math.sqrt((movedX * movedX + movedZ * movedZ)) * 4f), 0.4f);
	}

	private class EntityAIMeganeuropsisLand extends Goal {
		private final MeganeuropsisEntity taskOwner;
		private BlockPos landingPos = null;
		private Direction blockFace = null;
		private Player landingPlayer = null;

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
					Player nearestPlayer = taskOwner.level.getNearestPlayer(getX(), getY(), getZ(), 10, false);

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
						int y = taskOwner.level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, BlockPos.containing(x, taskOwner.getY(), z)).getY();

						if (taskOwner.getY() - y > 10)
							continue;

						if (y > taskOwner.getY()) {
							if (true) // TODO Alternate landing pattern
								return false;

							BlockHitResult trace = taskOwner.level.clip(new ClipContext(position(), new Vec3(x + 0.5d, y + 0.5d, z + 0.5d), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, null));

							if (trace.getType() == HitResult.Type.MISS)
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
				if (taskOwner.blockPosition().distToCenterSqr(targetPos.getX(), targetPos.getY(), targetPos.getZ()) <= 4) {
					taskOwner.getNavigation().stop();

					Vec3 actualLandingLocation = getActualLandingLocation();
					BlockPos actualLandingBlockPos = BlockPos.containing(actualLandingLocation);

					if (taskOwner.level.getBlockState(actualLandingBlockPos).getCollisionShape(taskOwner.level, actualLandingBlockPos) != Shapes.empty()) {
						landingPos = null;
						landingPlayer = null;

						return;
					}

					if (taskOwner.getStartLandingTicks() == 0)
						taskOwner.entityData.set(START_LANDING_TICKS, taskOwner.tickCount);

					if (taskOwner.blockPosition().distToCenterSqr(targetPos.getX(), targetPos.getY(), targetPos.getZ()) <= 1d) {
						double vecX = actualLandingLocation.x - taskOwner.getX();
						double vecZ = actualLandingLocation.z - taskOwner.getZ();
						float rotYaw = (float)(Mth.atan2(vecZ, vecX) * (180 / Math.PI)) - 90f;

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

						taskOwner.move(MoverType.SELF, new Vec3(vecX, vecY, vecZ));
					}
				}
			}
			else {
				if (landingPlayer == null) {
					Vec3 actualLandingLocation = getActualLandingLocation();

					taskOwner.setDeltaMovement(new Vec3(0, 0, 0));
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

		private Vec3 getActualLandingLocation() {
			if (landingPlayer != null) {
				return new Vec3(landingPlayer.getX(), landingPlayer.getY() + landingPlayer.getBbHeight() + taskOwner.getBbHeight() + 0.1d, landingPlayer.getZ());
			}
			else {
				switch (blockFace) {
					case NORTH:
						return new Vec3(landingPos.getX() + 0.5d, landingPos.getY() + 0.5d, landingPos.getZ() - taskOwner.getBbWidth() / 2d);
					case SOUTH:
						return new Vec3(landingPos.getX() + 0.5d, landingPos.getY() + 0.5d, landingPos.getZ() + 1 + taskOwner.getBbWidth() / 2d);
					case EAST:
						return new Vec3(landingPos.getX() + 1 + taskOwner.getBbWidth() / 2d, landingPos.getY() + 0.5d, landingPos.getZ() + 0.5d);
					case WEST:
						return new Vec3(landingPos.getX() - taskOwner.getBbWidth() / 2d, landingPos.getY() + 0.5d, landingPos.getZ() + 0.5d);
					default:
					case UP:
						return new Vec3(landingPos.getX() + 0.5d, landingPos.getY() + 0.5d, landingPos.getZ() + 0.5d);
				}
			}
		}
	}
}
