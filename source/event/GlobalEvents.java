package net.tslat.aoa3.event;

import net.minecraft.server.level.ServerLevel;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import net.neoforged.neoforge.event.level.LevelEvent;
import net.neoforged.neoforge.event.level.ModifyCustomSpawnersEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import net.tslat.aoa3.advent.AoAResourceCaching;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.world.event.AoAWorldEventManager;
import net.tslat.aoa3.content.world.spawner.AoACustomSpawner;
import net.tslat.aoa3.leaderboard.SkillsLeaderboard;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.scheduling.async.UpdateHalosMapTask;

import java.util.concurrent.TimeUnit;

public final class GlobalEvents {
	public static int tick;

	public static void preInit() {
		final IEventBus forgeBus = NeoForge.EVENT_BUS;

		forgeBus.addListener(EventPriority.NORMAL, false, ServerTickEvent.Post.class, GlobalEvents::serverTick);
		forgeBus.addListener(EventPriority.NORMAL, false, ModifyCustomSpawnersEvent.class, GlobalEvents::addCustomSpawners);
		forgeBus.addListener(EventPriority.NORMAL, false, LevelEvent.Load.class, GlobalEvents::levelLoad);
		forgeBus.addListener(EventPriority.NORMAL, false, LevelTickEvent.Pre.class, GlobalEvents::levelTick);
		forgeBus.addListener(EventPriority.NORMAL, false, ServerStartingEvent.class, GlobalEvents::serverStarting);
		forgeBus.addListener(EventPriority.NORMAL, false, ServerStartedEvent.class, GlobalEvents::serverStarted);
		forgeBus.addListener(EventPriority.NORMAL, false, ServerStoppingEvent.class, GlobalEvents::serverStopping);
		forgeBus.addListener(EventPriority.NORMAL, false, TagsUpdatedEvent.class, GlobalEvents::datapackUpdate);
	}

	private static void serverTick(final ServerTickEvent.Post ev) {
		tick++;
	}

	private static void addCustomSpawners(final ModifyCustomSpawnersEvent ev) {
		ev.getLevel().getServer().registryAccess().registryOrThrow(AoARegistries.CUSTOM_SPAWNERS_REGISTRY_KEY).stream()
				.filter(spawner -> spawner.shouldAddToDimension(ev.getLevel()))
				.map(AoACustomSpawner::copy)
				.forEach(ev::addCustomSpawner);
	}

	private static void levelLoad(final LevelEvent.Load ev) {
		if (ev.getLevel() instanceof ServerLevel level)
			AoAWorldEventManager.load(level);
	}

	private static void levelTick(final LevelTickEvent.Pre ev) {
		AoAWorldEventManager.tick(ev.getLevel());
	}

	private static void serverStarting(final ServerStartingEvent ev) {
		AoAScheduler.scheduleAsync(1, TimeUnit.SECONDS, new UpdateHalosMapTask());
	}

	private static void serverStarted(final ServerStartedEvent ev) {
		if (false && ev.getServer().isDedicatedServer()) {
			SkillsLeaderboard.init(ev.getServer());
		}
	}

	private static void serverStopping(final ServerStoppingEvent ev) {
		if (false && ev.getServer().isDedicatedServer())
			SkillsLeaderboard.shutdown();
	}

	private static void datapackUpdate(final TagsUpdatedEvent ev) {
		if (ev.shouldUpdateStaticData())
			AoAResourceCaching.datapackReload(ev.getRegistryAccess());
	}
}
