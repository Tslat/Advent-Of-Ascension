package net.tslat.aoa3.entity.mobs.abyss;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityMagicBall;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityWebReaper extends AoARangedMob {
	public static float entityWidth = 0.75f;

	public EntityWebReaper(World world) {
		super(world, entityWidth, 3.5625f);
	}

	@Override
	public float getEyeHeight() {
		return 2.375f;
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
	public double getBaseProjectileDamage() {
		return 18;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobWebReaperLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobWebReaperDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobWebReaperHit;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.shotWebReaperFire;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensAbyss, 1 + rand.nextInt(1 + lootingMod));

		if (rand.nextInt(40 - lootingMod) == 0)
			dropItem(WeaponRegister.staffWeb, 1);

		if (rand.nextInt(20 - lootingMod) == 0)
			dropItem(ItemRegister.bookOfShadows, 1);

		if (rand.nextInt(40 - lootingMod) == 0)
			dropItem(WeaponRegister.blasterSpiritShower, 1);

		if (rand.nextInt(150 - lootingMod) == 0)
			dropItem(ItemRegister.upgradeKitAbyssal, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityMagicBall(this, Enums.MobProjectileType.ENERGY);
	}
}
