package net.tslat.aoa3.mixin.common.function;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.event.custom.AoAEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FurnaceResultSlot.class)
public class FurnaceResultSlotMixin {
	@Inject(method = "onTake",
	at = @At(value = "HEAD"))
	public void onTake(Player player, ItemStack stack, CallbackInfo ci) {
		AoAEvents.firePlayerSmeltingEvent(player, stack, ((Slot)(Object)this).container);
	}
}
