package net.tslat.aoa3.mixin.common.function;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.event.custom.AoAEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(AbstractFurnaceContainer.class)
public class AbstractFurnaceContainerMixin {
	@Inject(method = "quickMoveStack",
	at = @At(value = "CONSTANT", args = {"intValue=2"}, ordinal = 0, shift = At.Shift.BY, by = 2))
	public void quickMoveStack(PlayerEntity player, int slotIndex, CallbackInfoReturnable<ItemStack> callback) {
		Slot mixinSlot = ((Container)(Object)this).slots.get(slotIndex);

		AoAEvents.firePlayerSmeltingEvent(player, mixinSlot.getItem(), mixinSlot.container);
	}

	@Inject(method = "quickMoveStack",
	at = @At(value = "RETURN", ordinal = 0, shift = At.Shift.BEFORE),
	locals = LocalCapture.CAPTURE_FAILSOFT)
	public void quickMoveStackReturn(PlayerEntity player, int slotIndex, CallbackInfoReturnable<ItemStack> callback, ItemStack itemstack, Slot slot, ItemStack itemstack1) {
		slot.container.setItem(slot.getSlotIndex(), itemstack);
	}
}
