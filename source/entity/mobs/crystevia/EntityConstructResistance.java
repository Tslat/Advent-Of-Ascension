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

public class EntityConstructResistance extends AoAMeleeMob implements SpecialPropertyEntity {
    public static final float entityWidth = 0.625f;

    public EntityConstructResistance(World world) {
        super(world, entityWidth, 2.375f);

        mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 2f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.15;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 80;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 7;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.28;
    }

    @Override
    protected double getBaseArmour() {
        return 15;
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
        return LootSystemRegister.entityConstructOfResistance;
    }

    @Override
    public boolean isImmuneToExplosions() {
        return true;
    }

    @Override
    public void addPotionEffect(PotionEffect effect) {
        if (effect.getPotion() == MobEffects.RESISTANCE)
            effect.combine(new PotionEffect(effect.getPotion(), effect.getDuration(), effect.getAmplifier() + 1, effect.getIsAmbient(), effect.doesShowParticles()));

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
