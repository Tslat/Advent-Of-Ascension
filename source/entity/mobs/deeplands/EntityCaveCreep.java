package net.tslat.aoa3.entity.mobs.deeplands;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityCaveCreep extends AoAMeleeMob {
    public static final float entityWidth = 1f;

    public EntityCaveCreep(World world) {
        super(world, entityWidth, 1.5f);
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.6d;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 65;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 7.5d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.295d;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobCaveCreepLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobCaveCreepDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobCaveCreepHit;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityCaveCreep;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 120 && super.getCanSpawnHere();
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!world.isRemote && rand.nextInt(250) == 0)
            heal(25);
    }
}
