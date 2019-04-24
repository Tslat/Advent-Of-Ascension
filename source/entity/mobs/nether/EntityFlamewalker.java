package net.tslat.aoa3.entity.mobs.nether;

import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityFlamewalker extends AoAMeleeMob implements HunterEntity {
    public static final float entityWidth = 0.8f;

    public EntityFlamewalker(World world) {
        super(world, entityWidth, 2.25f);

        this.isImmuneToFire = true;
        this.mobProperties.add(Enums.MobProperties.FIRE_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 2.03125f;
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
        return 4;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Override
    public int getHunterReq() {
        return 1;
    }

    @Override
    public float getHunterXp() {
        return 17;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobFlamewalkerLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobFlamewalkerDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobFlamewalkerHit;
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source) {
        return source.isFireDamage();
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(35 - lootingMod) == 0)
            dropItem(WeaponRegister.swordUltraflame, 1);

        if (rand.nextInt(3) == 0)
            dropItem(ItemRegister.tokensNether, 1 + rand.nextInt(3 + lootingMod));
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (!world.isRemote && getHealth() > 7 && world.getGameRules().getBoolean("mobGriefing")) {
            if (world.getBlockState(getPosition().down()).getBlock() == Blocks.NETHERRACK && world.getBlockState(getPosition()).getMaterial().isReplaceable())
                world.setBlockState(getPosition(), Blocks.FIRE.getDefaultState());
        }
    }
}
