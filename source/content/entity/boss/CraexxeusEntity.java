package net.tslat.aoa3.content.entity.boss;

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
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.CraexxeusNukeEntity;
import net.tslat.aoa3.content.entity.projectile.mob.CraexxeusShotEntity;
import net.tslat.aoa3.util.DamageUtil;

import javax.annotation.Nullable;

public class CraexxeusEntity extends AoAFlyingRangedMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private int chargeCooldown = 300;

	public CraexxeusEntity(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 3.9f;
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
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new CraexxeusShotEntity(this, BaseMobProjectile.Type.MAGIC);
	}

	@Override
	public void tick() {
		super.tick();

		setDeltaMovement(getDeltaMovement().multiply(1, 0.15f, 1));

		if (!level.isClientSide) {
			if (chargeCooldown > 0) {
				chargeCooldown--;

				if (chargeCooldown == 40)
					level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_CRAEXXEUS_CHARGE.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
			}
			else {
				chargeCooldown = 300;

				if (getTarget() != null) {
					LivingEntity target = getTarget();
					BaseMobProjectile projectile = new CraexxeusNukeEntity(this, BaseMobProjectile.Type.MAGIC);

					double distanceFactorX = target.getX() - projectile.getX();
					double distanceFactorY = target.getBoundingBox().minY + (target.getBbHeight() / 3) - projectile.getY();
					double distanceFactorZ = target.getZ() - projectile.getZ();
					double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

					level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_CRAEXXEUS_NUKE.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
					projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - this.level.getDifficulty().getId()));
					level.addFreshEntity(projectile);
				}
			}

			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getPercent())
				bossInfo.setPercent(healthPercent);
		}
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		if (projectile instanceof CraexxeusNukeEntity) {
			DamageUtil.dealBlasterDamage(this, target, projectile, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()) * 3, false);
		}
		else {
			super.doProjectileEntityImpact(projectile, target);
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			XxeusEntity xxeus = new XxeusEntity(this);

			level.addFreshEntity(xxeus);
			remove();
		}
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.CRAEXXEUS_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.CRAEXXEUS_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
