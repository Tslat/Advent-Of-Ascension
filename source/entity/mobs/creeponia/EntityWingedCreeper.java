package net.tslat.aoa3.entity.mobs.creeponia;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;

import javax.annotation.Nullable;

public class EntityWingedCreeper extends EntityCreeper {
    public static final float entityWidth = 0.6f;

    public EntityWingedCreeper(World world) {
        super(world);

        setSize(entityWidth, 1.625f);
    }

    @Override
    public float getEyeHeight() {
        return 1.40625f;
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(45);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.1);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobCreepoidLiving;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobCreepoidDeath;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundsRegister.mobCreepoidHit;
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

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        EntityPlayer pl = world.getClosestPlayer(posX, posY, posZ, 15, false);

        if (pl == null || pl.capabilities.isCreativeMode)
            return;

        if (pl.posY > posY && ticksExisted % 3 == 0) {
            motionY += 0.3;
            motionX += MathHelper.clamp(pl.posX - posX, -0.05, 0.05);
            motionZ += MathHelper.clamp(pl.posZ - posZ, -0.05, 0.05);
        }
    }
}
