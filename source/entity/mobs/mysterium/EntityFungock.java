package net.tslat.aoa3.entity.mobs.mysterium;

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

public class EntityFungock extends AoAMeleeMob {
	public static final float entityWidth = 0.875f;

	public EntityFungock(World world) {
		super(world, entityWidth, 2.125f);
	}

	@Override
	public float getEyeHeight() {
		return 1.1875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.15d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 90d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 9d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_FUNGI_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_FUNGI_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_FUNGI_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityFungock;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.POISON, 100, 0, false, true));
	}
}
