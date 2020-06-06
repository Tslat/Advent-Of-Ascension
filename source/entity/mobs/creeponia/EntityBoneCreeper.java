package net.tslat.aoa3.entity.mobs.creeponia;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityBoneCreeper extends EntityCreeponiaCreeper implements SpecialPropertyEntity {
    public static final float entityWidth = 0.5f;

    public EntityBoneCreeper(World world) {
        super(world, entityWidth, 1.625f);

        mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 1.4375f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0d;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 45d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.3d;
    }

    @Override
	public float getExplosionStrength() {
        return 2.75f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (rand.nextBoolean()) {
            return SoundsRegister.MOB_CREEPOID_LIVING;
        }
        else {
            return SoundEvents.ENTITY_SKELETON_AMBIENT;
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_CREEPOID_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityBoneCreeper;
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
