package net.tslat.aoa3.entity.mobs.greckon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.packet.PacketScreenOverlay;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PacketUtil;

import javax.annotation.Nullable;

public class EntityGrillface extends AoAMeleeMob {
    public static final float entityWidth = 0.6875f;

    private int scareCooldown = 0;

    public EntityGrillface(World world) {
        super(world, entityWidth, 2.25f);
    }

    @Override
    public float getEyeHeight() {
        return 2f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.2;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 131;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 16;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobGrillfaceLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobGrillfaceDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobGrillfaceHit;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityGrillface;
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        if (target instanceof EntityPlayerMP && getRevengeTarget() == null && scareCooldown <= 0) {
            world.playSound(null, posX, posY, posZ, SoundsRegister.mobGrillfaceScare, SoundCategory.HOSTILE, 1.0f, 1.0f);
            PacketUtil.network.sendTo(new PacketScreenOverlay(20, Enums.ScreenOverlays.GRILLFACE), (EntityPlayerMP)target);

            scareCooldown = 100;
        }
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (scareCooldown > 0)
            scareCooldown--;
    }
}
