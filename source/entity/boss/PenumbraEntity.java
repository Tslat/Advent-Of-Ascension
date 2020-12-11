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
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.mob.abyss.ApparitionEntity;
import net.tslat.aoa3.entity.mob.abyss.OcculentEntity;
import net.tslat.aoa3.entity.mob.dustopia.BasiliskEntity;
import net.tslat.aoa3.entity.mob.dustopia.DevourerEntity;
import net.tslat.aoa3.entity.mob.dustopia.StalkerEntity;
import net.tslat.aoa3.entity.mob.greckon.ShifterEntity;
import net.tslat.aoa3.entity.mob.nether.HellspotEntity;
import net.tslat.aoa3.entity.mob.voxponds.AlarmoEntity;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectile.mob.PenumbraShotEntity;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;

public class PenumbraEntity extends AoARangedMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getName().deepCopy().appendSibling(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenSky(false).setCreateFog(false);

	public PenumbraEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 3.0625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 3000;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 18;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_PENUMBRA_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_PENUMBRA_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_PENUMBRA_HURT.get();
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Override
	public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
		EntityType type = target.getType();

		if (type == AoAEntities.Mobs.OCCULENT.get() || type == AoAEntities.Mobs.ALARMO.get() || type == AoAEntities.Mobs.SHIFTER.get() || type == AoAEntities.Mobs.DEVOURER.get() ||
				type == AoAEntities.Mobs.BASILISK.get() || type == AoAEntities.Mobs.APPARITION.get() || type == AoAEntities.Mobs.STALKER.get() || type == AoAEntities.Mobs.HELLSPOT.get())
			return;

		super.doProjectileEntityImpact(projectile, target);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		Entity entity = null;

		entity = RandomUtil.getRandomSelection(
			new OcculentEntity(AoAEntities.Mobs.OCCULENT.get(), world),
			new AlarmoEntity(AoAEntities.Mobs.ALARMO.get(), world),
			new ShifterEntity(AoAEntities.Mobs.SHIFTER.get(), world),
			new DevourerEntity(AoAEntities.Mobs.DEVOURER.get(), world),
			new BasiliskEntity(AoAEntities.Mobs.BASILISK.get(), world),
			new ApparitionEntity(AoAEntities.Mobs.APPARITION.get(), world),
			new StalkerEntity(AoAEntities.Mobs.STALKER.get(), world),
			new HellspotEntity(AoAEntities.Mobs.HELLSPOT.get(), world)
		);

		entity.setPosition(projectile.getPosX(), projectile.getPosY(), projectile.getPosZ());
		world.addEntity(entity);
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new PenumbraShotEntity(this, BaseMobProjectile.Type.MAGIC);
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.PENUMBRA_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.PENUMBRA_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
