package net.tslat.aoa3.entity.mobs.crystevia;

import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityConstructTerrorShot;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityConstructTerror extends AoAFlyingRangedMob {
	public static final float entityWidth = 1.0f;

	public EntityConstructTerror(World world) {
		super(world, entityWidth, 1.0f);
	}

	@Override
	public float getEyeHeight() {
		return 0.4375f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 80;
	}

	@Override
	protected double getBaseProjectileDamage() {
		return 15;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobCrystalConstructLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobCrystalConstructDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobCrystalConstructHit;
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 4;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(3) == 0)
			dropItem(ItemRegister.tokensCrystevia, 1 + rand.nextInt(2 + lootingMod));

		if (rand.nextBoolean())
			dropItem(ItemRegister.gemstonesGreen, 3);

		if (rand.nextInt(6) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerCrystal), 1);
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityConstructTerrorShot(this, Enums.MobProjectileType.PHYSICAL);
	}
}
