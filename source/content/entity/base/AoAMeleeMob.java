package net.tslat.aoa3.content.entity.base;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.StopAttackingIfTargetInvalid;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.tslat.smartbrainlib.api.core.BrainActivityGroup;
import net.tslat.smartbrainlib.api.core.behaviour.custom.attack.AnimatableMeleeAttack;
import net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget;

public abstract class AoAMeleeMob<T extends AoAMeleeMob<T>> extends AoAMonster<T> {
	protected double attackReach;

	protected AoAMeleeMob(EntityType<? extends AoAMeleeMob> entityType, Level world) {
		super(entityType, world);

		this.attackReach = getBbWidth() * 1.75d + (getEyeHeight() / 3.6d * 0.25d);
	}

	@Override
	public BrainActivityGroup<T> getFightTasks() {
		return BrainActivityGroup.fightTasks(
				new StopAttackingIfTargetInvalid<>(target -> !target.isAlive() || (target instanceof Player pl && (pl.isCreative() || pl.isSpectator()))),
				new SetWalkTargetToAttackTarget<>(),
				new AnimatableMeleeAttack<>(getPreAttackTime()).attackInterval(entity -> getAttackSwingDuration()));
	}

	@Override
	public double getMeleeAttackRangeSqr(LivingEntity target) {
		double targetBBOffset = target.getBbWidth() * 0.5d;

		return this.attackReach * this.attackReach + targetBBOffset * targetBBOffset;
	}
}
