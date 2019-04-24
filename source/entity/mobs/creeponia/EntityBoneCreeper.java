package net.tslat.aoa3.entity.mobs.creeponia;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class EntityBoneCreeper extends EntityCreeper {
    public static final float entityWidth = 0.5f;

    public EntityBoneCreeper(World world) {
        super(world);

        setSize(entityWidth, 1.625f);
    }

    @Override
    public float getEyeHeight() {
        return 1.4375f;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.95);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        if (rand.nextBoolean()) {
            return SoundsRegister.mobCreepoidLiving;
        }
        else {
            return SoundEvents.ENTITY_SKELETON_AMBIENT;
        }
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobCreepoidDeath;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 2 + rand.nextInt(5 + lootingModifier));

        if (wasRecentlyHit) {
            if (rand.nextBoolean())
                dropItem(ItemRegister.tokensCreeponia, 1 + rand.nextInt(3 + lootingModifier));

            if (rand.nextInt(4) == 0)
                dropItem(Item.getItemFromBlock(BlockRegister.bannerCreepoid), 1);
        }
    }
}
