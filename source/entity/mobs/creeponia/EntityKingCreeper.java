package net.tslat.aoa3.entity.mobs.creeponia;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class EntityKingCreeper extends EntityCreeper {
    public static final float entityWidth = 0.6f;

    public EntityKingCreeper(World world) {
        super(world);

        setSize(entityWidth, 1.9375f);
    }

    @Override
    public float getEyeHeight() {
        return 1.40625f;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(500);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.95);
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

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        dropItem(ItemRegister.coinGold, 1 + rand.nextInt(2 + lootingModifier));

        if (wasRecentlyHit) {
            dropItem(ItemRegister.realmstoneCreeponia, 1);
        }
    }
}
