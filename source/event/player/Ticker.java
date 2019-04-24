package net.nevermine.event.player;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.nevermine.container.PlayerContainer;

import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static cpw.mods.fml.common.gameevent.TickEvent.Phase.END;

public class Ticker {
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private static final HashSet<Runnable> scheduledTasks = new HashSet<Runnable>();
	public static int tick;

	@SubscribeEvent
	public void tickServer(final TickEvent.ServerTickEvent evt) {
		if (evt.phase == END) {
			++Ticker.tick;

			if (Ticker.tick > 100000) {
				Ticker.tick = 0;
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(final TickEvent.PlayerTickEvent e) {
		if (e.phase == END) {
			PlayerContainer.getProperties(e.player).tickPlayer();
		}
	}

	public static void scheduleRequiredTask(Runnable run, int time, TimeUnit unit) {
		scheduler.schedule(run, time, unit);
		scheduledTasks.add(run);
	}

	public static void scheduleTask(Runnable run, int time, TimeUnit unit) {
		scheduler.schedule(run, time, unit);
	}

	public static void serverShutdownTasks() {
		for (Runnable task : scheduledTasks) {
			task.run();
		}
	}
}
