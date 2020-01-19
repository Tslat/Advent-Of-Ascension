package net.tslat.aoa3.entity.mobs.greckon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.boss.penumbra.EntityPenumbra;

import javax.annotation.Nullable;

public class EntityShifter extends AoAMeleeMob {
    public static final float entityWidth = 0.75f;
    private int cloakCooldown = 160;

    public EntityShifter(World world) {
        super(world, entityWidth, 1.5625f);
    }

    @Override
    public float getEyeHeight() {
        return 1.46875f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 130;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 15;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobShifterLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobShifterDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobShifterHit;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityShifter;
    }

    @Override
    public void setAttackTarget(@Nullable EntityLivingBase target) {
        if (target instanceof EntityPenumbra)
            return;

        super.setAttackTarget(target);
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (cloakCooldown > 0)
            cloakCooldown--;

        if (cloakCooldown == 0 && getAttackTarget() != null) {
            cloakCooldown = 160;

            addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 40, 2, true, true));
            world.playSound(null, posX, posY, posZ, SoundsRegister.mobShifterLiving, SoundCategory.HOSTILE, 1.0f, 1.0f);
        }
    }
}
