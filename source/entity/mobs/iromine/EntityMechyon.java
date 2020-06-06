package net.tslat.aoa3.entity.mobs.iromine;

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

public class EntityMechyon extends AoAMeleeMob {
	public static final float entityWidth = 0.75f;

	public EntityMechyon(World world) {
		super(world, entityWidth, 1.5f);
	}

	@Override
	public float getEyeHeight() {
		return 1.45f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.15;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 85;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 11;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.295;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_MECHYON_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_MECHYON_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_MECHYON_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityMechyon;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase && rand.nextInt(5) == 0)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 50, true, true));
	}
}
