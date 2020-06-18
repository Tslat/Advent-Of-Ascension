package net.tslat.aoa3.entity.mobs.deeplands;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityCaseConstruct extends AoAMeleeMob implements SpecialPropertyEntity {
    public static final float entityWidth = 1.2f;

    public EntityCaseConstruct(World world) {
        super(world, entityWidth, 2.125f);

        mobProperties.add(Enums.MobProperties.FIRE_IMMUNE);
        isImmuneToFire = true;
    }

    @Override
    public float getEyeHeight() {
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
        return LootSystemRegister.entityCaseConstruct;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 120 && super.getCanSpawnHere();
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (!world.isRemote && player.getHeldItem(hand).getItem() == Item.getItemFromBlock(BlockRegister.DEEP_CRYSTAL)) {
            if (ItemUtil.findInventoryItem(player, new ItemStack(ItemRegister.BLANK_REALMSTONE), true, 1))
                ItemUtil.givePlayerItemOrDrop(player, new ItemStack(ItemRegister.CRYSTEVIA_REALMSTONE));

            return true;
        }

        return super.processInteract(player, hand);
    }

    @Override
    protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
        return source.isFireDamage();
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
