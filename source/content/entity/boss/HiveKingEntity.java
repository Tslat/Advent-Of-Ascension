/*
package net.tslat.aoa3.content.entity.boss;

import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.datasync.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import net.minecraft.world.BossInfo;
import net.minecraft.world.level.Level;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;

import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.content.entity.mob.misc.HiveWorkerEntity;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;

public class HiveKingEntity extends AoAMeleeMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	public static final EntityDataAccessor<Integer> GROWTH_PERCENT = SynchedEntityData.<Integer>defineId(HiveKingEntity.class, EntityDataSerializers.INT);

	public int growthPercent = 100;

	public HiveKingEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		if (!world.isClientSide) {
			entityData.set(GROWTH_PERCENT, 100);
			growthPercent = 100;
			refreshDimensions();
		}
	}

	public HiveKingEntity(Level world, int growthPercent) {
		super(AoAMobs.HIVE_KING.get(), world);

		this.growthPercent = growthPercent;

		entityData.set(GROWTH_PERCENT, this.growthPercent);
		setHealth(Math.max(1, getMaxHealth() / (100 / (float)this.growthPercent)));
		refreshDimensions();
		setNoAi(true);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		entityData.define(GROWTH_PERCENT, growthPercent);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		super.onSyncedDataUpdated(key);

		if (key == GROWTH_PERCENT)
			refreshDimensions();
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return size.height * 0.85f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_HIVE_KING_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_HIVE_KING_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_HIVE_KING_AMBIENT.get();
	}

	@Nullable
	@Override
	protected ResourceLocation getDefaultLootTable() {
		return growthPercent >= 100 ? super.getDefaultLootTable() : null;
	}

	@Override
	public boolean canChangeDimensions() {
		return growthPercent >= 100;
	}

	public int getGrowthPercent() {
		return growthPercent;
	}

	@Override
	public float getScale() {
		return growthPercent >= 100 ? super.getScale() : growthPercent / 100f;
	}

	@Override
	public void tick() {
		super.tick();

		if (growthPercent < 100) {
			if (!level.isClientSide) {
				incrementGrowth();
			}
			else {
				growthPercent = entityData.get(GROWTH_PERCENT);
			}

			refreshDimensions();

			if (growthPercent == 100)
				setNoAi(false);

			return;
		}

		if (!level.isClientSide()) {
			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getPercent())
				bossInfo.setPercent(healthPercent);

			if (random.nextInt(500) == 0) {
				HiveWorkerEntity worker = new HiveWorkerEntity(this);

				level.addFreshEntity(worker);
			}
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (growthPercent >= 100)
			return super.hurt(source, amount);

		return false;
	}

	private void incrementGrowth() {
		growthPercent++;

		entityData.set(GROWTH_PERCENT, growthPercent);
		setHealth(getMaxHealth() / (100 / (float)growthPercent));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		if (entityData.get(GROWTH_PERCENT) < 100)
			setHealth(getMaxHealth());
	}

	@Override
	public void die(DamageSource cause) {
		if (growthPercent < 100)
			return;

		super.die(cause);

		if (!level.isClientSide) {
			Player killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null) {
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAMobs.HIVE_KING.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);

				if (killer instanceof ServerPlayer) // TODO
					AdvancementUtil.completeAdvancement((ServerPlayer)killer, new ResourceLocation(AdventOfAscension.MOD_ID, "barathos/daddy_issues"), "hive_king_hive_staff_kill");
			}
		}
	}

	@Override
	public MobType getMobType() {
		return MobType.ARTHROPOD;
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.HIVE_KING_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.HIVE_KING_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}
}
*/
