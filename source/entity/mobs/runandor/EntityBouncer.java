package net.tslat.aoa3.entity.mobs.runandor;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityBouncer extends AoAMeleeMob {
	public static final float entityWidth = 0.5f;
	private int jumpCooldown = 70;

	public EntityBouncer(World world) {
		super(world, entityWidth, 1.4375f);

		rand.setSeed(getUniqueID().getMostSignificantBits());
		jumpCooldown = rand.nextInt(80) + 40;
	}

	@Override
	public float getEyeHeight() {
		return 1.1875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 110;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 11;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_BOUNCER_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_BOUNCER_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_BOUNCER_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityBouncer;
	}

	@Override
	public void fall(float distance, float damageMultiplier) {}

	@Override
	protected float getJumpUpwardsMotion() {
		return jumpCooldown == 0 ? 1.2f : super.getJumpUpwardsMotion();
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isDead)
			return;

		if (jumpCooldown > 0) {
			jumpCooldown--;
		}
		else {
			jump();
			jumpCooldown = 70;
		}
	}
}
