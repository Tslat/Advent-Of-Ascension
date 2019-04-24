package net.tslat.aoa3.entity.mobs.greckon;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.packet.PacketScreenOverlay;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.PacketUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityFacelessFloater extends AoAMeleeMob implements HunterEntity {
    public static final float entityWidth = 0.75f;

    public EntityFacelessFloater(World world) {
        super(world, entityWidth, 2f);

        this.mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
        this.mobProperties.add(Enums.MobProperties.GUN_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 1.8125f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 70;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 9;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobFacelessRunnerLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobFacelessRunnerDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobFacelessRunnerHit;
    }

    @Override
    public int getHunterReq() {
        return 46;
    }

    @Override
    public float getHunterXp() {
        return 240;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(14 - lootingMod) == 0)
            dropItem(ItemRegister.darklyPowder, 1 + rand.nextInt(2 + lootingMod));

        if (rand.nextInt(7) == 0)
            dropItem(Item.getItemFromBlock(BlockRegister.bannerGhoul), 1);

        if (rand.nextBoolean())
            dropItem(ItemRegister.tokensGreckon, 1 + rand.nextInt(2 + lootingMod));
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source) {
        return EntityUtil.isGunDamage(source) || EntityUtil.isRangedDamage(source, this,1);
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        if (target instanceof EntityPlayerMP)
            PacketUtil.network.sendTo(new PacketScreenOverlay(300, Enums.ScreenOverlays.PURPLE_FOG), (EntityPlayerMP)target);
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
