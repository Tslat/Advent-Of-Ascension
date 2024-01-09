package net.tslat.aoa3.scheduling;

import com.google.common.collect.HashMultimap;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.event.GlobalEvents;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AoAScheduler {
	private static ScheduledExecutorService scheduler = null;
	private static final HashMultimap<Integer, Runnable> scheduledSynchTasks = HashMultimap.<Integer, Runnable>create();

	private static boolean running = true;

	public static void scheduleSyncronisedTask(Runnable run, int ticks) {
		scheduledSynchTasks.put(GlobalEvents.tick + ticks, run);
	}

	public static void scheduleAsyncTask(Runnable run, int time, TimeUnit unit) {
		if (scheduler == null || !running)
			serverStartupTasks();

		scheduler.schedule(run, time, unit);
	}

	public static void serverStartupTasks() {
		if (scheduler != null)
			scheduler.shutdownNow();

		scheduler = Executors.newScheduledThreadPool(1);

		handleSyncScheduledTasks(null);
	}

	public static void serverShutdownTasks() {
		handleSyncScheduledTasks(null);

		scheduler.shutdownNow();
		scheduler = null;
		running = false;
	}

	public static void handleSyncScheduledTasks(@Nullable Integer tick) {
		if (scheduledSynchTasks.containsKey(tick)) {
			Iterator<Runnable> tasks = tick == null ? scheduledSynchTasks.values().iterator() : scheduledSynchTasks.get(tick).iterator();

			while (tasks.hasNext()) {
				try {
					tasks.next().run();
				}
				catch (Exception ex) {
					Logging.logMessage(Level.ERROR, "Unable to run unhandled scheduled task, skipping.", ex);
				}

				tasks.remove();
			}
		}
	}
}
