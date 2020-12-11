package net.tslat.aoa3.entity.boss;

import net.minecraft.entity.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectile.mob.CraexxeusNukeEntity;
import net.tslat.aoa3.entity.projectile.mob.CraexxeusShotEntity;
import net.tslat.aoa3.util.DamageUtil;

import javax.annotation.Nullable;

public class CraexxeusEntity extends AoAFlyingRangedMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getName().deepCopy().appendSibling(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenSky(false).setCreateFog(false);
	private int chargeCooldown = 300;

	public CraexxeusEntity(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 3.9f;
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
		return 10;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CRAEXXEUS_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CRAEXXEUS_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CRAEXXEUS_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_CRAEXXEUS_SHOOT.get();
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new CraexxeusShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}

	@Override
	public void tick() {
		super.tick();

		setMotion(getMotion().mul(1, 0.15f, 1));

		if (!world.isRemote) {
			if (chargeCooldown > 0) {
				chargeCooldown--;

				if (chargeCooldown == 40)
					world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.ENTITY_CRAEXXEUS_CHARGE.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
			}
			else {
				chargeCooldown = 300;

				if (getAttackTarget() != null) {
					LivingEntity target = getAttackTarget();
					BaseMobProjectile projectile = new CraexxeusNukeEntity(this, BaseMobProjectile.Type.MAGIC);

					double distanceFactorX = target.getPosX() - projectile.getPosX();
					double distanceFactorY = target.getBoundingBox().minY + (target.getHeight() / 3) - projectile.getPosY();
					double distanceFactorZ = target.getPosZ() - projectile.getPosZ();
					double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

					world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.ENTITY_CRAEXXEUS_NUKE.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
					projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getId()));
					world.addEntity(projectile);
				}
			}
		}
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (projectile instanceof CraexxeusNukeEntity) {
			DamageUtil.dealBlasterDamage(this, target, projectile, (float)getBaseProjectileDamage() * 3, false);
		}
		else {
			super.doProjectileEntityImpact(projectile, target);
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			XxeusEntity xxeus = new XxeusEntity(this);

			world.addEntity(xxeus);
			remove();
		}
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.CRAEXXEUS_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.CRAEXXEUS_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
