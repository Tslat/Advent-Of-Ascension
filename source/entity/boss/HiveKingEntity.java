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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minion.HiveSoldierEntity;
import net.tslat.aoa3.entity.mob.misc.HiveWorkerEntity;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public class HiveKingEntity extends AoAMeleeMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private static final DataParameter<Integer> GROWTH_PERCENT = EntityDataManager.<Integer>defineId(HiveKingEntity.class, DataSerializers.INT);

	private int growthPercent = 0;

	public HiveKingEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		if (!world.isClientSide) {
			entityData.set(GROWTH_PERCENT, 100);
			growthPercent = 100;
			refreshDimensions();
		}
	}

	public HiveKingEntity(World world, int growthPercent) {
		super(AoAEntities.Mobs.HIVE_KING.get(), world);

		this.growthPercent = growthPercent;

		entityData.set(GROWTH_PERCENT, growthPercent);
		setHealth(Math.max(1, getMaxHealth() / (100 / (float)growthPercent)));
		refreshDimensions();
		setNoAi(true);
	}

	@Nullable
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
		if (world.isClientSide())
			growthPercent = 100;

		return super.finalizeSpawn(world, difficulty, reason, spawnData, dataTag);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		entityData.define(GROWTH_PERCENT, growthPercent);
	}

	@Override
	public void onSyncedDataUpdated(DataParameter<?> key) {
		super.onSyncedDataUpdated(key);

		if (key == GROWTH_PERCENT)
			refreshDimensions();
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
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
		return false;
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

		if (!level.isClientSide && random.nextInt(500) == 0) {
			HiveWorkerEntity worker = new HiveWorkerEntity(this);

			level.addFreshEntity(worker);
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (growthPercent >= 100)
			return super.hurt(source, amount);

		if (!level.isClientSide)
			remove();

		return true;
	}

	private void incrementGrowth() {
		growthPercent++;

		entityData.set(GROWTH_PERCENT, growthPercent);
		setHealth(getMaxHealth() / (100 / (float)growthPercent));
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
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
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null) {
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("message.mob.hiveKing.kill", killer.getDisplayName()), level, blockPosition(), 50);

				if (killer instanceof ServerPlayerEntity && cause.getDirectEntity() instanceof HiveSoldierEntity)
					AdvancementUtil.completeAdvancement((ServerPlayerEntity)killer, new ResourceLocation(AdventOfAscension.MOD_ID, "barathos/daddy_issues"), "hive_king_hive_staff_kill");
			}
		}
	}

	@Override
	public CreatureAttribute getMobType() {
		return CreatureAttribute.ARTHROPOD;
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.HIVE_KING_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.HIVE_KING_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}
}
