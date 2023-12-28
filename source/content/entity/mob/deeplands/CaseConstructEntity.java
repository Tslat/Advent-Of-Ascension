package net.tslat.aoa3.content.entity.mob.deeplands;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.ItemUtil;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;


public class CaseConstructEntity extends AoAMeleeMob<CaseConstructEntity> {
    public CaseConstructEntity(EntityType<? extends CaseConstructEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
        return 12.5f / 16f;
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
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!level().isClientSide && player.getItemInHand(hand).getItem() == Item.byBlock(AoABlocks.DEEP_CRYSTAL.get())) {
            if (ItemUtil.findInventoryItem(player, new ItemStack(AoAItems.BLANK_REALMSTONE.get()), true, 1)) {
                ItemUtil.givePlayerItemOrDrop(player, new ItemStack(AoAItems.CRYSTEVIA_REALMSTONE.get()));

                if (!player.isCreative())
                    player.getItemInHand(hand).shrink(1);
            }

            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(DefaultAnimations.genericWalkIdleController(this));
    }
}
