package net.tslat.aoa3.entity.mobs.mysterium;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.base.AoARangedMob;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.projectiles.mob.EntityWitherBall;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nullable;

public class EntityFungik extends AoARangedMob {
	public static final float entityWidth = 0.875f;

	public EntityFungik(World world) {
		super(world, entityWidth, 2.5f);
	}

	@Override
	public float getEyeHeight() {
		return 1.96875f;
	}

	@Override
	protected double getBaseKnockbackResistance() {
		return 0;
	}

	@Override
	protected double getBaseMaxHealth() {
		return 70;
	}

	@Override
	protected double getBaseProjectileDamage() {
		return 3.5;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.207;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobFungiLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobFungiDeath;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobFungiHit;
	}

	@Override
	protected int getSpawnChanceFactor() {
		return 3;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextInt(5) == 0)
			dropItem(ItemRegister.seedsMysticShroom, 1);

		if (rand.nextInt(7) == 0)
			dropItem(Item.getItemFromBlock(BlockRegister.bannerFungal), 1);

		if (rand.nextInt(100 - lootingMod) == 0)
			dropItem(ItemRegister.shroomStone, 1);

		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensMysterium, 1 + rand.nextInt(2 + lootingMod));
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	public void doProjectileImpactEffect(BaseMobProjectile projectile, Entity target) {
		if (target instanceof EntityLivingBase)
			((EntityLivingBase)target).addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE, 90, 1, true, true));
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundsRegister.shotFungikFire;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return new EntityWitherBall(this, Enums.MobProjectileType.ENERGY);
	}
}
