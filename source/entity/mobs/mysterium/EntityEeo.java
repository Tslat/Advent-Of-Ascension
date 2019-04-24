package net.tslat.aoa3.entity.mobs.mysterium;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityEeo extends AoAMeleeMob {
	public static final float entityWidth = 0.375f;

	public EntityEeo(EntityEeoRunner runner) {
		this(runner.world);

		setLocationAndAngles(runner.posX, runner.posY, runner.posZ, runner.rotationYaw, runner.rotationPitch);
	}

	public EntityEeo(World world) {
		super(world, entityWidth, 1.25f);
	}

	@Override
	public float getEyeHeight() {
		return 1.125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.7;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 1;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 1;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 3;
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

	@Override
	protected void doMeleeEffect(Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 80, 2, true, true));
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote && cause != DamageSource.OUT_OF_WORLD) {
			if (rand.nextBoolean()) {
				EntitySpiritGuardian guardian = new EntitySpiritGuardian(this);

				world.spawnEntity(guardian);
			}
			else {
				EntitySpiritProtector protector = new EntitySpiritProtector(this);

				world.spawnEntity(protector);
			}

			if (cause.getTrueSource() instanceof EntityLivingBase)
				setRevengeTarget((EntityLivingBase)cause.getTrueSource());

			EntityEeoRunner runner = new EntityEeoRunner(this);

			world.spawnEntity(runner);
			setDead();
		}
	}
}
