/*
package net.tslat.aoa3.content.entity.boss;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.RedGuardianShotEntity;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.EntityUtil;

import javax.annotation.Nullable;

public class RedGuardianEntity extends AoARangedMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	private BlueGuardianEntity blueGuardian;
	private GreenGuardianEntity greenGuardian;
	private YellowGuardianEntity yellowGuardian;

	public RedGuardianEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.875f;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GUARDIAN_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GUARDIAN_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_GUARDIAN_SHOOT.get();
	}

	@Nullable
	@Override
	protected ResourceLocation getDefaultLootTable() {
		if (checkGuardian(blueGuardian) && checkGuardian(greenGuardian) && checkGuardian(yellowGuardian))
			return super.getDefaultLootTable();

		return null;
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	public void setGreenGuardian(GreenGuardianEntity greenGuardian) {
		this.greenGuardian = greenGuardian;
	}

	public void setBlueGuardian(BlueGuardianEntity blueGuardian) {
		this.blueGuardian = blueGuardian;
	}

	public void setYellowGuardian(YellowGuardianEntity yellowGuardian) {
		this.yellowGuardian = yellowGuardian;
	}

	private boolean checkGuardian(LivingEntity guardian) {
		return guardian == null || !guardian.isAlive() || guardian.getHealth() == 0;
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

		if (!level.isClientSide) {
			remove();

			if (checkGuardian(yellowGuardian) && checkGuardian(greenGuardian) && checkGuardian(blueGuardian)) {
				for (ServerPlayer pl : level.getEntitiesOfClass(ServerPlayer.class, getBoundingBox().inflate(20))) {
					AdvancementUtil.completeAdvancement(pl, new ResourceLocation(AdventOfAscension.MOD_ID, "haven/guard_that"), "kill_four_guardians");
				}
			}
		}
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (target instanceof AoARangedMob && !target.canChangeDimensions())
			return;

		super.doProjectileEntityImpact(projectile, target);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 40).level(3));
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new RedGuardianShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}

	@Override
	public void setTarget(@Nullable LivingEntity target) {
		if (target instanceof AoARangedMob && !target.canChangeDimensions())
			return;

		super.setTarget(target);
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.RED_GUARDIAN_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.RED_GUARDIAN_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}
}
*/
