package net.tslat.aoa3.mixin.common.function;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.FurnaceResultSlot;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.tslat.aoa3.event.custom.AoAEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FurnaceResultSlot.class)
public class FurnaceResultSlotMixin {
	@Inject(method = "onTake",
	at = @At(value = "HEAD"))
	public void onTake(PlayerEntity player, ItemStack stack, CallbackInfoReturnable<ItemStack> callback) {
		AoAEvents.firePlayerSmeltingEvent(player, stack, ((Slot)(Object)this).container);
	}
}
