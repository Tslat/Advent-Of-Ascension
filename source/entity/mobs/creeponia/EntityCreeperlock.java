package net.tslat.aoa3.entity.mobs.creeponia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedAttacker;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityCreeperShot;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntityCreeperlock extends EntityCreeper implements AoARangedAttacker {
    public static final float entityWidth = 0.6f;

    public EntityCreeperlock(World world) {
        super(world);

        setSize(entityWidth, 2.37f);
    }

    @Override
    public float getEyeHeight() {
        return 1.40625f;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.95);
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

    @Override
    public boolean getCanSpawnHere() {
        return posY < 19 && super.getCanSpawnHere();
    }

    @Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 2 + rand.nextInt(5 + lootingModifier));

        if (wasRecentlyHit) {
            if (rand.nextBoolean())
                dropItem(ItemRegister.tokensCreeponia, 1 + rand.nextInt(3 + lootingModifier));

            if (rand.nextInt(4) == 0)
                dropItem(Item.getItemFromBlock(BlockRegister.bannerCreepy), 1);
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        EntityPlayer target = world.getClosestPlayer(posX, posY, posZ, 20, false);

        if (target == null || target.capabilities.isCreativeMode)
            return;

        if (!world.isRemote && rand.nextInt(120) == 0) {
            setPosition(target.posX, target.posY, target.posZ);
            world.playSound(null, posX, posY, posZ, SoundsRegister.mobCreeperlockTeleport, SoundCategory.HOSTILE, 1.0f, 1.0f);
        }

        if (rand.nextInt(70) == 0) {
            EntityCreeperShot projectile = new EntityCreeperShot(this, Enums.MobProjectileType.ENERGY);

            double distanceFactorX = target.posX - this.posX;
            double distanceFactorY = target.getEntityBoundingBox().minY + (double)(target.height / 3.0f) - projectile.posY;
            double distanceFactorZ = target.posZ - this.posZ;
            double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) + 0.2D;

            world.playSound(null, posX, posY, posZ, SoundsRegister.shotMagicCreeperFire, SoundCategory.HOSTILE, 1.0f, 1.0f);
            projectile.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getDifficultyId()));
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
        world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 2f, false);
    }

    @Override
    public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
        world.createExplosion(this, projectile.posX, projectile.posY, projectile.posZ, 2f, false);
    }
}
