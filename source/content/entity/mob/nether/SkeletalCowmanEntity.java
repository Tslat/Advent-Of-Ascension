package net.tslat.aoa3.content.entity.mob.nether;

import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;

import javax.annotation.Nullable;

public class SkeletalCowmanEntity extends AoARangedMob {
    public SkeletalCowmanEntity(EntityType<? extends MonsterEntity> entityType, World world) {
        super(entityType, world);
    }

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        ILivingEntityData data = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

        setItemInHand(Hand.MAIN_HAND, new ItemStack(Items.BOW));
        setDropChance(EquipmentSlotType.MAINHAND, 0);

        return data;
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return 1.74f;
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(2, new RangedBowAttackGoal(this, 1.0d, 5, 10));
        goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1));
        goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8f));
        goalSelector.addGoal(8, new LookRandomlyGoal(this));
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
        ArrowEntity arrow = new ArrowEntity(level, this);

        double distanceFactorX = target.getX() - this.getX();
        double distanceFactorY = target.getBoundingBox().minY + (double)(target.getBbHeight() / 3.0f) - arrow.getY();
        double distanceFactorZ = target.getZ() - this.getZ();
        double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) + 0.2D;

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
    public CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEAD;
    }
}
