package net.tslat.aoa3.entity.minion;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
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

        if (lifespan > 0 && this.tickCount >= lifespan)
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
            if (getAttributeValue(Attributes.ATTACK_DAMAGE) >= 0) {
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
    public void setTarget(@Nullable LivingEntity target) {
        if (target != null) {
            LivingEntity owner = getOwner();

            if (owner instanceof PlayerEntity && !HunterUtil.canAttackTarget(target, owner, false))
                return;
        }

        super.setTarget(target);
    }

    @Override
    public void setLastHurtByMob(@Nullable LivingEntity target) {
        if (target != null) {
            LivingEntity owner = getOwner();

            if (owner instanceof PlayerEntity && !HunterUtil.canAttackTarget(target, owner, false))
                return;
        }

        super.setLastHurtByMob(target);
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
    protected SoundEvent getFallDamageSound(int heightIn) {
        return heightIn > 4 ? SoundEvents.GENERIC_BIG_FALL : SoundEvents.GENERIC_SMALL_FALL;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return false;
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (entity.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttribute(Attributes.ATTACK_DAMAGE).getValue()))) {
            this.setLastHurtMob(entity);

            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return super.isInvulnerableTo(source) || (getOwnerUUID() != null && source.getEntity() != null && source.getEntity().getUUID().equals(getOwnerUUID()));
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity partner) {
        return null;
    }
}
