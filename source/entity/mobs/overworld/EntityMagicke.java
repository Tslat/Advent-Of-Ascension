package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityMagickeShot;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityMagicke extends AoARangedMob implements SpecialPropertyEntity {
	public static final float entityWidth = 0.6f;

	public EntityMagicke(World world) {
		super(world, entityWidth, 2.1875f);

		mobProperties.add(Enums.MobProperties.MAGIC_IMMUNE);
	}

	@Override
	public float getEyeHeight() {
		return 1.6875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 15;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobMagickeLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobMagickeDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobMagickeHit;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.shotMagickeFire;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityMagicke;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityMagickeShot(this, Enums.MobProjectileType.MAGIC);
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, world, projectile, 1f);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
		WorldUtil.createExplosion(this, world, projectile, 1f);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return EntityUtil.isMagicDamage(source, this, damage);
	}

	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return this.mobProperties;
	}
}
