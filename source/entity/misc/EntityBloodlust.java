package net.tslat.aoa3.entity.misc;

import net.minecraft.block.Block;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.skills.ButcheryUtil;

import javax.annotation.Nullable;

public class EntityBloodlust extends EntityCreature {
    public static final float entityWidth = 0.5f;

    public EntityBloodlust(World world) {
        super(world);
        setSize(entityWidth, 0.5f);
        setSilent(true);
        this.isImmuneToFire = true;
    }

    public EntityBloodlust(World world, BlockPos spawnPosition) {
        super(world);
        setPositionAndUpdate(spawnPosition.getX(), spawnPosition.getY(), spawnPosition.getZ());
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAIPanic(this, 0.55d));
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 0.55d));
        this.tasks.addTask(3, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1);
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.40000000298023225);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1);
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    protected void collideWithEntity(Entity entityIn) {}

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (isDead)
            return false;

        if (!world.isRemote && player.getHeldItem(hand).getItem() == ItemRegister.bloodAccumulator) {
            AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(player);
            int lvl = cap.getLevel(Enums.Skills.BUTCHERY);

            if (!player.capabilities.isCreativeMode)
                player.getHeldItem(hand).damageItem(1, player);

            world.playSound(null, getPosition().getX(), getPosition().getY(), getPosition().getZ(), SoundsRegister.bloodlustCollect, SoundCategory.NEUTRAL, 1.0f, 1.0f);
            cap.addXp(Enums.Skills.BUTCHERY, cap.getXpReqForLevel(lvl) / ButcheryUtil.getExpDenominator(lvl), false);
            setDead();

            return true;
        }

        return false;
    }

    @Override
    public boolean isSilent() {
        return true;
    }

    @Override
    public boolean getIsInvulnerable() {
        return true;
    }

    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        return source != DamageSource.OUT_OF_WORLD;
    }

    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (ticksExisted >= 200)
            setDead();
    }

    @Override
    protected boolean canDespawn() {
        return true;
    }

    @Override
    public float getEyeHeight() {
        return 0.25f;
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
    protected void playStepSound(BlockPos pos, Block blockIn) {}
}
