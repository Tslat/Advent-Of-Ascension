package net.tslat.aoa3.entity.mob.dustopia;

import net.minecraft.entity.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;

public class LostSoulEntity extends AoAMeleeMob {
    public LostSoulEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.78125f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_LOST_SOUL_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_LOST_SOUL_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_LOST_SOUL_HURT.get();
    }

    @Override
    public CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEAD;
    }

    @Override
    protected void onAttack(Entity target) {
        if (target instanceof ServerPlayerEntity)
            PlayerUtil.consumeResource((ServerPlayerEntity)target, AoAResources.SPIRIT.get(), 30, true);
    }
}
