package net.tslat.aoa3.entity.mobs.voxponds;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
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
import net.tslat.aoa3.entity.projectiles.mob.EntityBloodball;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.WorldUtil;

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
	protected void initEntityAI() {
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 1.0d));
		tasks.addTask(3, new EntityAIAttackRanged(this, 1.0d, 20, 40, 32));
		tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0d));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(5, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
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
		return 999;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 15;
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

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityDestructor;
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		WorldUtil.createExplosion(this, world, projectile, 3);
	}

	@Override
	public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
		WorldUtil.createExplosion(this, world, projectile, 3);
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
