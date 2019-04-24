package net.tslat.aoa3.entity.mobs.barathos;

import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityNospike extends AoAMeleeMob {
	public static final float entityWidth = 1.0f;

	public EntityNospike(World world) {
		super(world, entityWidth, 1.25f);
		setSlipperyMovement();
		setAIMoveSpeed(3.7f);
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
		return 3;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.329;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobNospikeLiving;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobNospikeDeath;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobNospikeHit;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(40 - lootingMod) == 0)
			dropItem(WeaponRegister.gunDestructionRifle, 1);

		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensBaron, 1 + rand.nextInt(2 + lootingMod));

		if (rand.nextInt(200 - lootingMod) == 0)
			dropItem(ItemRegister.upgradeKitRocky, 1);

		if (rand.nextInt(8) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerBaron), 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}
}
