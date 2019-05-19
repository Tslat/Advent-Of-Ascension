package net.tslat.aoa3.entity.mobs.voxponds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityBloodball;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityDestructor extends AoARangedMob implements SpecialPropertyEntity {
	public static final float entityWidth = 2.2f;

	public EntityDestructor(World world) {
		super(world, entityWidth, 8.53125f);

		mobProperties.add(Enums.MobProperties.SPECIAL_COMBAT_ENTITY);
		setEntityInvulnerable(true);
	}

	@Override
	public float getEyeHeight() {
		return 7.4375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 80;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 8;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0;
	}

	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		return source != DamageSource.OUT_OF_WORLD;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobDestructorLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobDestructorDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobDestructorHit;
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 5;
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 3, false);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
		world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 3, false);
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.mobDestructorLiving;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityBloodball(this, Enums.MobProjectileType.OTHER);
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
