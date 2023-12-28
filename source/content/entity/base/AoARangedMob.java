package net.tslat.aoa3.content.entity.base;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.PositionAndMotionUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableRangedAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.StayWithinDistanceOfAttackTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.StrafeTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.InvalidateAttackTarget;
import org.jetbrains.annotations.Nullable;


public abstract class AoARangedMob<T extends AoARangedMob<T>> extends AoAMonster<T> implements AoARangedAttacker {
	protected AoARangedMob(EntityType<? extends AoARangedMob> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	public BrainActivityGroup<T> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new InvalidateAttackTarget<>().invalidateIf((entity, target) -> !target.isAlive() || (target instanceof Player pl && pl.getAbilities().invulnerable) || distanceToSqr(target.position()) > Math.pow(getAttributeValue(Attributes.FOLLOW_RANGE), 2)),
				(isStrafingMob() ? new StrafeTarget<>() : new StayWithinDistanceOfAttackTarget<>()),
				new AnimatableRangedAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration()));
	}

	@Override
	public int calculateKillXp() {
		return !this.hasDrops ? 0 : (int)(5 + (getAttributeValue(Attributes.MAX_HEALTH) + getAttributeValue(Attributes.ARMOR) * 1.75f + getAttributeValue(Attributes.ARMOR_TOUGHNESS) * 1.5f + getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()) * 2) / 10f);
	}

	protected boolean isStrafingMob() {
		return false;
	}

	@Nullable
	protected abstract SoundEvent getShootSound();

	protected abstract BaseMobProjectile getNewProjectileInstance();

	@Override
	public void doRangedAttackEntity(@Nullable BaseMobProjectile projectile, Entity target) {
		if (projectile != null) {
			final boolean success = switch (projectile.getProjectileType()) {
				case MAGIC -> DamageUtil.doMagicProjectileAttack(this, projectile, target, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()));
				case GUN -> DamageUtil.doGunAttack(this, projectile, target, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()));
				case PHYSICAL -> DamageUtil.doProjectileAttack(this, projectile, target, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()));
				case ENERGY -> DamageUtil.doEnergyProjectileAttack(this, projectile, target, (float)getAttributeValue(AoAAttributes.RANGED_ATTACK_DAMAGE.get()));
			};

			if (success)
				onProjectileAttack(projectile, target);
		}
	}

	@Override
	public void doRangedAttackBlock(@Nullable BaseMobProjectile projectile, BlockState blockHit, BlockPos pos, Direction sideHit) {}

	public void onProjectileAttack(@Nullable BaseMobProjectile projectile, Entity target) {}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		BaseMobProjectile projectile = getNewProjectileInstance();

		projectile.setYRot(getYHeadRot());
		PositionAndMotionUtil.moveRelativeToFacing(projectile, 0, 0, 0);
		PositionAndMotionUtil.moveTowards(projectile, target.getEyePosition(), 1.6d, 4 - level().getDifficulty().getId());
		projectile.setDeltaMovement(PositionAndMotionUtil.accountForGravity(projectile.position(), projectile.getDeltaMovement(), target.position(), projectile.getGravity()));
		PositionAndMotionUtil.faceTowardsMotion(projectile);

		if (getShootSound() != null)
			level().playSound(null, getX(), getY(), getZ(), getShootSound(), SoundSource.HOSTILE, 1, 1);

		level().addFreshEntity(projectile);
	}
}
