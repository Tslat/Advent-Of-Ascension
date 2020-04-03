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
import net.tslat.aoa3.entity.base.ai.mob.EntityAIFullPanic;

import javax.annotation.Nullable;

public class EntityEeo extends AoAMeleeMob {
	public static final float entityWidth = 0.375f;

	public EntityEeo(World world) {
		super(world, entityWidth, 1.25f);
	}

	@Override
	protected void initEntityAI() {
		super.initEntityAI();

		tasks.addTask(1, new EntityAIFullPanic(this, 200, 1.1d));
	}

	@Override
	public float getEyeHeight() {
		return 1.125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 45;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 7.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.3;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobHunchLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobHunchDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobHunchHit;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityEeo;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 80, 2, true, true));
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (super.attackEntityFrom(source, amount)) {
			if (!world.isRemote && source != DamageSource.OUT_OF_WORLD) {
				if (!world.getEntitiesWithinAABB(EntityLivingBase.class, getEntityBoundingBox().grow(20), entity -> entity instanceof EntitySpiritGuardian || entity instanceof EntitySpiritProtector).isEmpty())
					return true;

				if (rand.nextBoolean()) {
					EntitySpiritGuardian guardian = new EntitySpiritGuardian(this);

					world.spawnEntity(guardian);
				} else {
					EntitySpiritProtector protector = new EntitySpiritProtector(this);

					world.spawnEntity(protector);
				}
			}

			return true;
		}

		return false;
	}
}
