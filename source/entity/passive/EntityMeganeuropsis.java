package net.tslat.aoa3.entity.passive;

import com.google.common.base.Optional;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAAnimal;
import net.tslat.aoa3.entity.base.ai.RoamingFlightMoveHelper;
import net.tslat.aoa3.entity.base.ai.mob.EntityAILookAround;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIRandomFly;

import javax.annotation.Nullable;
import java.util.UUID;

public class EntityMeganeuropsis extends AoAAnimal implements EntityFlying {
	private static final DataParameter<Boolean> LANDED = EntityDataManager.<Boolean>createKey(EntityMeganeuropsis.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> START_LANDING_TICKS = EntityDataManager.<Integer>createKey(EntityMeganeuropsis.class, DataSerializers.VARINT);
	private static final DataParameter<EnumFacing> LANDING_DIRECTION = EntityDataManager.<EnumFacing>createKey(EntityMeganeuropsis.class, DataSerializers.FACING);
	private static final DataParameter<Optional<UUID>> LANDED_PLAYER = EntityDataManager.<Optional<UUID>>createKey(EntityMeganeuropsis.class, DataSerializers.OPTIONAL_UNIQUE_ID);

	public static final float entityWidth = 0.5f;

	private EntityPlayer clientRidingPlayer = null;
	private int clientStartLandingTicks = 0;

	public EntityMeganeuropsis(World world) {
		super(world, entityWidth, 0.4375f);

		moveHelper = new RoamingFlightMoveHelper(this);
	}

	@Override
	protected void entityInit() {
		super.entityInit();

		dataManager.register(LANDED, false);
		dataManager.register(START_LANDING_TICKS, 0);
		dataManager.register(LANDING_DIRECTION, EnumFacing.DOWN);
		dataManager.register(LANDED_PLAYER, Optional.absent());
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAIMeganeuropsisLand(this));
		tasks.addTask(1, new EntityAIRandomFly(this, true));
		tasks.addTask(2, new EntityAILookAround(this));
	}

	@Override
	protected PathNavigate createNavigator(World world) {
		return new PathNavigateFlying(this, world);
	}

