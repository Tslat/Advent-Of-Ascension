package net.tslat.aoa3.entity.boss.shadowlord;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedAttacker;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityShadowlordShot;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.TreeSet;

public class EntityShadowlord extends EntityMob implements BossEntity, IRangedAttackMob, AoARangedAttacker, SpecialPropertyEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/shadowlord.png");
	private static final DataParameter<Integer> FIRST_HEAD_TARGET = EntityDataManager.<Integer>createKey(EntityShadowlord.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> SECOND_HEAD_TARGET = EntityDataManager.<Integer>createKey(EntityShadowlord.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> THIRD_HEAD_TARGET = EntityDataManager.<Integer>createKey(EntityShadowlord.class, DataSerializers.VARINT);
	private static final DataParameter<Integer>[] HEAD_TARGETS = new DataParameter[] {FIRST_HEAD_TARGET, SECOND_HEAD_TARGET, THIRD_HEAD_TARGET};
	private final float[] xRotationHeads = new float[2];
	private final float[] yRotationHeads = new float[2];
	private final float[] xRotOHeads = new float[2];
	private final float[] yRotOHeads = new float[2];
	private final int[] nextHeadUpdate = new int[2];
	private final int[] idleHeadUpdates = new int[2];
	private final TreeSet<Enums.MobProperties> mobProperties = new TreeSet<Enums.MobProperties>();
	public static final float entityWidth = 4f;

	public EntityShadowlord(World world) {
		super(world);

		this.setSize(entityWidth, 6.75f);
		this.mobProperties.add(Enums.MobProperties.EXPLOSION_IMMUNE);
		((PathNavigateGround)this.getNavigator()).setCanSwim(true);
	}

	@Override
	public float getEyeHeight() {
		return 3.8375f;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackRanged(this, 1.0D, 20, 50.0F));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, false, null));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();

		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(2000);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.32);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(52);
	}

	@Override
	protected void entityInit() {
		super.entityInit();

		this.dataManager.register(FIRST_HEAD_TARGET, 0);
		this.dataManager.register(SECOND_HEAD_TARGET, 0);
		this.dataManager.register(THIRD_HEAD_TARGET, 0);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_SHADOWLORD_LIVING;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_SHADOWLORD_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundsRegister.MOB_SHADOWLORD_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityShadowlord;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (world.isRemote && ticksExisted == 1)
			playMusic(this);
	}

	@Override
	public void onLivingUpdate() {
		this.motionY *= 0.6;

		if (!world.isRemote && getWatchedTargetId(0) > 0) {
			Entity target = world.getEntityByID(getWatchedTargetId(0));

			if (target != null) {
				if (this.posY < target.posY + 5) {
					if (this.motionY < 0)
						this.motionY = 0;

					this.motionY += (0.5 - this.motionY) * 0.6;
				}

				double distanceX = target.posX - this.posX;
				double distanceZ = target.posZ - this.posZ;
				double distanceSq = distanceX * distanceX + distanceZ * distanceZ;

				if (distanceSq > 9) {
					double distance = MathHelper.sqrt(distanceSq);
					this.motionX += (distanceX / distance * 0.5 - this.motionX) * 0.6;
					this.motionZ += (distanceZ / distance * 0.5 - this.motionZ) * 0.6;
				}
			}
		}

		if (this.motionX * this.motionX * this.motionZ * this.motionZ > 0.05)
			this.rotationYaw = (float)MathHelper.atan2(this.motionZ, this.motionX) * (180f / (float)Math.PI) - 90f;

		super.onLivingUpdate();

		for (int i = 0; i < 2; i++) {
			this.yRotOHeads[i] = this.yRotationHeads[i];
			this.xRotOHeads[i] = this.xRotationHeads[i];
		}

		for (int i = 0; i < 2; i++) {
			int nextTargetId = this.getWatchedTargetId(i + 1);
			Entity target = nextTargetId > 0 ? this.world.getEntityByID(nextTargetId) : null;

			if (target != null) {
				double nextHeadX = this.getHeadX(i + 1);
				double nextHeadY = this.getHeadY(i + 1);
				double nextHeadZ = this.getHeadZ(i + 1);
				double distanceX = target.posX - nextHeadX;
				double distanceY = target.posY + target.getEyeHeight() - nextHeadY;
				double distanceZ = target.posZ - nextHeadZ;
				double distance = MathHelper.sqrt(distanceX * distanceX + distanceZ * distanceZ);
				float yRotation = (float)MathHelper.atan2(distanceZ, distanceX) * (180f / (float)Math.PI) - 90f;
				float xRotation = (float)(-MathHelper.atan2(distanceY, distance) * (180f - Math.PI));
				this.xRotationHeads[i] = this.clampRotation(this.xRotationHeads[i], xRotation, 40f);
				this.yRotationHeads[i] = this.clampRotation(this.yRotationHeads[i], yRotation, 10f);
			}
			else {
				this.yRotationHeads[i] = this.clampRotation(this.yRotationHeads[i], this.renderYawOffset, 10);
			}
		}

		for (int i = 0; i < 3; i++) {
			double nextHeadX = this.getHeadX(i);
			double nextHeadY = this.getHeadY(i);
			double nextHeadZ = this.getHeadZ(i);

			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, nextHeadX - this.rand.nextGaussian() * 0.3, nextHeadY + this.rand.nextGaussian() * 0.3, nextHeadZ + this.rand.nextGaussian() * 0.3, 0, 0, 0);
		}
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();

		for (int i = 1; i < 3; i++) {
			if (this.ticksExisted >= this.nextHeadUpdate[i - 1]) {
				this.nextHeadUpdate[i - 1] = this.ticksExisted + 10 + rand.nextInt(10);

				if (world.getDifficulty() == EnumDifficulty.NORMAL || world.getDifficulty() == EnumDifficulty.HARD) {
					int head = i - 1;
					int headUpdateTime = idleHeadUpdates[head];
					this.idleHeadUpdates[head] = this.idleHeadUpdates[head] + 1;

					if (headUpdateTime > 15) {
						double randPosX = MathHelper.nextDouble(this.rand, this.posX - 10, this.posX + 10);
						double randPosY = MathHelper.nextDouble(this.rand, this.posY - 5, this.posY + 5);
						double randPosZ = MathHelper.nextDouble(this.rand, this.posZ - 10, this.posZ + 10);
						this.idleHeadUpdates[head] = 0;

						shootAtBlockPos(i + 1, randPosX, randPosY, randPosZ);
					}
				}

				int targetId = this.getWatchedTargetId(i);

				if (targetId > 0) {
					Entity target = this.world.getEntityByID(targetId);

					if (target != null && target.isEntityAlive() && target.getDistanceSq(target) <= 900 && canEntityBeSeen(target)) {
						if (target instanceof EntityPlayer && ((EntityPlayer)target).capabilities.disableDamage) {
							this.setWatchedTargetId(i, 0);
						}
						else {
							this.shootAtTarget(i + 1, (EntityLivingBase)target);
							this.nextHeadUpdate[i - 1] = this.ticksExisted + 40 + this.rand.nextInt(20);
							this.idleHeadUpdates[i - 1] = 0;
						}
					}
					else {
						this.setWatchedTargetId(i, 0);
					}
				}
				else {
					List<EntityLivingBase> targetList = world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().grow(20, 8, 20), entity -> !entity.isEntityUndead());

					for (int j = 0; j < 10 && !targetList.isEmpty(); ++j) {
						EntityLivingBase target = targetList.get(rand.nextInt(targetList.size()));

						if (target != this && target.isEntityAlive() && canEntityBeSeen(target)) {
							if (target instanceof EntityPlayer) {
								if (!((EntityPlayer)target).capabilities.disableDamage)
									setWatchedTargetId(i, target.getEntityId());
							}
							else {
								setWatchedTargetId(i, target.getEntityId());
							}

							break;
						}

						targetList.remove(target);
					}
				}
			}
		}

		if (getAttackTarget() != null) {
			setWatchedTargetId(0, getAttackTarget().getEntityId());
		}
		else {
			setWatchedTargetId(0, 0);
		}
	}

	@Override
	public void setInWeb() {}

	private double getHeadX(int head) {
		if (head <= 0)
			return this.posX;

		return this.posX + MathHelper.cos((this.renderYawOffset + (180 * (head - 1))) * 0.017453292F) * 1.3;
	}

	private double getHeadY(int head) {
		return this.posY + 3;
	}

	private double getHeadZ(int head) {
		if (head <= 0)
			return this.posZ;

		return this.posX + MathHelper.sin((this.renderYawOffset + (180 * (head - 1))) * 0.017453292F) * 1.3;
	}

	private float clampRotation(float xRotationOld, float xRotationNew, float max) {
		float degrees = MathHelper.wrapDegrees(xRotationNew - xRotationOld);

		return xRotationOld + MathHelper.clamp(degrees, -max, max);
	}

	private void shootAtBlockPos(int head, double posX, double posY, double posZ) {
		EntityShadowlordShot projectile = new EntityShadowlordShot(this, Enums.MobProjectileType.MAGIC);

		double distanceFactorX = posX - projectile.posX;
		double distanceFactorY = posY - projectile.posY;
		double distanceFactorZ = posZ - projectile.posZ;
		double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

		projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getId()));
		world.spawnEntity(projectile);
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	@Override
	public void addPotionEffect(PotionEffect effect) {}

	private void shootAtTarget(int head, EntityLivingBase target) {
		shootAtBlockPos(head, target.posX, target.posY + target.getEyeHeight() + 0.5, target.posZ);
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		this.shootAtTarget(0, target);
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {}

	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Nullable
	@Override
	public SoundEvent getBossMusic() {
		return SoundsRegister.SHADOWLORD_MUSIC;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	@Override
	public void setAttackTarget(@Nullable EntityLivingBase target) {
		if (target instanceof BossEntity)
			return;

		super.setAttackTarget(target);
	}

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}

	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		return source.getTrueSource() instanceof EntityShadowlord || EntityUtil.isMagicDamage(source, this, 1) || source.isExplosion() || source.isFireDamage() || super.isEntityInvulnerable(source);
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}

	@SideOnly(Side.CLIENT)
	public float getHeadYRotation(int head) {
		return this.yRotationHeads[head];
	}

	@SideOnly(Side.CLIENT)
	public float getHeadXRotation(int head) {
		return this.xRotationHeads[head];
	}

	public int getWatchedTargetId(int head) {
		return this.dataManager.get(HEAD_TARGETS[head]);
	}

	public void setWatchedTargetId(int head, int entityId) {
		this.dataManager.set(HEAD_TARGETS[head], entityId);
	}

	@Override
	protected boolean canBeRidden(Entity entityIn) {
		return false;
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			Entity source = cause.getTrueSource();
			EntityPlayer killer = null;

			if (source != null) {
				if (source instanceof EntityPlayer) {
					killer = (EntityPlayer)source;
				}
				else if (source instanceof EntityTameable && ((EntityTameable)source).getOwner() instanceof EntityPlayer) {
					killer = (EntityPlayer)((EntityTameable)source).getOwner();
				}
			}

			if (killer != null)
				StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.shadowlord.kill", killer.getDisplayNameString()), this, 50);
		}
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (EntityUtil.dealBlasterDamage(this, target, projectile, 5, false))
			doProjectileImpactEffect(projectile, target);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
		WorldUtil.createExplosion(this, world, projectile, 2f);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		if (target instanceof EntityLivingBase) {
			final float healthPercent = EntityUtil.getCurrentHealthPercent(this);

			if (healthPercent > 0.8) {
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 7, true, true));
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 2, true, true));
			} else if (healthPercent > 0.55) {
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 5, true, true));
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 4, true, true));
			} else if (healthPercent > 0.35) {
				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 3, true, true));
			} else {
				if (healthPercent > 0.25) {
					((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 6, true, true));
				} else {
					((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 8, true, true));
				}

				((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 1, true, true));
			}

			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 0, true, true));
		}

		WorldUtil.createExplosion(this, world, projectile, 2f);
	}
}
