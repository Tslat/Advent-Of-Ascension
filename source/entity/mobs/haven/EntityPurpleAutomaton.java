package net.tslat.aoa3.entity.mobs.haven;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.minions.AoAMinion;

import javax.annotation.Nullable;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class EntityPurpleAutomaton extends AoAMeleeMob {
	public static final float entityWidth = 1.125f;

	public EntityPurpleAutomaton(World world) {
		super(world, entityWidth, 2.125f);
	}

	@Override
	protected void initEntityAI() {
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, false));
		tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0d));
		tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0d));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
		tasks.addTask(5, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, AoAMinion.class, 10, true, false, (Predicate<AoAMinion>)minion -> minion.isTamed() && minion.isGlowing()));
		targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 10, true, false, (Predicate<EntityPlayer>)player -> player.isGlowing()));
	}

	@Override
	public float getEyeHeight() {
		return 1.84375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.9;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 108;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 10.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.25d;
	}

	@Override
	protected double getBaseArmour() {
		return 13;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobAutomatonLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobAutomatonDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobAutomatonHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.mobGolemStep;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityPurpleAutomaton;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			double resist = 1;
			IAttributeInstance attrib = ((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE);

			if (attrib != null)
				resist -= attrib.getAttributeValue();

			target.addVelocity(motionX * 2 * resist, motionY * 0.5 * resist, motionZ * 2 * resist);
			target.velocityChanged = true;
		}
	}
}
