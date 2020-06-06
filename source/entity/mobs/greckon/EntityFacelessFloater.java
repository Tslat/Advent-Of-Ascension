package net.tslat.aoa3.entity.mobs.greckon;

import net.minecraft.entity.Entity;
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

public class EntityFacelessFloater extends AoAMeleeMob {
    public static final float entityWidth = 0.75f;

    public EntityFacelessFloater(World world) {
        super(world, entityWidth, 2f);
    }

    @Override
    public float getEyeHeight() {
        return 1.8125f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 131d;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 12.5d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.27d;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_FACELESS_RUNNER_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_FACELESS_RUNNER_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_FACELESS_RUNNER_HIT;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityFacelessFloater;
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        if (target instanceof EntityPlayerMP)
            PacketUtil.network.sendTo(new PacketScreenOverlay(300, Enums.ScreenOverlays.PURPLE_FOG), (EntityPlayerMP)target);
    }
}
