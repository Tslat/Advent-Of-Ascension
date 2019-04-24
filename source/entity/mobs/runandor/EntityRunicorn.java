package net.tslat.aoa3.entity.mobs.runandor;

import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityRunicorn extends AoAMeleeMob {
	public static final float entityWidth = 0.6875f;

	public EntityRunicorn(EntityRunicornRider rider) {
		this(rider.world);

		setLocationAndAngles(rider.posX, rider.posY, rider.posZ, rider.rotationYaw, rider.rotationPitch);
	}

	public EntityRunicorn(World world) {
		super(world, entityWidth, 2f);

		setSlipperyMovement();
		setAIMoveSpeed(1.6f);
	}

	@Override
	public float getEyeHeight() {
		return 1.6875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.5;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 60;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobRainicornLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobRainicornDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobRainicornHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensRunandor, 1 + rand.nextInt(2 + lootingMod));

		if (rand.nextInt(7) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerRunic), 1);
	}
}
