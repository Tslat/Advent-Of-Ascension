package net.tslat.aoa3.entity.mobs.nether;

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

public class EntityNethengeicBeast extends AoAMeleeMob implements SpecialPropertyEntity {
    public static final float entityWidth = 1.0f;

    public EntityNethengeicBeast(World world) {
        super(world, entityWidth, 1.125f);

        this.isImmuneToFire = true;
        this.mobProperties.add(Enums.MobProperties.FIRE_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 0.75f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 65d;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 7d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.29d;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobNethengeicBeastLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobNethengeicBeastDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobNethengeicBeastHit;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundsRegister.heavyStep;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityNethengeicBeast;
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
        return source.isFireDamage();
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
