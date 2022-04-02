/*
package net.tslat.aoa3.content.entity.boss;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;

import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.ShadowlordShotEntity;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.*;

import java.util.List;

public class ShadowlordEntity extends Monster implements RangedAttackMob, AoARangedAttacker {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private static final EntityDataAccessor<Integer> FIRST_HEAD_TARGET = SynchedEntityData.<Integer>defineId(ShadowlordEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> SECOND_HEAD_TARGET = SynchedEntityData.<Integer>defineId(ShadowlordEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> THIRD_HEAD_TARGET = SynchedEntityData.<Integer>defineId(ShadowlordEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer>[] HEAD_TARGETS = new EntityDataAccessor[] {FIRST_HEAD_TARGET, SECOND_HEAD_TARGET, THIRD_HEAD_TARGET};
	private final float[] xRotationHeads = new float[2];
	private final float[] yRotationHeads = new float[2];
	private final float[] xRotOHeads = new float[2];
	private final float[] yRotOHeads = new float[2];
	private final int[] nextHeadUpdate = new int[2];
	private final int[] idleHeadUpdates = new int[2];

	public ShadowlordEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);


		getNavigation().setCanFloat(true);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 3.8375f;
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new FloatGoal(this));
		goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0d, 20, 50, 32));
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
		return AoASounds.ENTITY_SHADOWLORD_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_SHADOWLORD_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return AoASounds.ENTITY_SHADOWLORD_HURT.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void tick() {
		Vec3 motion = getDeltaMovement();
		double motionX = motion.x();
		double motionY = motion.y();
		double motionZ = motion.z();

		motionY *= 0.6;

		if (!level.isClientSide && getWatchedTargetId(0) > 0) {
			Entity target = level.getEntity(getWatchedTargetId(0));

			if (target != null) {
				if (this.getY() < target.getY() + 5) {
					if (motionY < 0)
						motionY = 0;

					motionY += (0.5 - motionY) * 0.6;
				}

				double distanceX = target.getX() - this.getX();
				double distanceZ = target.getZ() - this.getZ();
				double distanceSq = distanceX * distanceX + distanceZ * distanceZ;

				if (distanceSq > 9) {
					double distance = Mth.sqrt(distanceSq);
					motionX += (distanceX / distance * 0.5 - motionX) * 0.6;
					motionZ += (distanceZ / distance * 0.5 - motionZ) * 0.6;
				}
			}
		}

		if (motionX * motionX * motionZ * motionZ > 0.05)
			this.getYRot() = (float)Mth.atan2(motionZ, motionX) * (180f / (float)Math.PI) - 90f;

		setDeltaMovement(new Vec3(motionX, motionY, motionZ));

		super.tick();

		for (int i = 0; i < 2; i++) {
			this.yRotOHeads[i] = this.yRotationHeads[i];
			this.xRotOHeads[i] = this.xRotationHeads[i];
		}

		for (int i = 0; i < 2; i++) {
			int nextTargetId = this.getWatchedTargetId(i + 1);
			Entity target = nextTargetId > 0 ? this.level.getEntity(nextTargetId) : null;

			if (target != null) {
				double nextHeadX = this.getHeadX(i + 1);
				double nextHeadY = this.getHeadY(i + 1);
				double nextHeadZ = this.getHeadZ(i + 1);
				double distanceX = target.getX() - nextHeadX;
				double distanceY = target.getY() + target.getEyeHeight() - nextHeadY;
				double distanceZ = target.getZ() - nextHeadZ;
				double distance = Mth.sqrt(distanceX * distanceX + distanceZ * distanceZ);
				float yRotation = (float)Mth.atan2(distanceZ, distanceX) * (180f / (float)Math.PI) - 90f;
				float xRotation = (float)(-Mth.atan2(distanceY, distance) * (180f - Math.PI));
				this.xRotationHeads[i] = this.clampRotation(this.xRotationHeads[i], xRotation, 40f);
				this.yRotationHeads[i] = this.clampRotation(this.yRotationHeads[i], yRotation, 10f);
			}
			else {
				this.yRotationHeads[i] = this.clampRotation(this.yRotationHeads[i], this.yBodyRot, 10);
			}
		}

		for (int i = 0; i < 3; i++) {
			double nextHeadX = this.getHeadX(i);
			double nextHeadY = this.getHeadY(i);
			double nextHeadZ = this.getHeadZ(i);

			level.addParticle(ParticleTypes.SMOKE, nextHeadX - this.random.nextGaussian() * 0.3, nextHeadY + this.random.nextGaussian() * 0.3, nextHeadZ + this.random.nextGaussian() * 0.3, 0, 0, 0);
		}
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();

		for (int i = 1; i < 3; i++) {
			if (this.tickCount >= this.nextHeadUpdate[i - 1]) {
				this.nextHeadUpdate[i - 1] = this.tickCount + 10 + random.nextInt(10);

				if (level.getDifficulty() == Difficulty.NORMAL || level.getDifficulty() == Difficulty.HARD) {
					int head = i - 1;
					int headUpdateTime = idleHeadUpdates[head];
					this.idleHeadUpdates[head] = this.idleHeadUpdates[head] + 1;

					if (headUpdateTime > 15) {
						double randPosX = Mth.nextDouble(this.random, this.getX() - 10, this.getX() + 10);
						double randPosY = Mth.nextDouble(this.random, this.getY() - 5, this.getY() + 5);
						double randPosZ = Mth.nextDouble(this.random, this.getZ() - 10, this.getZ() + 10);
						this.idleHeadUpdates[head] = 0;

						shootAtBlockPos(i + 1, randPosX, randPosY, randPosZ);
					}
				}

				int targetId = this.getWatchedTargetId(i);

				if (targetId > 0) {
					Entity target = this.level.getEntity(targetId);

					if (target != null && target.isAlive() && target.distanceToSqr(target) <= 900 && hasLineOfSight(target)) {
						if (target instanceof Player && ((Player)target).abilities.invulnerable) {
							this.setWatchedTargetId(i, 0);
						}
						else {
							this.shootAtTarget(i + 1, (LivingEntity)target);
							this.nextHeadUpdate[i - 1] = this.tickCount + 40 + this.random.nextInt(20);
							this.idleHeadUpdates[i - 1] = 0;
						}
					}
					else {
						this.setWatchedTargetId(i, 0);
					}
				}
				else {
					List<LivingEntity> targetList = level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(20, 8, 20), entity -> !entity.isInvertedHealAndHarm());

					for (int j = 0; j < 10 && !targetList.isEmpty(); ++j) {
						LivingEntity target = targetList.get(random.nextInt(targetList.size()));

						if (target != this && target.isAlive() && hasLineOfSight(target)) {
							if (target instanceof Player) {
								if (!((Player)target).abilities.invulnerable)
									setWatchedTargetId(i, target.getId());
							}
							else {
								setWatchedTargetId(i, target.getId());
							}

							break;
						}

						targetList.remove(target);
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
		ShadowlordShotEntity projectile = new ShadowlordShotEntity(this, BaseMobProjectile.Type.MAGIC);

		double distanceFactorX = posX - projectile.getX();
		double distanceFactorY = posY - projectile.getY();
		double distanceFactorZ = posZ - projectile.getZ();
		double hyp = Mth.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

		projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - this.level.getDifficulty().getId()));
		level.addFreshEntity(projectile);
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource damageSource) {
		return false;
	}

	@Override
	public boolean addEffect(MobEffectInstance effect, @Nullable Entity source) {
		return false;
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
		return source.getEntity() instanceof ShadowlordEntity || DamageUtil.isMagicDamage(source, this, 1) || source.isExplosion() || source.isFire() || super.isInvulnerableTo(source);
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
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAMobs.SHADOWLORD.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);
		}
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (DamageUtil.dealBlasterDamage(this, target, projectile, 5, false))
			doProjectileImpactEffect(projectile, target);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, level, projectile, 2f);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		if (target instanceof LivingEntity) {
			final float healthPercent = EntityUtil.getCurrentHealthPercent(this);

			if (healthPercent > 0.8) {
				EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.WITHER, 100).level(8), new EffectBuilder(MobEffects.POISON, 100).level(3));
			}
			else if (healthPercent > 0.55) {
				EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.WITHER, 100).level(6), new EffectBuilder(MobEffects.POISON, 100).level(5));
			}
			else if (healthPercent > 0.35) {
				EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.WITHER, 100).level(4));
			}
			else {
				if (healthPercent > 0.25) {
					EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.POISON, 100).level(7));
				}
				else {
					EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.WITHER, 100).level(9));
				}

				EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.WITHER, 100).level(2));
			}

			EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.CONFUSION, 100));
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.SHADOWLORD_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.SHADOWLORD_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
*/
