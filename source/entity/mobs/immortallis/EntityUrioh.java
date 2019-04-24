package net.tslat.aoa3.entity.mobs.immortallis;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityUrioh extends AoAMeleeMob {
	public static final float entityWidth = 0.5f;

	public EntityUrioh(World world) {
		super(world, entityWidth, 0.9375f);
	}

	@Override
	public float getEyeHeight() {
		return height * 0.6f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 75;
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
		return SoundsRegister.mobApparitionLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobApparitionDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobApparitionHit;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (getHealth() < 10) {
			setSize(0.05f, 0.09375f);
		}
		else if (getHealth() < 25) {
			setSize(0.1f, 0.1875f);
		}
		else if (getHealth() < 40) {
			setSize(0.2f, 0.375f);
		}
		else if (getHealth() < 60) {
			setSize(0.3f, 0.5625f);
		}
		else {
			setSize(0.5f, 0.9375f);
		}
	}
}
