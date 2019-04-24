package net.tslat.aoa3.entity.mobs.lunalus;

import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ArmourRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityRefluct extends AoAMeleeMob {
	public static final float entityWidth = 0.6f;

	public EntityRefluct(World world) {
		super(world, entityWidth, 2f);
	}

	@Override
	public float getEyeHeight() {
		return 1.84375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 150;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 13;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobRefluctLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobRefluctDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobRefluctHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(100 - lootingMod) == 0)
			dropItem(ItemRegister.milleniumUpgrader, 1);

		if (rand.nextInt(30 - lootingMod) == 0)
			switch (rand.nextInt(4)) {
				case 0:
					dropItem(ArmourRegister.SpacekingHelmet, 1);
					break;
				case 1:
					dropItem(ArmourRegister.SpacekingBody, 1);
					break;
				case 2:
					dropItem(ArmourRegister.SpacekingLegs, 1);
					break;
				case 3:
					dropItem(ArmourRegister.SpacekingBoots, 1);
					break;
			}
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.coinSilver, 1 + rand.nextInt(1 + lootingMod));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		for (Entity e : world.getEntitiesInAABBexcluding(this, getEntityBoundingBox().grow(3), entity -> entity instanceof IProjectile)) {
			e.motionX *= -1;
			e.motionY *= -1;
			e.motionZ *= -1;
		}
	}
}
