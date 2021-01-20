package net.tslat.aoa3.entity.boss;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;

public class RaxxanEntity extends AoAMeleeMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getName().deepCopy().appendSibling(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenSky(false).setCreateFog(false);

	public RaxxanEntity(HarkosEntity raxxan) {
		this(AoAEntities.Mobs.RAXXAN.get(), raxxan.world);

		setLocationAndAngles(raxxan.getPosX(), raxxan.getPosY(), raxxan.getPosZ(), raxxan.rotationYaw, raxxan.rotationPitch);
	}

	public RaxxanEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		isSlipperyMovement = true;

		this.setAIMoveSpeed(1.4f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 2.009375f;
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
		return 15;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_PRIMORDIAL_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_PRIMORDIAL_DEATH.get();
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (!world.isRemote) {
			if (rand.nextInt(100) == 0) {
				PlayerEntity nearestTarget = world.getClosestPlayer(getPosX(), getPosY(), getPosZ(), 30, false);

				if (nearestTarget != null && !nearestTarget.isCreative()) {
					EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.NAUSEA, 100));
					world.playSound(null, getPosition(), AoASounds.ENTITY_VOXXULON_AMBIENT.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
				}
			}
			else if (rand.nextInt(100) == 0) {
				EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.INVISIBILITY, 25));
				EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.INVISIBILITY, 25));
			}
			else if (rand.nextInt(200) == 0) {
				heal(100);
			}
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			OkazorEntity okazor = new OkazorEntity(this);

			world.addEntity(okazor);
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.PRIMORDIAL_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.PRIMORDIAL_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}
}
