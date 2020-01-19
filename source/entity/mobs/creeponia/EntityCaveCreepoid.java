package net.tslat.aoa3.entity.mobs.creeponia;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityCaveCreepoid extends EntityCreeponiaCreeper implements SpecialPropertyEntity {
    public static final float entityWidth = 0.875f;

    public EntityCaveCreepoid(World world) {
        super(world, entityWidth, 1.6875f);

        mobProperties.add(Enums.MobProperties.EXPLOSION_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 1.40625f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.4f;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 65;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.27;
    }

    @Override
	public float getExplosionStrength() {
        return 3.2f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobCreepoidLiving;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobCreepoidDeath;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundsRegister.mobCreepoidHit;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityCaveCreepoid;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 30 && super.getCanSpawnHere();
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
        return source.isExplosion();
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (super.attackEntityFrom(source, amount)) {
            if (!world.isRemote && !source.isFireDamage() && rand.nextInt(3) == 0)
                WorldUtil.createExplosion(this, world, 2f);

            return true;
        }

        return false;
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
