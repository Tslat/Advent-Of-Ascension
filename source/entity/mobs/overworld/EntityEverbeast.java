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

public class EntityEverbeast extends AoAMeleeMob {
	public static final float entityWidth = 1.4f;

	public EntityEverbeast(World world) {
		super(world, entityWidth, 1.75f);
	}

	@Override
	public float getEyeHeight() {
		return 1.65f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 8;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.25;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobEverbeastLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobEverbeastHit;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobEverbeastHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityEverbeast;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 25 && super.getCanSpawnHere();
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 140, 0, true, true));
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
