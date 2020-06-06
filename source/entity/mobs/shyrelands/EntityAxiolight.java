package net.tslat.aoa3.entity.mobs.shyrelands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;
import java.util.List;

public class EntityAxiolight extends AoAMeleeMob {
    public static final float entityWidth = 0.7f;

    public EntityAxiolight(World world) {
        super(world, entityWidth, 2.4375f);
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 167;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 15.5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 35 && super.getCanSpawnHere();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_AXIOLIGHT_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_AXIOLIGHT_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_AXIOLIGHT_HIT;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityAxiolight;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        List<EntityPlayer> playerList = world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(12), PredicateUtil.IS_VULNERABLE_PLAYER);

        if (playerList.isEmpty())
            addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, -1, 1, true, true));
    }
}
