package net.tslat.aoa3.entity.mobs.mysterium;

import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntityFungback extends AoAMeleeMob {
	public static final float entityWidth = 1.0f;

	public EntityFungback(World world) {
		super(world, entityWidth, 0.875f);
	}

	@Override
	public float getEyeHeight() {
		return 0.71875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 90d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 8.5d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.27;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobFungiLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobFungiDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobFungiHit;
	}

	@Override
	public void addPotionEffect(PotionEffect effect) {
		if (effect.getPotion() != MobEffects.POISON)
			super.addPotionEffect(effect);
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityFungback;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (super.attackEntityFrom(source, amount) && !EntityUtil.isEnvironmentalDamage(source)) {
			EntityAreaEffectCloud effectCloud = new EntityAreaEffectCloud(world, posX, posY, posZ);

			effectCloud.setDuration(30);
			effectCloud.setRadius(1.5f);
			effectCloud.setOwner(this);
			effectCloud.setWaitTime(0);
			effectCloud.setColor(Enums.RGBIntegers.TOXIC_GREEN);
			effectCloud.setPotion(PotionTypes.POISON);
			effectCloud.addEffect(new PotionEffect(MobEffects.POISON, 40, 2, false, true));
			effectCloud.setRadiusPerTick(-(effectCloud.getRadius() - 0.5f) / (float)effectCloud.getDuration());

			world.spawnEntity(effectCloud);

			return true;
		}

		return false;
	}
}
