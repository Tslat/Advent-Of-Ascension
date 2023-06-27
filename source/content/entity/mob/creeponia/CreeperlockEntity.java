package net.tslat.aoa3.content.entity.mob.creeponia;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.CreeperShotEntity;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nullable;

public class CreeperlockEntity extends AoACreeponiaCreeper implements AoARangedAttacker {
    public CreeperlockEntity(EntityType<? extends AoACreeponiaCreeper> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 1.40625f;
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
    public void aiStep() {
        super.aiStep();

        if (!isAlive())
            return;

        Player target = level().getNearestPlayer(getX(), getY(), getZ(), 20, false);

        if (target == null || target.isCreative())
            return;

        if (!level().isClientSide && RandomUtil.oneInNChance(120)) {
            setPos(target.getX(), target.getY(), target.getZ());
            level().playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_CREEPERLOCK_TELEPORT.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
        }

        if (RandomUtil.oneInNChance(70)) {
            CreeperShotEntity projectile = new CreeperShotEntity(this, BaseMobProjectile.Type.MAGIC);

            double distanceFactorX = target.getX() - getX();
            double distanceFactorY = target.getBoundingBox().minY + (double)(target.getBbHeight() / 3.0f) - projectile.getY();
            double distanceFactorZ = target.getZ() - this.getZ();
            double hyp = Math.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) + 0.2D;

            level().playSound(null, getX(), getY(), getZ(), AoASounds.ENTITY_MAGICAL_CREEPER_SHOOT.get(), SoundSource.HOSTILE, 1.0f, 1.0f);
            projectile.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - this.level().getDifficulty().getId()));
            level().addFreshEntity(projectile);
        }
    }

    @Override
    public void doRangedAttackBlock(BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {
        WorldUtil.createExplosion(this, level(), projectile, 2f);
    }

    public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
        WorldUtil.createExplosion(this, level(), projectile, 2f);
    }

    @Override
    public void doRangedAttackEntity(BaseMobProjectile projectile, Entity target) {
        if (DamageUtil.doEnergyProjectileAttack(this, projectile, target, 7f))
            doProjectileImpactEffect(projectile, target);
    }

    @Override
    public void performRangedAttack(LivingEntity target, float distanceFactor) {

    }
}
