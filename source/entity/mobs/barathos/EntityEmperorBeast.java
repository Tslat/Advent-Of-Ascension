package net.tslat.aoa3.entity.mobs.barathos;

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
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

import static net.minecraft.entity.SharedMonsterAttributes.KNOCKBACK_RESISTANCE;

public class EntityEmperorBeast extends AoAMeleeMob {
	public static final float entityWidth = 1.7f;

	public EntityEmperorBeast(World world) {
		super(world, entityWidth, 6.8f);
	}

	@Override
	public float getEyeHeight() {
		return 5.3125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 250;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 9;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobEmperorBeastLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobEmperorBeastDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobEmperorBeastHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.mobEmperorBeastStep;
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase) {
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 350, 1, true, false));

			double resist = 1;
			IAttributeInstance attrib = ((EntityLivingBase)target).getEntityAttribute(KNOCKBACK_RESISTANCE);

			if (attrib != null)
				resist -= attrib.getAttributeValue();

			target.addVelocity(motionX * 21 * resist, 1.6 * resist, motionZ * 21 * resist);
			target.velocityChanged = true;
		}
	}
}
