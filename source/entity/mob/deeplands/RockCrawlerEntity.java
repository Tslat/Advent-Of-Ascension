package net.tslat.aoa3.entity.mob.deeplands;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.ClimberPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minion.AoAMinion;

import javax.annotation.Nullable;

public class RockCrawlerEntity extends AoAMeleeMob {
    private static final DataParameter<Byte> CLIMBING = EntityDataManager.<Byte>createKey(RockCrawlerEntity.class, DataSerializers.BYTE);

    public RockCrawlerEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(CLIMBING, (byte)0);
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new SwimGoal(this));
        goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.4f));
        goalSelector.addGoal(4, new MeleeAttackGoal(this, 1, true));
        goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1));
        goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8f));
        goalSelector.addGoal(8, new LookRandomlyGoal(this));
        targetSelector.addGoal(1, new NearestAttackableTargetGoal<AoAMinion>(this, AoAMinion.class, true));
        targetSelector.addGoal(2, new HurtByTargetGoal(this));
        targetSelector.addGoal(3, new NearestAttackableTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
    }

    @Override
    protected PathNavigator createNavigator(World world) {
        return new ClimberPathNavigator(this, world);
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.6875f;
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0.1d;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 70;
    }

    @Override
    protected double getBaseMeleeDamage() {
        return 7d;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.29d;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_CAVE_BUG_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_CAVE_BUG_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_CAVE_BUG_HURT.get();
    }

    @Override
    protected SoundEvent getStepSound(BlockPos pos, BlockState blockState) {
        return SoundEvents.ENTITY_SPIDER_STEP;
    }

    @Override
    protected int getMaxSpawnHeight() {
        return 120;
    }

    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.ARTHROPOD;
    }

    @Override
    public void tick() {
        super.tick();

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
