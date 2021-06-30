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

        teleportTo(spawnPosition.getX(), spawnPosition.getY(), spawnPosition.getZ());
    }

    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 1));
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return 0.25f;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    protected void doPush(Entity entity) {}

    @Override
    public boolean canChangeDimensions() {
        return false;
    }

    @Override
    public void playerTouch(PlayerEntity player) {
        if (player instanceof ServerPlayerEntity && isAlive()) {
            PlayerDataManager plData = PlayerUtil.getAdventPlayer((ServerPlayerEntity)player);
            int lvl = plData.stats().getLevelForDisplay(Skills.BUTCHERY);

            plData.stats().addXp(Skills.BUTCHERY, PlayerUtil.getXpRequiredForNextLevel(lvl) / ButcheryUtil.getExpDenominator(lvl), false, false);
            level.playSound(null, getX(), getY(), getZ(), AoASounds.BLOODLUST_COLLECT.get(), SoundCategory.NEUTRAL, 1.0f, 1.0f);
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
    public boolean isAffectedByPotions() {
        return false;
    }

    @Override
    public boolean addEffect(EffectInstance effect) {
        return false;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return source != DamageSource.OUT_OF_WORLD;
    }

    @Override
    public void tick() {
        super.tick();

        if (tickCount >= 200)
            remove();
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
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
    protected SoundEvent getFallDamageSound(int heightIn) {
        return null;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {}
}
