package net.tslat.aoa3.entity.mobs.deeplands;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateClimber;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.mobs.mysterium.EntityMushroomSpider;

import javax.annotation.Nullable;

public class EntityRockCrawler extends AoAMeleeMob {
    public static final float entityWidth = 0.875f;
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityMushroomSpider.class, DataSerializers.BYTE);

    public EntityRockCrawler(World world) {
        super(world, entityWidth, 1.9375f);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(CLIMBING, (byte)0);
    }

    @Override
    protected PathNavigate createNavigator(World world) {
        return new PathNavigateClimber(this, world);
    }

    @Override
    public float getEyeHeight() {
        return 1.6875f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 40;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 5;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobCaveBugLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobCaveBugDeath;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobCaveBugHit;
    }

    @Override
    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_SPIDER_STEP;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 120 && super.getCanSpawnHere();
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextBoolean())
            dropItem(ItemRegister.tokensDeeplands, 1 + rand.nextInt(2 + lootingMod));
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!world.isRemote)
            setBesideClimbableBlock(this.collidedHorizontally);
    }

    @Override
    public boolean isOnLadder() {
        return isBesideClimbableBlock();
    }

    public boolean isBesideClimbableBlock() {
        return (this.dataManager.get(CLIMBING) & 1) != 0;
    }

    public void setBesideClimbableBlock(boolean climbing) {
        byte climbingBit = this.dataManager.get(CLIMBING);

        if (climbing) {
            climbingBit = (byte)(climbingBit | 1);
        }
        else {
            climbingBit = (byte)(climbingBit & -2);
        }

        this.dataManager.set(CLIMBING, climbingBit);
    }
}
