package net.tslat.aoa3.event.custom.events;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class ItemSmeltingEvent extends PlayerEvent {
	@NotNull
	private final ItemStack outputStack;
	private final Container outputSlotInventory;

	public ItemSmeltingEvent(Player player, @NotNull ItemStack smelting, Container outputSlotInventory) {
		super(player);

		this.outputStack = smelting;
		this.outputSlotInventory = outputSlotInventory;
	}

	@NotNull
	public ItemStack getOutputStack() {
		return this.outputStack;
	}

	public Container getSlotInventory() {
		return this.outputSlotInventory;
	}
}
