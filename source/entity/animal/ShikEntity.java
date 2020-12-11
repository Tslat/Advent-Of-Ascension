package net.tslat.aoa3.entity.animal;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.ai.mob.CompletePanicGoal;
import net.tslat.aoa3.entity.base.AoAAnimal;
import net.tslat.aoa3.util.EntityUtil;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class ShikEntity extends AoAAnimal {
	private static final DataParameter<Boolean> SCARED = EntityDataManager.<Boolean>createKey(ShikEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> DANCING = EntityDataManager.<Boolean>createKey(ShikEntity.class, DataSerializers.BOOLEAN);
	private ShikEntity dancePartner = null;

	public ShikEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void registerData() {
		super.registerData();

		dataManager.register(SCARED, false);
		dataManager.register(DANCING, false);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(0, new SwimGoal(this));
		goalSelector.addGoal(1, new PanicAndHideGoal(this, 100, 2d));
		goalSelector.addGoal(2, new ShikDanceGoal(this));
		goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1));
		goalSelector.addGoal(4, new LookRandomlyGoal(this));
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 0.34375f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2;
	}

	@Override
	protected double getBaseArmor() {
		return 4;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return AoASounds.ENTITY_SHIK_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_SHIK_DEATH.get();
	}

	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return null;
	}

	public boolean isScared() {
		return dataManager.get(SCARED);
	}

	public boolean isDancing() {
		return dataManager.get(DANCING);
	}

	@Override
	public boolean canSpawn(IWorld world, SpawnReason reason) {
		if (!EntityUtil.isNaturalSpawnReason(reason))
			return true;

		if (getPosY() >= 120)
			return false;

		BlockPos checkPos = new BlockPos(this);
		BlockState spawnBlock = world.getBlockState(checkPos.down());

		return spawnBlock.getBlock() == AoABlocks.DENSE_STONE.get();
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 500;
	}

	@Override
	public float getBlockPathWeight(BlockPos pos, IWorldReader world) {
		return world.getBlockState(pos.down()).getBlock().isIn(Tags.Blocks.STONE) ? 1f : 1 - world.getBrightness(pos);
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return ticksExisted > 400;
	}

	@Override
	protected boolean checkSpawningLightConditions() {
		return true;
	}

	private class ShikDanceGoal extends Goal {
		private final ShikEntity shik;
		private Vec3d startPos;
		private int danceTimer = 0;
		private int nextDanceTime = 0;
		private float danceAngleX;
		private float danceAngleZ;

		private ShikDanceGoal(ShikEntity shik) {
			this.shik = shik;

			setMutexFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
		}

		@Override
		public boolean shouldExecute() {
			if (nextDanceTime + 100 >= shik.ticksExisted)
				return false;

			if (shik.dancePartner != null && shik.dancePartner.isAlive()) {
				return true;
			}

			if (rand.nextFloat() <= 0.05f) {
				List<ShikEntity> dancePartners = world.getEntitiesWithinAABB(ShikEntity.class, getBoundingBox().grow(0.5));

				for (ShikEntity potentialPartner : dancePartners) {
					if (potentialPartner == shik)
						continue;

					if (!potentialPartner.isScared() && potentialPartner.isAlive() && potentialPartner.dancePartner == null && potentialPartner.canEntityBeSeen(shik) && shik.canEntityBeSeen(potentialPartner)) {
						shik.dancePartner = potentialPartner;
						potentialPartner.dancePartner = shik;

						return true;
					}
				}
			}

			return false;
		}

		@Override
		public void resetTask() {
			shik.dataManager.set(DANCING, false);
			startPos = null;
			danceTimer = 0;
			shik.getNavigator().clearPath();
			nextDanceTime = ticksExisted;
			shik.dancePartner = null;
		}

		@Override
		public void startExecuting() {
			getNavigator().clearPath();
			startPos = getPositionVector();

			shik.dataManager.set(DANCING, true);

			shik.getLookController().setLookPosition(shik.dancePartner.getPosX(), shik.dancePartner.getPosY() + shik.dancePartner.getEyeHeight(), shik.dancePartner.getPosZ(), shik.dancePartner.getHorizontalFaceSpeed(), shik.dancePartner.getVerticalFaceSpeed());

			danceAngleX = MathHelper.sin(shik.rotationYaw * (float)Math.PI / 180f) * 2;
			danceAngleZ = MathHelper.cos(shik.rotationYaw * (float)Math.PI / 180f) * 2;

			getNavigator().tryMoveToXYZ(startPos.x + danceAngleX * 5, startPos.y, startPos.z + danceAngleZ * 5, 1.25f);
		}

		@Override
		public void tick() {
			danceTimer++;
			shik.getLookController().setLookPosition(shik.dancePartner.getPosX(), shik.dancePartner.getPosY() + shik.dancePartner.getEyeHeight(), shik.dancePartner.getPosZ(), shik.dancePartner.getHorizontalFaceSpeed(), shik.dancePartner.getVerticalFaceSpeed());
			shik.setRotation(shik.rotationYawHead, shik.rotationPitch);

			if (danceTimer % 10 == 0 || getNavigator().noPath()) {
				danceAngleX *= -1;
				danceAngleZ *= -1;

				shik.getNavigator().clearPath();
				shik.getNavigator().tryMoveToXYZ(startPos.x + danceAngleX * 5, startPos.y, startPos.z + danceAngleZ * 5, 1.25f);
			}
		}

		@Override
		public boolean shouldContinueExecuting() {
			return shik.dancePartner != null && startPos != null && shik.dancePartner.isAlive() && danceTimer < 100;
		}
	}

	public class PanicAndHideGoal extends CompletePanicGoal {
		private BlockPos hidePos;

		public PanicAndHideGoal(ShikEntity shik, int timeToPanic, double speed) {
			super(shik, timeToPanic, speed);
		}

		@Override
		public boolean shouldExecute() {
			if (taskOwner.getRNG().nextFloat() <= 0.1f && !world.getEntitiesInAABBexcluding(taskOwner, taskOwner.getBoundingBox().grow(5, 5, 5), entity -> entity != null && entity.getType() != taskOwner.getType()).isEmpty()) {
				return getRandomPosition();
			}
			else {
				return super.shouldExecute();
			}
		}

		@Override
		public void resetTask() {
			taskOwner.noClip = false;
			((ShikEntity)taskOwner).dataManager.set(SCARED, false);

			super.resetTask();
		}

		@Override
		public void startExecuting() {
			((ShikEntity)taskOwner).dataManager.set(SCARED, true);
			super.startExecuting();
		}

		@Override
		public void tick() {
			if (taskOwner.getRNG().nextFloat() < 0.1f) {
				if (hidePos != null) {
					if (hidePos.distanceSq(taskOwner.getPosX(), taskOwner.getPosY(), taskOwner.getPosZ(), true) < 3) {
						remove();

						return;
					}
					else if (taskOwner.getNavigator().noPath()) {
						BlockPos.Mutable checkPos = new BlockPos.Mutable();

						for (int x = -1; x <= 1; x++) {
							for (int z = -1; z <= 1; z++) {
								if (taskOwner.world.getBlockState(checkPos.setPos(taskOwner.getPosX() + -x, taskOwner.getPosY(), taskOwner.getPosZ() + -z)).getBlock() == AoABlocks.DENSE_STONE.get()) {
									remove();

									return;
								}
							}
						}
					}
				}

				if (hidePos == null && taskOwner.getRNG().nextFloat() < 0.1f) {
					BlockPos.Mutable checkPos = new BlockPos.Mutable();

					for (int x = 0; x <= 3; x++) {
						for (int z = 0; z <= 3; z++) {
							if (taskOwner.world.getBlockState(checkPos.setPos(taskOwner.getPosX() + x, taskOwner.getPosY(), taskOwner.getPosZ() + z)).getBlock() == AoABlocks.DENSE_STONE.get()) {
								getNavigator().tryMoveToXYZ(checkPos.getX() + 0.5d, checkPos.getY(), checkPos.getZ() + 0.5d, speed);
								panicTimer++;
								hidePos = checkPos.toImmutable();

								return;
							}
							else if (taskOwner.world.getBlockState(checkPos.setPos(taskOwner.getPosX() + -x, taskOwner.getPosY(), taskOwner.getPosZ() + -z)).getBlock() == AoABlocks.DENSE_STONE.get()) {
								taskOwner.getNavigator().tryMoveToXYZ(checkPos.getX() + 0.5d, checkPos.getY(), checkPos.getZ() + 0.5d, speed);
								panicTimer++;
								hidePos = checkPos.toImmutable();

								return;
							}
						}
					}
				}
			}

			super.tick();
		}
	}
}
