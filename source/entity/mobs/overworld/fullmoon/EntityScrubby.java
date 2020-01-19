package net.tslat.aoa3.entity.mobs.overworld.fullmoon;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIHopAtTarget;
import net.tslat.aoa3.entity.minions.AoAMinion;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityScrubby extends AoAMeleeMob {
	public static final float entityWidth = 0.6875f;

	public EntityScrubby(World world) {
		super(world, entityWidth, 1.125f);
	}

	@Override
	public float getEyeHeight() {
		return 0.9f;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, false));
		this.tasks.addTask(3, new EntityAIHopAtTarget(this, 0.9f));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0d));
		this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0d, false));
		this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0d));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, AoAMinion.class, 10, true, false, (Predicate<AoAMinion>)minion -> minion.isTamed()));
		this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 35;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.4;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobScrubbyLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobScrubbyHit;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobScrubbyHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityScrubby;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		target.setFire(3);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Nonnull
	@Override
	protected Enums.CreatureEvents getEventRequirement() {
		return Enums.CreatureEvents.FULL_MOON;
	}
}
