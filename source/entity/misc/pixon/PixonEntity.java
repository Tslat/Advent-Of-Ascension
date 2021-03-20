package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.item.tool.misc.InfusionBowl;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;

public abstract class PixonEntity extends CreatureEntity {
    public long nextHarvestTick = 0;

    public PixonEntity(EntityType<? extends CreatureEntity> entityType, World world) {
        super(entityType, world);
    }
    // TODO Fix level distribution across dimensions
    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new PanicGoal(this, 0.75d));
        goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.55d));
        goalSelector.addGoal(3, new LookRandomlyGoal(this));
    }

    @Override
    public boolean addEffect(EffectInstance effectInstanceIn) {
        return false;
    }

    @Override
    public boolean isAffectedByPotions() {
        return false;
    }

    @Override
    protected void doPush(Entity entity) {}

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 1;
    }

    public boolean canHarvest(ServerPlayerEntity player, ItemStack bowlStack) {
        if (level.getGameTime() >= this.nextHarvestTick && bowlStack.getItem() instanceof InfusionBowl) {
            InfusionBowl bowl = ((InfusionBowl)bowlStack.getItem());

            if (player.isCreative() || PlayerUtil.doesPlayerHaveLevel(player, Skills.INFUSION, getHarvestLevelReq() + bowl.getHarvestReqModifier())) {
                return true;
            }
            else {
                PlayerUtil.notifyPlayerOfInsufficientLevel(player, Skills.INFUSION, getHarvestLevelReq() + bowl.getHarvestReqModifier());
            }
        }

        return false;
    }

    @Override
    public boolean checkSpawnRules(IWorld world, SpawnReason reason) {
        if (!EntityUtil.isNaturalSpawnReason(reason))
            return true;

        if (!super.checkSpawnRules(world, reason))
            return false;

        return world.getBlockState(blockPosition().below()).getBlock() == world.getBiome(blockPosition()).getGenerationSettings().getSurfaceBuilder().get().config.getTopMaterial().getBlock();
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return source != DamageSource.OUT_OF_WORLD;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntitySize size) {
        return 0.65f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return AoASounds.ENTITY_PIXON_AMBIENT.get();
    }

    @Override
    protected float getSoundVolume() {
        return 0.6f;
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

    public abstract int getHarvestLevelReq();
}
