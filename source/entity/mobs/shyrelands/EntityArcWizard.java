package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityShyreBeam;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityArcWizard extends AoARangedMob implements SpecialPropertyEntity {
    public static final float entityWidth = 0.6f;

    public EntityArcWizard(World world) {
        super(world, entityWidth, 2.3f);

        mobProperties.add(Enums.MobProperties.MAGIC_IMMUNE);
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1d;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 148d;
    }

    @Override
    public double getBaseProjectileDamage() {
        return 16.5d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.207;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_ARC_WIZARD_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_ARC_WIZARD_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_ARC_WIZARD_HIT;
    }

    @Nullable
    @Override
    protected SoundEvent getShootSound() {
        return SoundsRegister.ARC_WIZARD_SHOOT;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityArcWizard;
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
        return EntityUtil.isMagicDamage(source, this, damage);
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 35 && super.getCanSpawnHere();
    }

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
        return new EntityShyreBeam(this, Enums.MobProjectileType.MAGIC);
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
