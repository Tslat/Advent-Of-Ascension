package net.tslat.aoa3.entity.passive;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAAnimal;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIFullPanic;

import javax.annotation.Nullable;
import java.util.List;

public class EntityShik extends AoAAnimal {
	public static final float entityWidth = 0.375f;
	private static final DataParameter<Boolean> SCARED = EntityDataManager.<Boolean>createKey(EntityShik.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> DANCING = EntityDataManager.<Boolean>createKey(EntityShik.class, DataSerializers.BOOLEAN);
	private EntityShik dancePartner = null;

	public EntityShik(World world) {
		super(world, entityWidth, 0.4375f);
	}

	@Override
	protected void entityInit() {
		super.entityInit();

		dataManager.register(SCARED, false);
		dataManager.register(DANCING, false);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIFullPanicHide(this, 100, 2d));
		tasks.addTask(2, new EntityAIShikDance(this));
		tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
		tasks.addTask(7, new EntityAILookIdle(this));
	}

	@Override
	public float getEyeHeight() {
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
		return SoundsRegister.ENTITY_SHIK_HIT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.ENTITY_SHIK_DEATH;
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

	public boolean isScared() {
		return dataManager.get(SCARED);
	}

	public boolean isDancing() {
		return dataManager.get(DANCING);
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 120 && super.getCanSpawnHere();
	}

	@Override
	protected boolean checkSpawningLightConditions() {
		return true;
	}

	private class EntityAIShikDance extends EntityAIBase {
		private final EntityShik shik;
		private Vec3d startPos;
		private int danceTimer = 0;
		private int nextDanceTime = 0;
		private float danceAngleX;
		private float danceAngleZ;

		private EntityAIShikDance(EntityShik shik) {
			this.shik = shik;

			setMutexBits(2 | 4);
		}

		@Override
		public boolean shouldExecute() {
			if (nextDanceTime + 100 >= shik.ticksExisted)
				return false;

			if (shik.dancePartner != null && !shik.dancePartner.isDead) {
				return true;
			}

			if (rand.nextFloat() <= 0.05f) {
				List<EntityShik> dancePartners = world.getEntitiesWithinAABB(EntityShik.class, getEntityBoundingBox().grow(0.5));

				for (EntityShik potentialPartner : dancePartners) {
					if (potentialPartner == shik)
						continue;

					if (!potentialPartner.isScared() && !potentialPartner.isDead && potentialPartner.dancePartner == null && potentialPartner.canEntityBeSeen(shik) && shik.canEntityBeSeen(potentialPartner)) {
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

			shik.getLookHelper().setLookPosition(shik.dancePartner.posX, shik.dancePartner.posY + shik.dancePartner.getEyeHeight(), shik.dancePartner.posZ, shik.dancePartner.getHorizontalFaceSpeed(), shik.dancePartner.getVerticalFaceSpeed());

			danceAngleX = MathHelper.sin(shik.rotationYaw * (float)Math.PI / 180f) * 2;
			danceAngleZ = MathHelper.cos(shik.rotationYaw * (float)Math.PI / 180f) * 2;

			getNavigator().tryMoveToXYZ(startPos.x + danceAngleX * 5, startPos.y, startPos.z + danceAngleZ * 5, 1.25f);
		}

		@Override
		public void updateTask() {
			danceTimer++;
			shik.getLookHelper().setLookPosition(shik.dancePartner.posX, shik.dancePartner.posY + shik.dancePartner.getEyeHeight(), shik.dancePartner.posZ, shik.dancePartner.getHorizontalFaceSpeed(), shik.dancePartner.getVerticalFaceSpeed());
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
			return shik.dancePartner != null && startPos != null && !shik.dancePartner.isDead && danceTimer < 100;
		}
	}

	public class EntityAIFullPanicHide extends EntityAIFullPanic {
		private BlockPos hidePos;

		public EntityAIFullPanicHide(EntityShik shik, int timeToPanic, double speed) {
			super(shik, timeToPanic, speed);
		}

		@Override
		public boolean shouldExecute() {
			if (taskOwner.getRNG().nextFloat() <= 0.1f && !world.getEntitiesWithinAABB(EntityLivingBase.class, taskOwner.getEntityBoundingBox().grow(5, 5, 5), entity -> entity != null && entity.getClass() != taskOwner.getClass()).isEmpty()) {
				return getRandomPosition();
			}
			else {
				return super.shouldExecute();
			}
		}

		@Override
		public void resetTask() {
			taskOwner.noClip = false;
			((EntityShik)taskOwner).dataManager.set(SCARED, false);

			super.resetTask();
		}

		@Override
		public void startExecuting() {
			((EntityShik)taskOwner).dataManager.set(SCARED, true);
			super.startExecuting();
		}

		@Override
		public void updateTask() {
			if (taskOwner.getRNG().nextFloat() < 0.1f) {
				if (hidePos != null) {
					if (hidePos.distanceSqToCenter(taskOwner.posX, taskOwner.posY, taskOwner.posZ) < 3) {
						setDead();

						return;
					}
					else if (taskOwner.getNavigator().noPath()) {
						BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();

						for (int x = -1; x <= 1; x++) {
							for (int z = -1; z <= 1; z++) {
								if (taskOwner.world.getBlockState(checkPos.setPos(taskOwner.posX + -x, taskOwner.posY, taskOwner.posZ + -z)).getBlock() == BlockRegister.DEEPLANDS_STONE) {
									setDead();

									return;
								}
							}
						}
					}
				}

				if (hidePos == null && taskOwner.getRNG().nextFloat() < 0.1f) {
					BlockPos.MutableBlockPos checkPos = new BlockPos.MutableBlockPos();

					for (int x = 0; x <= 3; x++) {
						for (int z = 0; z <= 3; z++) {
							if (taskOwner.world.getBlockState(checkPos.setPos(taskOwner.posX + x, taskOwner.posY, taskOwner.posZ + z)).getBlock() == BlockRegister.DEEPLANDS_STONE) {
								getNavigator().tryMoveToXYZ(checkPos.getX() + 0.5d, checkPos.getY(), checkPos.getZ() + 0.5d, speed);
								panicTimer++;
								hidePos = checkPos.toImmutable();

								return;
							}
							else if (taskOwner.world.getBlockState(checkPos.setPos(taskOwner.posX + -x, taskOwner.posY, taskOwner.posZ + -z)).getBlock() == BlockRegister.DEEPLANDS_STONE) {
								taskOwner.getNavigator().tryMoveToXYZ(checkPos.getX() + 0.5d, checkPos.getY(), checkPos.getZ() + 0.5d, speed);
								panicTimer++;
								hidePos = checkPos.toImmutable();

								return;
							}
						}
					}
				}
			}

			super.updateTask();
		}
	}
}
