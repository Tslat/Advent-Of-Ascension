package net.tslat.aoa3.entity.mob.overworld;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;

import javax.annotation.Nullable;
import java.util.UUID;

public class ChomperEntity extends AoAMeleeMob {
	private static final AttributeModifier BLOODTHIRSTY_BUFF = new AttributeModifier(UUID.fromString("2803f9b4-57ed-471f-8a0e-7a41fa100608"), "AoABloodthirstyBuff", 1.05, AttributeModifier.Operation.MULTIPLY_BASE);

	public ChomperEntity(EntityType<? extends MonsterEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
		return 1.25f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.2d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 2;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.23;
	}

	@Override
	protected float getWaterSlowDown() {
		return 1;
	}

	@Override
	public void setAttackTarget(@Nullable LivingEntity target) {
		if (target == null) {
			EntityUtil.removeAttributeModifier(this, SharedMonsterAttributes.MOVEMENT_SPEED, BLOODTHIRSTY_BUFF);
		}
		else {
			EntityUtil.applyAttributeModifierSafely(this, SharedMonsterAttributes.MOVEMENT_SPEED, BLOODTHIRSTY_BUFF);
		}

		super.setAttackTarget(target);
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_CHOMPER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_CHOMPER_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
		return AoASounds.ENTITY_GENERIC_HEAVY_STEP.get();
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity)
			((LivingEntity)target).addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 2, true, true));
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
