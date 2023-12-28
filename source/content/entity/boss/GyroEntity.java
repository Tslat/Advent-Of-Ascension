/*
package net.tslat.aoa3.content.entity.boss;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;

import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.ai.mob.FlyingRangedAttackGoal;
import net.tslat.aoa3.content.entity.ai.mob.RandomFlyingGoal;
import net.tslat.aoa3.content.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.content.entity.misc.GyrocopterEntity;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.BulletShotEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;


public class GyroEntity extends AoAFlyingRangedMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	public GyroEntity(GyrocopterEntity copter) {
		this(AoAMobs.GYRO.get(), copter.level);

		moveTo(copter.getX(), copter.getY(), copter.getZ(), copter.getYRot(), copter.getXRot());
	}

	public GyroEntity(EntityType<? extends FlyingMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.40625f;
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new RandomFlyingGoal(this, true));
		goalSelector.addGoal(2, new RandomLookAroundGoal(this));
		goalSelector.addGoal(3, new FlyingRangedAttackGoal(this, 10, 20));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<Player>(this, Player.class, 10, true, true, pl -> pl instanceof Player && PlayerUtil.shouldPlayerBeAffected((Player)pl)));
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_GYRO_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GYRO_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GYRO_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ITEM_GUN_RIFLE_HEAVY_FIRE_LONG.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	public void tick() {
		super.tick();

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
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAMobs.GYRO.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);
		}
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new BulletShotEntity(this, BaseMobProjectile.Type.PHYSICAL);
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.GYRO_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.GYRO_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
*/
