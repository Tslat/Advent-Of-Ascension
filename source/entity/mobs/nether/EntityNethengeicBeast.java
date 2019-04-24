package net.tslat.aoa3.entity.mobs.nether;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.*;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityNethengeicBeast extends AoAMeleeMob implements HunterEntity {
    public static final float entityWidth = 1.0f;

    public EntityNethengeicBeast(World world) {
        super(world, entityWidth, 1.125f);

        this.isImmuneToFire = true;
        this.mobProperties.add(Enums.MobProperties.FIRE_IMMUNE);
        this.mobProperties.add(Enums.MobProperties.GUN_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 0.75f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 450;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 20;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Override
    public int getHunterReq() {
        return 65;
    }

    @Override
    public float getHunterXp() {
        return 560;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobNethengeicBeastLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobNethengeicBeastDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobNethengeicBeastHit;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundsRegister.heavyStep;
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source) {
        return source.isFireDamage() || EntityUtil.isGunDamage(source);
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if(rand.nextInt(40 - lootingMod) == 0) {
            switch (rand.nextInt(4)) {
                case 0:
                    dropItem(ArmourRegister.NethengeicBody, 1);
                    break;
                case 1:
                    dropItem(ArmourRegister.NethengeicBoots, 1);
                    break;
                case 2:
                    dropItem(ArmourRegister.NethengeicHelmet, 1);
                    break;
                case 3:
                    dropItem(ArmourRegister.NethengeicLegs, 1);
                    break;
            }
        }

        if (rand.nextInt(100 - lootingMod) == 0)
            dropItem(WeaponRegister.gunNethengeicSlugger, 1);

        if (rand.nextInt(100 - lootingMod) == 0)
            dropItem(WeaponRegister.swordNethengeic, 1);

        if (rand.nextInt(200 - lootingMod) == 0)
            dropItem(ItemRegister.upgradeKitNether, 1);

        if (rand.nextBoolean())
            dropItem(ItemRegister.tokensNether, 1 + rand.nextInt(2 + lootingMod));

        if (rand.nextInt(7) == 0)
            dropItem(Item.getItemFromBlock(BlockRegister.bannerNethengeic), 1);

        if (rand.nextInt(20 - lootingMod) == 0)
            dropItem(ItemRegister.nethengeicCallstone, 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinSilver, 1 + rand.nextInt(2 + lootingMod));
    }

    @Override
    protected void doMeleeEffect(Entity target) {
        target.setFire(10);
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
