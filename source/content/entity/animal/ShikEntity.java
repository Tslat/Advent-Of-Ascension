package net.tslat.aoa3.content.entity.animal;

import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.content.entity.ai.mob.CompletePanicGoal;
import net.tslat.aoa3.content.entity.base.AoAAnimal;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;

import javax.annotation.Nullable;
import java.util.EnumSet;
import java.util.List;

public class ShikEntity extends AoAAnimal {
	private static final EntityDataAccessor<Boolean> SCARED = SynchedEntityData.<Boolean>defineId(ShikEntity.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> DANCING = SynchedEntityData.<Boolean>defineId(ShikEntity.class, EntityDataSerializers.BOOLEAN);
	private ShikEntity dancePartner = null;

	public ShikEntity(EntityType<? extends Animal> entityType, Level world) {
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
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, new PanicAndHideGoal(this, 100, 2d));
		goalSelector.addGoal(2, new ShikDanceGoal(this));
		goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1));
		goalSelector.addGoal(4, new RandomLookAroundGoal(this));
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
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
	public boolean checkSpawnRules(LevelAccessor world, MobSpawnType reason) {
		if (!EntityUtil.isNaturalSpawnReason(reason))
			return true;

		if (getY() >= 120)
			return false;

		BlockPos checkPos = new BlockPos(blockPosition());
		BlockState spawnBlock = world.getBlockState(checkPos.below());

		return spawnBlock.getBlock() == AoABlocks.DENSE_STONE.stone();
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader world) {
		return world.getBlockState(pos.below()).is(Tags.Blocks.STONE) ? 1f : 1 - world.getMaxLocalRawBrightness(pos) / 15f;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return tickCount > 400;
	}

	private class ShikDanceGoal extends Goal {
		private final ShikEntity shik;
		private Vec3 startPos;
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
				List<ShikEntity> dancePartners = level().getEntitiesOfClass(ShikEntity.class, getBoundingBox().inflate(0.5));

				for (ShikEntity potentialPartner : dancePartners) {
					if (potentialPartner == shik)
						continue;

					if (!potentialPartner.isScared() && potentialPartner.isAlive() && potentialPartner.dancePartner == null && potentialPartner.hasLineOfSight(shik) && shik.hasLineOfSight(potentialPartner)) {
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

			danceAngleX = Mth.sin(shik.getYRot() * (float)Math.PI / 180f) * 2;
			danceAngleZ = Mth.cos(shik.getYRot() * (float)Math.PI / 180f) * 2;

			getNavigation().moveTo(startPos.x + danceAngleX * 5, startPos.y, startPos.z + danceAngleZ * 5, 1.25f);
		}

		@Override
		public void tick() {
			danceTimer++;
			shik.getLookControl().setLookAt(shik.dancePartner.getX(), shik.dancePartner.getY() + shik.dancePartner.getEyeHeight(), shik.dancePartner.getZ(), shik.dancePartner.getMaxHeadYRot(), shik.dancePartner.getMaxHeadXRot());
			shik.setRot(shik.yHeadRot, shik.getXRot());

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
			if (taskOwner.getRandom().nextFloat() <= 0.1f && !EntityRetrievalUtil.getEntities(taskOwner, 5, new EntityPredicate<>().isNot(taskOwner.getType())).isEmpty()) {
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
					if (hidePos.distToCenterSqr(taskOwner.getX(), taskOwner.getY(), taskOwner.getZ()) < 3) {
						discard();

						return;
					}
					else if (taskOwner.getNavigation().isDone()) {
						BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();

						for (int x = -1; x <= 1; x++) {
							for (int z = -1; z <= 1; z++) {
								if (taskOwner.level().getBlockState(checkPos.set(taskOwner.getX() + -x, taskOwner.getY(), taskOwner.getZ() + -z)).getBlock() == AoABlocks.DENSE_STONE.stone()) {
									discard();

									return;
								}
							}
						}
					}
				}

				if (hidePos == null && taskOwner.getRandom().nextFloat() < 0.1f) {
					BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();

					for (int x = 0; x <= 3; x++) {
						for (int z = 0; z <= 3; z++) {
							if (taskOwner.level().getBlockState(checkPos.set(taskOwner.getX() + x, taskOwner.getY(), taskOwner.getZ() + z)).getBlock() == AoABlocks.DENSE_STONE.stone()) {
								getNavigation().moveTo(checkPos.getX() + 0.5d, checkPos.getY(), checkPos.getZ() + 0.5d, speed);
								panicTimer++;
								hidePos = checkPos.immutable();

								return;
							}
							else if (taskOwner.level().getBlockState(checkPos.set(taskOwner.getX() + -x, taskOwner.getY(), taskOwner.getZ() + -z)).getBlock() == AoABlocks.DENSE_STONE.stone()) {
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
