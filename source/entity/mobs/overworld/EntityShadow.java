package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityShadow extends AoAMeleeMob {
	public static final float entityWidth = 0.6f;

	public EntityShadow(World world) {
		super(world, entityWidth, 1.75f);
	}

	@Override
	public float getEyeHeight() {
		return 1.5f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 1d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 0.5d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 2.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1095;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 45 && super.getCanSpawnHere();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_SHADOW_LIVING;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_SHADOW_HIT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_SHADOW_HIT;
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);
		transform();
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}

	private void transform() {
		if (!world.isRemote) {
			EntityShade shade = new EntityShade(world);

			shade.setLocationAndAngles(posX, posY, posZ, rotationYaw, rotationPitch);
			world.spawnEntity(shade);
		}

		this.setDead();
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}

}
