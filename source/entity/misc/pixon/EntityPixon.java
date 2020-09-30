package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.tool.misc.InfusionBowl;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class EntityPixon extends EntityCreature {
    public static final float entityWidth = 0.9f;
    public long nextHarvestTick = 0;

    public EntityPixon(World world) {
        super(world);
        setSize(entityWidth, 1.3f);

        this.isImmuneToFire = true;
    }
    // TODO Fix level distribution across dimensions
    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIPanic(this, 0.75d));
        this.tasks.addTask(2, new EntityAIWanderAvoidWater(this, 0.55d));
        this.tasks.addTask(3, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15 + rand.nextInt(30));
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.40000000298023225);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1);
    }

    @Override
    public void addPotionEffect(PotionEffect effect) {}

    @Override
    public boolean canBeHitWithPotion() {
        return false;
    }

    @Override
    public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
        return forSpawnCount ? type == EnumCreatureType.AMBIENT : type == EnumCreatureType.CREATURE;
    }

    @Override
    protected void collideWithEntity(Entity entity) {}

    @Override
    public boolean canBePushed() {
        return false;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (getHealth() <= 0)
            return false;

        ItemStack heldStack = player.getHeldItem(hand);

        if (!world.isRemote && world.getTotalWorldTime() >= this.nextHarvestTick && heldStack.getItem() instanceof InfusionBowl) {
            InfusionBowl bowl = ((InfusionBowl)heldStack.getItem());

            if (player.capabilities.isCreativeMode || PlayerUtil.doesPlayerHaveLevel(player, Enums.Skills.INFUSION, getHarvestLevelReq() + bowl.getHarvestReqModifier())) {
                bowl.handlePixonHarvest(player, this, heldStack);
            }
            else {
                if (player instanceof EntityPlayerMP)
                    PlayerUtil.notifyPlayerOfInsufficientLevel((EntityPlayerMP)player, Enums.Skills.INFUSION, getHarvestLevelReq() + bowl.getHarvestReqModifier());
            }

            if (getHealth() <= 0) {
                player.awardKillScore(this, 1, DamageSource.GENERIC);
            }
            else {
                this.nextHarvestTick = world.getTotalWorldTime() + 8 + rand.nextInt(32);
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean getCanSpawnHere() {
        if (world.getWorldType() == WorldType.FLAT)
            return false;

        BlockPos position = new BlockPos(posX, posY, posZ);
        Biome biome = world.getBiome(position);
        IBlockState blockState = world.getBlockState(position);

        return blockState == biome.topBlock && blockState.canEntitySpawn(this);
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
    protected boolean canDespawn() {
        return false;
    }

    @Override
    public float getEyeHeight() {
        return 0.65f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.ENTITY_PIXON_LIVING;
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

    public abstract int getHarvestLevelReq();

    @Nonnull
    public abstract ResourceLocation getHarvestLootTable();
}
