package net.tslat.aoa3.entity.boss;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectile.mob.GrawShotEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public class GrawEntity extends AoAFlyingRangedMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private int pullCountdown = 400;

	public GrawEntity(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 2f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_GRAW_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GRAW_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GRAW_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (!isAlive())
			return;

		if (!level.isClientSide) {
			if (getY() > 110)
				setDeltaMovement(getDeltaMovement().add(-1.5 + random.nextInt(3), -4.5, -1.5 + random.nextInt(3)));

			pullCountdown--;

			if (pullCountdown < 60) {
				if (pullCountdown <= 0)
					pullCountdown = 400;

				for (PlayerEntity pl : level.getEntitiesOfClass(PlayerEntity.class, getBoundingBox().inflate(85), PlayerUtil::shouldPlayerBeAffected)) {
					if (!pl.isShiftKeyDown()) {
						pl.push(Math.signum(getX() - pl.getX()) * 0.139, Math.signum(getY() - pl.getY()) * 0.04, Math.signum(getZ() - pl.getZ()) * 0.139);
						pl.hurtMarked = true;
					}
				}
			}
			else {
				for (PlayerEntity pl : level.getEntitiesOfClass(PlayerEntity.class, getBoundingBox().inflate(85), PlayerUtil::shouldPlayerBeAffected)) {
					if (!pl.isShiftKeyDown()) {
						pl.push(Math.signum(getX() - pl.getX()) * 0.008, Math.signum(getY() - pl.getY()) * 0.005, Math.signum(getZ() - pl.getZ()) * 0.008);
						pl.hurtMarked = true;
					}
				}
			}
		}
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, level, projectile, 2.5f);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, level, projectile, 2.5f);
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new GrawShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("message.mob.graw.kill", killer.getDisplayName()), level, blockPosition(), 50);
		}
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
	protected void customServerAiStep() {
		super.customServerAiStep();

		bossInfo.setPercent(getHealth() / getMaxHealth());
	}

	@Override
	public void startSeenByPlayer(ServerPlayerEntity player) {
		super.startSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.GRAW_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.GRAW_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
