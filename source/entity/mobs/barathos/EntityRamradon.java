package net.tslat.aoa3.entity.mobs.barathos;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntityRamradon extends AoAMeleeMob {
	public static final float entityWidth = 0.875f;

	public EntityRamradon(World world) {
		super(world, entityWidth, 1f);
	}

	@Override
	public float getEyeHeight() {
		return 0.875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 45;
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
		return SoundsRegister.mobRamradonLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobRamradonDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobRamradonHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.veryHeavyStep;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(65 - lootingMod) == 0)
			dropItem(WeaponRegister.shotgunDestructionShotgun, 1);

		if (rand.nextInt(200 - lootingMod) == 0)
			dropItem(ItemRegister.upgradeKitRocky, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			if (EntityUtil.getCurrentHealthPercent((EntityLivingBase)target) < 0.5f)
				heal(15);
		}
	}
}
