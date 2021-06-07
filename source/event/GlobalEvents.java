package net.tslat.aoa3.event;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.ISpecialSpawner;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.world.spawner.PixonSpawner;
import net.tslat.aoa3.world.spawner.TraderSpawner;
import net.tslat.aoa3.world.spawner.VisualentSpawner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GlobalEvents {
	private static final HashMap<Item, Integer> FURNACE_FUELS = new HashMap<Item, Integer>();
	private static final HashMap<Supplier<? extends IItemProvider>, Integer> registeredFurnaceFuels = new HashMap<Supplier<? extends IItemProvider>, Integer>();

	public static int tick;

	@SubscribeEvent
	public static void serverTick(final TickEvent.ServerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END) {
			tick++;

			AoAScheduler.handleSyncScheduledTasks(tick);
		}
	}

	@SubscribeEvent
	public static void getBurnTime(final FurnaceFuelBurnTimeEvent ev) {
		ev.setBurnTime(FURNACE_FUELS.getOrDefault(ev.getItemStack().getItem(), ev.getBurnTime()));
	}

	@SubscribeEvent
	public static void worldLoad(final WorldEvent.Load ev) {
		if (ev.getWorld() instanceof ServerWorld) {
			ServerWorld world = (ServerWorld)ev.getWorld();
			ArrayList<ISpecialSpawner> spawners = new ArrayList<ISpecialSpawner>(world.customSpawners);

			if (PixonSpawner.isValidSpawnWorld(world))
				spawners.add(new PixonSpawner());

			if (world.dimension() == AoADimensions.LUNALUS.key)
				spawners.add(new VisualentSpawner());

			if (TraderSpawner.isValidSpawnWorld(world))
				spawners.add(new TraderSpawner());

			world.customSpawners = ImmutableList.copyOf(spawners);
		}
	}

	public static void init() {
		for (Map.Entry<Supplier<? extends IItemProvider>, Integer> entry : registeredFurnaceFuels.entrySet()) {
			FURNACE_FUELS.put(entry.getKey().get().asItem(), entry.getValue());
		}
	}

	public static void addFurnaceFuel(Supplier<? extends IItemProvider> item, int burnTime) {
		registeredFurnaceFuels.put(item, burnTime);
	}
}
