package net.tslat.aoa3.content.entity.brain.task.temp;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;

import java.util.function.Function;

/**
 * A base behaviour used for tasks that should have an ongoing effect, optionally with an early finish.<br>
 * This is most useful for things like attacks with multi-tick effects such as beams or flamethrowers, or other prolonged actions.
 * @param <E> The entity
 */
public abstract class HeldBehaviour<E extends LivingEntity> extends ExtendedBehaviour<E> {
	protected Function<E, Boolean> tickConsumer = entity -> true;
	protected int runningTime = 0;

	public HeldBehaviour() {
		runFor(entity -> Integer.MAX_VALUE);
	}

	/**
	 * Set the per-tick handler for this held behaviour
	 * @param tickConsumer The consumer to handle the per-action tick. Return false to end the behaviour, or true to continue running
	 */
	public HeldBehaviour<E> onTick(Function<E, Boolean> tickConsumer) {
		this.tickConsumer = tickConsumer;

		return this;
	}

	/**
	 * Gets the amount of ticks this behaviour has been held for
	 */
	public int getRunningTime() {
		return this.runningTime;
	}

	@Override
	protected boolean shouldKeepRunning(E entity) {
		return true;
	}

	@Override
	protected void start(ServerLevel level, E entity, long gameTime) {
		super.start(level, entity, gameTime);

		this.runningTime = 0;
	}

	@Override
	protected void tick(ServerLevel level, E owner, long gameTime) {
		super.tick(level, owner, gameTime);

		if (!this.tickConsumer.apply(owner))
			doStop(level, owner, gameTime);

		this.runningTime++;
	}
}
