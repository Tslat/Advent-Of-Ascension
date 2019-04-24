package net.tslat.aoa3.entity.base;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.EntityAIFindEntityNearest;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
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
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.tslat.aoa3.entity.base.ai.RoamingFlightMoveHelper;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIFlyingLookAround;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIFlyingMeleeAttack;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIRandomFly;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.properties.HunterEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.event.dimension.OverworldEvents;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.PlayerUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public abstract class AoAFlyingMeleeMob extends EntityFlying implements IMob {
    protected final TreeSet<Enums.MobProperties> mobProperties;

    public AoAFlyingMeleeMob(World world, float entityWidth, float entityHeight) {
        super(world);

        if (this instanceof SpecialPropertyEntity) {
            mobProperties = new TreeSet<Enums.MobProperties>();

            if (this instanceof HunterEntity)
                mobProperties.add(Enums.MobProperties.HUNTER_ENTITY);
        }
        else {
            mobProperties = null;
        }

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
        targetTasks.addTask(1, new EntityAIFindEntityNearest(this, AoAMinion.class));
        targetTasks.addTask(2, new EntityAIFindEntityNearestPlayer(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getBaseMeleeDamage());
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(52);
        getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(getBaseKnockbackResistance());
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseMaxHealth());
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getBaseMovementSpeed());
    }

    protected abstract double getBaseKnockbackResistance();

    protected abstract double getBaseMaxHealth();

    protected abstract double getBaseMeleeDamage();

    protected abstract double getBaseMovementSpeed();

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
        if (!isOverworldMob() || ((isDaylightMob() && world.isDaytime())))
            return true;

        BlockPos blockpos = new BlockPos(posX, getEntityBoundingBox().minY, posZ);

        if (world.getLightFor(EnumSkyBlock.SKY, blockpos) > rand.nextInt(32)) {
            return false;
        }
        else {
            int light = world.getLightFromNeighbors(blockpos);

            if (this.world.isThundering()) {
                int skyLight = world.getSkylightSubtracted();

                world.setSkylightSubtracted(10);

                light = world.getLightFromNeighbors(blockpos);

                world.setSkylightSubtracted(skyLight);
            }

            return light <= rand.nextInt(8);
        }
    }

    protected boolean isDaylightMob() {
        return false;
    }

    private boolean checkSpawnChance() {
        return getSpawnChanceFactor() <= 1 || rand.nextInt(getSpawnChanceFactor()) == 0;
    }

    protected int getSpawnChanceFactor() {
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

        if (this instanceof HunterEntity) {
            Entity trueSource = source.getTrueSource();
            EntityPlayer pl = null;

            if (trueSource instanceof EntityPlayer) {
                pl = (EntityPlayer)trueSource;
            }
            else if (trueSource instanceof EntityTameable && ((EntityTameable)trueSource).getOwner() instanceof EntityPlayer) {
                pl = (EntityPlayer)((EntityTameable)trueSource).getOwner();
            }

            if (pl != null) {
                if (!isSpecialImmuneTo(source))
                    return !pl.capabilities.isCreativeMode && !PlayerUtil.doesPlayerHaveLevel(pl, Enums.Skills.HUNTER, ((HunterEntity)this).getHunterReq());

                return true;
            }
        }

        return isSpecialImmuneTo(source);
    }

    protected boolean isSpecialImmuneTo(DamageSource source) {
        return false;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!world.isRemote && world.getDifficulty() == EnumDifficulty.PEACEFUL)
            setDead();
    }

    @Override
    public void onDeath(DamageSource cause) {
        super.onDeath(cause);

        if (this instanceof HunterEntity) {
            Entity trueSource = cause.getTrueSource();
            EntityPlayer pl = null;

            if (trueSource instanceof EntityPlayer) {
                pl = (EntityPlayer)trueSource;
            }
            else if (trueSource instanceof EntityTameable && ((EntityTameable)trueSource).getOwner() instanceof EntityPlayer) {
                pl = (EntityPlayer)((EntityTameable)trueSource).getOwner();
            }

            if (pl != null && PlayerUtil.doesPlayerHaveLevel(pl, Enums.Skills.HUNTER, ((HunterEntity)this).getHunterReq()))
                PlayerUtil.giveXpToPlayer(pl, Enums.Skills.HUNTER, ((HunterEntity)this).getHunterXp());
        }
    }

    @Override
    protected void dropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source) {
        if (this instanceof HunterEntity && (!(source.getTrueSource() instanceof EntityPlayer) && (!(source.getTrueSource() instanceof EntityTameable) || !(((EntityTameable)source.getTrueSource()).getOwner() instanceof EntityPlayer))))
            return;

        lootingModifier = MathHelper.clamp(lootingModifier, 0, 9);

        dropGuaranteedItems(lootingModifier, source);

        if (wasRecentlyHit)
            dropSpecialItems(lootingModifier, source);
    }

    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {}

    protected void dropSpecialItems(int lootingMod, DamageSource source) {}
}
