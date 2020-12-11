package net.tslat.aoa3.entity.animal;

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
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.ai.mob.RandomFlyingGoal;
import net.tslat.aoa3.entity.ai.movehelper.RoamingFlightMoveHelper;
import net.tslat.aoa3.entity.base.AoAAnimal;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.Optional;
import java.util.UUID;

public class MeganeuropsisEntity extends AoAAnimal {
	private static final DataParameter<Boolean> LANDED = EntityDataManager.<Boolean>createKey(MeganeuropsisEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> START_LANDING_TICKS = EntityDataManager.<Integer>createKey(MeganeuropsisEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Direction> LANDING_DIRECTION = EntityDataManager.<Direction>createKey(MeganeuropsisEntity.class, DataSerializers.DIRECTION);
	private static final DataParameter<Optional<UUID>> LANDED_PLAYER = EntityDataManager.<Optional<UUID>>createKey(MeganeuropsisEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);

	private PlayerEntity clientRidingPlayer = null;
	private int clientStartLandingTicks = 0;

	public MeganeuropsisEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);

		moveController = new RoamingFlightMoveHelper(this);
	}

	@Override
	protected void registerData() {
		super.registerData();

		dataManager.register(LANDED, false);
		dataManager.register(START_LANDING_TICKS, 0);
		dataManager.register(LANDING_DIRECTION, Direction.DOWN);
		dataManager.register(LANDED_PLAYER, Optional.empty());
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new EntityAIMeganeuropsisLand(this));
		goalSelector.addGoal(1, new RandomFlyingGoal(this, true));
	}

	@Override
	protected PathNavigator createNavigator(World world) {
		return new FlyingPathNavigator(this, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 0.3125f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 9;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.5;
	}

	@Override
	protected double getBaseArmor() {
		return 4;
	}

	public int getStartLandingTicks() {
		return world.isRemote ? clientStartLandingTicks : dataManager.get(START_LANDING_TICKS);
	}

	public boolean isLanded() {
		return dataManager.get(LANDED);
	}

	public Direction getLandingDirection() {
		return dataManager.get(LANDING_DIRECTION);
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
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return null;
	}

	@Override
	public boolean onLivingFall(float distance, float damageMultiplier) {
		return false;
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {}

	@Override
	public boolean isOnLadder() {
		return false;
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		super.notifyDataManagerChange(key);

		if (world.isRemote && key == START_LANDING_TICKS)
			clientStartLandingTicks = ticksExisted;
	}

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.ARTHROPOD;
	}

	@Override
	public void travel(Vec3d motion) {
		if (isLanded()) {
			if (world.isRemote && getLandingDirection() == Direction.DOWN && !isPassenger()) {
				if (dataManager.get(LANDED_PLAYER).isPresent()) {
					PlayerEntity pl = world.getPlayerByUuid(dataManager.get(LANDED_PLAYER).get());

					if (pl != null) {
						clientRidingPlayer = pl;
						startRiding(clientRidingPlayer, false);
					}
				}
			}

			return;
		}
		else if (world.isRemote && isPassenger() && clientRidingPlayer != null) {
			clientRidingPlayer = null;
			stopRiding();
		}

		if (isInWater()) {
			moveRelative(0.02f, motion);
			move(MoverType.SELF, getMotion());

			setMotion(getMotion().scale(0.8d));
		}
		else if (isInLava()) {
			moveRelative(0.02f, motion);
			move(MoverType.SELF, getMotion());

			setMotion(getMotion().scale(0.5d));
		}
		else {
			float friction = 0.91F;

			if (onGround) {
				BlockPos underPos = new BlockPos(MathHelper.floor(getPosX()), MathHelper.floor(getBoundingBox().minY) - 1, MathHelper.floor(getPosZ()));
				BlockState underState = world.getBlockState(underPos);
				friction = underState.getBlock().getSlipperiness(underState, world, underPos, this) * 0.91F;
			}

			moveRelative(onGround ? 0.1F * (0.16277136F / (friction * friction * friction)) : 0.02F, motion);
			friction = 0.91F;

			if (onGround) {
				BlockPos underPos = new BlockPos(MathHelper.floor(getPosX()), MathHelper.floor(getBoundingBox().minY) - 1, MathHelper.floor(getPosZ()));
				BlockState underState = world.getBlockState(underPos);
				friction = underState.getBlock().getSlipperiness(underState, world, underPos, this) * 0.91F;
			}

			move(MoverType.SELF, getMotion());
			setMotion(getMotion().scale(friction));
		}

		prevLimbSwingAmount = limbSwingAmount;
		double movedX = getPosZ() - prevPosX;
		double movedZ = getPosZ() - prevPosZ;
		float totalMotion = MathHelper.sqrt(movedX * movedX + movedZ * movedZ) * 4.0F;

		if (totalMotion > 1.0F)
			totalMotion = 1.0F;

		limbSwingAmount += (totalMotion - limbSwingAmount) * 0.4F;
		limbSwing += limbSwingAmount;
	}

	private class EntityAIMeganeuropsisLand extends Goal {
		private final MeganeuropsisEntity taskOwner;
		private BlockPos landingPos = null;
		private Direction blockFace = null;
		private PlayerEntity landingPlayer = null;

		public EntityAIMeganeuropsisLand(MeganeuropsisEntity creature) {
			this.taskOwner = creature;

			setMutexFlags(EnumSet.of(Flag.LOOK, Flag.MOVE, Flag.TARGET));
		}

		@Override
		public boolean shouldExecute() {
			if (taskOwner.getRevengeTarget() != null)
				return false;

			if (landingPos == null && landingPlayer == null && taskOwner.getRNG().nextFloat() <= 0.01f) {
				if (rand.nextBoolean()) {
					PlayerEntity nearestPlayer = taskOwner.world.getClosestPlayer(getPosX(), getPosY(), getPosZ(), 10, false);

					if (nearestPlayer != null && !nearestPlayer.isBeingRidden()) {
						taskOwner.navigator.clearPath();
						landingPlayer = nearestPlayer;

						return true;
					}
				}
				else {
					for (int i = 0; i < 3; i ++) {
						int x = (int)(taskOwner.getPosX() + taskOwner.getRNG().nextGaussian() * 10);
						int z = (int)(taskOwner.getPosZ() + taskOwner.getRNG().nextGaussian() * 10);
						int y = taskOwner.world.getHeight(Heightmap.Type.MOTION_BLOCKING, new BlockPos(x, taskOwner.getPosY(), z)).getY();

						if (taskOwner.getPosY() - y > 10)
							continue;

						if (y > taskOwner.getPosY()) {
							if (true) // TODO Alternate landing pattern
								return false;

							BlockRayTraceResult trace = taskOwner.world.rayTraceBlocks(new RayTraceContext(getPositionVec(), new Vec3d(x + 0.5d, y + 0.5d, z + 0.5d), RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, null));

							if (trace.getType() == RayTraceResult.Type.MISS)
								return false;

							if (trace.getFace() == Direction.DOWN)
								return false;

							taskOwner.navigator.clearPath();
							landingPos = trace.getPos();
							blockFace = trace.getFace();

							return true;
						}
						else {
							taskOwner.navigator.clearPath();
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
		public void startExecuting() {
			if (landingPos != null) {
				taskOwner.getNavigator().tryMoveToXYZ(landingPos.getX(), landingPos.getY() + 1, landingPos.getZ(), 1f);
			}
			else if (landingPlayer != null) {
				taskOwner.getNavigator().tryMoveToEntityLiving(landingPlayer, 1f);
			}
		}

		@Override
		public boolean shouldContinueExecuting() {
			if ((landingPos == null && (landingPlayer == null || !landingPlayer.isAlive() || (landingPlayer.isBeingRidden() && !landingPlayer.isPassenger(taskOwner))) || taskOwner.getRevengeTarget() != null))
				return false;

			return !taskOwner.getNavigator().noPath() || (taskOwner.getStartLandingTicks() > 0 && taskOwner.ticksExisted - taskOwner.getStartLandingTicks() < 240);
		}

		@Override
		public void tick() {
			BlockPos targetPos = landingPos != null ? landingPos : landingPlayer.getPosition().up();

			if (!taskOwner.isLanded()) {
				if (taskOwner.getPosition().distanceSq(targetPos.getX(), targetPos.getY(), targetPos.getZ(), true) <= 4) {
					taskOwner.getNavigator().clearPath();

					Vec3d actualLandingLocation = getActualLandingLocation();
					BlockPos actualLandingBlockPos = new BlockPos(actualLandingLocation);

					if (taskOwner.world.getBlockState(actualLandingBlockPos).getCollisionShape(taskOwner.world, actualLandingBlockPos) != VoxelShapes.empty()) {
						landingPos = null;
						landingPlayer = null;

						return;
					}

					if (taskOwner.getStartLandingTicks() == 0)
						taskOwner.dataManager.set(START_LANDING_TICKS, taskOwner.ticksExisted);

					if (taskOwner.getPosition().distanceSq(targetPos.getX(), targetPos.getY(), targetPos.getZ(), true) <= 1d) {
						double vecX = actualLandingLocation.x - taskOwner.getPosX();
						double vecZ = actualLandingLocation.z - taskOwner.getPosZ();
						float rotYaw = (float)(MathHelper.atan2(vecZ, vecX) * (180 / Math.PI)) - 90f;

						taskOwner.setLocationAndAngles(actualLandingLocation.x, actualLandingLocation.y, actualLandingLocation.z, rotYaw, 0);

						taskOwner.dataManager.set(LANDED, true);

						if (landingPlayer != null) {
							taskOwner.dataManager.set(LANDING_DIRECTION, Direction.DOWN);
							taskOwner.dataManager.set(LANDED_PLAYER, Optional.of(landingPlayer.getUniqueID()));
							taskOwner.startRiding(landingPlayer, true);
						}
						else {
							taskOwner.dataManager.set(LANDING_DIRECTION, blockFace);
						}
					}
					else {
						double vecX = (actualLandingLocation.x - taskOwner.getPosX()) * 0.5d;
						double vecZ = (actualLandingLocation.z - taskOwner.getPosZ()) * 0.5d;
						double vecY = (actualLandingLocation.y - taskOwner.getPosY()) * 0.5d;

						taskOwner.move(MoverType.SELF, new Vec3d(vecX, vecY, vecZ));
					}
				}
			}
			else {
				if (landingPlayer == null) {
					Vec3d actualLandingLocation = getActualLandingLocation();

					taskOwner.setMotion(new Vec3d(0, 0, 0));
					taskOwner.velocityChanged = true;

					taskOwner.setPositionAndUpdate(actualLandingLocation.x, actualLandingLocation.y - 0.5d, actualLandingLocation.z);
				}
			}
		}

		@Override
		public void resetTask() {
			landingPos = null;
			blockFace = null;
			landingPlayer = null;
			taskOwner.dataManager.set(START_LANDING_TICKS, 0);
			taskOwner.dataManager.set(LANDED, false);
			taskOwner.dataManager.set(LANDING_DIRECTION, Direction.DOWN);
			taskOwner.dataManager.set(LANDED_PLAYER, Optional.empty());
			taskOwner.stopRiding();
		}

		private Vec3d getActualLandingLocation() {
			if (landingPlayer != null) {
				return new Vec3d(landingPlayer.getPosX(), landingPlayer.getPosY() + landingPlayer.getHeight() + taskOwner.getHeight() + 0.1d, landingPlayer.getPosZ());
			}
			else {
				switch (blockFace) {
					case NORTH:
						return new Vec3d(landingPos.getX() + 0.5d, landingPos.getY() + 0.5d, landingPos.getZ() - taskOwner.getWidth() / 2d);
					case SOUTH:
						return new Vec3d(landingPos.getX() + 0.5d, landingPos.getY() + 0.5d, landingPos.getZ() + 1 + taskOwner.getWidth() / 2d);
					case EAST:
						return new Vec3d(landingPos.getX() + 1 + taskOwner.getWidth() / 2d, landingPos.getY() + 0.5d, landingPos.getZ() + 0.5d);
					case WEST:
						return new Vec3d(landingPos.getX() - taskOwner.getWidth() / 2d, landingPos.getY() + 0.5d, landingPos.getZ() + 0.5d);
					default:
					case UP:
						return new Vec3d(landingPos.getX() + 0.5d, landingPos.getY() + 0.5d, landingPos.getZ() + 0.5d);
				}
			}
		}
	}
}
