package net.tslat.aoa3.entity.boss;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.misc.BaronBombEntity;
import net.tslat.aoa3.entity.projectile.mob.BaronessShotEntity;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;

public class BaronessEntity extends AoARangedMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager.<Boolean>defineId(BaronessEntity.class, DataSerializers.BOOLEAN);
	private int invulnerableTicks = 0;
	private int bombCoolown = 150;

	public BaronessEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.71875f;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		this.entityData.define(INVULNERABLE, false);
	}

	@Override
	public boolean isInvulnerable() {
		return this.entityData.get(INVULNERABLE);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_ARIEL_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ARIEL_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ARIEL_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_BARONESS_SHOOT.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new BaronessShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		invulnerableTicks = 40;
		changeStage(true);
	}

	private void changeStage(boolean invulnerable) {
		this.entityData.set(INVULNERABLE, invulnerable);
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (!isAlive())
			return;

		if (!level.isClientSide()) {
			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getPercent())
				bossInfo.setPercent(healthPercent);
		}

		if (isNoAi())
			return;

		if (invulnerableTicks > 0) {
			invulnerableTicks--;

			if (invulnerableTicks == 0)
				changeStage(false);
		}

		if (bombCoolown > 0) {
			bombCoolown--;

			if (bombCoolown == 0) {
				bombCoolown = 50 + random.nextInt(50);
				LivingEntity target = getTarget();

				if (target != null)
					push(Math.signum((target.getX() - getX()) * 2.329), Math.signum((target.getY() + 1 - getY()) * 0.929), Math.signum(target.getZ() - getZ()) * 2.329);

				if (!level.isClientSide) {
					BaronBombEntity bomb = new BaronBombEntity(this);

					level.addFreshEntity(bomb);
					level.playSound(null, getX(), getY(), getZ(), AoASounds.BARON_BOMB_SPAWN.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
				}
			}
		}
	}


	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier) {
		return false;
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.BARONESS.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);
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
	public void startSeenByPlayer(ServerPlayerEntity player) {
		super.startSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.BARONESS_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.BARONESS_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
