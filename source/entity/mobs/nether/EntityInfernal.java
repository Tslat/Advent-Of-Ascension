package net.tslat.aoa3.entity.mobs.nether;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
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
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class EntityInfernal extends AoAMeleeMob implements SpecialPropertyEntity {
    public static final float entityWidth = 1.125f;

    public EntityInfernal(World world) {
        super(world, entityWidth, 1.875f);

        this.isImmuneToFire = true;
        this.mobProperties.add(Enums.MobProperties.FIRE_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 1.71875f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 95d;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 8.5d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.25d;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_INFERNAL_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_INFERNAL_HIT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_INFERNAL_HIT;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundsRegister.VERY_HEAVY_STEP;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityInfernal;
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
        return source.isFireDamage();
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        if (target instanceof EntityLivingBase) {
            double resist = 1;
            IAttributeInstance attrib = ((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE);

            if (attrib != null)
                resist -= attrib.getAttributeValue();

            target.setFire(5);
            target.addVelocity(motionX * 10.5 * resist, motionY * 0.5 * resist, motionZ * 10.5 * resist);
            target.velocityChanged = true;
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        boolean success = super.attackEntityFrom(source, amount);

        if (success && EntityUtil.isMeleeDamage(source) && (!(source.getTrueSource() instanceof EntityPlayer) || PlayerUtil.shouldPlayerBeAffected((EntityPlayer)source.getTrueSource())))
            source.getTrueSource().setFire(5);

        return success;
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
