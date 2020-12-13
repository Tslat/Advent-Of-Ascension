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
import net.minecraft.world.BossInfo;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
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
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getName().deepCopy().appendSibling(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenSky(false).setCreateFog(false);
	private static final DataParameter<Integer> GROWTH_PERCENT = EntityDataManager.<Integer>createKey(HiveKingEntity.class, DataSerializers.VARINT);

	private int growthPercent = 0;

	public HiveKingEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		if (!world.isRemote) {
			dataManager.set(GROWTH_PERCENT, 100);
			growthPercent = 100;
			recalculateSize();
		}
	}

	public HiveKingEntity(World world, int growthPercent) {
		super(AoAEntities.Mobs.HIVE_KING.get(), world);

		this.growthPercent = growthPercent;

		dataManager.set(GROWTH_PERCENT, growthPercent);
		setHealth(Math.max(1, getMaxHealth() / (100 / (float)growthPercent)));
		recalculateSize();
		setNoAI(true);
	}

	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		if (worldIn.isRemote())
			growthPercent = 100;

		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	protected void registerData() {
		super.registerData();

		dataManager.register(GROWTH_PERCENT, growthPercent);
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		super.notifyDataManagerChange(key);

		if (key == GROWTH_PERCENT)
			recalculateSize();
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return size.height * 0.85f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 2500;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 20;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
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
	protected ResourceLocation getLootTable() {
		return growthPercent >= 100 ? super.getLootTable() : null;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	public int getGrowthPercent() {
		return growthPercent;
	}

	@Override
	public float getRenderScale() {
		return growthPercent >= 100 ? super.getRenderScale() : growthPercent / 100f;
	}

	@Override
	public void tick() {
		super.tick();

		if (growthPercent < 100) {
			if (!world.isRemote) {
				incrementGrowth();
			}
			else {
				growthPercent = dataManager.get(GROWTH_PERCENT);
			}

			recalculateSize();

			if (growthPercent == 100)
				setNoAI(false);

			return;
		}

		if (!world.isRemote && rand.nextInt(500) == 0) {
			HiveWorkerEntity worker = new HiveWorkerEntity(this);

			world.addEntity(worker);
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (growthPercent >= 100)
			return super.attackEntityFrom(source, amount);

		if (!world.isRemote)
			remove();

		return true;
	}

	private void incrementGrowth() {
		growthPercent++;

		dataManager.set(GROWTH_PERCENT, growthPercent);
		setHealth(getMaxHealth() / (100 / (float)growthPercent));
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);

		if (dataManager.get(GROWTH_PERCENT) < 100)
			setHealth(getMaxHealth());
	}

	@Override
	public void onDeath(DamageSource cause) {
		if (growthPercent < 100)
			return;

		super.onDeath(cause);

		if (!world.isRemote) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getTrueSource());

			if (killer != null) {
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("message.mob.hiveKing.kill", killer.getDisplayName().getFormattedText()), world, getPosition(), 50);

				if (killer instanceof ServerPlayerEntity && cause.getImmediateSource() instanceof HiveSoldierEntity)
					AdvancementUtil.completeAdvancement((ServerPlayerEntity)killer, new ResourceLocation(AdventOfAscension.MOD_ID, "barathos/daddy_issues"), "hive_king_hive_staff_kill");
			}
		}
	}

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.ARTHROPOD;
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.HIVE_KING_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.HIVE_KING_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}
}
