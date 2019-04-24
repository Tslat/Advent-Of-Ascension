package net.tslat.aoa3.entity.misc.pixon;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.misc.InfusionStone;
import net.tslat.aoa3.item.tool.misc.InfusionBowl;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.StringUtil;

import javax.annotation.Nullable;

public abstract class EntityPixon extends EntityCreature {
    public static final float entityWidth = 0.9f;
    public long nextHarvestTick = 0;

    public EntityPixon(World world) {
        super(world);
        setSize(entityWidth, 1.3f);
        this.isImmuneToFire = true;
    }

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
    public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
        return type == EnumCreatureType.AMBIENT;
    }

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

        ItemStack stack = player.getHeldItem(hand);

        if (!world.isRemote && world.getTotalWorldTime() >= this.nextHarvestTick && stack.getItem() instanceof InfusionBowl) {
            AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(player);

            if (stack.getItem() == ItemRegister.stoneBowl) {
                if (player.capabilities.isCreativeMode || cap.getLevel(Enums.Skills.INFUSION) >= getHarvestLevelReq()) {
                    if (!player.capabilities.isCreativeMode)
                        stack.damageItem(1, player);

                    cap.addXp(Enums.Skills.INFUSION, getHarvestXp(), false);
                    cap.addTribute(Enums.Deities.LUXON, 5);
                    setHealth(getHealth() - 10);
                    setRevengeTarget(player);
                    ItemUtil.givePlayerItemOrDrop(player, new ItemStack(getInfusionStoneType()));
                    world.playSound(null, getPosition().getX(), getPosition().getY(), getPosition().getZ(), SoundsRegister.entityPixonHarvest, SoundCategory.MASTER, 1.0f, 1.0f);
                    this.nextHarvestTick = world.getTotalWorldTime() + 8 + rand.nextInt(32);

                    return true;
                }
                else {
                    cap.sendPlayerMessage(StringUtil.getLocaleWithArguments("message.feedback.pixon.harvestReq", Integer.toString(getHarvestLevelReq())));
                    return true;
                }
            }
            else if (stack.getItem() == ItemRegister.diamondBowl) {
                if (player.capabilities.isCreativeMode || cap.getLevel(Enums.Skills.INFUSION) >= getHarvestLevelReq() + 10) {
                    if (!player.capabilities.isCreativeMode)
                        stack.damageItem(1, player);

                    int harvestCount = (int)Math.ceil(Math.min(getHealth(), 100) / 10f);

                    for (int i = 0; i < harvestCount; i++) {
                        ItemUtil.givePlayerItemOrDrop(player, new ItemStack(getInfusionStoneType()));
                    }

                    cap.addXp(Enums.Skills.INFUSION, getHarvestXp() * harvestCount, false);
                    cap.addTribute(Enums.Deities.LUXON, 5 * harvestCount);
                    world.playSound(null, getPosition().getX(), getPosition().getY(), getPosition().getZ(), SoundsRegister.entityPixonHarvest, SoundCategory.MASTER, 1.0f, 1.0f);
                    this.setHealth(0);
                    this.setDead();

                    return true;
                }
                else {
                    cap.sendPlayerMessage(StringUtil.getLocaleWithArguments("message.feedback.pixon.harvestReq", Integer.toString(getHarvestLevelReq() + 10)));
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean getCanSpawnHere() {
        if (world.getWorldType() == WorldType.FLAT)
            return false;

        int height = world.getHeight((int)posX, (int)posZ);
        IBlockState block = world.getBlockState(new BlockPos(posX, height - 1, posZ));
        Biome biome = world.getBiome(new BlockPos(posX, height, posZ));

        return posY >= height && (block == biome.topBlock || block == biome.fillerBlock) && block.canEntitySpawn(this);
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
        return true;
    }

    @Override
    public float getEyeHeight() {
        return 0.65f;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.entityPixonLiving;
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

    public abstract float getHarvestXp();

    public abstract InfusionStone getInfusionStoneType();
}
