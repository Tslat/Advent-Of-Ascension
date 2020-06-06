package net.tslat.aoa3.entity.mobs.lunalus;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.projectiles.arrow.EntityHollyArrow;
import net.tslat.aoa3.entity.projectiles.mob.BaseMobProjectile;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.TreeSet;

public class EntityLunarcher extends AoAFlyingRangedMob implements SpecialPropertyEntity {
	public static final float entityWidth = 0.6f;

	public EntityLunarcher(World world) {
		super(world, entityWidth, 2.0f);

		this.isImmuneToFire = true;
		this.mobProperties.add(Enums.MobProperties.GUN_IMMUNE);
		setHeldItem(EnumHand.MAIN_HAND, new ItemStack(WeaponRegister.LUNAR_BOW));
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
		return 118;
	}

	@Override
	public double getBaseProjectileDamage() {
		return 14.5d;
	}

	@Override
	protected double getBaseMovementSpeed() {
		return 0.1;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundsRegister.MOB_LUNARCHER_LIVING;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundsRegister.MOB_LUNARCHER_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundsRegister.MOB_LUNARCHER_HIT;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundEvents.ENTITY_ARROW_SHOOT;
	}

	@Nullable
	@Override
	protected ResourceLocation getLootTable() {
		return LootSystemRegister.entityLunarcher;
	}

	@Override
	protected boolean isSpecialImmuneTo(DamageSource source, int damage) {
		return EntityUtil.isGunDamage(source);
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return null;
	}

	@Override
	public void attackEntityWithRangedAttack(@Nonnull EntityLivingBase target, float bowDamageFactor) {
		EntityHollyArrow projectile = new EntityHollyArrow(world, WeaponRegister.LUNAR_BOW, this, getBaseProjectileDamage());

		double distanceFactorX = target.posX - projectile.posX;
		double distanceFactorY = target.getEntityBoundingBox().minY + (target.height / 3) - projectile.posY;
		double distanceFactorZ = target.posZ - projectile.posZ;
		double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

		if (getShootSound() != null)
			world.playSound(null, posX, posY, posZ, getShootSound(), SoundCategory.HOSTILE, 1.0f, 1.0f);

		projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - this.world.getDifficulty().getId()));
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