package net.tslat.aoa3.entity.mobs.haven;

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
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nullable;

public class EntityOrbiter extends AoAMeleeMob {
	public static final float entityWidth = 0.5625f;

	public EntityOrbiter(World world) {
		super(world, entityWidth, 2.5f);
	}

	@Override
	public float getEyeHeight() {
		return 2.1875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 83;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 7.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_ORBITER_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_ORBITER_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_ORBITER_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityOrbiter;
	}

	@Override
	protected SoundEvent getStepSound() {
		return null;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (!world.isRemote && target instanceof EntityLivingBase) {
			switch (rand.nextInt(4)) {
				case 0:
					((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 40, 0, true, true));
					break;
				case 1:
					((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.POISON, 60, 0, true, true));
					break;
				case 2:
					target.setFire(3);
					break;
				case 3:
					EntityUtil.healEntity((EntityLivingBase)target, 3);
					break;
			}
		}
	}
}
