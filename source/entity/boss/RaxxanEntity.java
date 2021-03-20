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
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	public RaxxanEntity(HarkosEntity harkos) {
		this(AoAEntities.Mobs.RAXXAN.get(), harkos.level);

		moveTo(harkos.getX(), harkos.getY(), harkos.getZ(), harkos.yRot, harkos.xRot);
	}

	public RaxxanEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		isSlipperyMovement = true;

		this.setSpeed(1.4f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 2.009375f;
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
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide) {
			if (random.nextInt(100) == 0) {
				PlayerEntity nearestTarget = level.getNearestPlayer(getX(), getY(), getZ(), 30, false);

				if (nearestTarget != null && !nearestTarget.isCreative()) {
					EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.CONFUSION, 100));
					level.playSound(null, blockPosition(), AoASounds.ENTITY_VOXXULON_AMBIENT.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
				}
			}
			else if (random.nextInt(100) == 0) {
				EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.INVISIBILITY, 25));
				EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.INVISIBILITY, 25));
			}
			else if (random.nextInt(200) == 0) {
				heal(100);
			}
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			OkazorEntity okazor = new OkazorEntity(this);

			level.addFreshEntity(okazor);
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
	protected void customServerAiStep() {
		super.customServerAiStep();

		bossInfo.setPercent(getHealth() / getMaxHealth());
	}

	@Override
	public void startSeenByPlayer(ServerPlayerEntity player) {
		super.startSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.PRIMORDIAL_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.PRIMORDIAL_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}
}
