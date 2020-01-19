package net.tslat.aoa3.entity.animals;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAAnimal;

import javax.annotation.Nullable;

public class EntityPeppermintSnail extends AoAAnimal {
	public static final float entityWidth = 0.5f;

	public EntityPeppermintSnail(World world) {
		super(world, entityWidth, 0.8125f);
	}

	@Override
	public float getEyeHeight() {
		return 0.3125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.8;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 25;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.candyThump;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.candyThump;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.candySnailStep;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityPeppermintSnail;
	}
}
