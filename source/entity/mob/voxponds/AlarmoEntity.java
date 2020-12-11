package net.tslat.aoa3.entity.mob.voxponds;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PotionUtil;

import javax.annotation.Nullable;
import java.util.List;

public class AlarmoEntity extends AoAMeleeMob {

    public AlarmoEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1));
        goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8f));
        goalSelector.addGoal(3, new LookRandomlyGoal(this));
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 74;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 0;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_ALARMO_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_ALARMO_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_ALARMO_HURT.get();
    }

    @Override
    public void livingTick() {
        super.livingTick();

        if (world.isRemote || isAIDisabled())
            return;

        List<PlayerEntity> playerList = world.getEntitiesWithinAABB(PlayerEntity.class, getBoundingBox().grow(4), pl -> pl != null && !pl.isSpectator() && !pl.isCreative() && canEntityBeSeen(pl));

        if (!playerList.isEmpty()) {
            List<LivingEntity> mobList = world.getEntitiesWithinAABB(LivingEntity.class, getBoundingBox().grow(30), EntityUtil.Predicates.HOSTILE_MOB);

            EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.SLOWNESS).level(20));

            for (LivingEntity mob : mobList) {
                mob.setRevengeTarget(playerList.get(rand.nextInt(playerList.size())));
            }
        }
    }
}
