package net.tslat.aoa3.event;

import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.tslat.aoa3.common.registration.LootSystemRegister;
import net.tslat.aoa3.utils.ModUtil;

public class GlobalEvents {
	public static int tick;

	@SubscribeEvent
	public void serverTick(final TickEvent.ServerTickEvent ev) {
		if (ev.phase == TickEvent.Phase.END)
			tick++;

		ModUtil.handleSyncScheduledTasks(tick);
	}

	@SubscribeEvent
	public void onLootTableLoad(final LootTableLoadEvent ev) {
		LootSystemRegister.injectThirdPartyPools(ev);
	}
}
