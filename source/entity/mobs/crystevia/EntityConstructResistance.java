package net.tslat.aoa3.entity.mobs.crystevia;

import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityConstructResistance extends AoAMeleeMob implements SpecialPropertyEntity {
    public static final float entityWidth = 0.625f;

    public EntityConstructResistance(World world) {
        super(world, entityWidth, 2.375f);

        mobProperties.add(Enums.MobProperties.FIRE_IMMUNE);
        mobProperties.add(Enums.MobProperties.MAGIC_IMMUNE);
        mobProperties.add(Enums.MobProperties.EXPLOSION_IMMUNE);

        isImmuneToFire = true;
    }

    @Override
    public float getEyeHeight() {
        return 2f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 150;
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
        return SoundsRegister.mobCrystalConstructLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobCrystalConstructDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobCrystalConstructHit;
    }

    @Override
    public boolean isImmuneToExplosions() {
        return true;
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source) {
        if (source.isFireDamage() || source.isExplosion())
            return true;

        return EntityUtil.isMagicDamage(source, this, 1);

    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(3) == 0)
            dropItem(ItemRegister.tokensCrystevia, 1 + rand.nextInt(2 + lootingMod));

        if (rand.nextBoolean())
            dropItem(ItemRegister.gemstonesWhite, 3);

        if (rand.nextInt(6) == 0)
            dropItem(Item.getItemFromBlock(BlockRegister.bannerCrystal), 1);
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
