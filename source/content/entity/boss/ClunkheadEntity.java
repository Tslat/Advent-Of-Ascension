/*
package net.tslat.aoa3.content.entity.boss;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundEvent;

import net.minecraft.world.BossInfo;
import net.minecraft.world.level.Level;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;

import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.CyanShotEntity;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;


public class ClunkheadEntity extends AoARangedMob<ClunkheadEntity> {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private int stasisCountdown = 500;

	public ClunkheadEntity(EntityType<? extends ClunkheadEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.8125f;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CLUNKHEAD_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_CLUNKHEAD_SHOOT.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new CyanShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide()) {
			if (EntityUtil.getCurrentHealthPercent(this) < 0.5) {
				stasisCountdown--;

				if (stasisCountdown < 100) {
					heal(1);
					setDeltaMovement(0, 0, 0);

					if (stasisCountdown == 0)
						stasisCountdown = 500;
				}
			}

			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getPercent())
				bossInfo.setPercent(healthPercent);
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			Player killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAMobs.CLUNKHEAD.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);
		}
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (hasCustomName())
			bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void setCustomName(@Nullable MutableComponent name) {
		super.setCustomName(name);

		bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.CLUNKHEAD_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.CLUNKHEAD_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
*/
