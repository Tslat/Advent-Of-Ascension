package net.tslat.aoa3.entity.mobs.voxponds;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityExoid extends AoAMeleeMob {
    public static final float entityWidth = 0.5625f;
    private int tier = 1;

    public EntityExoid(World world, int tier) {
        this(world);
        this.tier = tier;
    }

    public EntityExoid(World world) {
        super(world, entityWidth, 2.3125f);
    }

    @Override
    public float getEyeHeight() {
        return 2f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 100;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 14;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobGadgetoidLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobGadgetoidDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobGadgetoidHit;
    }

    @Override
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (ticksExisted >= 900) {
            world.createExplosion(this, posX, posY, posZ, 3, false);
            setDead();
        }
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (tier >= 5) {
            dropItem(ItemRegister.doomStone, 1);

            if (rand.nextInt(3) == 0)
                dropItem(ItemRegister.realmstoneVoxPonds, 1);
        }
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (!world.isRemote && tier < 5) {
            EntityExoid exoid = new EntityExoid(world, tier + 1);
            int x = rand.nextBoolean() ? -45 : 45;
            int z = rand.nextBoolean() ? -45 : 45;

            exoid.setLocationAndAngles(posX + x, posY + 3, posZ + z, rotationYaw, rotationPitch);
            world.spawnEntity(exoid);
        }
    }
}
