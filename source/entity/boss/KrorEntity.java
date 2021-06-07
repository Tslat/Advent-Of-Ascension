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
import net.tslat.aoa3.entity.mob.lborean.AnglerEntity;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PotionUtil;
import net.tslat.aoa3.util.player.PlayerUtil;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import javax.annotation.Nullable;

public class KrorEntity extends AoAMeleeMob implements IAnimatable {
	private final ServerBossInfo bossInfo = (ServerBossInfo)(new ServerBossInfo(getType().getDescription().copy().append(getDisplayName()), BossInfo.Color.GREEN, BossInfo.Overlay.NOTCHED_20)).setDarkenScreen(false).setCreateWorldFog(false);

	private final AnimationFactory animationFactory = new AnimationFactory(this);
	private static final AnimationBuilder WALK_ANIMATION = new AnimationBuilder().addAnimation("kror.walk", true);
	private static final AnimationBuilder SWING_ANIMATION = new AnimationBuilder().addAnimation("kror.swing", false);

	public KrorEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 3.28125f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_KROR_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_KROR_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_KROR_AMBIENT.get();
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

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (DamageUtil.isRangedDamage(source, this, amount))
			amount *= 0.5;

		return super.hurt(source, amount);
	}

	@Override
	protected void onAttack(Entity target) {
		EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.MOVEMENT_SLOWDOWN, 150).level(3));
	}

	@Override
	public void tick() {
		super.tick();

		if (random.nextInt(200) == 0) {
			for (PlayerEntity pl : level.getEntitiesOfClass(PlayerEntity.class, getBoundingBox().inflate(20), PlayerUtil::shouldPlayerBeAffected)) {
				if (!pl.isShiftKeyDown())
					pl.push(Math.signum(getX() - pl.getX()) * 0.229, 0, Math.signum(getZ() - pl.getZ()) * 0.229);
			}

			level.playSound(null, blockPosition(), AoASounds.ENTITY_KROR_AMBIENT.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
		}
	}

	@Override
	public boolean startRiding(Entity entity, boolean force) {
		return false;
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (!level.isClientSide) {
			PlayerEntity killer = PlayerUtil.getPlayerOrOwnerIfApplicable(cause.getEntity());

			if (killer != null)
				PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.KROR.get().getDescriptionId() + ".kill", killer.getDisplayName()), level, blockPosition(), 50);
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

		AoAPackets.messagePlayer(player, new MusicPacket(true, AoASounds.KROR_MUSIC.getId()));
		bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);

		AoAPackets.messagePlayer(player, new MusicPacket(false, AoASounds.KROR_MUSIC.getId()));
		bossInfo.removePlayer(player);
	}

	@Override
	public int getCurrentSwingDuration() {
		return 25;
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(new AnimationController<KrorEntity>(this, "base_animations", 0, new AnimationController.IAnimationPredicate<KrorEntity>() {
			@Override
			public PlayState test(AnimationEvent<KrorEntity> event) {
				if (swinging) {
					event.getController().setAnimation(SWING_ANIMATION);

					return PlayState.CONTINUE;
				}
				else if (event.isMoving()) {
					event.getController().setAnimation(WALK_ANIMATION);

					return PlayState.CONTINUE;
				}

				return PlayState.STOP;
			}
		}));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.animationFactory;
	}
}
