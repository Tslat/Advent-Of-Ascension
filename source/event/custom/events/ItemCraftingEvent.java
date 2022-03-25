package net.tslat.aoa3.event.custom.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

import javax.annotation.Nonnull;

@Cancelable
public class ItemCraftingEvent extends PlayerEvent {
	@Nonnull
	private final ItemStack outputStack;
	private final CraftingInventory craftMatrix;
	private final CraftResultInventory outputInventory;

	public ItemCraftingEvent(PlayerEntity player, @Nonnull ItemStack crafting, CraftingInventory craftingInventory, CraftResultInventory outputInventory) {
		super(player);

		this.outputStack = crafting;
		this.craftMatrix = craftingInventory;
		this.outputInventory = outputInventory;
	}

	@Override
	public boolean isCancelable() {
		return true;
	}

	@Nonnull
	public ItemStack getOutputStack() {
		return this.outputStack;
	}

	public CraftingInventory getCraftMatrix() {
		return this.craftMatrix;
	}

	public CraftResultInventory getOutputInventory() {
		return this.outputInventory;
	}
}
