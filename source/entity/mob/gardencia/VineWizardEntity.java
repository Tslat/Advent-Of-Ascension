package net.tslat.aoa3.entity.mob.gardencia;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectile.mob.VineWizardShotEntity;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class VineWizardEntity extends AoARangedMob {
    private  static final AttributeModifier CANDIED_WATER_BUFF = new AttributeModifier(UUID.fromString("d5356e33-40b6-4515-a37b-4377f911f703"), "AoAGardenciaCandiedWaterBuff", 50, AttributeModifier.Operation.ADDITION);
    private boolean candiedWater = false;

    public VineWizardEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.875f;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState block) {}

    @Nullable
    @Override
    protected SoundEvent getShootSound() {
        return AoASounds.ENTITY_VINE_WIZARD_SHOOT.get();
    }

    @Override
    public void performRangedAttack(@Nonnull LivingEntity target, float bowDamageFactor) {
        level.addFreshEntity(new VineWizardShotEntity(this, target, BaseMobProjectile.Type.MAGIC));
    }

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
        return null;
    }

    @Override
    protected void onInsideBlock(BlockState state) {
        if (state.getBlock() == AoABlocks.CANDIED_WATER.get()) {
            if (!candiedWater) {
                EntityUtil.applyAttributeModifierSafely(this, Attributes.MAX_HEALTH, CANDIED_WATER_BUFF, false);
                setHealth(getHealth() * 1.5f);

                candiedWater = true;
            }
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT compound) {
        super.addAdditionalSaveData(compound);

        if (candiedWater)
            compound.putBoolean("AoACandiedWater", true);
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT compound) {
        super.readAdditionalSaveData(compound);

        candiedWater = compound.contains("AoACandiedWater");
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (isAlive() && getHealth() < getMaxHealth()) {
            if (isInWater()) {
                heal(0.2f);
            }
            else if (level.isRainingAt(blockPosition())) {
                heal(0.1f);
            }
        }
    }

    @Override
    public void die(DamageSource cause) {
        super.die(cause);

        if (!level.isClientSide && candiedWater && cause.getEntity() instanceof PlayerEntity && ItemUtil.findInventoryItem((PlayerEntity)cause.getEntity(), new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
            ItemUtil.givePlayerItemOrDrop((PlayerEntity)cause.getEntity(), new ItemStack(AoAItems.LBOREAN_REALMSTONE.get()));
    }
}
