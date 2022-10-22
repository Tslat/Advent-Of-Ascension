/*
package net.tslat.aoa3.content.entity.boss;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;

import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;

public class TyrosaurEntity extends AoAMeleeMob<TyrosaurEntity> {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private int stompCooldown = 100;

	public TyrosaurEntity(EntityType<? extends TyrosaurEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 0.96875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_TYROSAUR_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_TYROSAUR_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_TYROSAUR_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_TYROSAUR_STEP.get();
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

		if (stompCooldown > 0)
			stompCooldown--;

		if (stompCooldown > 70) {
			if (getTarget() != null && random.nextInt(150) == 0) {
				if (!level.isClientSide)
					level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_TYROSAUR_CHARGE.get(), SoundSource.HOSTILE, 1.0f, 1.0f);

				push(Math.signum(getTarget().getX() - getX()) * 1.029, (getTarget().getY() - getY()) * 0.0429, Math.signum(getTarget().getZ() - getZ()) * 1.029);
			}
		}
		else if (stompCooldown == 40) {
			if (!level.isClientSide)
				level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_TYROSAUR_READY_STOMP.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
		}
		else if (stompCooldown == 0) {
			stompCooldown = 100;

			if (!level.isClientSide)
				level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_TYROSAUR_STOMP.get(), SoundSource.HOSTILE, 1.0f, 1.0f);

			for (Player pl : level.getEntitiesOfClass(Player.class, getBoundingBox().inflate(35), PlayerUtil::shouldPlayerBeAffected)) {
				if (pl.onGround && !level.isClientSide) {
					if (DamageUtil.dealAoeDamage(null, this, pl, 10, false))
						pl.sendSystemMessage(LocaleUtil.getLocaleMessage(AoAMobs.TYROSAUR.get().getDescriptionId() + ".stomp", ChatFormatting.DARK_GREEN));
				}
			}
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
			Player killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAMobs.TYROSAUR.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.TYROSAUR_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.TYROSAUR_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}
}
*/
