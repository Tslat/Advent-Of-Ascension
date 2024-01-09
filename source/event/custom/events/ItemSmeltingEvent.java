package net.tslat.aoa3.event.custom.events;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import javax.annotation.Nonnull;

public class ItemSmeltingEvent extends PlayerEvent {
	@Nonnull
	private final ItemStack outputStack;
	private final Container outputSlotInventory;

	public ItemSmeltingEvent(Player player, @Nonnull ItemStack smelting, Container outputSlotInventory) {
		super(player);

		this.outputStack = smelting;
		this.outputSlotInventory = outputSlotInventory;
	}

	@Nonnull
	public ItemStack getOutputStack() {
		return this.outputStack;
	}

	public Container getSlotInventory() {
		return this.outputSlotInventory;
	}
}
