package net.tslat.aoa3.entity.mobs.overworld;

import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityClownShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityClown extends AoARangedMob {
	public static final float entityWidth = 0.5f;

	public EntityClown(World world) {
		super(world, entityWidth, 2f);
		setHeldItem(EnumHand.MAIN_HAND, new ItemStack(WeaponRegister.blasterConfettiCannon));
	}

	@Override
	public float getEyeHeight() {
		return 1.8125f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0.0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 40;
	}

	@Override
	protected double getBaseProjectileDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobClownLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobClownDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobClownHit;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.shotClownFire;
	}

	@Override
	public boolean getCanSpawnHere() {
		return posY < 50 && super.getCanSpawnHere();
	}

	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(25 - lootingMod) == 0)
			dropItem(WeaponRegister.blasterConfettiCannon, 1);

		if (rand.nextInt(12 - lootingMod) == 0)
			dropItem(ItemRegister.realmstoneCeleve, 1);

		if (rand.nextInt(15 - lootingMod) == 0)
			dropItem(ItemRegister.realmstoneDeeplands, 1);
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityClownShot(this, Enums.MobProjectileType.ENERGY);
	}

	@Override
	protected boolean isOverworldMob() {
		return true;
	}
}
