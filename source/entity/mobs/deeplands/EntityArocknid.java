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
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityArocknid extends AoAMeleeMob implements HunterEntity {
    public static final float entityWidth = 1.7f;
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(EntityMushroomSpider.class, DataSerializers.BYTE);

    public EntityArocknid(World world) {
        super(world, entityWidth, 1.3125f);
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
        return 0.59375f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.8;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 25;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 9;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.2875;
    }

    @Override
    public int getHunterReq() {
        return 68;
    }

    @Override
    public float getHunterXp() {
        return 700;
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
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ARTHROPOD;
    }

    @Override
    public boolean getCanSpawnHere() {
        return posY < 120 && super.getCanSpawnHere();
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinSilver, 1 + rand.nextInt(2 + lootingMod));

        if (rand.nextBoolean()) {
            dropItem(ItemRegister.runeWither, 4 + (2 * rand.nextInt(4 + lootingMod)));
        }
        else {
            dropItem(ItemRegister.runeFire, 4 + (2 * rand.nextInt(4 + lootingMod)));
        }
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

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
