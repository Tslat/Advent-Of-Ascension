package net.tslat.aoa3.mixin.common.function;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.tslat.aoa3.event.custom.AoAEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorkbenchContainer.class)
public class WorkbenchContainerMixin {
	@Inject(method = "slotChangedCraftingGrid",
	at = @At(value = "INVOKE", target = "Lnet/minecraft/inventory/CraftResultInventory;setItem(ILnet/minecraft/item/ItemStack;)V", shift = At.Shift.AFTER))
	private static void recipeEventHook(int containerId, World world, PlayerEntity player, CraftingInventory inventory, CraftResultInventory resultInventory, CallbackInfo callback) {
		if (!resultInventory.getItem(0).isEmpty() && AoAEvents.firePlayerCraftingEvent(player, resultInventory.getItem(0), inventory, resultInventory))
			resultInventory.setItem(0, ItemStack.EMPTY);
	}
}
