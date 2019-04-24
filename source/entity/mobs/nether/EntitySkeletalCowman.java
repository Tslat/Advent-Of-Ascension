package net.tslat.aoa3.entity.mobs.nether;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIAttackAdventBow;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntitySkeletalCowman extends AoARangedMob implements SpecialPropertyEntity {
    public static final float entityWidth = 0.6f;

    public EntitySkeletalCowman(World world) {
        super(world, entityWidth, 1.95f);

        this.isImmuneToFire = true;
        this.mobProperties.add(Enums.MobProperties.FIRE_IMMUNE);
        this.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BOW));
    }

    @Override
    public float getEyeHeight() {
        return 1.74f;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 1.0d));
        this.tasks.addTask(3, new EntityAIAttackAdventBow(this, 1.0d, 5, 10));
        this.tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0d));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, AoAMinion.class, 10, true, false, (Predicate<AoAMinion>) minion -> minion.isTamed()));
        this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
    }

    @Override
    protected double getBaseKnockbackResistance() {
        return 0;
    }

    @Override
    protected double getBaseMaxHealth() {
        return 60;
    }

    @Override
    protected double getBaseProjectileDamage() {
        return 12;
    }

    @Override
    protected double getBaseMovementSpeed() {
        return 0.207;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundsRegister.mobSkeletalCowmanLiving;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundsRegister.mobSkeletalCowmanHit;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundsRegister.mobSkeletalCowmanHit;
    }

    @Nullable
    @Override
    protected SoundEvent getShootSound() {
        return SoundEvents.ENTITY_ARROW_SHOOT;
    }

    @Override
    protected void dropSpecialItems(int lootingMod, DamageSource source) {
        if (rand.nextInt(7) == 0)
            dropItem(Item.getItemFromBlock(BlockRegister.bannerNether), 1);
    }

    @Override
    protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
        dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float bowDamageFactor) {
        EntityArrow arrow = new EntityTippedArrow(world, this);

        double distanceFactorX = target.posX - this.posX;
        double distanceFactorY = target.getEntityBoundingBox().minY + (double)(target.height / 3.0f) - arrow.posY;
        double distanceFactorZ = target.posZ - this.posZ;
        double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) + 0.2D;

        if (getShootSound() != null)
            world.playSound(null, posX, posY, posZ, getShootSound(), SoundCategory.HOSTILE, 1.0f, 1.0f);

        arrow.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getDifficultyId()));
        world.spawnEntity(arrow);
    }

    @Override
    protected BaseMobProjectile getNewProjectileInstance() {
        return null;
    }

    @Override
    protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {}

    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Nonnull
    @Override
    public TreeSet<Enums.MobProperties> getMobProperties() {
        return mobProperties;
    }
}
