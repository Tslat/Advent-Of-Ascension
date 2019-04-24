package net.tslat.aoa3.entity.mobs.haven;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;

import javax.annotation.Nullable;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class EntitySurveyor extends AoAFlyingMeleeMob {
	public static final float entityWidth = 1.375f;

	public EntitySurveyor(World world) {
		super(world, entityWidth, 2.3f);
	}

	@Override
	public float getEyeHeight() {
		return 1.5f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 20;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobSurveyorLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobSurveyorDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobSurveyorHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensHaven, 1);

		if (rand.nextInt(30 - lootingMod) == 0)
			dropItem(ItemRegister.realmstoneLelyetia, 1);

		if (rand.nextInt(100 - lootingMod) == 0)
			dropItem(ItemRegister.upgradeKitLight, 1);

		if (rand.nextInt(70 - lootingMod) == 0)
			dropItem(WeaponRegister.gunMinigun, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			double resist = 1;
			IAttributeInstance attrib = ((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE);

			if (attrib != null)
				resist -= attrib.getAttributeValue();

			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 50, 0, true, true));
			target.addVelocity(motionX * 3.5 * resist, motionY * 0.2 * resist, motionZ * 3.5 * resist);
			target.velocityChanged = true;
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		EntityLivingBase target = getAttackTarget();

		if (target != null && !target.isSneaking())
			target.addVelocity(Math.signum(posX - target.posX) * 0.029, 0, Math.signum(posZ - target.posZ) * 0.029);
	}
}
