package net.tslat.aoa3.entity.mobs.nether;

import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityFakePigman extends AoAMeleeMob {
    public static final float entityWidth = 0.6f;

    public EntityFakePigman(World world) {
        super(world, entityWidth, 1.95f);
        isImmuneToFire = true;
        setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.GOLDEN_SWORD));
    }

    @Override
    public float getEyeHeight() {
        return 1.74f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 20;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 6;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (!world.isRemote && super.attackEntityFrom(source, amount)) {
            boolean transform = false;

            if (source.getTrueSource() instanceof EntityPlayer) {
                transform = true;
            }
            else if (source.getTrueSource() instanceof EntityTameable && ((EntityTameable)source.getTrueSource()).getOwner() instanceof EntityPlayer) {
                transform = true;
            }

            if (transform) {
                EntityPigotron pigotron = new EntityPigotron(this);

                world.spawnEntity(pigotron);
                world.playSound(null, posX, posY, posZ, SoundsRegister.MOB_PIGOTRON_APPEAR, SoundCategory.HOSTILE, 1.0f, 1.0f);
                setDead();
            }
        }

        return super.attackEntityFrom(source, amount);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_PIG_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_PIG_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_ZOMBIE_PIG_HURT;
    }

    @Override
    protected double getSpawnChanceFactor() {
        return 0.5f;
    }
}
