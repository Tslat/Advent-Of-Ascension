package net.tslat.aoa3.entity.mobs.gardencia;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityVineWizardShot;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.misc.AoAAttributes;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityVineWizard extends AoARangedMob implements SpecialPropertyEntity {
    public static final float entityWidth = 0.6f;
    private boolean candiedWater = false;

    public EntityVineWizard(World world) {
        super(world, entityWidth, 2.125f);

        mobProperties.add(Enums.MobProperties.MAGIC_IMMUNE);
    }

    @Override
    public float getEyeHeight() {
        return 1.875f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.15;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 90;
    }

    @Override
    public double getBaseProjectileDamage() {
        return 12;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.207;
    }

    @Override
    protected SoundEvent getStepSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getShootSound() {
        return SoundsRegister.shotVineWizardFire;
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable() {
        return LootSystemRegister.entityVineWizard;
    }

    @Override
    public void attackEntityWithRangedAttack(@Nonnull EntityLivingBase target, float bowDamageFactor) {
        world.spawnEntity(new EntityVineWizardShot(this, target, Enums.MobProjectileType.MAGIC));
    }

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
        return null;
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
        return EntityUtil.isMagicDamage(source, this, damage);
    }

    @Override
    protected void onInsideBlock(IBlockState state) {
        if (state.getBlock() == BlockRegister.candiedWater) {
            if (!candiedWater) {
                EntityUtil.applyAttributeModifierSafely(this, SharedMonsterAttributes.MAX_HEALTH, AoAAttributes.GARDENCIA_CANDIED_WATER_BUFF);
                setHealth(getHealth() * 1.5f);

                candiedWater = true;
            }
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        if (candiedWater)
            compound.setBoolean("AoACandiedWater", true);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (compound.hasKey("AoACandiedWater"))
            candiedWater = true;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (isEntityAlive() && getHealth() < getMaxHealth()) {
            if (isInWater()) {
                heal(0.2f);
            }
            else if (world.isRainingAt(getPosition())) {
                heal(0.1f);
            }
        }
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (!world.isRemote && candiedWater && cause.getTrueSource() instanceof EntityPlayer && ItemUtil.consumeItem((EntityPlayer)cause.getTrueSource(), new ItemStack(ItemRegister.realmstoneBlank)))
            ItemUtil.givePlayerItemOrDrop((EntityPlayer)cause.getTrueSource(), new ItemStack(ItemRegister.realmstoneBorean));
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
