package net.tslat.aoa3.event.custom.events;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.ICancellableEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;

public class GrindstoneResultEvent extends PlayerEvent implements ICancellableEvent {
	@NotNull
	private final ItemStack outputStack;
	private final Container inputs;

	public GrindstoneResultEvent(Player player, ItemStack result, Container inputs) {
		super(player);

		this.outputStack = result;
		this.inputs = inputs;
	}

	@NotNull
	public ItemStack getOutputStack() {
		return this.outputStack;
	}

	public Container getInputs() {
		return this.inputs;
	}
}
