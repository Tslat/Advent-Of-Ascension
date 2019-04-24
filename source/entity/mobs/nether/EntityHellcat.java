package net.tslat.aoa3.entity.mobs.nether;

import net.minecraft.entity.Entity;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
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
        return 1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 80;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 6;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobHellcatLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobHellcatDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobHellcatHit;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundsRegister.dinoStep;
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source) {
        return source.isFireDamage();
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextBoolean())
            dropItem(ItemRegister.tokensNether, 1 + rand.nextInt(3 + lootingMod));
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.fieryChops, 2 + rand.nextInt(3 + lootingMod));
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 0, 0, true, true));
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
