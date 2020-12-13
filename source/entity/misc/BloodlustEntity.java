package net.tslat.aoa3.entity.misc;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;
import net.tslat.aoa3.util.skill.ButcheryUtil;

import javax.annotation.Nullable;

public class BloodlustEntity extends CreatureEntity {
    public BloodlustEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);

        setSilent(true);
    }

    public BloodlustEntity(World world, BlockPos spawnPosition) {
        super(AoAEntities.Misc.BLOODLUST.get(), world);

        setPositionAndUpdate(spawnPosition.getX(), spawnPosition.getY(), spawnPosition.getZ());
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1));
    }

    @Override
    protected void registerAttributes() {
        super.registerAttributes();

        getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1);
        getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2);
        getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return 0.25f;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    protected void collideWithEntity(Entity entity) {}

    @Override
    public void onCollideWithPlayer(PlayerEntity player) {
        if (player instanceof ServerPlayerEntity && isAlive()) {
            PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);
            int lvl = plData.stats().getLevelForDisplay(Skills.BUTCHERY);

            plData.stats().addXp(Skills.BUTCHERY, PlayerUtil.getXpRequiredForNextLevel(lvl) / ButcheryUtil.getExpDenominator(lvl), false, false);
            world.playSound(null, getPosX(), getPosY(), getPosZ(), AoASounds.BLOODLUST_COLLECT.get(), SoundCategory.NEUTRAL, 1.0f, 1.0f);
            remove();
        }
    }

    @Override
    public boolean isSilent() {
        return true;
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public boolean canBeHitWithPotion() {
        return false;
    }

    @Override
    public boolean addPotionEffect(EffectInstance effect) {
        return false;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return source != DamageSource.OUT_OF_WORLD;
    }

    @Override
    public void tick() {
        super.tick();

        if (ticksExisted >= 200)
            remove();
    }

    @Override
    public boolean canDespawn(double distanceToClosestPlayer) {
        return true;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Override
    protected SoundEvent getFallSound(int heightIn) {
        return null;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {}
}
