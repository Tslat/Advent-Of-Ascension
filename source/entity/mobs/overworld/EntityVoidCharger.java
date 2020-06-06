package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityVoidCharger extends AoAMeleeMob {
	public static final float entityWidth = 0.625f;

	public EntityVoidCharger(World world) {
		super(world, entityWidth, 1.5f);
		this.setSlipperyMovement();
		this.setAIMoveSpeed(1.275f);
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.0f;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 20;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_CHARGER_LIVING;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_CHARGER_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_CHARGER_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityVoidCharger;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 20 && super.getCanSpawnHere();
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 150, 0, true, true));
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
