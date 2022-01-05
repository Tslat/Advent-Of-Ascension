package net.tslat.aoa3.object.entity.brain.task;

import com.google.common.collect.ImmutableMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.util.BrainUtils;
import net.tslat.aoa3.util.EntityUtil;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class RetaliateOrTargetTask<E extends MobEntity> extends Task<E> {
	private final Predicate<E> canAttackPredicate;
	private final Predicate<Entity> retaliationPredicate;
	private final Function<E, Optional<? extends LivingEntity>> targetFinder;

	private LivingEntity toTarget = null;

	public RetaliateOrTargetTask(E owner) {
		this(owner2 -> true, entity -> owner.getTarget() == null && EntityUtil.Predicates.ATTACKABLE_ENTITY.test(entity), entity -> BrainUtils.getOptionalMemory(owner, MemoryModuleType.NEAREST_VISIBLE_TARGETABLE_PLAYER));
	}

	public RetaliateOrTargetTask(Predicate<E> canAttack, Predicate<Entity> shouldRetaliate, Function<E, Optional<? extends LivingEntity>> targetFinder) {
		super(ImmutableMap.of(MemoryModuleType.ATTACK_TARGET, MemoryModuleStatus.REGISTERED, MemoryModuleType.HURT_BY_ENTITY, MemoryModuleStatus.REGISTERED));

		this.retaliationPredicate = shouldRetaliate;
		this.targetFinder = targetFinder;
		this.canAttackPredicate = canAttack;
	}

	@Override
	protected boolean checkExtraStartConditions(ServerWorld pLevel, E owner) {
		if (!canAttackPredicate.test(owner))
			return false;

		toTarget = this.targetFinder.apply(owner).orElse(null);

		if (toTarget != null)
			return true;

		toTarget = BrainUtils.getLastAttacker(owner);

		return toTarget != null && this.retaliationPredicate.test(toTarget);
	}

	@Override
	protected void start(ServerWorld pLevel, E owner, long gameTime) {
		BrainUtils.setTargetOfEntity(owner, toTarget);
		BrainUtils.clearMemory(owner, MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE);

		toTarget = null;
	}
}
