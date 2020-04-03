package net.tslat.aoa3.entity.misc;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;
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
        this.tasks.addTask(1, new EntityAIWanderAvoidWater(this, 1d));
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
    public void onCollideWithPlayer(EntityPlayer player) {
        if (!world.isRemote && !isDead) {
            PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);
            int lvl = plData.stats().getLevelForDisplay(Enums.Skills.BUTCHERY);

            plData.stats().addXp(Enums.Skills.BUTCHERY, PlayerUtil.getXpRequiredForNextLevel(lvl) / ButcheryUtil.getExpDenominator(lvl), false, false);
            world.playSound(null, posX, posY, posZ, SoundsRegister.bloodlustCollect, SoundCategory.NEUTRAL, 1.0f, 1.0f);
            setDead();
        }
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
    public boolean canBeHitWithPotion() {
        return false;
    }

    @Override
    public void addPotionEffect(PotionEffect effect) {}

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
