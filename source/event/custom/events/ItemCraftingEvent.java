package net.tslat.aoa3.event.custom.events;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class ItemCraftingEvent extends PlayerEvent implements ICancellableEvent {
	@NotNull
	private final ItemStack outputStack;
	private final Container craftingInputs;
	private final ResultContainer outputInventory;

	public ItemCraftingEvent(Player player, @NotNull ItemStack crafting, Container craftingInventory, ResultContainer outputInventory) {
		super(player);

		this.outputStack = crafting;
		this.craftingInputs = craftingInventory;
		this.outputInventory = outputInventory;
	}

	@NotNull
	public ItemStack getOutputStack() {
		return this.outputStack;
	}

	public Container getCraftingInputs() {
		return this.craftingInputs;
	}

	public ResultContainer getOutputInventory() {
		return this.outputInventory;
	}
}
