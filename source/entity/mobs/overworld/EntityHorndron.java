package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityHorndron extends AoAMeleeMob {
	public static final float entityWidth = 1.7f;

	public EntityHorndron(World world) {
		super(world, entityWidth, 2.2f);
	}

	@Override
	public float getEyeHeight() {
		return 1.5625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.3d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.25;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobHorndronLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobHorndronDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobHorndronHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityHorndron;
	}

	@Override
	protected boolean isDaylightMob() {
		return true;
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		motionX *= 0.800000011920929;
		motionZ *= 0.800000011920929;
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
