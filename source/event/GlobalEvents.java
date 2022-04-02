package net.tslat.aoa3.event;

import com.google.common.collect.ImmutableList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.CustomSpawner;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.content.world.spawner.PixonSpawner;
import net.tslat.aoa3.content.world.spawner.TraderSpawner;
import net.tslat.aoa3.content.world.spawner.VisualentSpawner;
import net.tslat.aoa3.leaderboard.SkillsLeaderboard;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.WebUtil;

import java.util.ArrayList;

public final class GlobalEvents {
	public static int tick;

	public static void preInit() {
		final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, TickEvent.ServerTickEvent.class, GlobalEvents::serverTick);
		forgeBus.addListener(EventPriority.NORMAL, false, WorldEvent.Load.class, GlobalEvents::worldLoad);
		forgeBus.addListener(EventPriority.NORMAL, false, ServerStartingEvent.class, GlobalEvents::serverStarting);
		forgeBus.addListener(EventPriority.NORMAL, false, ServerStartedEvent.class, GlobalEvents::serverStarted);
		forgeBus.addListener(EventPriority.NORMAL, false, ServerStoppingEvent.class, GlobalEvents::serverStopping);
	}

	private static void serverTick(final TickEvent.ServerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END) {
			tick++;

			AoAScheduler.handleSyncScheduledTasks(tick);
		}
	}

	private static void worldLoad(final WorldEvent.Load ev) {
		if (ev.getWorld() instanceof ServerLevel world) {
			ArrayList<CustomSpawner> spawners = new ArrayList<>(world.customSpawners);

			if (PixonSpawner.isValidSpawnWorld(world))
				spawners.add(new PixonSpawner());

			if (world.dimension() == AoADimensions.LUNALUS.key)
				spawners.add(new VisualentSpawner());

			if (TraderSpawner.isValidSpawnWorld(world))
				spawners.add(new TraderSpawner());

			world.customSpawners = ImmutableList.copyOf(spawners);
		}
	}

	private static void serverStarting(final ServerStartingEvent ev) {
		WebUtil.extraPlayerHalosFromWeb();
		AoAScheduler.serverStartupTasks();
	}

	private static void serverStarted(final ServerStartedEvent ev) {
		if (ev.getServer().isDedicatedServer())
			SkillsLeaderboard.init();
	}

	private static void serverStopping(final ServerStoppingEvent ev) {
		if (ev.getServer().isDedicatedServer())
			AoAScheduler.serverShutdownTasks();
	}
}
