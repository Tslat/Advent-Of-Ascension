package net.tslat.aoa3.entity.mobs.overworld.deathday;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityDeathHunter extends AoAMeleeMob {
	public static final float entityWidth = 1.5f;

	public EntityDeathHunter(World world) {
		super(world, entityWidth, 3.125f);
	}

	@Override
	public float getEyeHeight() {
		return 2.95f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.75f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 85;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 7;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_DEATH_HUNTER_LIVING;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_DEATH_HUNTER_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_DEATH_HUNTER_HIT;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.VERY_HEAVY_STEP;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityDeathHunter;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.DEATH_DAY;
	}
}
