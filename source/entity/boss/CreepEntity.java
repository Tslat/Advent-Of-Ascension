package net.tslat.aoa3.entity.boss;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectile.mob.CreepBombEntity;
import net.tslat.aoa3.entity.projectile.mob.CreepTubeEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;

public class CreepEntity extends AoARangedMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	public CreepEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.53125f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CREEPOID_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CREEPOID_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CREEPOID_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new CreepTubeEntity(this, BaseMobProjectile.Type.OTHER);
	}

	@Override
	public void tick() {
		super.tick();

		if (!isAlive())
			return;

		if (!level.isClientSide()) {
			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getPercent())
				bossInfo.setPercent(healthPercent);
		}


		if (isNoAi())
			return;

		if (random.nextInt(10) == 0) {
			CreepBombEntity creepBomb = new CreepBombEntity(this, BaseMobProjectile.Type.OTHER);

			creepBomb.shoot(1 / (random.nextInt(15) + 1) * (1 - random.nextInt(3)), 4.300000190734863, (double)(1 / (random.nextInt(15) + 1) * (1 - random.nextInt(3))), 1.6f, 12.0f);
			level.addFreshEntity(creepBomb);
		}
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (projectile instanceof CreepBombEntity) {
			if (target.hurt(DamageSource.indirectMobAttack(projectile, this), (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()) * 2.5f))
				doProjectileImpactEffect(projectile, target);
		}
		else {
			super.doProjectileEntityImpact(projectile, target);
		}
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, level, projectile, 1.2f);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
		WorldUtil.createExplosion(this, level, projectile, 1.2f);
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.CREEP.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.CREEP_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.CREEP_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
