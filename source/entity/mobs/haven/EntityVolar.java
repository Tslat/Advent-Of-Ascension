package net.tslat.aoa3.entity.mobs.haven;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityVolarShot;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;

import javax.annotation.Nullable;

public class EntityVolar extends AoAFlyingRangedMob {
	public static final float entityWidth = 0.75f;

	public EntityVolar(World world) {
		super(world, entityWidth, 0.9375f);
	}

	@Override
	public float getEyeHeight() {
		return 0.71875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 86;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 10;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_VOLAR_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_VOLAR_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_VOLAR_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityVolar;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Override
	protected double getSpawnChanceFactor() {
		return ConfigurationUtil.EntityConfig.mobSpawnFrequencyModifier / 4d;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityVolarShot(this, Enums.MobProjectileType.PHYSICAL);
	}
}
