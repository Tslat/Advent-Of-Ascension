package net.tslat.aoa3.entity.mobs.crystevia;

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

public class EntityConstructMind extends AoAMeleeMob implements SpecialPropertyEntity {
    public static final float entityWidth = 2f;

    public EntityConstructMind(World world) {
        super(world, entityWidth, 2f);

        mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 1.75f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.4;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 74;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 8;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
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
        return LootSystemRegister.entityConstructOfMind;
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
