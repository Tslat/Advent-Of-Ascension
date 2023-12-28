package net.tslat.aoa3.content.entity.mob.dustopia;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.PlayerUtil;
import org.jetbrains.annotations.Nullable;


public class LostSoulEntity extends AoAMeleeMob<LostSoulEntity> {
    public LostSoulEntity(EntityType<? extends LostSoulEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
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
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    protected void onAttack(Entity target) {
        if (target instanceof ServerPlayer)
            PlayerUtil.consumeResource((ServerPlayer)target, AoAResources.SPIRIT.get(), 30, true);
    }
}
