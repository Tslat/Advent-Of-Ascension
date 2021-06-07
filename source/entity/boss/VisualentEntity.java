package net.tslat.aoa3.entity.boss;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.player.PlayerEntity;
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
import net.tslat.aoa3.entity.ai.mob.FlyingLookRandomlyGoal;
import net.tslat.aoa3.entity.ai.mob.FlyingMeleeAttackGoal;
import net.tslat.aoa3.entity.ai.mob.RandomFlyingGoal;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.entity.minion.AoAMinion;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public class VisualentEntity extends AoAFlyingMeleeMob {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	private boolean isEnraged = false;

	public VisualentEntity(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected void registerGoals() {
		goalSelector.addGoal(1, new RandomFlyingGoal(this, true));
		goalSelector.addGoal(2, new FlyingLookRandomlyGoal(this));
		goalSelector.addGoal(3, new FlyingMeleeAttackGoal(this, 0.6f, false));
		targetSelector.addGoal(1, new NearestAttackableTargetGoal<AoAMinion>(this, AoAMinion.class, 10, true, true, entity -> this.isEnraged && entity instanceof AoAMinion && ((AoAMinion)entity).isTame()));
		targetSelector.addGoal(2, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, 10, true, true, pl -> this.isEnraged && pl instanceof PlayerEntity && PlayerUtil.shouldPlayerBeAffected((PlayerEntity)pl)));
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.15625f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_VISULAR_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_VISULAR_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_VISULAR_HURT.get();
	}

	@Override
	public boolean canChangeDimensions() {
		return false;
	}

	@Override
	protected void onAttack(Entity target) {
		if (!level.isClientSide)
			teleportTo(getX() - 10 + random.nextInt(20), getY() + random.nextInt(10), getZ() - 10 + random.nextInt(20));
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.VISUALENT.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);
		}
	}

	public void enrage() {
		this.isEnraged = true;
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (super.hurt(source, amount)) {
			enrage();

			return true;
		}

		return false;
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.VISUALENT_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.VISUALENT_MUSIC.getId()));
		bossInfo.removePlayer(player);

		if (bossInfo.getPlayers().isEmpty())
			this.isEnraged = false;
	}
}
