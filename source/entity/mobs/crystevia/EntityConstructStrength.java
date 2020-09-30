package net.tslat.aoa3.entity.mobs.crystevia;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityConstructStrength extends AoAMeleeMob implements SpecialPropertyEntity {
    public static final float entityWidth = 1f;

    public EntityConstructStrength(World world) {
        super(world, entityWidth, 2.375f);

        mobProperties.add(Enums.MobProperties.RANGED_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 2.125f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.5;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 69;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 15;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Override
    protected double getBaseArmour() {
        return 3;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.MOB_CRYSTAL_CONSTRUCT_LIVING;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.MOB_CRYSTAL_CONSTRUCT_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.MOB_CRYSTAL_CONSTRUCT_HIT;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityConstructOfStrength;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (isEntityAlive() && getHealth() < getMaxHealth())
            heal(0.1f);
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
        return EntityUtil.isRangedDamage(source, this, damage);
    }

    @Override
    public void addPotionEffect(PotionEffect effect) {
        if (effect.getPotion() == MobEffects.STRENGTH)
            effect.combine(new PotionEffect(effect.getPotion(), effect.getDuration(), (effect.getAmplifier() + 1) * 4, effect.getIsAmbient(), effect.doesShowParticles()));

        super.addPotionEffect(effect);
    }

    @Override
    public boolean attackEntityAsMob(Entity target) {
        if (super.attackEntityAsMob(target)) {

            if (!world.isRemote && target instanceof EntityPlayer && ((EntityPlayer)target).getHealth() > 0 && isPotionActive(MobEffects.STRENGTH) && ItemUtil.findInventoryItem((EntityPlayer)target, new ItemStack(ItemRegister.BLANK_REALMSTONE), true, 1))
                ItemUtil.givePlayerItemOrDrop((EntityPlayer)target, new ItemStack(ItemRegister.IMMORTALLIS_REALMSTONE));

            return true;
        }

        return false;
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
