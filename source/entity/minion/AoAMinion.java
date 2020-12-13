package net.tslat.aoa3.entity.minion;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.skill.HunterUtil;

import javax.annotation.Nullable;

public abstract class AoAMinion extends TameableEntity {
    private final int lifespan;
    private int animationTicks = 0;
    protected String currentAnimation = null;

    public AoAMinion(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);

        this.lifespan = -1;
    }

    public AoAMinion(EntityType<? extends TameableEntity> entityType, World world, int tickLifespan) {
        super(entityType, world);

        this.lifespan = tickLifespan;
    }

    @Override
    public void tick() {
        super.tick();

        if (lifespan > 0 && this.ticksExisted >= lifespan)
            remove();

        if (animationTicks >= 0)
            animationTicks++;
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new SwimGoal(this));
        goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1));
        goalSelector.addGoal(3, new LookRandomlyGoal(this));
        goalSelector.addGoal(4, new FollowOwnerGoal(this, 1, 30, lifespan == -1 ? 2 : 100, true));
        goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8));

        if (isHostile()) {
            if (getBaseMeleeDamage() >= 0) {
                goalSelector.addGoal(6, new MeleeAttackGoal(this, 1.5f, true));
                goalSelector.addGoal(7, new LeapAtTargetGoal(this, 0.3f));
                goalSelector.addGoal(8, new MoveTowardsTargetGoal(this, 0.8f, 16f));
            }

            targetSelector.addGoal(1, new NearestAttackableTargetGoal<LivingEntity>(this, LivingEntity.class, 0, false, false, EntityUtil.Predicates.HOSTILE_MOB));
            targetSelector.addGoal(2, new HurtByTargetGoal(this));
            targetSelector.addGoal(3, new OwnerHurtByTargetGoal(this));
            targetSelector.addGoal(4, new OwnerHurtTargetGoal(this));
        }
    }

    @Override
    public boolean canAttack(LivingEntity target) {
        return super.canAttack(target) && !(target instanceof AoAMinion);
    }

    @Override
    public void setAttackTarget(@Nullable LivingEntity target) {
        if (target != null) {
            LivingEntity owner = getOwner();

            if (owner instanceof PlayerEntity && !HunterUtil.canAttackTarget(target, owner, false))
                return;
        }

        super.setAttackTarget(target);
    }

    @Override
    public void setRevengeTarget(@Nullable LivingEntity target) {
        if (target != null) {
            LivingEntity owner = getOwner();

            if (owner instanceof PlayerEntity && !HunterUtil.canAttackTarget(target, owner, false))
                return;
        }

        super.setRevengeTarget(target);
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMoveSpeed());
        getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth());

        if (isHostile())
            getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getBaseMeleeDamage());
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
        if (entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float)((int)this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue()))) {
            this.setLastAttackedEntity(entity);

            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return super.isInvulnerableTo(source) || (getOwnerId() != null && source.getTrueSource() != null && source.getTrueSource().getUniqueID().equals(getOwnerId()));
    }

    @Nullable
    @Override
    public AgeableEntity createChild(AgeableEntity ageable) {
        return null;
    }
}
