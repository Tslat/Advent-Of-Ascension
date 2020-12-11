package net.tslat.aoa3.entity.mob.deeplands;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoABlocks;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.ItemUtil;

import javax.annotation.Nullable;

public class CaseConstructEntity extends AoAMeleeMob {
    public CaseConstructEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.96875f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.2f;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 90;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 6f;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.25d;
    }

    @Override
    protected double getBaseArmour() {
        return 2;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_CRYSTAL_CONSTRUCT_HURT.get();
    }

    @Override
    protected int getMaxSpawnHeight() {
        return 120;
    }

    @Override
    protected boolean processInteract(PlayerEntity player, Hand hand) {
        if (!world.isRemote && player.getHeldItem(hand).getItem() == Item.getItemFromBlock(AoABlocks.DEEP_CRYSTAL.get())) {
            if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
                ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.CRYSTEVIA_REALMSTONE.get()));

            return true;
        }

        return super.processInteract(player, hand);
    }
}
