package net.tslat.aoa3.entity.mobs.creeponia;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class EntityWingedCreeper extends EntityCreeponiaCreeper {
    public static final float entityWidth = 0.6f;

    public EntityWingedCreeper(World world) {
        super(world, entityWidth, 1.625f);
    }

    @Override
    public float getEyeHeight() {
        return 1.40625f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.15d;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 55d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.29d;
    }

    @Override
    public float getExplosionStrength() {
        return 3.1f;
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

    @Override
    public void fall(float distance, float damageMultiplier) {}

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityWingedCreeper;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        EntityPlayer pl = world.getClosestPlayer(posX, posY, posZ, 15, false);

        if (pl == null || pl.capabilities.isCreativeMode)
            return;

        if (pl.posY > posY && ticksExisted % 3 == 0) {
            motionY += 0.3;
            motionX += MathHelper.clamp(pl.posX - posX, -0.05, 0.05);
            motionZ += MathHelper.clamp(pl.posZ - posZ, -0.05, 0.05);
        }
    }
}
