package net.tslat.aoa3.entity.mobs.dustopia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.packet.PacketScreenOverlay;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PacketUtil;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityCrusilisk extends AoAMeleeMob implements HunterEntity {
    public static final float entityWidth = 0.75f;

    public EntityCrusilisk(World world) {
        super(world, entityWidth, 1.3125f);
    }

    @Override
    public float getEyeHeight() {
        return 1.35f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 250;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 25;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobCrusiliskLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobCrusiliskDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobCrusiliskHit;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(100 - lootingMod) == 0)
            dropItem(WeaponRegister.blasterBeamer, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinSilver, 1);
    }

    @Override
    public int getHunterReq() {
        return 95;
    }

    @Override
    public float getHunterXp() {
        return 1600;
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        if (target instanceof EntityLivingBase) {
            ((EntityLivingBase) target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 150, 30, true, false));

            if (target instanceof EntityPlayerMP)
                PacketUtil.network.sendTo(new PacketScreenOverlay(50, Enums.ScreenOverlays.BLOODY), (EntityPlayerMP)target);
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (rand.nextInt(100) == 0) {
            for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(16), PredicateUtil.IS_VULNERABLE_PLAYER)) {
                if (!pl.isSneaking())
                    pl.addVelocity(Math.signum(posX - pl.posX) * 1.129, 0, Math.signum(posZ - pl.posZ) * 1.129);

                world.playSound(null, posX, posY, posZ, SoundsRegister.mobCrusiliskScream, SoundCategory.HOSTILE, 1.0f, 1.0f);
            }
        }
    }
}
