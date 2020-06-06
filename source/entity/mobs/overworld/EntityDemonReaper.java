package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
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

public class EntityDemonReaper extends AoAMeleeMob {
	public static final float entityWidth = 0.9f;

	public EntityDemonReaper(World world) {
		super(world, entityWidth, 2.4f);
	}

	@Override
	public float getEyeHeight() {
		return 1.225f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 6;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.25;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_REAPER_LIVING;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_REAPER_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_REAPER_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityDemonReaper;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 0, true, true));
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}
}
