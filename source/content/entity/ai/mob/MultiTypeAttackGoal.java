package net.tslat.aoa3.content.entity.ai.mob;

import it.unimi.dsi.fastutil.objects.Object2IntFunction;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Consumer;

public class MultiTypeAttackGoal extends Goal {
	private final List<Goal> goals;
	private final Object2IntFunction<Goal> goalSelector;
	private Goal currentGoal;
	private int currentGoalIndex = 0;
	private Consumer<Goal> goalChangeConsumer = null;

	public MultiTypeAttackGoal(Object2IntFunction<Goal> goalSelector, Goal goal, Goal... otherGoals) {
		this.goals = new ArrayList<>(otherGoals.length + 1);
		this.goalSelector = goalSelector;

		this.goals.add(goal);
		this.goals.addAll(Arrays.asList(otherGoals));
		this.currentGoal = goal;
	}

	public MultiTypeAttackGoal onChange(Consumer<Goal> goalChangeConsumer) {
		this.goalChangeConsumer = goalChangeConsumer;

		return this;
	}

	@Override
	public boolean canUse() {
		return this.currentGoal.canUse();
	}

	@Override
	public boolean canContinueToUse() {
		if (checkAndSwitchGoal())
			return false;

		return this.currentGoal.canContinueToUse();
	}

	@Override
	public boolean isInterruptable() {
		return this.currentGoal.isInterruptable();
	}

	@Override
	public void start() {
		this.currentGoal.start();
	}

	@Override
	public void stop() {
		this.currentGoal.stop();
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return this.currentGoal.requiresUpdateEveryTick();
	}

	@Override
	public void tick() {
		this.currentGoal.tick();
	}

	@Override
	public void setFlags(EnumSet<Flag> flagSet) {
		this.currentGoal.setFlags(flagSet);
	}

	@Override
	public EnumSet<Flag> getFlags() {
		checkAndSwitchGoal();

		return this.currentGoal.getFlags();
	}

	@Override
	protected int adjustedTickDelay(int ticks) {
		return this.currentGoal.requiresUpdateEveryTick() ? ticks : reducedTickDelay(ticks);
	}

	private boolean checkAndSwitchGoal() {
		int selectedGoal = this.goalSelector.apply(this.currentGoal);

		if (selectedGoal == this.currentGoalIndex)
			return false;

		this.currentGoal.stop();

		this.currentGoal = this.goals.get(selectedGoal);
		this.currentGoalIndex = selectedGoal;

		if (this.goalChangeConsumer != null)
			this.goalChangeConsumer.accept(this.currentGoal);

		return true;
	}
}
