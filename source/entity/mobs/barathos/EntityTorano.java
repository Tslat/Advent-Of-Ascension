package net.tslat.aoa3.entity.mobs.barathos;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;

public class EntityTorano extends AoAMeleeMob {
	public static final float entityWidth = 0.7f;

	public EntityTorano(World world) {
		super(world, entityWidth, 1.4f);
	}

	@Override
	public float getEyeHeight() {
		return 1.3125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60;
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
		return SoundsRegister.mobToranoLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobToranoDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobToranoHit;
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.coinCopper, 3 + rand.nextInt(2 + lootingMod));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(7), PredicateUtil.IS_VULNERABLE_PLAYER)) {
			if (!pl.isSneaking())
				pl.addVelocity(motionX * 3.5, motionY * 0.5, motionZ * 3.5);
		}
	}
}
