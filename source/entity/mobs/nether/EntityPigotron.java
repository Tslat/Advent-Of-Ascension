package net.tslat.aoa3.entity.mobs.nether;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
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

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class EntityPigotron extends AoAMeleeMob implements SpecialPropertyEntity {
    public static final float entityWidth = 0.875f;

    public EntityPigotron(EntityFakePigman fakePigman) {
        this(fakePigman.world);

        setPositionAndRotation(fakePigman.posX, fakePigman.posY, fakePigman.posZ, fakePigman.rotationYaw, fakePigman.rotationPitch);
    }

    public EntityPigotron(World world) {
        super(world, entityWidth, 2.25f);

        this.isImmuneToFire = true;
        this.mobProperties.add(Enums.MobProperties.FIRE_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 2.03125f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8d;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 80;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 8;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_PIGOTRON_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_PIGOTRON_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_PIGOTRON_HIT;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityPigotron;
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

    @Override
    protected void doMeleeEffect(Entity target) {
        if (target instanceof EntityLivingBase) {
            double resist = 1;
            IAttributeInstance attrib = ((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE);

            if (attrib != null)
                resist -= attrib.getAttributeValue();

            target.addVelocity(motionX * 10.5 * resist, motionY * 0.5 * resist, motionZ * 10.5 * resist);
            target.velocityChanged = true;
        }
    }
}
