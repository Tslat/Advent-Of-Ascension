package net.tslat.aoa3.entity.base;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateFlying;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.tslat.aoa3.entity.base.ai.RoamingFlightMoveHelper;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIFlyingFindNearestAttackableTargetHunter;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIFlyingLookAround;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIFlyingMeleeAttack;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIRandomFly;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.event.dimension.OverworldEvents;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public abstract class AoAFlyingMeleeMob extends EntityFlying implements IMob {
    protected final TreeSet<Enums.MobProperties> mobProperties;

    public AoAFlyingMeleeMob(World world, float entityWidth, float entityHeight) {
        super(world);

        mobProperties = this instanceof SpecialPropertyEntity ? new TreeSet<Enums.MobProperties>() : null;
        moveHelper = new RoamingFlightMoveHelper(this);

        setSize(entityWidth, entityHeight);
        setXpValue((int)getBaseMaxHealth() / 10);
    }

    @Override
    protected void initEntityAI() {
        tasks.addTask(1, new EntityAIRandomFly(this, true));
        tasks.addTask(2, new EntityAIFlyingLookAround(this));
        tasks.addTask(3, new EntityAILookIdle(this));
        tasks.addTask(4, new EntityAIFlyingMeleeAttack(this, 0.6f, false));
        targetTasks.addTask(1, new EntityAIFlyingFindNearestAttackableTargetHunter<>(this, AoAMinion.class, EntityTameable::isTamed));
        targetTasks.addTask(2, new EntityAIFlyingFindNearestAttackableTargetHunter<>(this, EntityPlayer.class));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getBaseMeleeDamage());
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(36);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(getBaseKnockbackResistance());
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth());
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMovementSpeed());
        getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(getBaseArmour());
    }

    protected abstract double getBaseKnockbackResistance();

    protected abstract double getBaseMaxHealth();

    protected abstract double getBaseMeleeDamage();

    protected abstract double getBaseMovementSpeed();

    protected double getBaseArmour() {
        return 0;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return null;
    }

    @Override
    @Nonnull
    public PathNavigate getNavigator() {
        return new PathNavigateFlying(this, world);
    }

    protected void setXpValue(int amount) {
        experienceValue = amount;
    }

    @Override
    public boolean getCanSpawnHere() {
        return world.getDifficulty() != EnumDifficulty.PEACEFUL && checkWorldRequirements() && checkSpawnChance() && isValidLightLevel() && canSpawnOnBlock(world.getBlockState(getPosition().down()));
    }

    protected boolean isOverworldMob() {
        return false;
    }

    protected boolean isValidLightLevel() {
        if (isDaylightMob() || !isOverworldMob()) {
            if (!world.isDaytime() && isDaylightMob())
                return false;

            return WorldUtil.getLightLevel(world, getPosition(), true, false) <= rand.nextInt(8);
        }

        BlockPos blockPos = new BlockPos(posX, getEntityBoundingBox().minY, posZ);

        if (world.getLightFor(EnumSkyBlock.SKY, blockPos) > rand.nextInt(32)) {
            return false;
        }
        else {
            int light;

            if (world.isThundering()) {
                int skylightSubtracted = world.getSkylightSubtracted();

                world.setSkylightSubtracted(10);
                light = world.getLightFromNeighbors(blockPos);
                world.setSkylightSubtracted(skylightSubtracted);
            }
            else {
                light = world.getLightFromNeighbors(blockPos);
            }

            return light <= rand.nextInt(8);
        }
    }

    protected boolean isDaylightMob() {
        return false;
    }

    private boolean checkSpawnChance() {
        if (isOverworldMob()) {
            if (isDaylightMob()) {
                if (rand.nextFloat() > 0.1)
                    return false;
            }
            else if (rand.nextFloat() > 0.4) {
                return false;
            }
        }
        else {
            if (rand.nextFloat() > 0.1)
                return false;
        }

        return getSpawnChanceFactor() <= 1 || rand.nextInt(getSpawnChanceFactor()) == 0;
    }

    protected int getSpawnChanceFactor() {
        return 1;
    }

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    protected boolean canSpawnOnBlock(IBlockState block) {
        return block.canEntitySpawn(this);
    }

    private boolean checkWorldRequirements() {
        if (isOverworldMob()) {
            if (world.provider.getDimension() != 0) {
                EntityRegistry.removeSpawn(getClass(), EnumCreatureType.MONSTER, world.getBiome(getPosition()));

                return false;
            }
        }

        Enums.CreatureEvents eventReq = getEventRequirement();

        return eventReq == null || OverworldEvents.isEventActive(eventReq);
    }

    @Nullable
    protected Enums.CreatureEvents getEventRequirement() {
        return null;
    }

    @Override
    public boolean doesEntityNotTriggerPressurePlate() {
        return true;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    protected void playStepSound(BlockPos pos, Block block) {}

    public boolean attackEntityAsMob(Entity target) {
        float baseDamage = (float)getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
        int knockbackValue = 0;

        if (target instanceof EntityLivingBase) {
            baseDamage += EnchantmentHelper.getModifierForCreature(getHeldItemMainhand(), ((EntityLivingBase)target).getCreatureAttribute());
            knockbackValue += EnchantmentHelper.getKnockbackModifier(this);
        }

        boolean attackSuccess = target.attackEntityFrom(DamageSource.causeMobDamage(this), baseDamage);

        if (attackSuccess) {
            if (knockbackValue > 0) {
                ((EntityLivingBase)target).knockBack(this, (float)knockbackValue * 0.5F, (double)MathHelper.sin(rotationYaw * 0.017453292F), (double)(-MathHelper.cos(rotationYaw * 0.017453292F)));
                motionX *= 0.6D;
                motionZ *= 0.6D;
            }

            int fireAspectMod = EnchantmentHelper.getFireAspectModifier(this);

            if (fireAspectMod > 0)
                target.setFire(fireAspectMod * 4);

            if (target instanceof EntityPlayer) {
                EntityPlayer playerTarget = (EntityPlayer)target;
                ItemStack mainHandStack = getHeldItemMainhand();
                ItemStack activeHeldStack = playerTarget.isHandActive() ? playerTarget.getActiveItemStack() : ItemStack.EMPTY;

                if (!mainHandStack.isEmpty() && !activeHeldStack.isEmpty() && mainHandStack.getItem().canDisableShield(mainHandStack, activeHeldStack, playerTarget, this) && activeHeldStack.getItem().isShield(activeHeldStack, playerTarget)) {
                    float hasteMod = 0.25F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;

                    if (rand.nextFloat() < hasteMod) {
                        playerTarget.getCooldownTracker().setCooldown(activeHeldStack.getItem(), 100);
                        world.setEntityState(playerTarget, (byte)30);
                    }
                }
            }

            applyEnchantments(this, target);
            doMeleeEffect(target);
        }

        return attackSuccess;
    }

    protected void doMeleeEffect(Entity target) {}

    @Override
    public boolean isEntityInvulnerable(DamageSource source) {
        if (source == DamageSource.OUT_OF_WORLD)
            return false;

        if (getIsInvulnerable())
            return true;

        return isSpecialImmuneTo(source, 1);
    }

    protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
        return false;
    }

    @Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        if (getLootTable() != null) {
            LootTable lootTable = world.getLootTableManager().getLootTableFromLocation(getLootTable());

            LootContext.Builder lootBuilder = (new LootContext.Builder((WorldServer)world)).withLootedEntity(this).withDamageSource(source);

            if (wasRecentlyHit && attackingPlayer != null)
                lootBuilder.withPlayer(attackingPlayer).withLuck(attackingPlayer.getLuck() + lootingModifier);

            for (ItemStack stack : lootTable.generateLootForPools(rand, lootBuilder.build())) {
                entityDropItem(stack, 0);
            }

            dropEquipment(wasRecentlyHit, lootingModifier);
        }
        else {
            super.dropLoot(wasRecentlyHit, lootingModifier, source);
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!world.isRemote && world.getDifficulty() == EnumDifficulty.PEACEFUL)
            setDead();
    }
}
