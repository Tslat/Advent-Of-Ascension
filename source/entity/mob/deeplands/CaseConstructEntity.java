package net.tslat.aoa3.entity.mob.deeplands;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
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
    protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        if (!level.isClientSide && player.getItemInHand(hand).getItem() == Item.byBlock(AoABlocks.DEEP_CRYSTAL.get())) {
            if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1))
                ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.CRYSTEVIA_REALMSTONE.get()));

            return ActionResultType.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }
}
