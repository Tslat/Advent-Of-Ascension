package net.tslat.aoa3.content.entity.animal;

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
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.ai.mob.CompletePanicGoal;
import net.tslat.aoa3.content.entity.base.AoAAnimal;
import net.tslat.aoa3.util.EntityUtil;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class ShikEntity extends AoAAnimal {
	private static final DataParameter<Boolean> SCARED = EntityDataManager.<Boolean>defineId(ShikEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> DANCING = EntityDataManager.<Boolean>defineId(ShikEntity.class, DataSerializers.BOOLEAN);
	private ShikEntity dancePartner = null;

	public ShikEntity(EntityType<? extends AnimalEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		entityData.define(SCARED, false);
		entityData.define(DANCING, false);
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
	protected void playStepSound(BlockPos pos, BlockState block) {}

	public boolean isScared() {
		return entityData.get(SCARED);
	}

	public boolean isDancing() {
		return entityData.get(DANCING);
	}

	@Override
	public boolean checkSpawnRules(IWorld world, SpawnReason reason) {
		if (!EntityUtil.isNaturalSpawnReason(reason))
			return true;

		if (getY() >= 120)
			return false;

		BlockPos checkPos = new BlockPos(blockPosition());
		BlockState spawnBlock = world.getBlockState(checkPos.below());

		return spawnBlock.getBlock() == AoABlocks.DENSE_STONE.get();
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, IWorldReader world) {
		return world.getBlockState(pos.below()).getBlock().is(Tags.Blocks.STONE) ? 1f : 1 - world.getBrightness(pos);
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return tickCount > 400;
	}

	private class ShikDanceGoal extends Goal {
		private final ShikEntity shik;
		private Vector3d startPos;
		private int danceTimer = 0;
		private int nextDanceTime = 0;
		private float danceAngleX;
		private float danceAngleZ;

		private ShikDanceGoal(ShikEntity shik) {
			this.shik = shik;

			setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
		}

		@Override
		public boolean canUse() {
			if (nextDanceTime + 100 >= shik.tickCount)
				return false;

			if (shik.dancePartner != null && shik.dancePartner.isAlive()) {
				return true;
			}

			if (random.nextFloat() <= 0.05f) {
				List<ShikEntity> dancePartners = level.getEntitiesOfClass(ShikEntity.class, getBoundingBox().inflate(0.5));

				for (ShikEntity potentialPartner : dancePartners) {
					if (potentialPartner == shik)
						continue;

					if (!potentialPartner.isScared() && potentialPartner.isAlive() && potentialPartner.dancePartner == null && potentialPartner.canSee(shik) && shik.canSee(potentialPartner)) {
						shik.dancePartner = potentialPartner;
						potentialPartner.dancePartner = shik;

						return true;
					}
				}
			}

			return false;
		}

		@Override
		public void stop() {
			shik.entityData.set(DANCING, false);
			startPos = null;
			danceTimer = 0;
			shik.getNavigation().stop();
			nextDanceTime = tickCount;
			shik.dancePartner = null;
		}

		@Override
		public void start() {
			getNavigation().stop();
			startPos = position();

			shik.entityData.set(DANCING, true);

			shik.getLookControl().setLookAt(shik.dancePartner.getX(), shik.dancePartner.getY() + shik.dancePartner.getEyeHeight(), shik.dancePartner.getZ(), shik.dancePartner.getMaxHeadYRot(), shik.dancePartner.getMaxHeadXRot());

			danceAngleX = MathHelper.sin(shik.yRot * (float)Math.PI / 180f) * 2;
			danceAngleZ = MathHelper.cos(shik.yRot * (float)Math.PI / 180f) * 2;

			getNavigation().moveTo(startPos.x + danceAngleX * 5, startPos.y, startPos.z + danceAngleZ * 5, 1.25f);
		}

		@Override
		public void tick() {
			danceTimer++;
			shik.getLookControl().setLookAt(shik.dancePartner.getX(), shik.dancePartner.getY() + shik.dancePartner.getEyeHeight(), shik.dancePartner.getZ(), shik.dancePartner.getMaxHeadYRot(), shik.dancePartner.getMaxHeadXRot());
			shik.setRot(shik.yHeadRot, shik.xRot);

			if (danceTimer % 10 == 0 || getNavigation().isDone()) {
				danceAngleX *= -1;
				danceAngleZ *= -1;

				shik.getNavigation().stop();
				shik.getNavigation().moveTo(startPos.x + danceAngleX * 5, startPos.y, startPos.z + danceAngleZ * 5, 1.25f);
			}
		}

		@Override
		public boolean canContinueToUse() {
			return shik.dancePartner != null && startPos != null && shik.dancePartner.isAlive() && danceTimer < 100;
		}
	}

	public class PanicAndHideGoal extends CompletePanicGoal {
		private BlockPos hidePos;

		public PanicAndHideGoal(ShikEntity shik, int timeToPanic, double speed) {
			super(shik, timeToPanic, speed);
		}

		@Override
		public boolean canUse() {
			if (taskOwner.getRandom().nextFloat() <= 0.1f && !level.getEntities(taskOwner, taskOwner.getBoundingBox().inflate(5, 5, 5), entity -> entity != null && entity.getType() != taskOwner.getType()).isEmpty()) {
				return getRandomPosition();
			}
			else {
				return super.canUse();
			}
		}

		@Override
		public void stop() {
			taskOwner.noPhysics = false;
			((ShikEntity)taskOwner).entityData.set(SCARED, false);

			super.stop();
		}

		@Override
		public void start() {
			((ShikEntity)taskOwner).entityData.set(SCARED, true);
			super.start();
		}

		@Override
		public void tick() {
			if (taskOwner.getRandom().nextFloat() < 0.1f) {
				if (hidePos != null) {
					if (hidePos.distSqr(taskOwner.getX(), taskOwner.getY(), taskOwner.getZ(), true) < 3) {
						remove();

						return;
					}
					else if (taskOwner.getNavigation().isDone()) {
						BlockPos.Mutable checkPos = new BlockPos.Mutable();

						for (int x = -1; x <= 1; x++) {
							for (int z = -1; z <= 1; z++) {
								if (taskOwner.level.getBlockState(checkPos.set(taskOwner.getX() + -x, taskOwner.getY(), taskOwner.getZ() + -z)).getBlock() == AoABlocks.DENSE_STONE.get()) {
									remove();

									return;
								}
							}
						}
					}
				}

				if (hidePos == null && taskOwner.getRandom().nextFloat() < 0.1f) {
					BlockPos.Mutable checkPos = new BlockPos.Mutable();

					for (int x = 0; x <= 3; x++) {
						for (int z = 0; z <= 3; z++) {
							if (taskOwner.level.getBlockState(checkPos.set(taskOwner.getX() + x, taskOwner.getY(), taskOwner.getZ() + z)).getBlock() == AoABlocks.DENSE_STONE.get()) {
								getNavigation().moveTo(checkPos.getX() + 0.5d, checkPos.getY(), checkPos.getZ() + 0.5d, speed);
								panicTimer++;
								hidePos = checkPos.immutable();

								return;
							}
							else if (taskOwner.level.getBlockState(checkPos.set(taskOwner.getX() + -x, taskOwner.getY(), taskOwner.getZ() + -z)).getBlock() == AoABlocks.DENSE_STONE.get()) {
								taskOwner.getNavigation().moveTo(checkPos.getX() + 0.5d, checkPos.getY(), checkPos.getZ() + 0.5d, speed);
								panicTimer++;
								hidePos = checkPos.immutable();

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
