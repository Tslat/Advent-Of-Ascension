package net.tslat.aoa3.entity.mob.lunalus;

import net.minecraft.entity.*;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.entity.projectile.arrow.CustomArrowEntity;
import net.tslat.aoa3.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.item.weapon.bow.BaseBow;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class LunarcherEntity extends AoAFlyingRangedMob {
	public LunarcherEntity(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Nullable
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		ILivingEntityData data = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

		setItemInHand(Hand.MAIN_HAND, new ItemStack(AoAWeapons.LUNAR_BOW.get()));
		setDropChance(EquipmentSlotType.MAINHAND, 0);

		return data;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.625f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_LUNARCHER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_LUNARCHER_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_LUNARCHER_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return SoundEvents.ARROW_SHOOT;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return null;
	}

	@Override
	public void performRangedAttack(@Nonnull LivingEntity target, float bowDamageFactor) {
		CustomArrowEntity projectile = new CustomArrowEntity(level, (BaseBow)AoAWeapons.LUNAR_BOW.get(), this, getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()));

		double distanceFactorX = target.getX() - projectile.getX();
		double distanceFactorY = target.getBoundingBox().minY + (target.getBbHeight() / 3) - projectile.getY();
		double distanceFactorZ = target.getZ() - projectile.getZ();
		double hyp = MathHelper.sqrt(distanceFactorX * distanceFactorX + distanceFactorZ * distanceFactorZ) * 0.05d;

		if (getShootSound() != null)
			playSound(getShootSound(), 1.0f, 1.0f);

		projectile.shoot(distanceFactorX, distanceFactorY + hyp, distanceFactorZ, 1.6f, (float)(4 - this.level.getDifficulty().getId()));
		level.addFreshEntity(projectile);
	}
}