package net.tslat.aoa3.entity.mobs.crystevia;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityConstructTerrorShot;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityConstructTerror extends AoAFlyingRangedMob implements SpecialPropertyEntity {
	public static final float entityWidth = 1.0f;

	public EntityConstructTerror(World world) {
		super(world, entityWidth, 1.0f);

		mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 0.4375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 53;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 9.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Override
	protected double getBaseArmour() {
		return 3;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobCrystalConstructLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobCrystalConstructDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobCrystalConstructHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityConstructOfTerror;
	}

	@Override
	protected double getSpawnChanceFactor() {
		return ConfigurationUtil.EntityConfig.mobSpawnFrequencyModifier / 4d;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityConstructTerrorShot(this, Enums.MobProjectileType.PHYSICAL);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (isEntityAlive() && getHealth() < getMaxHealth())
			heal(0.1f);
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return EntityUtil.isRangedDamage(source, this, damage);
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
