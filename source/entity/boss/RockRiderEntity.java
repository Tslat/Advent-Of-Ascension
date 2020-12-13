package net.tslat.aoa3.entity.boss;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.MusicPacket;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class RockRiderEntity extends AoAMeleeMob {
	private static final DataParameter<Boolean> ALTERNATE_FORM = EntityDataManager.<Boolean>createKey(RockRiderEntity.class, DataSerializers.BOOLEAN);
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getName().deepCopy().appendSibling(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenSky(false).setCreateFog(false);
	private boolean alternateForm = false;
	private int formCooldown = 300;

	public RockRiderEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		isSlipperyMovement = true;

		this.setAIMoveSpeed(0.8f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 3.0625f;
	}

	@Override
	protected void registerData() {
		super.registerData();

		this.dataManager.register(ALTERNATE_FORM, false);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.9;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1500;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 30;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ROCK_RIDER_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ROCK_RIDER_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_HEAVY_STEP.get();
	}

	@Override
	public boolean isNonBoss() {
		return false;
	}

	private void changeForm(boolean alternate) {
		if (alternate) {
			formCooldown = 300;

			if (!world.isRemote)
				this.dataManager.set(ALTERNATE_FORM, true);
		}
		else {
			formCooldown = 300;

			if (!world.isRemote)
				this.dataManager.set(ALTERNATE_FORM, false);
		}
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if (dataManager.get(ALTERNATE_FORM)) {
			if (DamageUtil.isRangedDamage(source, this, 1))
				return true;
		}
		else {
			if (DamageUtil.isMeleeDamage(source))
				return true;
		}

		return super.isInvulnerableTo(source);
	}

	public boolean isAlternateForm() {
		return this.dataManager.get(ALTERNATE_FORM);
	}

	@Override
	public void tick() {
		super.tick();

		if (formCooldown > 0) {
			formCooldown--;
		}
		else {
			if (alternateForm) {
				alternateForm = false;

				changeForm(false);

				if (!world.isRemote)
					world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.ENTITY_ROCK_RIDER_SWITCH.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
			}
			else {
				alternateForm = true;

				changeForm(true);

				if (!world.isRemote)
					world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.ENTITY_ROCK_RIDER_SWITCH.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
			}
		}
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity) {
			if (alternateForm) {
				double resist = 1;
				IAttributeInstance attrib = ((LivingEntity)target).getAttribute(KNOCKBACK_RESISTANCE);

				if (attrib != null)
					resist -= attrib.getValue();

				EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.SLOWNESS, 150).level(4));
				target.addVelocity(getMotion().getX() * 5 * resist, getMotion().getY() * resist, getMotion().getZ() * 5 * resist);
				target.velocityChanged = true;
			}
			else {
				EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.WEAKNESS, 150).level(4));
			}
		}
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getTrueSource());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage("message.mob.rockrider.kill", killer.getDisplayName().getFormattedText()), world, getPosition(), 50);
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.ROCK_RIDER_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.ROCK_RIDER_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}
}
