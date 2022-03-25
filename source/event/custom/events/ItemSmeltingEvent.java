package net.tslat.aoa3.event.custom.events;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

import javax.annotation.Nonnull;

public class ItemSmeltingEvent extends PlayerEvent {
	@Nonnull
	private final ItemStack outputStack;
	private final IInventory outputSlotInventory;

	public ItemSmeltingEvent(PlayerEntity player, @Nonnull ItemStack smelting, IInventory outputSlotInventory) {
		super(player);

		this.outputStack = smelting;
		this.outputSlotInventory = outputSlotInventory;
	}

	@Nonnull
	public ItemStack getOutputStack() {
		return this.outputStack;
	}

	public IInventory getSlotInventory() {
		return this.outputSlotInventory;
	}
}
