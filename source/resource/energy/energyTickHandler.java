package net.nevermine.resource.energy;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;

public class energyTickHandler {
	@SubscribeEvent
	public void onTick(final TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.START) {
			onTickStart(event.player);
		}
		else {
			onTickEnd(event.player);
		}
	}

	private void onTickEnd(final EntityPlayer player) {
		energyHelper.getProperties(player).updateAllBars();
	}

	private void onTickStart(final EntityPlayer player) {
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(final PlayerEvent.PlayerLoggedInEvent event) {
		energyHelper.getProperties(event.player).setBarValue(0.0f);
	}

	@SubscribeEvent
	public void onPlayerLoggedOut(final PlayerEvent.PlayerLoggedOutEvent event) {
		energyHelper.getProperties(event.player).setBarValue(0.0f);
	}

	@SubscribeEvent
	public void onPlayerRespawn(final PlayerEvent.PlayerRespawnEvent event) {
		energyHelper.getProperties(event.player).setBarValue(0.0f);
	}

	@SubscribeEvent
	public void onPlayerChangedDimension(final PlayerEvent.PlayerChangedDimensionEvent event) {
		energyHelper.getProperties(event.player).setBarValue(0.0f);
	}
}
