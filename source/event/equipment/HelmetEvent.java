package net.nevermine.event.equipment;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.nevermine.common.nevermine;
import net.nevermine.izer.equipment.Armorizer;

public class HelmetEvent {
	private World world;
	private Item helmet;

	public HelmetEvent() {
		helmet = null;
	}

	@SubscribeEvent
	public void onTickEvent(final TickEvent.PlayerTickEvent ev) {
		world = ev.player.worldObj;
		final ItemStack stackHelmet = ev.player.inventory.armorItemInSlot(3);
		if (stackHelmet != null) {
			helmet = stackHelmet.getItem();
		}
		else {
			helmet = null;
		}
		if (helmet == Armorizer.ThermalHelmet) {
			if (world.isRemote) {
				nevermine.proxy.displayHelmetScreen(true, 2, ev.player);
			}
		}
		else if (helmet == Armorizer.NightVisionGoggles) {
			if (world.isRemote) {
				nevermine.proxy.displayHelmetScreen(true, 1, ev.player);
			}
		}
		else if (world.isRemote) {
			nevermine.proxy.displayHelmetScreen(false, 1, ev.player);
		}
	}
}
