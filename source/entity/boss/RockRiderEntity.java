package net.tslat.aoa3.entity.boss;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.attributes.ModifiableAttributeInstance;
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
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;

public class RockRiderEntity extends AoAMeleeMob {
	private static final DataParameter<Boolean> ALTERNATE_FORM = EntityDataManager.<Boolean>defineId(RockRiderEntity.class, DataSerializers.BOOLEAN);
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);
	private boolean alternateForm = false;
	private int formCooldown = 300;

	public RockRiderEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);

		isSlipperyMovement = true;

		this.setSpeed(0.8f);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 3.0625f;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		this.entityData.define(ALTERNATE_FORM, false);
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
	public boolean canChangeDimensions() {
		return false;
	}

	private void changeForm(boolean alternate) {
		if (alternate) {
			formCooldown = 300;

			if (!level.isClientSide)
				this.entityData.set(ALTERNATE_FORM, true);
		}
		else {
			formCooldown = 300;

			if (!level.isClientSide)
				this.entityData.set(ALTERNATE_FORM, false);
		}
	}

	@Override
	public boolean isInvulnerableTo(DamageSource source) {
		if (entityData.get(ALTERNATE_FORM)) {
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
		return this.entityData.get(ALTERNATE_FORM);
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

			}
			else {
				alternateForm = true;

				changeForm(true);

			}

			if (!level.isClientSide)
				level.playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_ROCK_RIDER_SWITCH.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
		}

		if (!level.isClientSide()) {
			float healthPercent = getHealth() / getMaxHealth();

			if (healthPercent != bossInfo.getPercent())
				bossInfo.setPercent(healthPercent);
		}
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity) {
			if (alternateForm) {
				double resist = 1;
				ModifiableAttributeInstance attrib = ((LivingEntity)target).getAttribute(Attributes.KNOCKBACK_RESISTANCE);

				if (attrib != null)
					resist -= attrib.getValue();

				EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.MOVEMENT_SLOWDOWN, 150).level(4));
				target.push(getDeltaMovement().x() * 5 * resist, getDeltaMovement().y() * resist, getDeltaMovement().z() * 5 * resist);
				target.hurtMarked = true;
			}
			else {
				EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.WEAKNESS, 150).level(4));
			}
		}
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.ROCK_RIDER.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.ROCK_RIDER_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.ROCK_RIDER_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}
}
