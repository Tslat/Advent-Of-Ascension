package net.tslat.aoa3.entity.mobs.lborean;

import com.google.common.base.Predicate;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityCyanShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityMermage extends AoARangedMob {
	public static final float entityWidth = 0.6f;

	public EntityMermage(World world) {
		super(world, entityWidth, 2.125f);
	}

	@Override
	public float getEyeHeight() {
		return 1.875f;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIMoveTowardsRestriction(this, 1.0d));
		this.tasks.addTask(3, new EntityAIAttackRanged(this, 1.0d, 20, 40, 32));
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
	protected double getBaseMaxHealth() {
		return 130;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 14;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobMermageLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobMermageDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobMermageHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityMermage;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isInWater() && getHealth() > 0 && getHealth() < getMaxHealth())
			heal(0.2f);
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.shotMermageFire;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityCyanShot(this, Enums.MobProjectileType.MAGIC);
	}
}
