package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityShyreKnight extends AoAMeleeMob {
    public static final float entityWidth = 0.6f;

    public EntityShyreKnight(World world) {
        super(world, entityWidth, 2f);
    }

    @Override
    public float getEyeHeight() {
        return 1.71875f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.35;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 140;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 18;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Override
    protected double getBaseArmour() {
        return 19;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_ANVIL_LAND;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.BLOCK_ANVIL_LAND;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityShyreKnight;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 35 && super.getCanSpawnHere();
    }
}
