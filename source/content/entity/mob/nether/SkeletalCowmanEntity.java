package net.tslat.aoa3.content.entity.mob.nether;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;

import javax.annotation.Nullable;

public class SkeletalCowmanEntity extends AoARangedMob<SkeletalCowmanEntity> {
    public SkeletalCowmanEntity(EntityType<? extends SkeletalCowmanEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        SpawnGroupData data = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

        setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.BOW));
        setDropChance(EquipmentSlot.MAINHAND, 0);

        return data;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return 1.74f;
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(2, new RangedBowAttackGoal<SkeletalCowmanEntity>(this, 1.0d, 5, 10));
        goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1));
        goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8f));
        goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_SKELETAL_COWMAN_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return AoASounds.ENTITY_SKELETAL_COWMAN_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return AoASounds.ENTITY_SKELETAL_COWMAN_HURT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getShootSound() {
        return SoundEvents.ARROW_SHOOT;
    }

    @Override
    public void performRangedAttack(LivingEntity target, float bowDamageFactor) {
        Arrow arrow = new Arrow(level, this);

        double distanceFactorX = target.getX() - this.getX();
        double distanceFactorY = target.getBoundingBox().minY + (double)(target.getBbHeight() / 3.0f) - arrow.getY();
        double distanceFactorZ = target.getZ() - this.getZ();
        double hyp = Math.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) + 0.2D;

        if (getShootSound() != null)
            playSound(getShootSound(), 1.0f, 1.0f);

        arrow.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(8 - this.level.getDifficulty().getId()));
        level.addFreshEntity(arrow);
    }

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
        return null;
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }
}
