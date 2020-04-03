package net.tslat.aoa3.entity.boss.skeletalarmy;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntitySkelePig extends AoAMeleeMob {
	public static final float entityWidth = 0.7f;

	public EntitySkelePig(World world) {
		super(world, entityWidth, 1.125f);
	}

	@Override
	public float getEyeHeight() {
		return 0.71875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 20;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 8;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_SKELETON_AMBIENT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SKELETON_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_SKELETON_HURT;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	@Override
	protected SoundEvent getStepSound() {
		return null;
	}
}
