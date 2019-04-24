package net.tslat.aoa3.entity.mobs.precasia;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class EntityGiantSlug extends AoAMeleeMob {
	public static final float entityWidth = 1.2f;

	public EntityGiantSlug(World world) {
		super(world, entityWidth, 1.5625f);
	}

	@Override
	public float getEyeHeight() {
		return 1.125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.1;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 70;
	}

	@Override
	protected double getBaseMeleeDamage() {
		return 5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.2875;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobGiantSlugLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobGiantSlugDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobGiantSlugHit;
	}

	@Override
	protected SoundEvent getStepSound() {
		return SoundsRegister.mobGiantSlugStep;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensPrecasian, 1);

		if (rand.nextInt(15 - lootingMod) == 0)
			dropItem(ItemRegister.realmstoneGardencia, 1);

		if (rand.nextInt(55 - lootingMod) == 0)
			dropItem(WeaponRegister.cannonUltraCannon, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote && world.getGameRules().getBoolean("mobGriefing")) {
			if (world.getBlockState(getPosition().down()).isOpaqueCube() && world.getBlockState(getPosition()).getMaterial().isReplaceable())
				world.setBlockState(getPosition(), BlockRegister.giantSnailAcid.getDefaultState());
		}
	}
}
