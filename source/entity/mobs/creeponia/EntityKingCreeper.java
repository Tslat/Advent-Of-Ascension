package net.tslat.aoa3.entity.mobs.creeponia;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class EntityKingCreeper extends EntityCreeponiaCreeper {
    public static final float entityWidth = 0.6f;

    public EntityKingCreeper(World world) {
        super(world, entityWidth, 1.9375f);

        fuseTime = 80;
    }

    @Override
    public float getEyeHeight() {
        return 1.40625f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1d;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 85d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.23d;
    }

    @Override
	public float getExplosionStrength() {
        return 6f;
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

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityKingCreeper;
    }
}
