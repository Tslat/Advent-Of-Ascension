package net.tslat.aoa3.entity.mobs.misc;

import com.google.common.base.Predicate;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIAttackAdventBow;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.item.weapon.bow.BaseBow;

import javax.annotation.Nullable;

public class EntitySeaSkeleton extends AoARangedMob {
	public static final float entityWidth = 0.55f;

	public EntitySeaSkeleton(World world) {
		super(world, entityWidth, 2.03125f);
		setHeldItem(EnumHand.MAIN_HAND, new ItemStack(WeaponRegister.SPEED_BOW));
	}

	public EntitySeaSkeleton(World world, double posX, double posY, double posZ) {
		this(world);
		setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360, 1.0f);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 1.0d));
		this.tasks.addTask(3, new EntityAIAttackAdventBow(this, 1.0d, 15, 7));
		this.tasks.addTask(4, new EntityAIWander(this, 1.0d));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, AoAMinion.class, 10, true, false, (Predicate<AoAMinion>)minion -> minion.isTamed()));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.23;
	}

	@Override
	protected double getBaseArmour() {
		return 1d;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_SKELETON_AMBIENT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SKELETON_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_SKELETON_HURT;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundEvents.ENTITY_SKELETON_SHOOT;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundEvents.ENTITY_SKELETON_STEP;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entitySeaSkeleton;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return null;
	}

	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float bowDamageFactor) {
		EntityHollyArrow projectile = new EntityHollyArrow(world, (BaseBow)WeaponRegister.SPEED_BOW, this, getBaseProjectileDamage());

		double distanceFactorX = target.posX - this.posX;
		double distanceFactorY = target.getEntityBoundingBox().minY + (double)(target.height / 3.0f) - projectile.posY;
		double distanceFactorZ = target.posZ - this.posZ;
		double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) + 0.2D;

		if (getShootSound() != null)
			world.playSound(null, posX, posY, posZ, getShootSound(), SoundCategory.HOSTILE, 1.0f, 1.0f);

		projectile.shoot(distanceFactorX, distanceFactorY + hyp * 0.20000000298023224D, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getId()));
		world.spawnEntity(projectile);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}
}
