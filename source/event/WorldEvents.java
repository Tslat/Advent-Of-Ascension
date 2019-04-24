package net.tslat.aoa3.event;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.tslat.aoa3.event.dimension.OverworldEvents;

public class WorldEvents {
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent ev) {
		if (ev.phase != TickEvent.Phase.END || ev.world.isRemote)
			return;

		int dim = ev.world.provider.getDimension();

		if (dim == 0)
			OverworldEvents.doTickCheck(ev);
	}
}