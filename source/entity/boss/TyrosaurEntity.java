package net.tslat.aoa3.entity.boss;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public class TyrosaurEntity extends AoAMeleeMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getName().deepCopy().appendSibling(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenSky(false).setCreateFog(false);
	private int stompCooldown = 100;

	public TyrosaurEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 0.96875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.9;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 4000;
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
	public boolean isNonBoss() {
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
			if (getAttackTarget() != null && rand.nextInt(150) == 0) {
				if (!world.isRemote)
					world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.ENTITY_TYROSAUR_CHARGE.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);

				addVelocity(Math.signum(getAttackTarget().getPosX() - getPosX()) * 1.029, (getAttackTarget().getPosY() - getPosY()) * 0.0429, Math.signum(getAttackTarget().getPosZ() - getPosZ()) * 1.029);
			}
		}
		else if (stompCooldown == 40) {
			if (!world.isRemote)
				world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.ENTITY_TYROSAUR_READY_STOMP.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
		}
		else if (stompCooldown == 0) {
			stompCooldown = 100;

			if (!world.isRemote)
				world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.ENTITY_TYROSAUR_STOMP.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);

			for (PlayerEntity pl : world.getEntitiesWithinAABB(PlayerEntity.class, getBoundingBox().grow(35), PlayerUtil::shouldPlayerBeAffected)) {
				if (pl.onGround && !world.isRemote) {
					if (DamageUtil.dealAoeDamage(null, this, pl, 10, false))
						pl.sendMessage(LocaleUtil.getLocaleMessage("message.mob.tyrosaur.stomp", TextFormatting.DARK_GREEN));
				}
			}
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getTrueSource());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("message.mob.tyrosaur.kill", killer.getDisplayName().getFormattedText()), world, getPosition(), 50);
		}
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.TYROSAUR_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.TYROSAUR_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}
}
