package net.tslat.aoa3.entity.mobs.overworld;

import com.google.common.base.Predicate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityPincher extends AoAMeleeMob {
	public static final float entityWidth = 1f;

	public EntityPincher(World world) {
		super(world, 1f, 0.75f);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, false));
		this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0d));
		this.tasks.addTask(4, new EntityAIWander(this, 1.0d));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, AoAMinion.class, 10, true, false, (Predicate<AoAMinion>)minion -> minion.isTamed()));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseArmour() {
		return 4d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 25;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.29d;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobPincherLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobPincherDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobPincherHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityPincher;
	}

	@Override
	public boolean handleWaterMovement() {
		return false;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 55 && super.getCanSpawnHere();
	}

	@Override
	protected boolean canSpawnOnBlock(IBlockState block) {
		return super.canSpawnOnBlock(block) || block.getBlock() == Blocks.WATER;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isInWater() && getAttackTarget() != null && getAttackTarget().posY > posY)
			motionY = 0.25;

		if (isInWater()) {
			if (motionX > -1.100000023841858 && motionX < 1.100000023841858)
				motionX *= 1.100000023841858;

			if (motionZ > -1.100000023841858 && motionZ < 1.100000023841858)
				motionZ *= 1.100000023841858;
		}
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (!world.isRemote && isInWater() && target.isInWater())
			WorldUtil.createExplosion(this, world, 1.5f);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
