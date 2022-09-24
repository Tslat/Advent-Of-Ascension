package net.tslat.aoa3.content.entity.mob.nether;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.behavior.StopAttackingIfTargetInvalid;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.tslat.aoa3.client.render.AoAAnimations;
import net.tslat.aoa3.content.entity.base.AoAMeleeMob;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.ConditionlessAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;
import net.tslat.smartbrainlib.api.util.BrainUtils;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class LittleBamEntity extends AoAMeleeMob {
	public LittleBamEntity(EntityType<? extends Monster> entityType, Level world) {
		super(entityType, world);

		setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0);
		setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1 + 7 / 16f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.BAT_HURT;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BAT_DEATH;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.BAT_HURT;
	}

	@Override
	public BrainActivityGroup<AoAMeleeMob> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new StopAttackingIfTargetInvalid<>(target -> !target.isAlive() || (target instanceof Player pl && (pl.isCreative() || pl.isSpectator()))),
				new SetWalkTargetToAttackTarget<>(),
				new ConditionlessAttack<AoAMeleeMob>(getAttackSwingDuration())
						.attack(mob -> WorldUtil.createExplosion(this, level, 2f))
						.requiresTarget().startCondition(entity -> {
							LivingEntity target = BrainUtils.getTargetOfEntity(entity);

							return entity.getSensing().hasLineOfSight(target) && entity.isWithinMeleeAttackRange(target);
						})
		);
	}

	@Override
	protected int getAttackSwingDuration() {
		return 31;
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(AoAAnimations.genericWalkIdleController(this));
		animationData.addAnimationController(AoAAnimations.genericAttackController(this, AoAAnimations.ATTACK_POWERUP));
	}
}