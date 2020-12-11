package net.tslat.aoa3.entity.boss;

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
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.base.AoARangedAttacker;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectile.mob.MechFallEntity;
import net.tslat.aoa3.entity.projectile.mob.MechShotEntity;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public class MechbotEntity extends AoAMeleeMob implements AoARangedAttacker {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getName().deepCopy().appendSibling(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenSky(false).setCreateFog(false);
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

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.9;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 2500;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 15;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
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
	public boolean isNonBoss() {
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (getAttackTarget() == null || !isAlive())
			return;

		LivingEntity target = getAttackTarget();

		if (jumpCooldown > 0)
			jumpCooldown--;

		if (jumpCooldown <= 0) {
			if (jumpCount < 4) {
				jumpIntervalTimer--;

				if (jumpIntervalTimer <= 0) {
					setMotion(0.699, (target.getPosX() - getPosX()) * 0.045, (target.getPosZ() - getPosZ()) * 0.045);

					if (!world.isRemote) {
						world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.ENTITY_MECHBOT_JUMP.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
						world.addEntity(new MechFallEntity(this, getPosX(), getPosY() - 0.25, getPosZ(), BaseMobProjectile.Type.MAGIC));

						if (getHealth() < 1250) {
							world.addEntity(new MechFallEntity(this, getPosX() - 2.25, getPosY() - 0.25, getPosZ() - 2.25, BaseMobProjectile.Type.MAGIC));
							world.addEntity(new MechFallEntity(this, getPosX() - 2.25, getPosY() - 0.25, getPosZ() + 2.25, BaseMobProjectile.Type.MAGIC));
							world.addEntity(new MechFallEntity(this, getPosX() + 2.25, getPosY() - 0.25, getPosZ() - 2.25, BaseMobProjectile.Type.MAGIC));
							world.addEntity(new MechFallEntity(this, getPosX() + 2.25, getPosY() - 0.25, getPosZ() + 2.25, BaseMobProjectile.Type.MAGIC));
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

		if (getHealth() < 1000 ? rand.nextInt(10) == 0 : rand.nextInt(40) == 0) {
			MechShotEntity projectile = new MechShotEntity(this, BaseMobProjectile.Type.MAGIC);

			double distanceFactorX = target.getPosX() - this.getPosX();
			double distanceFactorY = target.getBoundingBox().minY + (double)(target.getHeight() / 3.0f) - projectile.getPosY();
			double distanceFactorZ = target.getPosZ() - this.getPosZ();
			double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) + 0.2D;

			world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.ENTITY_MECHBOT_SHOOT.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
			projectile.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getId()));
			world.addEntity(projectile);
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getTrueSource());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("message.mob.mechbot.kill", killer.getDisplayName().getFormattedText()), world, getPosition(), 50);
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
		WorldUtil.createExplosion(this, world, projectile, 2f);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, world, projectile, 1f);
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
	protected void updateAITasks() {
		super.updateAITasks();

		bossInfo.setPercent(getHealth() / getMaxHealth());
	}

	@Override
	public void addTrackingPlayer(ServerPlayerEntity player) {
		super.addTrackingPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.MECHBOT_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.MECHBOT_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
