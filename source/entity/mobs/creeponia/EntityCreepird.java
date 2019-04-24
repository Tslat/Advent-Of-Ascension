package net.tslat.aoa3.entity.mobs.creeponia;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;

import javax.annotation.Nullable;

public class EntityCreepird extends AoAFlyingMeleeMob {
	public static final float entityWidth = 0.5f;

	public EntityCreepird(World world) {
		super(world, entityWidth, 0.6875f);
	}

	@Override
	public float getEyeHeight() {
		return 0.46875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobCreepirdLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobCreepirdDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobCreepirdHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(3) == 0)
			dropItem(ItemRegister.tokensCreeponia, 1 + rand.nextInt(3 + lootingMod));
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 2 + rand.nextInt(5 + lootingMod));
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		world.createExplosion(this, posX, posY, posZ, 1.5f, false);
	}
}
