package net.tslat.aoa3.entity.mobs.nether;

import net.minecraft.entity.Entity;
import net.minecraft.init.MobEffects;
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

public class EntityHellcat extends AoAMeleeMob implements SpecialPropertyEntity {
    public static final float entityWidth = 1.7f;

    public EntityHellcat(World world) {
        super(world, entityWidth, 2.1875f);

        this.isImmuneToFire = true;
        this.mobProperties.add(Enums.MobProperties.FIRE_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 2f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8d;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 85d;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 7.5d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.28d;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_HELLCAT_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_HELLCAT_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_HELLCAT_HIT;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundsRegister.ENTITY_GENERIC_DINO_STEP;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityHellcat;
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
        return source.isFireDamage();
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 0, 0, true, true));
    }

    @Override
    protected double getSpawnChanceFactor() {
        return 0.5f;
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
