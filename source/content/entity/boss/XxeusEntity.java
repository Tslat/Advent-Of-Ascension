/*
package net.tslat.aoa3.content.entity.boss;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.BlockPos;

import net.minecraft.world.BossInfo;
import net.minecraft.world.level.Level;
import net.minecraft.world.server.ServerBossInfo;

import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;


public class XxeusEntity extends AoAMeleeMob<XxeusEntity> {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private int jumpCooldown = 60;

	public XxeusEntity(CraexxeusEntity craexxeus) {
		this(AoAMobs.XXEUS.get(), craexxeus.level);

		moveTo(craexxeus.getX(), craexxeus.getY() + 1, craexxeus.getZ(), random.nextFloat() * 360, 0);
	}

	public XxeusEntity(EntityType<? extends XxeusEntity> entityType, Level world) {
		super(entityType, world);

		isSlipperyMovement = true;

		this.setSpeed(2.1f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 2.55f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_XXEUS_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_XXEUS_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_XXEUS_HURT.get();
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

		if (getTarget() != null) {
			if (jumpCooldown > 0) {
				jumpCooldown--;
			}
			else {
				jumpCooldown = 60;
				LivingEntity target = getTarget();
				setDeltaMovement((target.getX() - getX()) * 0.165, target.getY() > getY() ? 0.85 : 0.449, (target.getZ() - getZ()) * 0.165);

				level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_XXEUS_DASH.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
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

		if (!level.isClientSide && !isNoAi()) {
			Player killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAMobs.XXEUS.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);
		}
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier, DamageSource damageSource) {
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

		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		bossInfo.removePlayer(player);
	}
}
*/
