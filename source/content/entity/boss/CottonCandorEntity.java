package net.tslat.aoa3.content.entity.boss;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
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
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.content.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.CottonCandorShotEntity;
import net.tslat.aoa3.content.entity.projectile.staff.*;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;

public class CottonCandorEntity extends AoAFlyingRangedMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private static final DataParameter<Byte> STAGE = EntityDataManager.<Byte>defineId(CottonCandorEntity.class, DataSerializers.BYTE);
	private int stageCountdown = 100;

	public CottonCandorEntity(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
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

		if (source.getDirectEntity() instanceof ProjectileEntity) {
			ProjectileEntity projectile = (ProjectileEntity)source.getDirectEntity();

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
				case 5:
					if (projectile instanceof LunarFallEntity)
						return false;
					break;
				default:
					return true;
			}
		}

		return true;
	}

	@Override
	protected void actuallyHurt(DamageSource source, float amount) {
		float mod = 0.25f;

		if (source.getDirectEntity() instanceof ProjectileEntity) {
			ProjectileEntity projectile = (ProjectileEntity)source.getDirectEntity();

			switch (getStage()) {
				case 0:
					if (projectile instanceof PrimordialShotEntity)
						mod = 1.25f;
					break;
				case 1:
					if (projectile instanceof WaterShotEntity)
						mod = 1.25f;
					break;
				case 2:
					if (projectile instanceof FirestormFallEntity || projectile instanceof FireflyShotEntity || projectile instanceof BaronShotEntity)
						mod = 1.25f;
					break;
				case 3:
					if (projectile instanceof PoisonShotEntity || projectile instanceof NoxiousShotEntity)
						mod = 1.25f;
					break;
				case 4:
					if (projectile instanceof WitherShotEntity)
						mod = 1.25f;
					break;
				case 5:
					if (projectile instanceof LunarFallEntity)
						mod = 1.25f;
					break;
				default:
					break;
			}
		}

		super.actuallyHurt(source, amount * mod);
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
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null) {
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.COTTON_CANDOR.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);

				if (cause.getDirectEntity() instanceof PrimordialShotEntity && (killer.getMainHandItem().getItem() == AoAWeapons.WIND_STAFF.get() || killer.getOffhandItem().getItem() == AoAWeapons.WIND_STAFF.get()))
					AdvancementUtil.completeAdvancement((ServerPlayerEntity)killer, new ResourceLocation(AdventOfAscension.MOD_ID, "candyland/when_push_comes_to_shove"), "wind_staff_cotton_candor_kill");
			}
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.COTTON_CANDOR_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.COTTON_CANDOR_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
