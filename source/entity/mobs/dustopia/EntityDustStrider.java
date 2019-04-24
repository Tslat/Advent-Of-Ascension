package net.tslat.aoa3.entity.mobs.dustopia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.packet.PacketScreenOverlay;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PacketUtil;

import javax.annotation.Nullable;

public class EntityDustStrider extends AoAMeleeMob {
    public static final float entityWidth = 0.8f;

    public EntityDustStrider(EntityDuston duston) {
        this(duston.world);

        setLocationAndAngles(duston.posX, duston.posY + 0.5, duston.posZ, duston.rotationYaw, duston.rotationPitch);
        fallDistance = -10;
    }

    public EntityDustStrider(World world) {
        super(world, entityWidth, 1.25f);
    }

    @Override
    public float getEyeHeight() {
        return 0.8125f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.3;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 50;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 4;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobDustStriderLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobDustStriderDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobDustStriderHit;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(30 - lootingMod) == 0)
            dropItem(ItemRegister.unchargedOrb, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        if (target instanceof EntityPlayerMP)
            PacketUtil.network.sendTo(new PacketScreenOverlay(60, Enums.ScreenOverlays.DARKNESS), (EntityPlayerMP)target);
    }
}
