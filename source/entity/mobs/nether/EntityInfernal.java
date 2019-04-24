package net.tslat.aoa3.entity.mobs.nether;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

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
        this.mobProperties.add(Enums.MobProperties.GUN_IMMUNE);
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
        return 190;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 35;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobInfernalLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobInfernalHit;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobInfernalHit;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundsRegister.veryHeavyStep;
    }

    @Override
    protected int getSpawnChanceFactor() {
        return 5;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.tokensNether, 1 + rand.nextInt(2 + lootingMod));

        if (rand.nextInt(4) == 0)
            dropItem(ArmourRegister.InfernalBody, 1);

        if (rand.nextInt(4) == 0)
            dropItem(ArmourRegister.InfernalLegs, 1);

        if (rand.nextInt(4) == 0)
            dropItem(ArmourRegister.InfernalHelmet, 1);

        if (rand.nextInt(4) == 0)
            dropItem(ArmourRegister.InfernalBoots, 1);

        if (rand.nextInt(200 - lootingMod) == 0)
            dropItem(ItemRegister.upgradeKitNether, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.ingotEmberstone, 2 + rand.nextInt(3 + lootingMod));
        dropItem(ItemRegister.coinSilver, 10 + rand.nextInt(5 + lootingMod));
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source) {
        return source.isFireDamage() || EntityUtil.isGunDamage(source);
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        if (target instanceof EntityLivingBase) {
            double resist = 1;
            IAttributeInstance attrib = ((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE);

            if (attrib != null)
                resist -= attrib.getAttributeValue();

            ((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 50, 0, true, false));
            target.addVelocity(motionX * 10.5 * resist, motionY * 0.5 * resist, motionZ * 10.5 * resist);
            target.velocityChanged = true;
        }
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
