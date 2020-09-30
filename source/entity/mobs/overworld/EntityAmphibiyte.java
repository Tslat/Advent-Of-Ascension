package net.tslat.aoa3.entity.mobs.overworld;

import com.google.common.base.Predicate;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minions.AoAMinion;

import javax.annotation.Nullable;

public class EntityAmphibiyte extends AoAMeleeMob {
	public static final float entityWidth = 0.95f;

	public EntityAmphibiyte(World world) {
		super(world, entityWidth, 1.25f);

		if (navigator instanceof PathNavigateGround)
			((PathNavigateGround)navigator).setCanSwim(true);
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, false));
		this.tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0d));
		this.tasks.addTask(4, new EntityAIWander(this, 1.0d));
		this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 16.0f));
		this.tasks.addTask(5, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, AoAMinion.class, 10, true, false, (Predicate<AoAMinion>)minion -> minion.isTamed()));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 37d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 3.5d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.28d;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.98f;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_AMPHIBIYTE_LIVING;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_AMPHIBIYTE_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_AMPHIBIYTE_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityAmphibiyte;
	}

	@Override
	protected boolean isValidLightLevel() {
		return true;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isInWater()) {
			if (navigator instanceof PathNavigateGround)
				navigator = new PathNavigateSwimmer(this, world);

			if (getAttackTarget() != null && posY < getAttackTarget().posY)
				motionY = 0.25;

			fallDistance = -0.5f;
		}
		else if (navigator instanceof PathNavigateSwimmer) {
			navigator = new PathNavigateGround(this, world);
		}
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
