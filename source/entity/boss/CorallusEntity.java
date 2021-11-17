package net.tslat.aoa3.entity.boss;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.projectile.mob.CorallusShotEntity;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class CorallusEntity extends AoAMeleeMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private static final DataParameter<Boolean> ENRAGED = EntityDataManager.<Boolean>defineId(CorallusEntity.class, DataSerializers.BOOLEAN);

	private int shotCooldown = 7;
	private int shootStageTimer = 0;
	private int jumpCooldown = 320;
	private int rageStateCooldown = 200;

	public CorallusEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 2.72f;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		this.entityData.define(ENRAGED, false);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CORALLUS_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CORALLUS_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CORALLUS_HURT.get();
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
	public boolean canBreatheUnderwater() {
		return true;
	}

	private void setEnraged(boolean enraged) {
		this.entityData.set(ENRAGED, enraged);
	}

	public boolean isEnraged() {
		return this.entityData.get(ENRAGED);
	}

	@Override
	public void tick() {
		super.tick();

		Vector3d motion = getDeltaMovement();
		double motionX = motion.x();
		double motionY = motion.y();
		double motionZ = motion.z();

		if (jumpCooldown == 0) {
			motionY = 1.6;
			fallDistance = -100;
			jumpCooldown = 320;
			shootStageTimer = 60;

			level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_CORALLUS_TAUNT.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
		}
		else {
			jumpCooldown--;
		}

		if (shootStageTimer > 0) {
			if (shotCooldown == 0 && !level.isClientSide) {
				List<PlayerEntity> targets = level.getEntitiesOfClass(PlayerEntity.class, getBoundingBox().inflate(40), PlayerUtil::shouldPlayerBeAffected);

				for (PlayerEntity target : targets) {
					CorallusShotEntity shot = new CorallusShotEntity(this, target, 12);

					shot.moveTo(getX(), getY() + getEyeHeight(), getZ(), random.nextFloat() * 360, 0);
					level.addFreshEntity(shot);
				}

				shotCooldown = 10 + random.nextInt(15);
			}
			else {
				shotCooldown--;
			}

			shootStageTimer--;
		}

		rageStateCooldown--;

		if (rageStateCooldown <= 0) {
			if (isEnraged()) {
				if (!level.isClientSide)
					setEnraged(false);

				rageStateCooldown = 200;
			}
			else {
				if (!level.isClientSide)
					setEnraged(true);

				rageStateCooldown = 80;
				EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.DAMAGE_BOOST, 80).level(4));
			}
		}

		if (isInWater()) {
			if (motionX > -1.2000000476837158 && motionX < 1.2000000476837158)
				motionX *= 1.2000000476837158;

			if (motionZ > -1.2000000476837158 && motionZ < 1.2000000476837158)
				motionZ *= 1.2000000476837158;
		}

		setDeltaMovement(motionX, motionY, motionZ);

		if (!level.isClientSide()) {
			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getPercent())
				bossInfo.setPercent(healthPercent);
		}
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity) {
			target.setDeltaMovement(getDeltaMovement().x(), -1.799, getDeltaMovement().z());

			if (!isInWater()) {
				EntityUtil.applyPotions(target,
						new PotionUtil.EffectBuilder(Effects.WEAKNESS, 100).level(15),
						new PotionUtil.EffectBuilder(Effects.MOVEMENT_SLOWDOWN, 50).level(4));
			}
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide && !isNoAi()) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.CORALLUS.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);
		}
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.CORALLUS_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.CORALLUS_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
