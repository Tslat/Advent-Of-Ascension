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

import javax.annotation.Nullable;

public class FlashEntity extends AoAMeleeMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getName().deepCopy().appendSibling(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenSky(false).setCreateFog(false);

	public FlashEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		isSlipperyMovement = true;

		this.setAIMoveSpeed(3.2f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.75f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1000;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 9;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
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
		return SoundEvents.BLOCK_ANVIL_LAND;
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (!world.isRemote && DamageUtil.isMeleeDamage(source)) {
			if (world.getDimension().getType() == AoADimensions.IMMORTALLIS.type()) {
				switch (rand.nextInt(3)) {
					case 0:
						setPositionAndUpdate(235, 22, 10);
						break;
					case 1:
						setPositionAndUpdate(235, 22, -3);
						break;
					case 2:
						setPositionAndUpdate(228, 22, 3);
						break;
				}
			}
			else {
				int x = (int)getPosX() + (rand.nextInt(14) - 7);
				int z = (int)getPosZ() + (rand.nextInt(14) - 7);
				setPosition(x, world.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, new BlockPos(x, getPosY(), z)).getY(), z);
			}
		}

		return super.attackEntityFrom(source, amount);
	}

	@Override
	protected void onAttack(Entity target) {
		if (!world.isRemote) {
			if (world.getDimension().getType() == AoADimensions.IMMORTALLIS.type()) {
				switch (rand.nextInt(3)) {
					case 0:
						setPositionAndUpdate(235, 22, 10);
						break;
					case 1:
						setPositionAndUpdate(235, 22, -3);
						break;
					case 2:
						setPositionAndUpdate(228, 22, 3);
						break;
				}
			}
			else {
				int x = (int)getPosX() + (rand.nextInt(14) - 7);
				int z = (int)getPosZ() + (rand.nextInt(14) - 7);
				setPosition(x, world.getHeight(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, new BlockPos(x, getPosY(), z)).getY(), z);
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

		bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);

		bossInfo.removePlayer(player);
	}

}
