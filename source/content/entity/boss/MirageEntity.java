/*
package net.tslat.aoa3.content.entity.boss;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.core.BlockPos;

import net.minecraft.world.BossInfo;
import net.minecraft.world.level.Level;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.SpiritualShotEntity;
import net.tslat.aoa3.util.WorldUtil;


public class MirageEntity extends AoARangedMob<MirageEntity> {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	public MirageEntity(EntityType<? extends MirageEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 1.75f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_IMMORTAL_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_IMMORTAL_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ANVIL_LAND;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_MIRAGE_SHOOT.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new SpiritualShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}

	@Override
	public void tick() {
		super.tick();
		
		if (!level.isClientSide) {
			if (random.nextInt(80) == 0) {
				level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_MIRAGE_TELEPORT.get(), SoundSource.HOSTILE, 1.0f, 1.0f);

				if (WorldUtil.isWorld(level, AoADimensions.NOWHERE)) {
					switch (random.nextInt(4)) {
						case 0:
							moveTo(167, 24, 8, random.nextFloat() * 360, 0);
							break;
						case 1:
							moveTo(168, 24, -2, random.nextFloat() * 360, 0);
							break;
						case 2:
							moveTo(177, 24, 8, random.nextFloat() * 360, 0);
							break;
						case 3:
							moveTo(177, 24, -2, random.nextFloat() * 360, 0);
							break;
					}
				}
				else {
					int x = (int)(random.nextBoolean() ? getX() + 5 : getX() - 5);
					int z = (int)(random.nextBoolean() ? getZ() + 5 : getZ() - 5);

					moveTo(x, level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, new BlockPos(x, getY(), z)).getY(), z, random.nextFloat() * 360, 0);
				}
			}

			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getPercent())
				bossInfo.setPercent(healthPercent);
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
	public void setCustomName(@Nullable MutableComponent name) {
		super.setCustomName(name);

		bossInfo.setName(getType().getDescription().copy().append(getDisplayName()));
	}

	@Override
	public void startSeenByPlayer(ServerPlayer player) {
		super.startSeenByPlayer(player);

		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayer player) {
		super.stopSeenByPlayer(player);

		bossInfo.removePlayer(player);
	}
}
*/
