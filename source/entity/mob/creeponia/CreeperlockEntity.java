package net.tslat.aoa3.entity.mob.creeponia;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoARangedAttacker;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectile.mob.CreeperShotEntity;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;

public class CreeperlockEntity extends AoACreeponiaCreeper implements AoARangedAttacker {
    public CreeperlockEntity(EntityType<? extends AoACreeponiaCreeper> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.40625f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0d;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 50d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.207d;
    }

    @Override
	public float getExplosionStrength() {
        return 4;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_CREEPOID_AMBIENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_CREEPOID_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_CREEPOID_HURT.get();
    }

    @Override
    protected int getMaxSpawnHeight() {
        return 50;
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if (!isAlive())
            return;

        PlayerEntity target = world.getClosestPlayer(getPosX(), getPosY(), getPosZ(), 20, false);

        if (target == null || target.isCreative())
            return;

        if (!world.isRemote && RandomUtil.oneInNChance(120)) {
            setPosition(target.getPosX(), target.getPosY(), target.getPosZ());
            world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.ENTITY_CREEPERLOCK_TELEPORT.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
        }

        if (RandomUtil.oneInNChance(70)) {
            CreeperShotEntity projectile = new CreeperShotEntity(this, BaseMobProjectile.Type.MAGIC);

            double distanceFactorX = target.getPosX() - getPosX();
            double distanceFactorY = target.getBoundingBox().minY + (double)(target.getHeight() / 3.0f) - projectile.getPosY();
            double distanceFactorZ = target.getPosZ() - this.getPosZ();
            double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) + 0.2D;

            world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.ENTITY_MAGICAL_CREEPER_SHOOT.get(), SoundCategory.HOSTILE, 1.0f, 1.0f);
            projectile.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getId()));
            world.addEntity(projectile);
        }
    }

    @Override
    public void doProjectileBlockImpact(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
        WorldUtil.createExplosion(this, world, projectile, 2f);
    }

    @Override
    public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
        WorldUtil.createExplosion(this, world, projectile, 2f);
    }

    @Override
    public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
        if (DamageUtil.dealBlasterDamage(this, target, projectile, 7f, false))
            doProjectileImpactEffect(projectile, target);
    }
}
