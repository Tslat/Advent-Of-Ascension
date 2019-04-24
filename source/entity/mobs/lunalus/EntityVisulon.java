package net.tslat.aoa3.entity.mobs.lunalus;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;

import javax.annotation.Nullable;

public class EntityVisulon extends AoAFlyingMeleeMob {
	public static final float entityWidth = 1.5f;

	public EntityVisulon(World world) {
		super(world, entityWidth, 2.625f);
	}

	@Override
	public float getEyeHeight() {
		return 1.3125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 150;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 3;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobVisulonLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobVisularDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobVisularHit;
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 5;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.observingEye, 1);
		dropItem(ItemRegister.realmstoneLunalus, 1);
	}
}