	@Override
	public float getEyeHeight() {
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

	public EnumFacing getLandingDirection() {
		return dataManager.get(LANDING_DIRECTION);
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundsRegister.ENTITY_MEGANEUROPSIS_HIT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.ENTITY_MEGANEUROPSIS_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.ENTITY_MEGANEUROPSIS_LIVING;
	}

	@Override
	protected SoundEvent getStepSound() {
		return null;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityShik;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) {}

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
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public void travel(float strafe, float vertical, float forward) {
		if (isLanded()) {
			if (world.isRemote && getLandingDirection() == EnumFacing.DOWN && !isRiding()) {
				if (dataManager.get(LANDED_PLAYER).isPresent()) {
					EntityPlayer pl = world.getPlayerEntityByUUID(dataManager.get(LANDED_PLAYER).get());

					if (pl != null) {
						clientRidingPlayer = pl;
						startRiding(clientRidingPlayer, false);
					}
				}
			}

			return;
		}
		else if (world.isRemote && isRiding() && clientRidingPlayer != null) {
			clientRidingPlayer = null;
			dismountRidingEntity();
		}

		if (isInWater()) {
			moveRelative(strafe, vertical, forward, 0.02F);
			move(MoverType.SELF, motionX, motionY, motionZ);

			motionX *= 0.800000011920929D;
			motionY *= 0.800000011920929D;
			motionZ *= 0.800000011920929D;
		}
		else if (isInLava()) {
			moveRelative(strafe, vertical, forward, 0.02F);
			move(MoverType.SELF, motionX, motionY, motionZ);

			motionX *= 0.5D;
			motionY *= 0.5D;
			motionZ *= 0.5D;
		}
		else {
			float friction = 0.91F;

			if (onGround) {
				BlockPos underPos = new BlockPos(MathHelper.floor(posX), MathHelper.floor(getEntityBoundingBox().minY) - 1, MathHelper.floor(posZ));
				IBlockState underState = world.getBlockState(underPos);
				friction = underState.getBlock().getSlipperiness(underState, world, underPos, this) * 0.91F;
			}

			moveRelative(strafe, vertical, forward, onGround ? 0.1F * (0.16277136F / (friction * friction * friction)) : 0.02F);
			friction = 0.91F;

			if (onGround) {
				BlockPos underPos = new BlockPos(MathHelper.floor(posX), MathHelper.floor(getEntityBoundingBox().minY) - 1, MathHelper.floor(posZ));
				IBlockState underState = world.getBlockState(underPos);
				friction = underState.getBlock().getSlipperiness(underState, world, underPos, this) * 0.91F;
			}

			move(MoverType.SELF, motionX, motionY, motionZ);
			motionX *= friction;
			motionY *= friction;
			motionZ *= friction;
		}

		prevLimbSwingAmount = limbSwingAmount;
		double movedX = posX - prevPosX;
		double movedZ = posZ - prevPosZ;
		float totalMotion = MathHelper.sqrt(movedX * movedX + movedZ * movedZ) * 4.0F;

		if (totalMotion > 1.0F)
			totalMotion = 1.0F;

		limbSwingAmount += (totalMotion - limbSwingAmount) * 0.4F;
		limbSwing += limbSwingAmount;
	}

	private class EntityAIMeganeuropsisLand extends EntityAIBase {
		private final EntityMeganeuropsis taskOwner;
		private BlockPos landingPos = null;
		private EnumFacing blockFace = null;
		private EntityPlayer landingPlayer = null;

		public EntityAIMeganeuropsisLand(EntityMeganeuropsis creature) {
			this.taskOwner = creature;

			setMutexBits(1 | 2 | 4);
		}

		@Override
		public boolean shouldExecute() {
			if (taskOwner.getRevengeTarget() != null)
				return false;

			if (landingPos == null && landingPlayer == null && taskOwner.getRNG().nextFloat() <= 0.01f) {
				if (rand.nextBoolean()) {
					EntityPlayer nearestPlayer = taskOwner.world.getNearestAttackablePlayer(taskOwner, 10, 10);

					if (nearestPlayer != null && !nearestPlayer.isBeingRidden()) {
						taskOwner.navigator.clearPath();
						landingPlayer = nearestPlayer;

						return true;
					}
				}
				else {
					for (int i = 0; i < 3; i ++) {
						int x = (int)(taskOwner.posX + taskOwner.getRNG().nextGaussian() * 10);
						int z = (int)(taskOwner.posZ + taskOwner.getRNG().nextGaussian() * 10);
						int y = taskOwner.world.getHeight(x, z);

						if (taskOwner.posY - y > 10)
							continue;

						if (y > taskOwner.posY) {
							if (true)
								return false;
							RayTraceResult trace = taskOwner.world.rayTraceBlocks(getPositionVector(), new Vec3d(x + 0.5d, y + 0.5d, z + 0.5d));

							if (trace == null)
								return false;

							if (trace.typeOfHit == RayTraceResult.Type.BLOCK) {
								if (trace.sideHit == EnumFacing.DOWN)
									return false;

								taskOwner.navigator.clearPath();
								landingPos = trace.getBlockPos();
								blockFace = trace.sideHit;

								return true;
							}
						}
						else {
							taskOwner.navigator.clearPath();
							landingPos = new BlockPos(x, y, z);
							blockFace = EnumFacing.UP;

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
			if ((landingPos == null && (landingPlayer == null || landingPlayer.isDead || (landingPlayer.isBeingRidden() && !landingPlayer.isPassenger(taskOwner))) || taskOwner.getRevengeTarget() != null))
				return false;

			return !taskOwner.getNavigator().noPath() || (taskOwner.getStartLandingTicks() > 0 && taskOwner.ticksExisted - taskOwner.getStartLandingTicks() < 240);
		}

		@Override
		public void updateTask() {
			BlockPos targetPos = landingPos != null ? landingPos : landingPlayer.getPosition().up();

			if (!taskOwner.isLanded()) {
				if (taskOwner.getPosition().distanceSq(targetPos.getX(), targetPos.getY(), targetPos.getZ()) <= 4) {
					taskOwner.getNavigator().clearPath();

					Vec3d actualLandingLocation = getActualLandingLocation();
					BlockPos actualLandingBlockPos = new BlockPos(actualLandingLocation);

					if (taskOwner.world.getBlockState(actualLandingBlockPos).getCollisionBoundingBox(taskOwner.world, actualLandingBlockPos) != null) {
						landingPos = null;
						landingPlayer = null;

						return;
					}

					if (taskOwner.getStartLandingTicks() == 0)
						taskOwner.dataManager.set(START_LANDING_TICKS, taskOwner.ticksExisted);

					if (taskOwner.getPosition().distanceSq(targetPos.getX() + 0.5d, targetPos.getY() + 0.5d, targetPos.getZ() + 0.5d) <= 1d) {
						double vecX = actualLandingLocation.x - taskOwner.posX;
						double vecZ = actualLandingLocation.z - taskOwner.posZ;
						float rotYaw = (float)(MathHelper.atan2(vecZ, vecX) * (180 / Math.PI)) - 90f;

						taskOwner.setLocationAndAngles(actualLandingLocation.x, actualLandingLocation.y, actualLandingLocation.z, rotYaw, 0);

						taskOwner.dataManager.set(LANDED, true);

						if (landingPlayer != null) {
							taskOwner.dataManager.set(LANDING_DIRECTION, EnumFacing.DOWN);
							taskOwner.dataManager.set(LANDED_PLAYER, Optional.of(landingPlayer.getUniqueID()));
							taskOwner.startRiding(landingPlayer, true);
						}
						else {
							taskOwner.dataManager.set(LANDING_DIRECTION, blockFace);
						}
					}
					else {
						double vecX = (actualLandingLocation.x - taskOwner.posX) * 0.5d;
						double vecZ = (actualLandingLocation.z - taskOwner.posZ) * 0.5d;
						double vecY = (actualLandingLocation.y - taskOwner.posY) * 0.5d;

						taskOwner.move(MoverType.SELF, vecX, vecY, vecZ);
					}
				}
			}
			else {
				if (landingPlayer == null) {
					Vec3d actualLandingLocation = getActualLandingLocation();
					taskOwner.motionX = 0;
					taskOwner.motionY = 0;
					taskOwner.motionZ = 0;
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
			taskOwner.dataManager.set(LANDING_DIRECTION, EnumFacing.DOWN);
			taskOwner.dataManager.set(LANDED_PLAYER, Optional.absent());
			taskOwner.dismountRidingEntity();
		}

		private Vec3d getActualLandingLocation() {
			if (landingPlayer != null) {
				return new Vec3d(landingPlayer.posX, landingPlayer.posY + landingPlayer.height + taskOwner.height + 0.1d, landingPlayer.posZ);
			}
			else {
				switch (blockFace) {
					case NORTH:
						return new Vec3d(landingPos.getX() + 0.5d, landingPos.getY() + 0.5d, landingPos.getZ() - width / 2d);
					case SOUTH:
						return new Vec3d(landingPos.getX() + 0.5d, landingPos.getY() + 0.5d, landingPos.getZ() + 1 + width / 2d);
					case EAST:
						return new Vec3d(landingPos.getX() + 1 + width / 2d, landingPos.getY() + 0.5d, landingPos.getZ() + 0.5d);
					case WEST:
						return new Vec3d(landingPos.getX() - width / 2d, landingPos.getY() + 0.5d, landingPos.getZ() + 0.5d);
					default:
					case UP:
						return new Vec3d(landingPos.getX() + 0.5d, landingPos.getY() + 0.5d, landingPos.getZ() + 0.5d);
				}
			}
		}
	}
}
