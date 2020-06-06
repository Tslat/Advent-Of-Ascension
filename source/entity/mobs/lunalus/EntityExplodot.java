package net.tslat.aoa3.entity.mobs.lunalus;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.utils.WorldUtil;

import javax.annotation.Nullable;

public class EntityExplodot extends AoAFlyingMeleeMob {
	public static final float entityWidth = 0.6f;

	public EntityExplodot(World world) {
		super(world, entityWidth, 1f);
	}

	@Override
	public float getEyeHeight() {
		return 0.71875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 30;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 1;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_EXPLODOT_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_EXPLODOT_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_EXPLODOT_HIT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityExplodot;
	}

	@Override
	protected void doMeleeEffect(Entity target) {
		if (!world.isRemote) {
			WorldUtil.createExplosion(this, world, 1.75f);
			setDead();
		}
	}
}
