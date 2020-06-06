package net.tslat.aoa3.entity.mobs.lelyetia;

import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityZhinx extends AoAMeleeMob {
	public static final float entityWidth = 0.6f;

	public EntityZhinx(World world) {
		super(world, entityWidth, 0.6875f);
	}

	@Override
	public float getEyeHeight() {
		return 0.59375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0d;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60d;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 6.5d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.28d;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_ZHINX_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_ZHINX_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_ZHINX_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityZhinx;
	}
}
