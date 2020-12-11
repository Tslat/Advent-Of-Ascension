package net.tslat.aoa3.entity.mob.abyss;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectile.mob.MagicBallEntity;
import net.tslat.aoa3.util.*;

import javax.annotation.Nullable;
import java.util.UUID;

public class WebReaperEntity extends AoARangedMob {
	private static final DataParameter<Integer> STAGE = EntityDataManager.<Integer>createKey(WebReaperEntity.class, DataSerializers.VARINT);
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

	private int stage;
	private float stageMod;
	private boolean shouldHeal = false;

	public WebReaperEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	public ILivingEntityData onInitialSpawn(IWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		EntityUtil.applyAttributeModifierSafely(this, SharedMonsterAttributes.MAX_HEALTH, STAGE_HEALTH_MOD);
		EntityUtil.applyAttributeModifierSafely(this, SharedMonsterAttributes.KNOCKBACK_RESISTANCE, STAGE_KNOCKBACK_MOD);

		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	protected void registerData() {
		super.registerData();

		this.stage = 1;
		this.stageMod = 1;
		dataManager.register(STAGE, 1);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 2.375f * stageMod;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.6d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 107;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 13 * stageMod;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
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
	protected boolean processInteract(PlayerEntity player, Hand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (heldStack.getItem() == AoAItems.NIGHTMARE_FLAKES.get()) {
			if (stage >= 10)
				return false;

			if (!world.isRemote) {
				heldStack.shrink(1);
				stage++;
				updateStage();
				shouldHeal = true;
			}

			return true;
		}
		else if (heldStack.getItem() == Items.ENCHANTED_BOOK && stage > 1) {
			if (!world.isRemote) {
				player.setHeldItem(hand, new ItemStack(AoAItems.BOOK_OF_SHADOWS.get()));

				if (stage <= 10) {
					stage += 5;
					updateStage();
					shouldHeal = true;
				}
			}

			return true;
		}

		return false;
	}

	private void updateStage() {
		if (!world.isRemote) {
			dataManager.set(STAGE, stage);
		}
		else {
			stage = dataManager.get(STAGE);
		}

		stageMod = 1 + (stage - 1) / 7.5f;

		if (!world.isRemote) {
			EntityUtil.reapplyAttributeModifier(this, SharedMonsterAttributes.MAX_HEALTH, STAGE_HEALTH_MOD);
			EntityUtil.reapplyAttributeModifier(this, SharedMonsterAttributes.KNOCKBACK_RESISTANCE, STAGE_KNOCKBACK_MOD);
		}

		recalculateSize();
		experienceValue = (int)getMaxHealth() / 10 + (int)((getMaxHealth() - getBaseMaxHealth()) * 0.1f);
	}

	@Override
	public EntitySize getSize(Pose poseIn) {
		return getType().getSize().scale(stageMod);
	}

	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		if (key == STAGE)
			updateStage();
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);

		if (compound.contains("WebReaperStage")) {
			stage = compound.getInt("WebReaperStage");
			updateStage();
		}
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);

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
			EntityUtil.applyPotions(target, new PotionUtil.EffectBuilder(Effects.WITHER, 100).level(2));
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (cause.getTrueSource() instanceof ServerPlayerEntity)
			AdvancementUtil.completeAdvancement((ServerPlayerEntity)cause.getTrueSource(), new ResourceLocation(AdventOfAscension.MOD_ID, "abyss/reaper_reaper"), "nightmare_web_reaper_kill");
	}

	@Override
	public void livingTick() {
		super.livingTick();

		if (shouldHeal) {
			setHealth(getMaxHealth());

			shouldHeal = false;
		}
	}

	@Override
	public ITextComponent getName() {
		if (hasCustomName()) {
			return getCustomName();
		}
		else {
			String baseName = getType().getRegistryName().getPath();
			String stagePrefix = (stage >= 15 ? "nightmare" : stage >= 5 ? "empowered" : null);

			if (stagePrefix != null)
				return LocaleUtil.getLocaleMessage("entity." + AdventOfAscension.MOD_ID + "." + stagePrefix + "_" + baseName + ".name");

			return LocaleUtil.getLocaleMessage("entity.aoa3." + baseName + ".name");
		}
	}
}
