package net.tslat.aoa3.entity.boss;

import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public class CrystocoreEntity extends AoAFlyingMeleeMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private byte damageType = 0;
	private int changeCooldown = 220;
	private static final DataParameter<Byte> TYPE = EntityDataManager.<Byte>defineId(CrystocoreEntity.class, DataSerializers.BYTE);

	public CrystocoreEntity(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 3.625f;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		this.entityData.define(TYPE, (byte)0);
	}

	public int getPhase() {
		return this.entityData.get(TYPE);
	}

	private void changeState() {
		damageType = (byte)random.nextInt(6);

		this.entityData.set(TYPE, damageType);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_HURT.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide) {
			if (changeCooldown > 0) {
				changeCooldown--;
			}
			else {
				changeCooldown = 220;
				changeState();

				EntityUtil.applyPotions(level.getEntitiesOfClass(PlayerEntity.class, getBoundingBox().inflate(10), PlayerUtil::shouldPlayerBeAffected), RandomUtil.getRandomSelection(
						new PotionUtil.EffectBuilder(Effects.POISON, 180).level(2),
						new PotionUtil.EffectBuilder(Effects.BLINDNESS, 180),
						new PotionUtil.EffectBuilder(Effects.WEAKNESS, 180).level(2),
						new PotionUtil.EffectBuilder(Effects.CONFUSION, 180),
						new PotionUtil.EffectBuilder(Effects.WITHER, 180).level(2),
						new PotionUtil.EffectBuilder(Effects.MOVEMENT_SLOWDOWN, 180).level(2)
				));
			}
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide && !isNoAi()) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("message.mob.crystocore.kill", killer.getDisplayName()), level, blockPosition(), 50);
		}
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity) {
			switch (damageType) {
				case 0:
					EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.POISON, 180).level(2));
					break;
				case 1:
					EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.BLINDNESS, 180));
					break;
				case 2:
					EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.WEAKNESS, 180).level(2));
					break;
				case 3:
					EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.CONFUSION, 180));
					break;
				case 4:
					EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.WITHER, 180).level(2));
					break;
				case 5:
				default:
					EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.MOVEMENT_SLOWDOWN, 180).level(2));
					break;
			}
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
	protected void customServerAiStep() {
		super.customServerAiStep();

		bossInfo.setPercent(getHealth() / getMaxHealth());
	}

	@Override
	public void startSeenByPlayer(ServerPlayerEntity player) {
		super.startSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.CRYSTOCORE_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.CRYSTOCORE_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

}
