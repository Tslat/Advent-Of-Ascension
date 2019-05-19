package net.tslat.aoa3.entity.base;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.entity.base.ai.RoamingFlightMoveHelper;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIFlyingLookAround;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIFlyingRangedAttack;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIRandomFly;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.event.dimension.OverworldEvents;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public abstract class AoAFlyingRangedMob extends EntityFlying implements IMob, IRangedAttackMob, AoARangedAttacker {
    private static final DataParameter<Boolean> SHOOTING = EntityDataManager.<Boolean>createKey(AoAFlyingRangedMob.class, DataSerializers.BOOLEAN);
    protected final TreeSet<Enums.MobProperties> mobProperties;

    public AoAFlyingRangedMob(World world, float entityWidth, float entityHeight) {
        super(world);

        if (this instanceof SpecialPropertyEntity) {
            mobProperties = new TreeSet<Enums.MobProperties>();

            if (this instanceof HunterEntity)
                mobProperties.add(Enums.MobProperties.HUNTER_ENTITY);
        }
        else {
            mobProperties = null;
        }

        moveHelper = new RoamingFlightMoveHelper(this);

        setSize(entityWidth, entityHeight);
        setXpValue((int)getBaseMaxHealth() / 10);
    }

    @Override
    protected void entityInit() {
        super.entityInit();

        dataManager.register(SHOOTING, false);
    }

    @Override
    protected void initEntityAI() {
        tasks.addTask(1, new EntityAIRandomFly(this, true));
        tasks.addTask(2, new EntityAIFlyingLookAround(this));
        tasks.addTask(3, new EntityAILookIdle(this));
        tasks.addTask(4, new EntityAIFlyingRangedAttack(this, 40, 80));
        targetTasks.addTask(1, new EntityAIFindEntityNearest(this, AoAMinion.class));
        targetTasks.addTask(2, new EntityAIFindEntityNearestPlayer(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(52);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(getBaseKnockbackResistance());
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth());
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMovementSpeed());
    }

    protected abstract double getBaseKnockbackResistance();

    protected abstract double getBaseMaxHealth();

    public abstract double getBaseProjectileDamage();

    protected abstract double getBaseMovementSpeed();

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return null;
    }

    @Nullable
    protected abstract SoundEvent getShootSound();

    @Override
    public PathNavigate getNavigator() {
        return new PathNavigateFlying(this, world);
    }

    protected void setXpValue(int amount) {
        experienceValue = amount;
    }

    @Override
    public boolean getCanSpawnHere() {
        return world.getDifficulty() != EnumDifficulty.PEACEFUL && checkWorldRequirements() && checkSpawnChance() && isValidLightLevel() && canSpawnOnBlock(world.getBlockState(getPosition().down()));
    }

    protected boolean isOverworldMob() {
        return false;
    }

    protected boolean isValidLightLevel() {
        if (!isOverworldMob() || ((isDaylightMob() && world.isDaytime())))
            return true;

        BlockPos blockpos = new BlockPos(posX, getEntityBoundingBox().minY, posZ);

        if (world.getLightFor(EnumSkyBlock.SKY, blockpos) > rand.nextInt(32)) {
            return false;
        }
        else {
            int light = world.getLightFromNeighbors(blockpos);

            if (this.world.isThundering()) {
                int skyLight = world.getSkylightSubtracted();

                world.setSkylightSubtracted(10);

                light = world.getLightFromNeighbors(blockpos);

                world.setSkylightSubtracted(skyLight);
            }

            return light <= rand.nextInt(8);
        }
    }

    protected boolean isDaylightMob() {
        return false;
    }

    private boolean checkSpawnChance() {
        return getSpawnChanceFactor() == 1 || rand.nextInt(getSpawnChanceFactor()) == 0;
    }

    protected int getSpawnChanceFactor() {
        return 1;
    }

    protected boolean canSpawnOnBlock(IBlockState block) {
        return block.canEntitySpawn(this);
    }

    private boolean checkWorldRequirements() {
        if (isOverworldMob()) {
            if (world.provider.getDimension() != 0) {
                EntityRegistry.removeSpawn(getClass(), EnumCreatureType.MONSTER, world.getBiome(getPosition()));

                return false;
            }
        }

        Enums.CreatureEvents eventReq = getEventRequirement();

        return eventReq == null || OverworldEvents.isEventActive(eventReq);
    }

    @Nullable
    protected Enums.CreatureEvents getEventRequirement() {
        return null;
    }

    @Override
    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    protected void playStepSound(BlockPos pos, Block block) {}

    @SideOnly(Side.CLIENT)
    public boolean isShooting() {
        return dataManager.get(SHOOTING);
    }

    @Override
    public void setSwingingArms(boolean swingingArms) {
        dataManager.set(SHOOTING, swingingArms);
    }

    @Override
    public void setAttackTarget(@Nullable EntityLivingBase target) {
        if (!(target instanceof AoAMinion) || ((AoAMinion)target).isTamed()) {
            super.setAttackTarget(target);
            this.idleTime = 0;
        }
    }

    @Override
    public void attackEntityWithRangedAttack(@Nonnull EntityLivingBase target, float bowDamageFactor) {
        BaseMobProjectile projectile = getNewProjectileInstance();

        double distanceFactorX = target.posX - projectile.posX;
        double distanceFactorY = target.getEntityBoundingBox().minY + (target.height / 3) - projectile.posY;
        double distanceFactorZ = target.posZ - projectile.posZ;
        double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

        if (getShootSound() != null)
            world.playSound(null, posX, posY, posZ, getShootSound(), SoundCategory.HOSTILE, 1.0f, 1.0f);

        projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - world.getDifficulty().getDifficultyId()));
        world.spawnEntity(projectile);
    }

    protected abstract BaseMobProjectile getNewProjectileInstance();

    @Override
    public void doProjectileEntityImpact(BaseMobProjectile projectile, Entity target) {
        boolean success;

        switch (projectile.getProjectileType()) {
            case MAGIC:
                success = EntityUtil.dealMagicDamage(projectile, this, target, (float)getBaseProjectileDamage(), false);
                break;
            case GUN:
                success = EntityUtil.dealGunDamage(target, this, projectile, (float)getBaseProjectileDamage());
                break;
            case ENERGY:
                success = EntityUtil.dealBlasterDamage(this, target, projectile, (float)getBaseProjectileDamage(), false);
                break;
            case PHYSICAL:
                success = EntityUtil.dealRangedDamage(target, this, projectile, (float)getBaseProjectileDamage());
                break;
            case OTHER:
            default:
                success = target.attackEntityFrom(DamageSource.causeIndirectDamage(projectile, this), (float)getBaseProjectileDamage());
                break;
        }

        if (success)
            doProjectileImpactEffect(projectile, target);
    }

    @Override
    public void doProjectileBlockImpact(BaseMobProjectile projectile, IBlockState blockHit, BlockPos pos, EnumFacing sideHit) {}

    @Override
    public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {}

    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        if (source == DamageSource.OUT_OF_WORLD)
            return false;

        if (getIsInvulnerable())
            return true;

        if (this instanceof HunterEntity) {
            Entity trueSource = source.getTrueSource();
            EntityPlayer pl = null;

            if (trueSource instanceof EntityPlayer) {
                pl = (EntityPlayer)trueSource;
            }
            else if (trueSource instanceof EntityTameable && ((EntityTameable)trueSource).getOwner() instanceof EntityPlayer) {
                pl = (EntityPlayer)((EntityTameable)trueSource).getOwner();
            }

            if (pl != null) {
                if (!isSpecialImmuneTo(source))
                    return !pl.capabilities.isCreativeMode && !PlayerUtil.doesPlayerHaveLevel(pl, Enums.Skills.HUNTER, ((HunterEntity)this).getHunterReq());

                return true;
            }
        }

        return isSpecialImmuneTo(source);
    }

    protected boolean isSpecialImmuneTo(DamageSource source) {
        return false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!world.isRemote && world.getDifficulty() == EnumDifficulty.PEACEFUL)
            setDead();
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (this instanceof HunterEntity) {
            Entity trueSource = cause.getTrueSource();
            EntityPlayer pl = null;

            if (trueSource instanceof EntityPlayer) {
                pl = (EntityPlayer)trueSource;
            }
            else if (trueSource instanceof EntityTameable && ((EntityTameable)trueSource).getOwner() instanceof EntityPlayer) {
                pl = (EntityPlayer)((EntityTameable)trueSource).getOwner();
            }

            if (pl != null && PlayerUtil.doesPlayerHaveLevel(pl, Enums.Skills.HUNTER, ((HunterEntity)this).getHunterReq()))
                PlayerUtil.giveXpToPlayer(pl, Enums.Skills.HUNTER, ((HunterEntity)this).getHunterXp());
        }
    }

    @Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        if (this instanceof HunterEntity && (!(source.getTrueSource() instanceof EntityPlayer) && (!(source.getTrueSource() instanceof EntityTameable) || !(((EntityTameable)source.getTrueSource()).getOwner() instanceof EntityPlayer))))
            return;

        lootingModifier = MathHelper.clamp(lootingModifier, 0, 9);

        dropGuaranteedItems(lootingModifier, source);

        if (wasRecentlyHit)
            dropSpecialItems(lootingModifier, source);
    }

    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {}

    protected void dropSpecialItems(int lootingMod, DamageSource source) {}
}
