/*
package net.tslat.aoa3.content.entity.boss;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.datasync.SynchedEntityData;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

import net.minecraft.world.BossInfo;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.Level;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;

import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.NethengeicWitherShotEntity;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.*;

import javax.annotation.Nullable;
import java.util.List;

public class NethengeicWitherEntity extends Monster implements RangedAttackMob, AoARangedAttacker {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private static final EntityDataAccessor<Integer> FIRST_HEAD_TARGET = SynchedEntityData.<Integer>defineId(NethengeicWitherEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> SECOND_HEAD_TARGET = SynchedEntityData.<Integer>defineId(NethengeicWitherEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> THIRD_HEAD_TARGET = SynchedEntityData.<Integer>defineId(NethengeicWitherEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer>[] HEAD_TARGETS = new EntityDataAccessor[] {FIRST_HEAD_TARGET, SECOND_HEAD_TARGET, THIRD_HEAD_TARGET};
	private static final EntityPredicate NOT_UNDEAD = (new EntityPredicate()).range(20.0D).selector((entity) -> entity.getMobType() != MobType.UNDEAD && entity.attackable());
	private final float[] xRotationHeads = new float[2];
	private final float[] yRotationHeads = new float[2];
	private final float[] xRotOHeads = new float[2];
	private final float[] yRotOHeads = new float[2];
	private final int[] nextHeadUpdate = new int[2];
	private final int[] idleHeadUpdates = new int[2];

	private int attackCooldown = 45;

	public NethengeicWitherEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		getNavigation().setCanFloat(true);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 2.484375f;
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new FloatGoal(this));
		goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0d, 1, 10, 32));
		goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
		goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8f));
		goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		targetSelector.addGoal(1, new HurtByTargetGoal(this));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<LivingEntity>(this, LivingEntity.class, true));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		this.entityData.define(FIRST_HEAD_TARGET, 0);
		this.entityData.define(SECOND_HEAD_TARGET, 0);
		this.entityData.define(THIRD_HEAD_TARGET, 0);
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
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void aiStep() {
		Vec3 motion = getDeltaMovement().multiply(1, 0.6d, 1);

		if (!level.isClientSide && getWatchedTargetId(0) > 0) {
			Entity primaryTarget = level.getEntity(getWatchedTargetId(0));

			if (primaryTarget != null) {
				double yVelocity = motion.y;

				if (getY() < primaryTarget.getY() || getY() < primaryTarget.getY() + 5) {
					yVelocity = Math.max(0.0D, yVelocity);
					yVelocity = yVelocity + (0.3D - yVelocity * (double)0.6f);
				}

				motion = new Vec3(motion.x, yVelocity, motion.z);
				Vec3 targetVec = new Vec3(primaryTarget.getX() - getX(), 00D, primaryTarget.getZ() - getZ());

				if (getHorizontalDistanceSqr(targetVec) > 9.0D) {
					targetVec = targetVec.normalize();
					motion = motion.add(targetVec.x * 0.3d - motion.x * 0.6d, 0, targetVec.z * 0.3d - motion.z * 0.6d);
				}
			}
		}

		setDeltaMovement(motion);

		if (getHorizontalDistanceSqr(motion) > 0.05d)
			yRot = (float)Mth.atan2(motion.z, motion.x) * (180f / (float)Math.PI) - 90f;

		super.aiStep();

		for (int i = 0; i < 2; ++i) {
			yRotOHeads[i] = yRotationHeads[i];
			xRotOHeads[i] = xRotationHeads[i];
		}

		for (int headId = 0; headId < 2; ++headId) {
			int nextTargetID = getWatchedTargetId(headId + 1);
			Entity target = null;

			if (nextTargetID > 0)
				target = level.getEntity(nextTargetID);

			if (target != null) {
				double headX = getHeadX(headId + 1);
				double headY = getHeadY(headId + 1);
				double headZ = getHeadZ(headId + 1);
				double headTargetDistanceX = target.getX() - headX;
				double headTargetDistanceY = target.getEyeY() - headY;
				double headTargetDistanceZ = target.getZ() - headZ;
				double targetDistance = Mth.sqrt(headTargetDistanceX * headTargetDistanceX + headTargetDistanceZ * headTargetDistanceZ);
				float f = (float)(Mth.atan2(headTargetDistanceZ, headTargetDistanceX) * (double)(180f / (float)Math.PI)) - 90f;
				float f1 = (float)(-(Mth.atan2(headTargetDistanceY, targetDistance) * (double)(180f / (float)Math.PI)));
				xRotationHeads[headId] = clampRotation(xRotationHeads[headId], f1, 40.0F);
				yRotationHeads[headId] = clampRotation(yRotationHeads[headId], f, 10.0F);
			}
			else {
				yRotationHeads[headId] = clampRotation(yRotationHeads[headId], yBodyRot, 10.0F);
			}
		}

		for (int i = 0; i < 3; i++) {
			double nextHeadX = getHeadX(i);
			double nextHeadY = getHeadY(i);
			double nextHeadZ = getHeadZ(i);

			level.addParticle(ParticleTypes.SMOKE, nextHeadX - random.nextGaussian() * 0.3, nextHeadY + random.nextGaussian() * 0.3, nextHeadZ + random.nextGaussian() * 0.3, 0, 0, 0);
		}

		if (getStage() > 1 && getHealth() > 0)
			heal(0.3f);
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		for(int i = 1; i < 3; ++i) {
			if (tickCount >= nextHeadUpdate[i - 1]) {
				nextHeadUpdate[i - 1] = tickCount + 10 + random.nextInt(10);

				if (level.getDifficulty() == Difficulty.NORMAL || level.getDifficulty() == Difficulty.HARD) {
					int prevHeadId = i - 1;
					int idleHeadUpdate = idleHeadUpdates[prevHeadId];
					idleHeadUpdates[prevHeadId] = idleHeadUpdates[prevHeadId] + 1;

					if (idleHeadUpdate > 15) {
						double targetPosX = Mth.nextDouble(random, getX() - 10, getX() + 10);
						double targetPosY = Mth.nextDouble(random, getY() - 5, getY() + 5);
						double targetPosZ = Mth.nextDouble(random, getZ() - 10, getZ() + 10);

						shootAtBlockPos(i + 1, targetPosX, targetPosY, targetPosZ);

						idleHeadUpdates[prevHeadId] = 0;
					}
				}

				int targetId = getWatchedTargetId(i);

				if (targetId > 0) {
					Entity entity = level.getEntity(targetId);

					if (entity != null && entity.isAlive() && !(distanceToSqr(entity) > 900) && hasLineOfSight(entity)) {
						if (entity instanceof Player && ((Player)entity).abilities.invulnerable) {
							setWatchedTargetId(i, 0);
						}
						else {
							shootAtTarget(i + 1, (LivingEntity)entity);
							nextHeadUpdate[i - 1] = tickCount + 40 + random.nextInt(20);
							idleHeadUpdates[i - 1] = 0;
						}
					}
					else {
						setWatchedTargetId(i, 0);
					}
				}
				else {
					List<LivingEntity> list = level.getNearbyEntities(LivingEntity.class, NOT_UNDEAD, this, getBoundingBox().inflate(20, 8, 20));

					for(int j2 = 0; j2 < 10 && !list.isEmpty(); ++j2) {
						LivingEntity entity = list.get(random.nextInt(list.size()));

						if (entity != this && entity.isAlive() && !entity.isInvertedHealAndHarm() && hasLineOfSight(entity)) {
							if (entity instanceof Player) {
								if (!((Player)entity).abilities.invulnerable)
									setWatchedTargetId(i, entity.getId());
							}
							else {
								setWatchedTargetId(i, entity.getId());
							}
							break;
						}

						list.remove(entity);
					}
				}
			}
		}

		if (getTarget() != null) {
			setWatchedTargetId(0, getTarget().getId());
		}
		else {
			setWatchedTargetId(0, 0);
		}

		bossInfo.setPercent(getHealth() / getMaxHealth());
	}

	private double getHeadX(int head) {
		if (head <= 0)
			return this.getX();

		return this.getX() + Mth.cos((this.yBodyRot + (180 * (head - 1))) * 0.017453292F) * 1.3;
	}

	private double getHeadY(int head) {
		return this.getY() + 3;
	}

	private double getHeadZ(int head) {
		if (head <= 0)
			return this.getZ();

		return this.getX() + Mth.sin((this.yBodyRot + (180 * (head - 1))) * 0.017453292F) * 1.3;
	}

	private float clampRotation(float xRotationOld, float xRotationNew, float max) {
		float degrees = Mth.wrapDegrees(xRotationNew - xRotationOld);

		return xRotationOld + Mth.clamp(degrees, -max, max);
	}

	private void shootAtBlockPos(int head, double posX, double posY, double posZ) {
		if (attackCooldown > 0) {
			attackCooldown--;
		}
		else {
			attackCooldown = getAttackCooldown();
			BaseMobProjectile projectile = new NethengeicWitherShotEntity(this, getStage() == 3);

			double distanceFactorX = posX - projectile.getX();
			double distanceFactorY = posY - projectile.getY();
			double distanceFactorZ = posZ - projectile.getZ();
			double hyp = Mth.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

			projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - this.level.getDifficulty().getId()));
			level.addFreshEntity(projectile);
		}
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource damageSource) {
		return false;
	}

	@Override
	public boolean addEffect(MobEffectInstance effectInstanceIn) {
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
		shootAtBlockPos(head, target.getX(), target.getY() + target.getEyeHeight() + 0.5, target.getZ());
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		this.shootAtTarget(0, target);
	}

	@Override
	public void swing(InteractionHand hand) {}

	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

	@Override
	public boolean ignoreExplosion() {
		return true;
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		return source.getEntity() instanceof NethengeicWitherEntity || DamageUtil.isMagicDamage(source, this, 1) || source.isExplosion() || source.isFire() || super.isInvulnerableTo(source);
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
		return this.entityData.get(HEAD_TARGETS[head]);
	}

	public void setWatchedTargetId(int head, int entityId) {
		this.entityData.set(HEAD_TARGETS[head], entityId);
	}

	@Override
	protected boolean canRide(Entity entityIn) {
		return false;
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			Player killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAMobs.NETHENGEIC_WITHER.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);
		}
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (target.hurt(DamageSource.indirectMobAttack(projectile, this), projectile instanceof NethengeicWitherShotEntity ? ((NethengeicWitherShotEntity)projectile).cataclysmic ? 30 : 20 : 20))
			doProjectileImpactEffect(projectile, target);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, level, projectile, 2f);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		if (target instanceof LivingEntity) {
			if (projectile instanceof NethengeicWitherShotEntity) {
				if (((NethengeicWitherShotEntity)projectile).cataclysmic) {
					EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.WITHER, 70).level(3));
				}
				else {
					EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.WITHER, 90).level(2));
				}
			}
		}

		WorldUtil.createExplosion(this, level, projectile, 2f);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (hasCustomName())
			bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void setCustomName(@Nullable TextComponent name) {
		super.setCustomName(name);

		bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.NETHENGEIC_WITHER_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.NETHENGEIC_WITHER_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
*/
