package net.tslat.aoa3.event;

import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.scheduling.AoAScheduler;

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
		if (ev.phase == TickEvent.Phase.END)
			tick++;

		AoAScheduler.handleSyncScheduledTasks(tick);
	}

	@SubscribeEvent
	public static void getBurnTime(final FurnaceFuelBurnTimeEvent ev) {
		ev.setBurnTime(FURNACE_FUELS.getOrDefault(ev.getItemStack().getItem(), ev.getBurnTime()));
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
