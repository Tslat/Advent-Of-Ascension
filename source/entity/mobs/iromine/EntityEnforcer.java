package net.tslat.aoa3.entity.mobs.iromine;

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

public class EntityEnforcer extends AoAMeleeMob implements SpecialPropertyEntity {
    public static final float entityWidth = 0.75f;

    public EntityEnforcer(World world) {
        super(world, entityWidth, 2.25f);

        mobProperties.add(Enums.MobProperties.MELEE_IMMUNE);
        mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 2.0625f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.4;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 89;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 10;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.25;
    }

    @Override
    protected double getBaseArmour() {
        return 4;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_ENFORCER_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_ENFORCER_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_ENFORCER_HIT;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityEnforcer;
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
        return EntityUtil.isMeleeDamage(source) || EntityUtil.isRangedDamage(source, this, damage);
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
