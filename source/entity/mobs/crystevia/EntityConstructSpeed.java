package net.tslat.aoa3.entity.mobs.crystevia;

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
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityConstructSpeed extends AoAMeleeMob implements SpecialPropertyEntity {
    public static final float entityWidth = 0.625f;

    public EntityConstructSpeed(World world) {
        super(world, entityWidth, 2.1875f);
        setSlipperyMovement();
        setAIMoveSpeed(1.6f);

        mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 2f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 58;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 7.5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.31;
    }

    @Override
    protected double getBaseArmour() {
        return 3;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_CRYSTAL_CONSTRUCT_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_CRYSTAL_CONSTRUCT_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_CRYSTAL_CONSTRUCT_HIT;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityConstructOfSpeed;
    }

    @Override
    public void addPotionEffect(PotionEffect effect) {
        if (effect.getPotion() == MobEffects.SPEED)
            effect.combine(new PotionEffect(effect.getPotion(), effect.getDuration(), (effect.getAmplifier() + 1) * 2, effect.getIsAmbient(), effect.doesShowParticles()));

        super.addPotionEffect(effect);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (isEntityAlive() && getHealth() < getMaxHealth())
            heal(0.1f);
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
        return EntityUtil.isRangedDamage(source, this, damage);
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
