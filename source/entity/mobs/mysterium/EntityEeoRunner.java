package net.tslat.aoa3.entity.mobs.mysterium;

import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;
import net.tslat.aoa3.entity.base.ai.mob.EntityAIFullPanic;

import javax.annotation.Nullable;

public class EntityEeoRunner extends AoAMeleeMob {
	public static final float entityWidth = 0.375f;

	public EntityEeoRunner(EntityEeo eeo) {
		this(eeo.world);

		setLocationAndAngles(eeo.posX, eeo.posY, eeo.posZ, eeo.rotationYaw, eeo.rotationPitch);
	}

	public EntityEeoRunner(World world) {
		super(world, entityWidth, 1.25f);
	}

	@Override
	public float getEyeHeight() {
		return 1.125f;
	}

	@Override
	protected void initEntityAI() {
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIFullPanic(this, 1.0d));
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 5;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 0;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
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
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(65 - lootingMod) == 0)
			dropItem(WeaponRegister.blasterDoomBringer, 1);
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote && ticksExisted >= 200) {
			EntityEeo eeo = new EntityEeo(this);

			world.spawnEntity(eeo);
			setDead();
		}
	}
}
