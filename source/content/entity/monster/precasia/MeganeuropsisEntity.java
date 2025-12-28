package net.tslat.aoa3.content.entity.monster.precasia;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAEntityStats;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.entity.ai.movehelper.AirborneMoveControl;
import net.tslat.aoa3.content.entity.base.AoAFlyingMeleeMob;
import net.tslat.aoa3.library.builder.EntitySpawnConditions;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetRandomFlyingTarget;
import net.tslat.smartbrainlib.api.core.behaviour.custom.target.TargetOrRetaliate;
import net.tslat.tme.api.object.builder.EffectBuilder;
import org.jetbrains.annotations.Nullable;

public class MeganeuropsisEntity extends AoAFlyingMeleeMob<MeganeuropsisEntity> {
	public MeganeuropsisEntity(EntityType<? extends MeganeuropsisEntity> entityType, Level world) {
		super(entityType, world);
	}

    @Override
    protected MoveControl createMoveControl() {
        return new MeganeuropsisMoveControl(this);
    }

    @Override
	public BrainActivityGroup<? extends MeganeuropsisEntity> getIdleTasks() {
		return BrainActivityGroup.idleTasks(
				new TargetOrRetaliate<>()
						.useMemory(MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER)
						.attackablePredicate(target -> DamageUtil.isAttackable(target) && !isAlliedTo(target)),
				new SetRandomFlyingTarget<>()
						.verticalWeight(entity -> rand().oneInNChance(10) ? -1 : 0)
						.setRadius(4, 4)
                        .speedModifier(1.15f));
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return AoASounds.ENTITY_MEGANEUROPSIS_HURT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_MEGANEUROPSIS_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_MEGANEUROPSIS_AMBIENT.get();
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity livingTarget && rand().oneInNChance(10))
			EntityUtil.applyPotions(livingTarget, this, new EffectBuilder(MobEffects.CONFUSION, 120).hideParticles());
	}

	public static SpawnPlacements.SpawnPredicate<MeganeuropsisEntity> spawnRules(EntityType<MeganeuropsisEntity> entityType) {
		return EntitySpawnConditions.createDayNightMonster(entityType).noLowerThanY(AoADimensions.PRECASIA, 65).difficultyBasedSpawnChance(0.05f);
	}

	public static AoAEntityStats.AttributeBuilder entityStats(EntityType<MeganeuropsisEntity> entityType) {
		return AoAEntityStats.AttributeBuilder.createMonster(entityType)
				.health(19)
				.meleeStrength(5)
				.moveSpeed(0)
				.flyingSpeed(0.2875f)
				.aggroRange(8)
				.armour(1)
				.followRange(16);
	}

    private static class MeganeuropsisMoveControl extends AirborneMoveControl {
        protected int repathCooldown = 0;

        public MeganeuropsisMoveControl(Mob mob) {
            super(mob);

            canHover();
            strafeSpeedPenalty(0.5f);
        }

        @Override
        public void tick() {
            if (this.mob.tickCount > 0) {
                if (this.repathCooldown > 0) {
                    this.operation = Operation.WAIT;
                    this.repathCooldown--;

                    this.mob.setDeltaMovement(this.mob.getDeltaMovement().scale(0.5f));
                }
            }

            super.tick();
        }

        @Override
        protected void tickMoveTo() {
            this.operation = Operation.WAIT;
            double xDelta = this.wantedX - this.mob.getX();
            double yDelta = this.wantedY - this.mob.getY();
            double zDelta = this.wantedZ - this.mob.getZ();

            if (xDelta * xDelta + yDelta * yDelta + zDelta * zDelta < 0.9f) {
                this.mob.setYya(0);
                this.mob.setZza(0);
                this.repathCooldown = this.mob.getTarget() != null ? 0 : this.mob.getRandom().nextIntBetweenInclusive(15, 25);

                return;
            }

            float yRot = (float)(Mth.atan2(zDelta, xDelta) * Mth.RAD_TO_DEG) - 90;
            float moveSpeed = getMoveSpeed();

            this.mob.setNoGravity(true);
            this.mob.setSpeed(moveSpeed);
            this.mob.setYRot(rotClamped(this.mob.getYRot(), yRot, 90f));

            double lateralDist = Math.sqrt(xDelta * xDelta + zDelta * zDelta);

            if (Math.abs(yDelta) > 0.75f || Math.abs(lateralDist) > 0.75f) {
                double angle = Mth.atan2(yDelta, lateralDist) * -Mth.RAD_TO_DEG;

                this.mob.setXRot(rotClamped(this.mob.getXRot(), (float)angle, this.maxTurn));

                if (Math.abs(yDelta) > 0.75f)
                    this.mob.setYya(yDelta > 0 ? moveSpeed : -moveSpeed);
            }
            else {
                this.mob.setSpeed(0);
            }
        }
    }
}
