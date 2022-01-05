package net.tslat.aoa3.object.entity.boss;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.base.AoAMeleeMob;
import net.tslat.aoa3.object.entity.base.AoARangedAttacker;
import net.tslat.aoa3.object.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.object.entity.projectile.mob.MechFallEntity;
import net.tslat.aoa3.object.entity.projectile.mob.MechShotEntity;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;

public class MechbotEntity extends AoAMeleeMob implements AoARangedAttacker {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private int jumpCooldown = 200;
	private int jumpCount = 0;
	private int jumpIntervalTimer = 0;

	public MechbotEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 2.21875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_MECHYON_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_MECHYON_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_MECHYON_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_HEAVY_STEP.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (getTarget() == null || !isAlive())
			return;

		LivingEntity target = getTarget();

		if (jumpCooldown > 0)
			jumpCooldown--;

		if (jumpCooldown <= 0) {
			if (jumpCount < 4) {
				jumpIntervalTimer--;

				if (jumpIntervalTimer <= 0) {
					setDeltaMovement((target.getX() - getX()) * 0.045, 0.699, (target.getZ() - getZ()) * 0.045);

					hurtMarked = true;

					if (!level.isClientSide) {
						level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_MECHBOT_JUMP.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
						level.addFreshEntity(new MechFallEntity(this, getX(), getY() - 0.25, getZ(), BaseMobProjectile.Type.MAGIC));

						if (getHealth() < 1250) {
							level.addFreshEntity(new MechFallEntity(this, getX() - 2.25, getY() - 0.25, getZ() - 2.25, BaseMobProjectile.Type.MAGIC));
							level.addFreshEntity(new MechFallEntity(this, getX() - 2.25, getY() - 0.25, getZ() + 2.25, BaseMobProjectile.Type.MAGIC));
							level.addFreshEntity(new MechFallEntity(this, getX() + 2.25, getY() - 0.25, getZ() - 2.25, BaseMobProjectile.Type.MAGIC));
							level.addFreshEntity(new MechFallEntity(this, getX() + 2.25, getY() - 0.25, getZ() + 2.25, BaseMobProjectile.Type.MAGIC));
						}
					}

					jumpCount++;
					jumpIntervalTimer = 15;
				}
			}
			else {
				jumpCooldown = 200;
				jumpCount = 0;
				jumpIntervalTimer = 15;
			}
		}

		if (getHealth() < 1000 ? random.nextInt(10) == 0 : random.nextInt(40) == 0) {
			MechShotEntity projectile = new MechShotEntity(this, BaseMobProjectile.Type.MAGIC);

			double distanceFactorX = target.getX() - this.getX();
			double distanceFactorY = target.getBoundingBox().minY + (double)(target.getBbHeight() / 3.0f) - projectile.getY();
			double distanceFactorZ = target.getZ() - this.getZ();
			double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) + 0.2D;

			level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_MECHBOT_SHOOT.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
			projectile.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - this.level.getDifficulty().getId()));
			level.addFreshEntity(projectile);
		}

		if (!level.isClientSide()) {
			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getPercent())
				bossInfo.setPercent(healthPercent);
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.MECHBOT.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);
		}
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (DamageUtil.dealBlasterDamage(this, target, projectile, 5f, false))
			doProjectileImpactEffect(projectile, target);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, level, projectile, 2f);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, level, projectile, 1f);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);

		if (hasCustomName())
			bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void setCustomName(@Nullable ITextComponent name) {
		super.setCustomName(name);

		bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void startSeenByPlayer(ServerPlayerEntity player) {
		super.startSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.MECHBOT_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.MECHBOT_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
