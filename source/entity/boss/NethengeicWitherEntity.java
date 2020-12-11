package net.tslat.aoa3.entity.boss;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoARangedAttacker;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectile.mob.NethengeicWitherShotEntity;
import net.tslat.aoa3.util.*;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class NethengeicWitherEntity extends MonsterEntity implements IRangedAttackMob, AoARangedAttacker {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getName().deepCopy().appendSibling(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenSky(false).setCreateFog(false);
	private static final DataParameter<Integer> FIRST_HEAD_TARGET = EntityDataManager.<Integer>createKey(NethengeicWitherEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> SECOND_HEAD_TARGET = EntityDataManager.<Integer>createKey(NethengeicWitherEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> THIRD_HEAD_TARGET = EntityDataManager.<Integer>createKey(NethengeicWitherEntity.class, DataSerializers.VARINT);
	private static final DataParameter<Integer>[] HEAD_TARGETS = new DataParameter[] {FIRST_HEAD_TARGET, SECOND_HEAD_TARGET, THIRD_HEAD_TARGET};
	private final float[] xRotationHeads = new float[2];
	private final float[] yRotationHeads = new float[2];
	private final float[] xRotOHeads = new float[2];
	private final float[] yRotOHeads = new float[2];
	private final int[] nextHeadUpdate = new int[2];
	private final int[] idleHeadUpdates = new int[2];
	public static final float entityWidth = 1.3f;

	private int attackCooldown = 45;

	public NethengeicWitherEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		getNavigator().setCanSwim(true);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 2.484375f;
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new SwimGoal(this));
		goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0d, 20, 50, 32));
		goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1));
		goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8f));
		goalSelector.addGoal(8, new LookRandomlyGoal(this));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<AoAMinion>(this, AoAMinion.class, true));
		targetSelector.addGoal(2, new HurtByTargetGoal(this));
		targetSelector.addGoal(3, new NearestAttackableTargetGoal<LivingEntity>(this, LivingEntity.class, true));
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();

		getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1100);
		getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.32);
		getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(52);
	}

	@Override
	protected void registerData() {
		super.registerData();

		this.dataManager.register(FIRST_HEAD_TARGET, 0);
		this.dataManager.register(SECOND_HEAD_TARGET, 0);
		this.dataManager.register(THIRD_HEAD_TARGET, 0);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_NETHENGEIC_WITHER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_NETHENGEIC_WITHER_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return AoASounds.ENTITY_NETHENGEIC_WITHER_HURT.get();
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	public void tick() {
		Vec3d motion = getMotion();
		double motionX = motion.getX();
		double motionY = motion.getY();
		double motionZ = motion.getZ();

		motionY *= 0.6;

		if (!world.isRemote && getWatchedTargetId(0) > 0) {
			Entity target = world.getEntityByID(getWatchedTargetId(0));

			if (target != null) {
				if (this.getPosY() < target.getPosY() + 5) {
					if (motionY < 0)
						motionY = 0;

					motionY += (0.5 - motionY) * 0.6;
				}

				double distanceX = target.getPosX() - this.getPosX();
				double distanceZ = target.getPosZ() - this.getPosZ();
				double distanceSq = distanceX * distanceX + distanceZ * distanceZ;

				if (distanceSq > 9) {
					double distance = MathHelper.sqrt(distanceSq);
					motionX += (distanceX / distance * 0.5 - motionX) * 0.6;
					motionZ += (distanceZ / distance * 0.5 - motionZ) * 0.6;
				}
			}
		}

		if (motionX * motionX * motionZ * motionZ > 0.05)
			this.rotationYaw = (float)MathHelper.atan2(motionZ, motionX) * (180f / (float)Math.PI) - 90f;

		setMotion(new Vec3d(motionX, motionY, motionZ));

		super.tick();

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
				double distanceX = target.getPosX() - nextHeadX;
				double distanceY = target.getPosY() + target.getEyeHeight() - nextHeadY;
				double distanceZ = target.getPosZ() - nextHeadZ;
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

			world.addParticle(ParticleTypes.SMOKE, nextHeadX - this.rand.nextGaussian() * 0.3, nextHeadY + this.rand.nextGaussian() * 0.3, nextHeadZ + this.rand.nextGaussian() * 0.3, 0, 0, 0);
		}

		if (getStage() > 1 && getHealth() > 0)
			heal(0.3f);
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();

		for (int i = 1; i < 3; i++) {
			if (this.ticksExisted >= this.nextHeadUpdate[i - 1]) {
				this.nextHeadUpdate[i - 1] = this.ticksExisted + 10 + rand.nextInt(10);

				if (world.getDifficulty() == Difficulty.NORMAL || world.getDifficulty() == Difficulty.HARD) {
					int head = i - 1;
					int headUpdateTime = idleHeadUpdates[head];
					this.idleHeadUpdates[head] = this.idleHeadUpdates[head] + 1;

					if (headUpdateTime > 15) {
						double randPosX = MathHelper.nextDouble(this.rand, this.getPosX() - 10, this.getPosX() + 10);
						double randPosY = MathHelper.nextDouble(this.rand, this.getPosY() - 5, this.getPosY() + 5);
						double randPosZ = MathHelper.nextDouble(this.rand, this.getPosZ() - 10, this.getPosZ() + 10);
						this.idleHeadUpdates[head] = 0;

						shootAtBlockPos(i + 1, randPosX, randPosY, randPosZ);
					}
				}

				int targetId = this.getWatchedTargetId(i);

				if (targetId > 0) {
					Entity target = this.world.getEntityByID(targetId);

					if (target != null && target.isAlive() && target.getDistanceSq(target) <= 900 && canEntityBeSeen(target)) {
						if (target instanceof PlayerEntity && ((PlayerEntity)target).abilities.disableDamage) {
							this.setWatchedTargetId(i, 0);
						}
						else {
							this.shootAtTarget(i + 1, (LivingEntity)target);
							this.nextHeadUpdate[i - 1] = this.ticksExisted + 40 + this.rand.nextInt(20);
							this.idleHeadUpdates[i - 1] = 0;
						}
					}
					else {
						this.setWatchedTargetId(i, 0);
					}
				}
				else {
					List<LivingEntity> targetList = world.getEntitiesWithinAABB(LivingEntity.class, this.getBoundingBox().grow(20, 8, 20), entity -> !entity.isEntityUndead());

					for (int j = 0; j < 10 && !targetList.isEmpty(); ++j) {
						LivingEntity target = targetList.get(rand.nextInt(targetList.size()));

						if (target != this && target.isAlive() && canEntityBeSeen(target)) {
							if (target instanceof PlayerEntity) {
								if (!((PlayerEntity)target).abilities.disableDamage)
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

		bossInfo.setPercent(getHealth() / getMaxHealth());
	}


	private double getHeadX(int head) {
		if (head <= 0)
			return this.getPosX();

		return this.getPosX() + MathHelper.cos((this.renderYawOffset + (180 * (head - 1))) * 0.017453292F) * 1.3;
	}

	private double getHeadY(int head) {
		return this.getPosY() + 3;
	}

	private double getHeadZ(int head) {
		if (head <= 0)
			return this.getPosZ();

		return this.getPosX() + MathHelper.sin((this.renderYawOffset + (180 * (head - 1))) * 0.017453292F) * 1.3;
	}

	private float clampRotation(float xRotationOld, float xRotationNew, float max) {
		float degrees = MathHelper.wrapDegrees(xRotationNew - xRotationOld);

		return xRotationOld + MathHelper.clamp(degrees, -max, max);
	}

	private void shootAtBlockPos(int head, double posX, double posY, double posZ) {
		if (attackCooldown > 0) {
			attackCooldown--;
		}
		else {
			attackCooldown = getAttackCooldown();
			BaseMobProjectile projectile = new NethengeicWitherShotEntity(this, getStage() == 3);

			double distanceFactorX = posX - projectile.getPosX();
			double distanceFactorY = posY - projectile.getPosY();
			double distanceFactorZ = posZ - projectile.getPosZ();
			double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

			projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getId()));
			world.addEntity(projectile);
		}
	}

	@Override
	public boolean onLivingFall(float distance, float damageMultiplier) {
		return false;
	}

	@Override
	public boolean addPotionEffect(EffectInstance effectInstanceIn) {
		return false;
	}

	public int getStage() {
		if (getHealth() > 550) {
			return 1;
		}
		else if (getHealth() > 220) {
			return 2;
		}
		else {
			return 3;
		}
	}

	private int getAttackCooldown() {
		switch (getStage()) {
			case 1:
			default:
				return 45;
			case 2:
				return 20;
			case 3:
				return 7;
		}
	}

	private void shootAtTarget(int head, LivingEntity target) {
		shootAtBlockPos(head, target.getPosX(), target.getPosY() + target.getEyeHeight() + 0.5, target.getPosZ());
	}

	@Override
	public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
		this.shootAtTarget(0, target);
	}

	@Override
	public void swingArm(Hand hand) {}

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.UNDEAD;
	}

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source.getTrueSource() instanceof NethengeicWitherEntity || DamageUtil.isMagicDamage(source, this, 1) || source.isExplosion() || source.isFireDamage() || super.isInvulnerableTo(source);
	}

	@OnlyIn(Dist.CLIENT)
	public float getHeadYRotation(int head) {
		return this.yRotationHeads[head];
	}

	@OnlyIn(Dist.CLIENT)
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
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getTrueSource());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("message.mob.nethengeicWither.kill", killer.getDisplayName().getFormattedText()), world, getPosition(), 50);
		}
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (target.attackEntityFrom(DamageSource.causeIndirectDamage(projectile, this), projectile instanceof NethengeicWitherShotEntity ? ((NethengeicWitherShotEntity)projectile).cataclysmic ? 30 : 20 : 20))
			doProjectileImpactEffect(projectile, target);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, world, projectile, 2f);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		if (target instanceof LivingEntity) {
			if (projectile instanceof NethengeicWitherShotEntity) {
				if (((NethengeicWitherShotEntity)projectile).cataclysmic) {
					EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.WITHER, 70).level(3));
				}
				else {
					EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.WITHER, 90).level(2));
				}
			}
		}

		WorldUtil.createExplosion(this, world, projectile, 2f);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);

		if (hasCustomName())
			bossInfo.setName(getType().getName().deepCopy().appendSibling(getDisplayName()));
	}

	@Override
	public void setCustomName(@Nullable ITextComponent name) {
		super.setCustomName(name);

		bossInfo.setName(getType().getName().deepCopy().appendSibling(getDisplayName()));
	}

	@Override
	public void addTrackingPlayer(ServerPlayerEntity player) {
		super.addTrackingPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.NETHENGEIC_WITHER_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.NETHENGEIC_WITHER_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
