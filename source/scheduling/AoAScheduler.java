package net.tslat.aoa3.scheduling;

import net.tslat.tme.api.scheduling.TickScheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public final class AoAScheduler {
	private static final TickScheduler SCHEDULER = TickScheduler.createForServer();
	private static final ScheduledExecutorService ASYNC_SCHEDULER = Executors.newSingleThreadScheduledExecutor();

	public static void schedule(int delay, TickScheduler.Task task) {
		SCHEDULER.schedule(delay, task);
	}

	public static void scheduleEveryTick(int delay, int duration, TickScheduler.Task task) {
		SCHEDULER.scheduleEveryTick(delay, duration, task);
	}

	public static void scheduleCritical(int delay, TickScheduler.Task task) {
		SCHEDULER.scheduleCritical(delay, task);
	}

	public static void scheduleAsync(int time, TimeUnit unit, Runnable run) {
		ASYNC_SCHEDULER.schedule(run, time, unit);
	}

	public static void shutdown(Consumer<ExecutorService> shutdownHandler) {
		shutdownHandler.accept(ASYNC_SCHEDULER);
	}
}
