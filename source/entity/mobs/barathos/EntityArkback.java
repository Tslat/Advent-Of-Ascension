package net.tslat.aoa3.entity.mobs.barathos;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;

public class EntityArkback extends AoAMeleeMob {
	public static final float entityWidth = 3.8f;

	public EntityArkback(World world) {
		super(world, entityWidth, 3f);
	}

	@Override
	public float getEyeHeight() {
		return 2.4375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 200;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 11;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobArkbackLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobArkbackDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobArkbackHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.veryHeavyStep;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(30 - lootingMod) == 0)
			dropItem(WeaponRegister.greatbladeBaron, 1);

		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensBaron, 1 + rand.nextInt(2 + lootingMod));
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (isAIDisabled())
			return;

		for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, this.getEntityBoundingBox().grow(4), PredicateUtil.IS_VULNERABLE_PLAYER)) {
			if (pl.getHealth() > 0)
				pl.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0f);
		}
	}
}
