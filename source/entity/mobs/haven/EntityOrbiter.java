package net.tslat.aoa3.entity.mobs.haven;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;

public class EntityOrbiter extends AoAMeleeMob {
	public static final float entityWidth = 0.5625f;

	public EntityOrbiter(World world) {
		super(world, entityWidth, 2.5f);
	}

	@Override
	public float getEyeHeight() {
		return 2.1875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 15;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 2;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobOrbiterLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobOrbiterDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobOrbiterHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return null;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensHaven, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(8), PredicateUtil.IS_VULNERABLE_PLAYER)) {
			if (!pl.isSneaking()) {
				pl.addVelocity(motionX * 0.05, 0.1, motionZ * 0.05);
				pl.fallDistance = -0.5f;
				pl.velocityChanged = true;
			}
		}
	}
}
