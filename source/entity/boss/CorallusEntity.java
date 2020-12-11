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
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.projectile.mob.CorallusShotEntity;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class CorallusEntity extends AoAMeleeMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getName().deepCopy().appendSibling(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenSky(false).setCreateFog(false);
	private static final DataParameter<Boolean> ENRAGED = EntityDataManager.<Boolean>createKey(CorallusEntity.class, DataSerializers.BOOLEAN);

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
	protected void registerData() {
		super.registerData();

		this.dataManager.register(ENRAGED, false);
	}

	@Override
	protected void registerAttributes() {
		super.registerAttributes();

		getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(52);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1800;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 25;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.3286;
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
	public boolean isNonBoss() {
		return false;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	private void setEnraged(boolean enraged) {
		this.dataManager.set(ENRAGED, enraged);
	}

	public boolean isEnraged() {
		return this.dataManager.get(ENRAGED);
	}

	@Override
	public void tick() {
		super.tick();

		Vec3d motion = getMotion();
		double motionX = motion.getX();
		double motionY = motion.getY();
		double motionZ = motion.getZ();

		if (jumpCooldown == 0) {
			motionY = 1.6;
			fallDistance = -100;
			jumpCooldown = 320;
			shootStageTimer = 60;

			world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.ENTITY_CORALLUS_TAUNT.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
		}
		else {
			jumpCooldown--;
		}

		if (shootStageTimer > 0) {
			if (shotCooldown == 0 && !world.isRemote) {
				List<PlayerEntity> targets = world.getEntitiesWithinAABB(PlayerEntity.class, getBoundingBox().grow(40), PlayerUtil::shouldPlayerBeAffected);

				for (PlayerEntity target : targets) {
					CorallusShotEntity shot = new CorallusShotEntity(this, target, 12);

					shot.setLocationAndAngles(getPosX(), getPosY() + getEyeHeight(), getPosZ(), rand.nextFloat() * 360, 0);
					world.addEntity(shot);
				}

				shotCooldown = 10 + rand.nextInt(15);
			}
			else {
				shotCooldown--;
			}

			shootStageTimer--;
		}

		rageStateCooldown--;

		if (rageStateCooldown <= 0) {
			if (isEnraged()) {
				if (!world.isRemote)
					setEnraged(false);

				rageStateCooldown = 200;
			}
			else {
				if (!world.isRemote)
					setEnraged(true);

				rageStateCooldown = 80;
				EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.STRENGTH, 80).level(4));
			}
		}

		if (isInWater()) {
			if (motionX > -1.2000000476837158 && motionX < 1.2000000476837158)
				motionX *= 1.2000000476837158;

			if (motionZ > -1.2000000476837158 && motionZ < 1.2000000476837158)
				motionZ *= 1.2000000476837158;
		}

		setMotion(motionX, motionY, motionZ);
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity) {
			target.setMotion(getMotion().getX(), -1.799, getMotion().getZ());

			if (!isInWater()) {
				EntityUtil.applyPotions(target,
						new PotionUtil.EffectBuilder(Effects.WEAKNESS, 100).level(15),
						new PotionUtil.EffectBuilder(Effects.SLOWNESS, 50).level(4));
			}
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote && !isAIDisabled()) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getTrueSource());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("message.mob.corallus.kill", killer.getDisplayName().getFormattedText()), world, getPosition(), 50);
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.CORALLUS_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.CORALLUS_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
