package net.tslat.aoa3.entity.mobs.dustopia;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;

import javax.annotation.Nullable;

public class EntityDuston extends AoAFlyingMeleeMob {
	public static final float entityWidth = 0.6f;

	public EntityDuston(World world) {
		super(world, entityWidth, 1.5f);
	}

	@Override
	public float getEyeHeight() {
		return 0.875f;
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
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobDustonHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensDustopia, 1);
	}

	@Override
	public void onDeath(DamageSource cause) {
		super.onDeath(cause);

		if (!world.isRemote) {
			for (int i = 0; i < 4; i++) {
				EntityDustStrider strider = new EntityDustStrider(this);

				world.spawnEntity(strider);
			}

			setDead();
		}
	}
}
