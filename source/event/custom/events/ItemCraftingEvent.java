package net.tslat.aoa3.event.custom.events;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.ResultContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Cancelable;

import javax.annotation.Nonnull;

@Cancelable
public class ItemCraftingEvent extends PlayerEvent {
	@Nonnull
	private final ItemStack outputStack;
	private final CraftingContainer craftMatrix;
	private final ResultContainer outputInventory;

	public ItemCraftingEvent(Player player, @Nonnull ItemStack crafting, CraftingContainer craftingInventory, ResultContainer outputInventory) {
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

	public CraftingContainer getCraftMatrix() {
		return this.craftMatrix;
	}

	public ResultContainer getOutputInventory() {
		return this.outputInventory;
	}
}
