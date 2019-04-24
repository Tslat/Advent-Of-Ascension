package net.tslat.aoa3.entity.mobs.immortallis;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class EntityReaver extends AoAMeleeMob {
	public static final float entityWidth = 0.375f;

	public EntityReaver(World world) {
		super(world, entityWidth, 2.5f);
	}

	@Override
	public float getEyeHeight() {
		return 2.09375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 50;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
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

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			double resist = 1;
			IAttributeInstance attrib = ((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE);

			if (attrib != null)
				resist -= attrib.getAttributeValue();

			float motionFactor = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);

			target.addVelocity((motionX * 3 / motionFactor) * resist, 0.1 * resist, (motionZ * 3 / motionFactor) * resist);
			target.velocityChanged = true;
		}
	}
}
