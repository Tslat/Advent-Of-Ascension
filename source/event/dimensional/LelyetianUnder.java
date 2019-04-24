package net.nevermine.event.dimensional;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.nevermine.assist.ConfigurationHelper;

public class LelyetianUnder {
	private World world;

	@SubscribeEvent
	public void onTickEvent(final TickEvent.PlayerTickEvent ev) {
		world = ev.player.worldObj;
		if (ev.player.dimension == ConfigurationHelper.lelyetia && ev.player.posY < 54.0) {
			if (ev.player.motionY < 0.0) {
				final EntityPlayer player = ev.player;
				player.motionY *= 0.800000011920929;
			}
			ev.player.fallDistance = -0.5f;
		}
	}
}
