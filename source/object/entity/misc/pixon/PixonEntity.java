package net.tslat.aoa3.object.entity.misc.pixon;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.object.item.tool.misc.InfusionBowl;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.PlayerUtil;

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

    @Nullable
    @Override
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        this.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("Random spawn health bonus", this.random.nextInt(30), AttributeModifier.Operation.ADDITION));

        setHealth(getMaxHealth());

        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
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
