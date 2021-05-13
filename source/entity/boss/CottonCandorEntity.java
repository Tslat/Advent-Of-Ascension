package net.tslat.aoa3.entity.boss;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectile.mob.CottonCandorShotEntity;
import net.tslat.aoa3.entity.projectile.staff.*;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public class CottonCandorEntity extends AoAFlyingRangedMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getName().deepCopy().appendSibling(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenSky(false).setCreateFog(false);
	private static final DataParameter<Byte> STAGE = EntityDataManager.<Byte>createKey(CottonCandorEntity.class, DataSerializers.BYTE);
	private int stageCountdown = 100;

	public CottonCandorEntity(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.71875f;
	}

	@Override
	protected void registerData() {
		super.registerData();

		dataManager.register(STAGE, (byte)0);
	}

	private void changeStage(int stage) {
		dataManager.set(STAGE, (byte)(stage & 0x7));
	}

	public int getStage() {
		return (int)dataManager.get(STAGE);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 3000;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 35;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
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
	public boolean isNonBoss() {
		return false;
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if (source == DamageSource.OUT_OF_WORLD)
			return false;

		if (source.getImmediateSource() instanceof IProjectile) {
			IProjectile projectile = (IProjectile)source.getImmediateSource();

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

		if (!world.isRemote) {
			stageCountdown--;

			if (stageCountdown == 0) {
				changeStage(rand.nextInt(5));
				stageCountdown = 100;
			}
		}
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, world, projectile, 5f);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, world, projectile, 5f);
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getTrueSource());

			if (killer != null) {
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("message.mob.cottonCandor.kill", killer.getDisplayName().getFormattedText()), world, getPosition(), 50);

				if (cause.getImmediateSource() instanceof PrimordialShotEntity && (killer.getHeldItemMainhand().getItem() == AoAWeapons.WIND_STAFF.get() || killer.getHeldItemOffhand().getItem() == AoAWeapons.WIND_STAFF.get()))
					AdvancementUtil.completeAdvancement((ServerPlayerEntity)killer, new ResourceLocation(AdventOfAscension.MOD_ID, "candyland/when_push_comes_to_shove"), "wind_staff_cotton_candor_kill");
			}
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.COTTON_CANDOR_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.COTTON_CANDOR_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
