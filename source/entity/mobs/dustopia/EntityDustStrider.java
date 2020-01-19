package net.tslat.aoa3.entity.mobs.dustopia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.packet.PacketScreenOverlay;
import net.tslat.aoa3.common.registration.LootSystemRegister;
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
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 110;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 11.5;
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

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityDustStrider;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        if (target instanceof EntityPlayerMP)
            PacketUtil.network.sendTo(new PacketScreenOverlay(60, Enums.ScreenOverlays.DARKNESS), (EntityPlayerMP)target);
    }
}
