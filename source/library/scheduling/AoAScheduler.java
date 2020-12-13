package net.tslat.aoa3.library.scheduling;

import com.google.common.collect.HashMultimap;
import net.tslat.aoa3.event.GlobalEvents;

import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AoAScheduler {
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private static final HashSet<Runnable> scheduledTasks = new HashSet<Runnable>();
	private static final HashMultimap<Integer, Runnable> scheduledSynchTasks = HashMultimap.<Integer, Runnable>create();

	public static void scheduleRequiredAsyncTask(Runnable run, int time, TimeUnit unit) {
		scheduler.schedule(run, time, unit);
		scheduledTasks.add(run);
	}

	public static void scheduleSyncronisedTask(Runnable run, int ticks) {
		scheduledSynchTasks.put(GlobalEvents.tick + ticks, run);
	}

	public static void scheduleAsyncTask(Runnable run, int time, TimeUnit unit) {
		scheduler.schedule(run, time, unit);
	}

	public static void serverShutdownTasks() {
		for (Runnable task : scheduledTasks) {
			task.run();
		}
	}

	public static void handleSyncScheduledTasks(int tick) {
		if (scheduledSynchTasks.containsKey(tick)) {
			Iterator<Runnable> tasks = scheduledSynchTasks.get(tick).iterator();

			while (tasks.hasNext()) {
				tasks.next().run();
				tasks.remove();
			}
		}
	}
}
