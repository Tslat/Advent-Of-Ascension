package net.tslat.aoa3.entity.mobs.creeponia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedAttacker;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityCreeperShot;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityMagicalCreeper extends EntityCreeponiaCreeper implements AoARangedAttacker {
    public static final float entityWidth = 0.6f;

    public EntityMagicalCreeper(World world) {
        super(world, entityWidth, 2.37f);
    }

    @Override
    public float getEyeHeight() {
        return 1.40625f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0d;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 55;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.25d;
    }

    @Override
    public float getExplosionStrength() {
        return 3f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobCreepoidLiving;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobCreepoidDeath;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundsRegister.mobCreepoidHit;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityMagicalCreeper;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 50 && super.getCanSpawnHere();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        EntityPlayer target = world.getClosestPlayer(posX, posY, posZ, 20, false);

        if (target == null || target.capabilities.isCreativeMode)
            return;

        if (rand.nextInt(70) == 0) {
            EntityCreeperShot projectile = new EntityCreeperShot(this, Enums.MobProjectileType.MAGIC);

            double distanceFactorX = target.posX - this.posX;
            double distanceFactorY = target.getEntityBoundingBox().minY + (double)(target.height / 3.0f) - projectile.posY;
            double distanceFactorZ = target.posZ - this.posZ;
            double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) + 0.2D;

            world.playSound(null, posX, posY, posZ, SoundsRegister.shotMagicCreeperFire, SoundCategory.HOSTILE, 1.0f, 1.0f);
            projectile.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getId()));
            world.spawnEntity(projectile);
        }
    }

    @Override
    public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
        if (EntityUtil.dealBlasterDamage(this, target, projectile, 7f, false))
            doProjectileImpactEffect(projectile, target);
    }

    @Override
    public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {
        WorldUtil.createExplosion(this, world, projectile, 2f);
    }

    @Override
    public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
        WorldUtil.createExplosion(this, world, projectile, 2f);
    }
}
