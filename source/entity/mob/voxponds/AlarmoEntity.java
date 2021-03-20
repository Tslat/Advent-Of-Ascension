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
    public void aiStep() {
        super.aiStep();

        if (level.isClientSide || isNoAi())
            return;

        List<PlayerEntity> playerList = level.getEntitiesOfClass(PlayerEntity.class, getBoundingBox().inflate(4), pl -> pl != null && !pl.isSpectator() && !pl.isCreative() && canSee(pl));

        if (!playerList.isEmpty()) {
            List<LivingEntity> mobList = level.getEntitiesOfClass(LivingEntity.class, getBoundingBox().inflate(30), EntityUtil.Predicates.HOSTILE_MOB);

            EntityUtil.applyPotions(this, new PotionUtil.EffectBuilder(Effects.MOVEMENT_SLOWDOWN).level(20));

            for (LivingEntity mob : mobList) {
                mob.setLastHurtByMob(playerList.get(random.nextInt(playerList.size())));
            }
        }
    }
}
