package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntitySasquatch extends AoAMeleeMob {
	public static final float entityWidth = 0.6f;

	public EntitySasquatch(World world) {
		super(world, entityWidth, 2.0625f);
	}

	@Override
	public float getEyeHeight() {
		return 1.9375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 25;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_SASQUATCH_LIVING;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_YETI_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_YETI_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entitySasquatch;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
