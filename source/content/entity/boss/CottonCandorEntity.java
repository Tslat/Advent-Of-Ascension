/*
package net.tslat.aoa3.content.entity.boss;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;

import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAWeapons;
import net.tslat.aoa3.content.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.CottonCandorShotEntity;
import net.tslat.aoa3.content.entity.projectile.staff.*;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class CottonCandorEntity extends AoAFlyingRangedMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private static final EntityDataAccessor<Byte> STAGE = SynchedEntityData.<Byte>defineId(CottonCandorEntity.class, EntityDataSerializers.BYTE);
	private int stageCountdown = 100;

	public CottonCandorEntity(EntityType<? extends FlyingMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.71875f;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		entityData.define(STAGE, (byte)0);
	}

	private void changeStage(int stage) {
		entityData.set(STAGE, (byte)(stage & 0x7));
	}

	public int getStage() {
		return (int)entityData.get(STAGE);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_COTTON_CANDOR_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_COTTON_CANDOR_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_COTTON_CANDOR_HURT.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if (source == DamageSource.OUT_OF_WORLD)
			return false;

		if (source.getDirectEntity() instanceof Projectile) {
			Projectile projectile = (Projectile)source.getDirectEntity();

			switch (getStage()) {
				case 0:
					if (projectile instanceof PrimordialShotEntity)
						return false;
					break;
				case 1:
					if (projectile instanceof WaterShotEntity)
						return false;
					break;
				case 2:
					if (projectile instanceof FirestormFallEntity || projectile instanceof FireflyShotEntity || projectile instanceof BaronShotEntity)
						return false;
					break;
				case 3:
					if (projectile instanceof PoisonShotEntity || projectile instanceof NoxiousShotEntity)
						return false;
					break;
				case 4:
					if (projectile instanceof WitherShotEntity)
						return false;
					break;
				default:
					return true;
			}
		}

		return true;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_COTTON_CANDOR_SHOOT.get();
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new CottonCandorShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide) {
			stageCountdown--;

			if (stageCountdown == 0) {
				changeStage(random.nextInt(5));
				stageCountdown = 100;
			}

			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getPercent())
				bossInfo.setPercent(healthPercent);
		}
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, level, projectile, 5f);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, level, projectile, 5f);
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			Player killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null) {
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAMobs.COTTON_CANDOR.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);

				if (cause.getDirectEntity() instanceof PrimordialShotEntity && (killer.getMainHandItem().getItem() == AoAWeapons.WIND_STAFF.get() || killer.getOffhandItem().getItem() == AoAWeapons.WIND_STAFF.get()))
					AdvancementUtil.completeAdvancement((ServerPlayer)killer, new ResourceLocation(AdventOfAscension.MOD_ID, "candyland/when_push_comes_to_shove"), "wind_staff_cotton_candor_kill");
			}
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
	public void setCustomName(@Nullable TextComponent name) {
		super.setCustomName(name);

		bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.COTTON_CANDOR_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.COTTON_CANDOR_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
*/
