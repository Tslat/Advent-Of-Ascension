package net.tslat.aoa3.entity.mobs.voxponds;

import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityToxxulous extends AoAMeleeMob implements SpecialPropertyEntity {
    public static final float entityWidth = 0.75f;

    public EntityToxxulous(World world) {
        super(world, entityWidth, 1.125f);

        mobProperties.add(Enums.MobProperties.STATUS_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 1.1875f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 95;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 12.5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.295;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_TOXXULOUS_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_TOXXULOUS_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_TOXXULOUS_HIT;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityToxxulous;
    }

    @Override
    public boolean canBeHitWithPotion() {
        return false;
    }

    @Override
    public void addPotionEffect(PotionEffect effect) {}

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
