package net.nevermine.event.player;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.nevermine.event.recoil.RecoilClientTick;
import net.nevermine.gui.screen.MobScreen;

public class ClientTicker {
	public static int tick;

	@SubscribeEvent
	public void tickClient(final TickEvent.ClientTickEvent evt) {
		if (evt.phase == TickEvent.Phase.END) {
			++ClientTicker.tick;
			if (ClientTicker.tick > 100000) {
				ClientTicker.tick = 0;
			}
			--MobScreen.showTicks;
			if (RecoilClientTick.tickNumber > 0) {
				RecoilClientTick.doRotation();
			}
			--RecoilClientTick.tickNumber;
		}
	}
}
