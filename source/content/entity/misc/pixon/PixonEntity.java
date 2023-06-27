package net.tslat.aoa3.content.entity.misc.pixon;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.AoATags;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.content.item.tool.misc.InfusionBowl;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;

public abstract class PixonEntity extends PathfinderMob {
    public long nextHarvestTick = 0;

    public PixonEntity(EntityType<? extends PathfinderMob> entityType, Level world) {
        super(entityType, world);
    }
    // TODO Fix level distribution across dimensions
    @Override
    protected void registerGoals() {
        goalSelector.addGoal(1, new PanicGoal(this, 0.75d));
        goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.55d));
        goalSelector.addGoal(3, new RandomLookAroundGoal(this));
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
        this.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("Random spawn health bonus", this.random.nextInt(30), AttributeModifier.Operation.ADDITION));

        setHealth(getMaxHealth());

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    public boolean addEffect(MobEffectInstance effectInstanceIn, @Nullable Entity source) {
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

    public boolean canHarvest(ServerPlayer player, ItemStack bowlStack) {
        if (level().getGameTime() >= this.nextHarvestTick && bowlStack.getItem() instanceof InfusionBowl) {
            InfusionBowl bowl = ((InfusionBowl)bowlStack.getItem());

            if (player.isCreative() || PlayerUtil.doesPlayerHaveLevel(player, AoASkills.IMBUING.get(), getHarvestLevelReq() + bowl.getHarvestReqModifier())) {
                return true;
            }
            else {
                PlayerUtil.notifyPlayerOfInsufficientLevel(player, AoASkills.IMBUING.get(), getHarvestLevelReq() + bowl.getHarvestReqModifier());
            }
        }

        return false;
    }

    @Override
    public boolean checkSpawnRules(LevelAccessor world, MobSpawnType reason) {
        if (!EntityUtil.isNaturalSpawnReason(reason))
            return true;

        if (!super.checkSpawnRules(world, reason))
            return false;

        return false; //world.getBlockState(blockPosition().below()).getBlock() == world.getBiome(blockPosition()).getGenerationSettings().getSurfaceBuilder().get().config.getTopMaterial().getBlock();
    }

    @Override
    public boolean isInvulnerable() {
        return true;
    }

    @Override
    public boolean isInvulnerableTo(DamageSource source) {
        return !source.is(AoATags.DamageTypes.IS_TECHNICAL);
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
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
    public SoundEvent getFallDamageSound(int heightIn) {
        return null;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {}

    public abstract int getHarvestLevelReq();
}
