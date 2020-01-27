package net.tslat.aoa3.entity.base;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.event.dimension.OverworldEvents;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;
import java.util.TreeSet;

public abstract class AoAMeleeMob extends EntityMob implements AnimatableEntity {
    protected final TreeSet<Enums.MobProperties> mobProperties;
    private boolean isSlipperyMob = false;
    private int animationTicks = 0;
    protected String currentAnimation = null;

    public AoAMeleeMob(World world, float entityWidth, float entityHeight) {
        super(world);

        mobProperties = this instanceof SpecialPropertyEntity ? new TreeSet<Enums.MobProperties>() : null;

        setSize(entityWidth, entityHeight);
        setXpValue((int)getBaseMaxHealth() / 10);
    }

    @Override
    protected void initEntityAI() {
        tasks.addTask(1, new EntityAISwimming(this));
        tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, false));
        tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0d));
        tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0d));
        tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
        tasks.addTask(5, new EntityAILookIdle(this));
        targetTasks.addTask(1, new EntityAINearestAttackableTarget<>(this, AoAMinion.class, 10, true, false, EntityTameable::isTamed));
        targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(3, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(getBaseMeleeDamage());
        getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16);
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

    @Nullable
    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_PIG_STEP;
    }

    protected void setXpValue(int amount) {
        experienceValue = amount;
    }

    protected void setSlipperyMovement() {
        isSlipperyMob = true;
    }

    @Override
    public boolean getCanSpawnHere() {
       return world.getDifficulty() != EnumDifficulty.PEACEFUL && checkWorldRequirements() && checkSpawnChance() && isValidLightLevel() && canSpawnOnBlock(world.getBlockState(getPosition().down()));
    }

    protected boolean isOverworldMob() {
        return false;
    }

    @Override
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

    @Override
    public int getMaxSpawnedInChunk() {
        return 1;
    }

    protected boolean isDaylightMob() {
        return false;
    }

    private boolean checkSpawnChance() {
        if (isOverworldMob()) {
            if (isDaylightMob()) {
                return !(rand.nextDouble() > getSpawnChanceFactor());
            }
            else {
                return !(rand.nextDouble() > getSpawnChanceFactor() * 4);
            }
        }
        else {
            return !(rand.nextDouble() > getSpawnChanceFactor());
        }
    }

    protected double getSpawnChanceFactor() {
        return ConfigurationUtil.EntityConfig.mobSpawnFrequencyModifier;
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
    public boolean isNotColliding() {
        return !canBreatheUnderwater() ? super.isNotColliding() : this.world.getCollisionBoxes(this, this.getEntityBoundingBox()).isEmpty() && this.world.checkNoEntityCollision(this.getEntityBoundingBox(), this);
    }

    protected void playStepSound(BlockPos pos, Block block) {
        if (getStepSound() != null)
            playSound(getStepSound(), 0.55f, 1.0F);
    }

    @Override
    public boolean attackEntityAsMob(Entity target) {
        startAnimation(Enums.EntityAnimations.ATTACK_1);

        if (super.attackEntityAsMob(target)) {
            doMeleeEffect(target);

            return true;
        }

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

        if (animationTicks >= 0)
            animationTicks++;
    }

    @Override
    public int getCurrentAnimationTicks() {
        return animationTicks;
    }

    @Nullable
    @Override
    public String getCurrentAnimation() {
        return currentAnimation;
    }

    @Override
    public void finishAnimation() {
        currentAnimation = null;
        animationTicks = -1;
    }

    @Override
    public void startAnimation(String animation) {
        currentAnimation = animation;
        animationTicks = 0;
    }

    @Override
    public void resetAnimation() {
        animationTicks = 0;
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
    public void travel(float strafe, float vertical, float forward) {
        if (isServerWorld() || canPassengerSteer()) {
            if (!isInWater()) {
                if (!isInLava()) {
                    if (isElytraFlying()) {
                        if (motionY > -0.5D)
                            fallDistance = 1.0F;

                        Vec3d lookVec = getLookVec();
                        float lookPitch = rotationPitch * 0.017453292F;
                        double lookVecHypot = Math.sqrt(lookVec.x * lookVec.x + lookVec.z * lookVec.z);
                        double motion = Math.sqrt(motionX * motionX + motionZ * motionZ);
                        float pitchAngle = MathHelper.cos(lookPitch);
                        pitchAngle = (float)((double)pitchAngle * (double)pitchAngle * Math.min(1.0D, lookVec.length() / 0.4D));
                        motionY += -0.08D + (double)pitchAngle * 0.06D;

                        if (motionY < 0.0D && lookVecHypot > 0.0D) {
                            double motionYMod = motionY * -0.1D * (double)pitchAngle;
                            motionY += motionYMod;
                            motionX += lookVec.x * motionYMod / lookVecHypot;
                            motionZ += lookVec.z * motionYMod / lookVecHypot;
                        }

                        if (lookPitch < 0.0F) {
                            double inverseMotionMod = motion * (double)(-MathHelper.sin(lookPitch)) * 0.04D;
                            motionY += inverseMotionMod * 3.2D;
                            motionX -= lookVec.x * inverseMotionMod / lookVecHypot;
                            motionZ -= lookVec.z * inverseMotionMod / lookVecHypot;
                        }

                        if (lookVecHypot > 0.0D) {
                            motionX += (lookVec.x / lookVecHypot * motion - motionX) * 0.1D;
                            motionZ += (lookVec.z / lookVecHypot * motion - motionZ) * 0.1D;
                        }

                        motionX *= 0.9900000095367432D;
                        motionY *= 0.9800000190734863D;
                        motionZ *= 0.9900000095367432D;
                        move(MoverType.SELF, motionX, motionY, motionZ);

                        if (collidedHorizontally && !world.isRemote) {
                            double newMotion = Math.sqrt(motionX * motionX + motionZ * motionZ);
                            float impactVelocity = (float)((motion - newMotion) * 10.0D - 3.0D);

                            if (impactVelocity > 0.0F) {
                                playSound(getFallSound((int)impactVelocity), 1.0F, 1.0F);
                                attackEntityFrom(DamageSource.FLY_INTO_WALL, impactVelocity);
                            }
                        }

                        if (!world.isRemote && onGround) {
                            setFlag(7, false);
                        }
                    }
                    else {
                        float friction = 0.91F;
                        BlockPos.PooledMutableBlockPos checkPos = BlockPos.PooledMutableBlockPos.retain(posX, getEntityBoundingBox().minY - 1.0D, posZ);

                        if (onGround) {
                            IBlockState underState = world.getBlockState(checkPos);
                            friction = underState.getBlock().getSlipperiness(underState, world, checkPos, this) * 0.91F;

                            if (isSlipperyMob)
                                friction *= 0.9;
                        }

                        friction = 0.16277136F / (friction * friction * friction);
                        float frictionMod;

                        if (onGround) {
                            frictionMod = getAIMoveSpeed() * friction;
                        }
                        else {
                            frictionMod = jumpMovementFactor;
                        }

                        moveRelative(strafe, vertical, forward, frictionMod);

                        friction = 0.91F;

                        if (onGround) {
                            IBlockState underState = world.getBlockState(checkPos.setPos(posX, getEntityBoundingBox().minY - 1.0D, posZ));
                            friction = underState.getBlock().getSlipperiness(underState, world, checkPos, this) * 0.91F;

                            if (isSlipperyMob)
                                friction *= 0.9;
                        }

                        if (isOnLadder()) {
                            motionX = MathHelper.clamp(motionX, -0.15000000596046448D, 0.15000000596046448D);
                            motionZ = MathHelper.clamp(motionZ, -0.15000000596046448D, 0.15000000596046448D);
                            fallDistance = 0.0F;

                            if (motionY < -0.15D)
                                motionY = -0.15D;

                            if (isSneaking() && motionY < 0.0D)
                                motionY = 0.0D;
                        }

                        move(MoverType.SELF, motionX, motionY, motionZ);

                        if (collidedHorizontally && isOnLadder())
                            motionY = 0.2D;

                        if (isPotionActive(MobEffects.LEVITATION)) {
                            motionY += (0.05D * (double)(getActivePotionEffect(MobEffects.LEVITATION).getAmplifier() + 1) - motionY) * 0.2D;
                        }
                        else {
                            checkPos.setPos(posX, 0.0D, posZ);

                            if (!world.isRemote || world.isBlockLoaded(checkPos) && world.getChunk(checkPos).isLoaded()) {
                                if (!hasNoGravity())
                                    motionY -= 0.08D;
                            }
                            else if (posY > 0.0D) {
                                motionY = -0.1D;
                            }
                            else {
                                motionY = 0.0D;
                            }
                        }

                        motionY *= 0.9800000190734863D;
                        motionX *= (double)friction;
                        motionZ *= (double)friction;

                        checkPos.release();
                    }
                }
                else {
                    moveRelative(strafe, vertical, forward, 0.02F);
                    move(MoverType.SELF, motionX, motionY, motionZ);

                    motionX *= 0.5D;
                    motionY *= 0.5D;
                    motionZ *= 0.5D;

                    if (!hasNoGravity())
                        motionY -= 0.02D;

                    if (collidedHorizontally && isOffsetPositionInLiquid(motionX, motionY + 0.6000000238418579D, motionZ))
                        motionY = 0.30000001192092896D;
                }
            }
            else {
                float waterDrag = getWaterSlowDown();
                float friction = 0.02F;
                float depthStriderMod = (float)EnchantmentHelper.getDepthStriderModifier(this);

                if (depthStriderMod > 3.0F)
                    depthStriderMod = 3.0F;

                if (!onGround)
                    depthStriderMod *= 0.5F;

                if (depthStriderMod > 0.0F) {
                    waterDrag += (0.54600006F - waterDrag) * depthStriderMod / 3.0F;
                    friction += (getAIMoveSpeed() - friction) * depthStriderMod / 3.0F;
                }

                moveRelative(strafe, vertical, forward, friction);
                move(MoverType.SELF, motionX, motionY, motionZ);

                motionX *= (double)waterDrag;
                motionY *= 0.800000011920929D;
                motionZ *= (double)waterDrag;

                if (!hasNoGravity())
                    motionY -= 0.02D;

                if (collidedHorizontally && isOffsetPositionInLiquid(motionX, motionY + 0.6000000238418579D, motionZ))
                    motionY = 0.30000001192092896D;
            }
        }

        prevLimbSwingAmount = limbSwingAmount;
        double movedX = posX - prevPosX;
        double movedZ = posZ - prevPosZ;
        double movedY = this instanceof net.minecraft.entity.passive.EntityFlying ? posY - prevPosY : 0.0D;
        float moveDistance = MathHelper.sqrt(movedX * movedX + movedY * movedY + movedZ * movedZ) * 4.0F;

        if (moveDistance > 1.0F)
            moveDistance = 1.0F;

        limbSwingAmount += (moveDistance - limbSwingAmount) * 0.4F;
        limbSwing += limbSwingAmount;
    }
}
