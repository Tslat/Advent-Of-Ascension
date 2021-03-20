package net.tslat.aoa3.entity.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class FlashEntity extends AoAMeleeMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	public FlashEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		isSlipperyMovement = true;

		this.setSpeed(3.2f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
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

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (!level.isClientSide && DamageUtil.isMeleeDamage(source)) {
			if (WorldUtil.isWorld(level, AoADimensions.IMMORTALLIS.key)) {
				switch (random.nextInt(3)) {
					case 0:
						teleportTo(235, 22, 10);
						break;
					case 1:
						teleportTo(235, 22, -3);
						break;
					case 2:
						teleportTo(228, 22, 3);
						break;
				}
			}
			else {
				int x = (int)getX() + (random.nextInt(14) - 7);
				int z = (int)getZ() + (random.nextInt(14) - 7);
				setPos(x, level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, new BlockPos(x, getY(), z)).getY(), z);
			}
		}

		return super.hurt(source, amount);
	}

	@Override
	protected void onAttack(Entity target) {
		if (!level.isClientSide) {
			if (WorldUtil.isWorld(level, AoADimensions.IMMORTALLIS.key)) {
				switch (random.nextInt(3)) {
					case 0:
						teleportTo(235, 22, 10);
						break;
					case 1:
						teleportTo(235, 22, -3);
						break;
					case 2:
						teleportTo(228, 22, 3);
						break;
				}
			}
			else {
				int x = (int)getX() + (random.nextInt(14) - 7);
				int z = (int)getZ() + (random.nextInt(14) - 7);
				setPos(x, level.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, new BlockPos(x, getY(), z)).getY(), z);
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
	protected void customServerAiStep() {
		super.customServerAiStep();

		bossInfo.setPercent(getHealth() / getMaxHealth());
	}

	@Override
	public void startSeenByPlayer(ServerPlayerEntity player) {
		super.startSeenByPlayer(player);

		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		bossInfo.removePlayer(player);
	}

}
