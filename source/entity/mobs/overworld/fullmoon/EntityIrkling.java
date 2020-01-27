package net.tslat.aoa3.entity.mobs.overworld.fullmoon;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityIrkling extends AoAMeleeMob {
	public static final float entityWidth = 0.8f;
	private int jumpTimer = 80;

	public EntityIrkling(World world) {
		super(world, entityWidth, 1.6875f);

		rand.setSeed(entityUniqueID.getMostSignificantBits());
		jumpTimer = rand.nextInt(80) + 40;
	}

	@Override
	public float getEyeHeight() {
		return 1.32f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.31;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobIrklingLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobIrklingDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobIrklingHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityIrkling;
	}

	@Override
	protected float getJumpUpwardsMotion() {
		return jumpTimer == 0 ? 0.8f : super.getJumpUpwardsMotion();
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isDead)
			return;

		fallDistance = -0.5f;

		if (jumpTimer > 0)
			--jumpTimer;

		if (jumpTimer == 0) {
			jump();
			jumpTimer = 80;

			if (getAttackTarget() != null) {
				motionX = (getAttackTarget().posZ - posZ) * 0.0949999988079071;
				motionZ = (getAttackTarget().posZ - posZ) * 0.0949999988079071;
			}
		}
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
