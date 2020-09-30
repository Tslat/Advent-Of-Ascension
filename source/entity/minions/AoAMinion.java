package net.tslat.aoa3.entity.minions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.utils.skills.HunterUtil;

import javax.annotation.Nullable;

public abstract class AoAMinion extends EntityTameable {
    private final int lifespan;
    private int animationTicks = 0;
    protected String currentAnimation = null;

    public AoAMinion(World world, int tickLifespan, float entityWidth, float entityHeight) {
        super(world);

        this.lifespan = tickLifespan;
        setSize(entityWidth, entityHeight);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (lifespan > 0 && this.ticksExisted >= lifespan)
            setDead();

        if (animationTicks >= 0)
            animationTicks++;
    }

    @Override
    protected void initEntityAI() {
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(2, new EntityAIWanderAvoidWater(this, 1.0d));
        tasks.addTask(3, new EntityAILookIdle(this));
        tasks.addTask(4, new EntityAIFollowOwner(this, 1.0d, 30.0f, lifespan == -1 ? 2 : 100));
        tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        if (isHostile()) {
            if (getBaseMeleeDamage() >= 0) {
                tasks.addTask(6, new EntityAIAttackMelee(this, 1.5f, true));
                tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3f));
                tasks.addTask(8, new EntityAIMoveTowardsTarget(this, 0.8f, 16f));
            }

            targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, false, false, IMob.MOB_SELECTOR));
            targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
            targetTasks.addTask(3, new EntityAIOwnerHurtByTarget(this));
            targetTasks.addTask(4, new EntityAIOwnerHurtTarget(this));
        }
    }

    @Override
    public boolean canAttackClass(Class<? extends EntityLivingBase> clazz) {
        return !AoAMinion.class.isAssignableFrom(clazz);
    }

    @Override
    public void setAttackTarget(@Nullable EntityLivingBase target) {
        if (target != null) {
            EntityLivingBase owner = getOwner();

            if (owner instanceof EntityPlayer && !HunterUtil.doesPlayerMeetHunterReq(target, (EntityPlayer)owner))
                return;
        }

        super.setAttackTarget(target);
    }

    @Override
    public void setRevengeTarget(@Nullable EntityLivingBase target) {
        if (target != null) {
            EntityLivingBase owner = getOwner();

            if (owner instanceof EntityPlayer && !HunterUtil.doesPlayerMeetHunterReq(target, (EntityPlayer)owner))
                return;
        }

        super.setRevengeTarget(target);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMoveSpeed());
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth());

        if (isHostile())
            getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getBaseMeleeDamage());
    }

    protected double getBaseMoveSpeed() {
        return 0.25D;
    }

    protected abstract double getBaseMaxHealth();

    protected double getBaseMeleeDamage() {
        return -1;
    }

    protected abstract boolean isHostile();

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    protected SoundEvent getFallSound(int heightIn) {
        return heightIn > 4 ? SoundEvents.ENTITY_GENERIC_BIG_FALL : SoundEvents.ENTITY_GENERIC_SMALL_FALL;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public boolean attackEntityAsMob(Entity entity) {
        if (entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue()))) {
            this.setLastAttackedEntity(entity);

            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        return super.isEntityInvulnerable(source) || (getOwnerId() != null && source.getTrueSource() != null && source.getTrueSource().getUniqueID().equals(getOwnerId()));
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable) {
        return null;
    }
}
