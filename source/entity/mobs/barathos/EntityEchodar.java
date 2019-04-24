package net.tslat.aoa3.entity.mobs.barathos;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;

import javax.annotation.Nullable;

public class EntityEchodar extends AoAFlyingMeleeMob {
	public static float entityWidth = 0.75f;

	public EntityEchodar(World world) {
		super(world, entityWidth, 0.75f);
	}

	@Override
	public float getEyeHeight() {
		return 0.625f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 35;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobEchodarLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobEchodarHit;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobEchodarDeath;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 20 && super.getCanSpawnHere();
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(40 - lootingMod) == 0)
			dropItem(WeaponRegister.gunEchoGull, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}
}
