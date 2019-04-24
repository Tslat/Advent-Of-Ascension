package net.nevermine.event.dimensional;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.world.World;
import net.nevermine.assist.ConfigurationHelper;

import java.util.Random;

public class CavernAmbientEvent {
	private World world;
	private Random rand;

	public CavernAmbientEvent() {
		rand = new Random();
	}

	@SubscribeEvent
	public void onTickEvent(final TickEvent.PlayerTickEvent ev) {
		world = ev.player.worldObj;
		if (ev.player.dimension == ConfigurationHelper.ancientcavern) {
			if (rand.nextInt(3) == 2 && ev.player.ticksExisted % 100 == 0) {
				final int pick = rand.nextInt(5);
				if (pick == 1) {
					world.playSoundAtEntity(ev.player, "nevermine:Cavern1", 2.85f, 2.0f);
				}
				else if (pick == 2) {
					world.playSoundAtEntity(ev.player, "nevermine:Cavern2", 2.85f, 2.0f);
				}
				else if (pick == 3) {
					world.playSoundAtEntity(ev.player, "nevermine:Cavern3", 2.85f, 2.0f);
				}
				else if (pick == 4) {
					world.playSoundAtEntity(ev.player, "nevermine:Cavern4", 2.85f, 2.0f);
				}
				else {
					world.playSoundAtEntity(ev.player, "nevermine:Cavern5", 2.85f, 2.0f);
				}
			}
		}
		else if (ev.player.dimension == ConfigurationHelper.immortallis && rand.nextInt(3) == 1 && ev.player.ticksExisted % 200 == 0) {
			final int pick = rand.nextInt(5);
			if (pick == 1) {
				world.playSoundAtEntity(ev.player, "nevermine:ImmortallisAmbient1", 2.85f, 2.0f);
			}
			else if (pick == 2) {
				world.playSoundAtEntity(ev.player, "nevermine:ImmortallisAmbient2", 2.85f, 2.0f);
			}
			else if (pick == 3) {
				world.playSoundAtEntity(ev.player, "nevermine:ImmortallisAmbient3", 2.85f, 2.0f);
			}
		}
	}
}
