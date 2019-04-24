package net.tslat.aoa3.entity.mobs.lunalus;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.item.weapon.bow.BaseBow;
import net.tslat.aoa3.library.Enums;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityLunarcher extends AoAFlyingRangedMob implements SpecialPropertyEntity {
	public static final float entityWidth = 0.6f;

	public EntityLunarcher(World world) {
		super(world, entityWidth, 2.0f);

		this.isImmuneToFire = true;
		this.mobProperties.add(Enums.MobProperties.FIRE_IMMUNE);
		setHeldItem(EnumHand.MAIN_HAND, new ItemStack(WeaponRegister.bowLunar));
	}

	@Override
	public float getEyeHeight() {
		return 1.625f;
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
	protected double getBaseProjectileDamage() {
		return 4;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.mobLunarcherLiving;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.mobLunarcherHit;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.mobLunarcherHit;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundEvents.ENTITY_ARROW_SHOOT;
	}

	@Override
	protected void dropSpecialItems(int lootingMod, DamageSource source) {
		if (rand.nextBoolean())
			dropItem(ItemRegister.tokensLunar, 1 + rand.nextInt(2 + lootingMod));

		if (rand.nextInt(100 - lootingMod) == 0)
			dropItem(ItemRegister.upgradeKitLunar, 1);
	}

	@Override
	protected void dropGuaranteedItems(int lootingMod, DamageSource source) {
		dropItem(ItemRegister.coinCopper, 5 + rand.nextInt(9 + lootingMod));
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source) {
		return source.isFireDamage();
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return null;
	}

	@Override
	public void attackEntityWithRangedAttack(@Nonnull EntityLivingBase target, float bowDamageFactor) {
		EntityHollyArrow projectile = new EntityHollyArrow(world, (BaseBow)WeaponRegister.bowLunar, this, getBaseProjectileDamage());

		double distanceFactorX = target.posX - projectile.posX;
		double distanceFactorY = target.getEntityBoundingBox().minY + (target.height / 3) - projectile.posY;
		double distanceFactorZ = target.posZ - projectile.posZ;
		double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

		if (getShootSound() != null)
			world.playSound(null, posX, posY, posZ, getShootSound(), SoundCategory.HOSTILE, 1.0f, 1.0f);

		projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getDifficultyId()));
		world.spawnEntity(projectile);
	}

	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}