package net.nevermine.event.equipment;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.nevermine.container.PlayerContainer;
import net.nevermine.item.tool.ExtractionTool;

import static net.nevermine.container.PlayerContainer.Skills.Extraction;

public class ExtractionRequirement {

	@SubscribeEvent
	public void onTickEvent(final TickEvent.PlayerTickEvent ev) {
		if (ev.player.worldObj.isRemote || ev.player.capabilities.isCreativeMode)
			return;

		final ItemStack stack = ev.player.getHeldItem();

		if (stack != null) {
			final Item item = stack.getItem();
			final int lvl = PlayerContainer.getProperties(ev.player).getLevel(Extraction);

			if (item instanceof ExtractionTool && ((ExtractionTool)item).getLevelReq() > lvl) {
				ev.player.dropItem(item, 1);
				ev.player.inventory.mainInventory[ev.player.inventory.currentItem] = null;
			}
		}
	}
}
