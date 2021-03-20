package net.tslat.aoa3.entity.mob.creeponia;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.mob.nether.SkeletalCowmanEntity;

import javax.annotation.Nullable;

public class KingCreeperEntity extends AoACreeponiaCreeper {
    public KingCreeperEntity(EntityType<? extends AoACreeponiaCreeper> entityType, World world) {
        super(entityType, world);

        fuseTime = 80;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.40625f;
    }

    @Override
	public float getExplosionStrength() {
        return 6f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_CREEPOID_AMBIENT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_CREEPOID_DEATH.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return AoASounds.ENTITY_CREEPOID_HURT.get();
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);

        if (level.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT) && cause.getEntity() instanceof SkeletalCowmanEntity)
            spawnAtLocation(AoAItems.MUSIC_DISC_OUTLAW.get(), 1);
    }
}
