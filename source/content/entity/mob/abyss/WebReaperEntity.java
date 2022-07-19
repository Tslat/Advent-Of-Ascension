package net.tslat.aoa3.content.entity.mob.abyss;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.MagicBallEntity;
import net.tslat.aoa3.library.builder.EffectBuilder;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.UUID;

public class WebReaperEntity extends AoARangedMob {
	private static final EntityDataAccessor<Integer> STAGE = SynchedEntityData.<Integer>defineId(WebReaperEntity.class, EntityDataSerializers.INT);
	private final AttributeModifier STAGE_HEALTH_MOD = new AttributeModifier(UUID.fromString("9c59eceb-dcd0-40e0-a608-a46d3794b1c3"), "StageHealthModifier", 1, AttributeModifier.Operation.MULTIPLY_TOTAL) {
		@Override
		public double getAmount() {
			return Math.max(0, stageMod - 1);
		}
	};
	private final AttributeModifier STAGE_KNOCKBACK_MOD = new AttributeModifier(UUID.fromString("a7cd0b89-ca94-4e54-a0c4-f56e8cb70bb0"), "StageKnockbackModifier", 1, AttributeModifier.Operation.MULTIPLY_TOTAL) {
		@Override
		public double getAmount() {
			return Math.max(0, (stageMod - 1) * 0.83d);
		}
	};
	private final AttributeModifier STAGE_DAMAGE_MOD = new AttributeModifier(UUID.fromString("104c09f0-28cc-43dd-81c0-10de6b3083bd"), "StageDamageModifier", 1, AttributeModifier.Operation.MULTIPLY_TOTAL) {
		@Override
		public double getAmount() {
			return Math.max(0, stageMod - 1);
		}
	};

	private int stage;
	private float stageMod;
	private boolean shouldHeal = false;

	public WebReaperEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		EntityUtil.applyAttributeModifierSafely(this, Attributes.MAX_HEALTH, STAGE_HEALTH_MOD, false);
		EntityUtil.applyAttributeModifierSafely(this, Attributes.KNOCKBACK_RESISTANCE, STAGE_KNOCKBACK_MOD, false);
		EntityUtil.applyAttributeModifierSafely(this, AoAAttributes.RANGED_ATTACK_DAMAGE.get(), STAGE_DAMAGE_MOD, false);

		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();

		this.stage = 1;
		this.stageMod = 1;
		entityData.define(STAGE, 1);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 2.375f * stageMod;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_WEB_REAPER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_WEB_REAPER_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_WEB_REAPER_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return AoASounds.ENTITY_WEB_REAPER_SHOOT.get();
	}

	public int getStage() {
		return stage;
	}

	public float getStageMod() {
		return stageMod;
	}

	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack heldStack = player.getItemInHand(hand);

		if (heldStack.getItem() == AoAItems.NIGHTMARE_FLAKES.get()) {
			if (stage >= 10)
				return InteractionResult.FAIL;

			if (!level.isClientSide) {
				heldStack.shrink(1);
				stage++;
				updateStage();
				shouldHeal = true;
			}

			return InteractionResult.SUCCESS;
		}
		else if (heldStack.getItem() == Items.ENCHANTED_BOOK && stage > 1) {
			if (!level.isClientSide) {
				player.setItemInHand(hand, new ItemStack(AoAItems.BOOK_OF_SHADOWS.get()));

				if (stage <= 10) {
					stage += 5;
					updateStage();
					shouldHeal = true;
				}
			}

			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	private void updateStage() {
		if (!level.isClientSide) {
			entityData.set(STAGE, stage);
		}
		else {
			stage = entityData.get(STAGE);
		}

		stageMod = 1 + (stage - 1) / 7.5f;

		if (!level.isClientSide) {
			EntityUtil.reapplyAttributeModifier(this, Attributes.MAX_HEALTH, STAGE_HEALTH_MOD, false);
			EntityUtil.reapplyAttributeModifier(this, Attributes.KNOCKBACK_RESISTANCE, STAGE_KNOCKBACK_MOD, false);
			EntityUtil.reapplyAttributeModifier(this, AoAAttributes.RANGED_ATTACK_DAMAGE.get(), STAGE_DAMAGE_MOD, false);
		}

		refreshDimensions();
		xpReward = (int)getMaxHealth() / 10 + (int)((getMaxHealth() - getAttributeValue(Attributes.MAX_HEALTH)) * 0.1f);
	}

	@Override
	public EntityDimensions getDimensions(Pose poseIn) {
		return getType().getDimensions().scale(stageMod);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		if (key.equals(STAGE))
			updateStage();
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);

		if (compound.contains("WebReaperStage")) {
			stage = compound.getInt("WebReaperStage");
			updateStage();
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);

		if (stage > 1)
			compound.putInt("WebReaperStage", stage);
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new MagicBallEntity(this, BaseMobProjectile.Type.MAGIC);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		if (stage >= 15)
			EntityUtil.applyPotions(target, new EffectBuilder(MobEffects.WITHER, 100).level(2));
	}

	@Override
	public void die(DamageSource cause) {
		super.die(cause);

		if (cause.getEntity() instanceof ServerPlayer && stage >= 15)
			AdvancementUtil.completeAdvancement((ServerPlayer)cause.getEntity(), new ResourceLocation(AdventOfAscension.MOD_ID, "abyss/reaper_reaper"), "nightmare_web_reaper_kill");
	}

	@Override
	public void aiStep() {
		super.aiStep();

		if (shouldHeal) {
			setHealth(getMaxHealth());

			shouldHeal = false;
		}
	}

	@Override
	public Component getName() {
		if (hasCustomName()) {
			return getCustomName();
		}
		else {
			String baseName = ForgeRegistries.ENTITY_TYPES.getKey(getType()).getPath();
			String stagePrefix = (stage >= 15 ? "nightmare" : stage >= 5 ? "empowered" : null);

			if (stagePrefix != null)
				return LocaleUtil.getLocaleMessage("entity." + AdventOfAscension.MOD_ID + "." + stagePrefix + "_" + baseName);

			return LocaleUtil.getLocaleMessage("entity.aoa3." + baseName);
		}
	}
}
