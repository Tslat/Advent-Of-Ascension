package net.tslat.aoa3.content.entity.ai;

import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.tslat.aoa3.util.RandomUtil;

import java.util.function.Consumer;

public abstract class ExtendedGoal<T extends Mob> extends Goal {
	protected final T entity;

	private Consumer<ExtendedGoal<T>> onStart = goal -> {};
	private Consumer<ExtendedGoal<T>> onStop = goal -> {};
	private Consumer<ExtendedGoal<T>> onTelegraphStart = goal -> {};
	private IntProvider maxRunningTime = ConstantInt.of(Integer.MAX_VALUE);
	private IntProvider cooldownProvider = ConstantInt.of(-1);
	protected int chargeUpTime = 0;
	protected int actionTelegraphTicks = 0;

	protected int taskExpiresAt = Integer.MAX_VALUE;
	protected int runningTime = 0;
	protected long nextAvailableTime = 0;
	protected int nextActionTelegraphCompleteTime = 0;

	protected ExtendedGoal(T entity) {
		this.entity = entity;
	}

	public ExtendedGoal<T> onStart(Consumer<ExtendedGoal<T>> consumer) {
		this.onStart = consumer;

		return this;
	}

	public ExtendedGoal<T> onStop(Consumer<ExtendedGoal<T>> consumer) {
		this.onStop = consumer;

		return this;
	}

	public ExtendedGoal<T> onTelegraphStart(Consumer<ExtendedGoal<T>> consumer) {
		this.onTelegraphStart = consumer;

		return this;
	}

	public ExtendedGoal<T> cooldown(IntProvider cooldownProvider) {
		this.cooldownProvider = cooldownProvider;

		return this;
	}

	public ExtendedGoal<T> maxRuntime(IntProvider ticksProvider) {
		this.maxRunningTime = ticksProvider;

		return this;
	}

	public ExtendedGoal<T> chargeUpTime(int ticks) {
		this.chargeUpTime = ticks;

		return this;
	}

	public ExtendedGoal<T> actionTelegraphTicks(int ticks) {
		this.actionTelegraphTicks = ticks;

		return this;
	}

	@Override
	public boolean canUse() {
		return this.nextAvailableTime < entity.level.getGameTime();
	}

	@Override
	public boolean canContinueToUse() {
		return runningTime < taskExpiresAt;
	}

	@Override
	public boolean requiresUpdateEveryTick() {
		return true;
	}

	@Override
	public void start() {
		super.start();

		this.onStart.accept(this);
		this.runningTime = 0;
		this.taskExpiresAt = this.maxRunningTime.sample(RandomUtil.RANDOM);
	}

	@Override
	public void stop() {
		super.stop();

		this.onStop.accept(this);
		this.nextAvailableTime = entity.level.getGameTime() + this.cooldownProvider.sample(RandomUtil.RANDOM);
		this.nextActionTelegraphCompleteTime = 0;
	}

	@Override
	public void tick() {
		this.runningTime++;
	}

	protected boolean hasChargedUp() {
		return this.chargeUpTime <= this.runningTime;
	}

	protected boolean hasActionTelegraphFinished() {
		return this.nextActionTelegraphCompleteTime <= this.runningTime;
	}

	protected void startTelegraphingNextAction() {
		this.nextActionTelegraphCompleteTime = this.runningTime + this.actionTelegraphTicks;
		this.onTelegraphStart.accept(this);
	}

	protected boolean isTelegraphingAction() {
		return this.nextActionTelegraphCompleteTime < Integer.MAX_VALUE;
	}

	protected void resetActionTelegraph() {
		this.nextActionTelegraphCompleteTime = Integer.MAX_VALUE;
	}
}
