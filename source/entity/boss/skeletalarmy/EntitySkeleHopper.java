package net.tslat.aoa3.entity.boss.skeletalarmy;

import com.google.common.base.Predicate;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minions.AoAMinion;

import javax.annotation.Nullable;

public class EntitySkeleHopper extends AoAMeleeMob {
	public static final float entityWidth = 0.6f;

	public EntitySkeleHopper(World world, BlockPos armyBlockPos) {
		this(world);

		int posX = armyBlockPos.getX() - 3 + rand.nextInt(6);
		int posZ = armyBlockPos.getZ() - 3 + rand.nextInt(6);
		int posY = world.getHeight(posX, posZ);

		setLocationAndAngles(posX, posY, posZ, rand.nextFloat() * 360, 0);
	}

	public EntitySkeleHopper(World world) {
		super(world, entityWidth, 1.2f);
	}

	@Override
	public float getEyeHeight() {
		return 0.9375f;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAILeapAtTarget(this, 1));
		this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0d, false));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0d));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, AoAMinion.class, 10, true, false, (Predicate<AoAMinion>)minion -> minion.isTamed()));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 16;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
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

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	@Override
	protected SoundEvent getStepSound() {
		return null;
	}
}
