package net.tslat.aoa3.entity.mobs.abyss;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityJawe extends AoAMeleeMob {
	public static final float entityWidth = 0.8f;

	public EntityJawe(World world) {
		super(world, entityWidth, 0.9f);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 93d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 11d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.28d;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobJaweLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobJaweDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobJaweHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityJawe;
	}
}
