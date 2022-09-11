package net.tslat.aoa3.mixin.common.function;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftResultInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.tslat.aoa3.event.custom.AoAEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import shadows.fastbench.util.CraftingInventoryExt;
import shadows.fastbench.util.FastBenchUtil;

@Mixin(FastBenchUtil.class)
public class FastBenchUtilMixin {
	@Inject(method = "slotChangedCraftingGrid",
	at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/item/crafting/IRecipe;assemble(Lnet/minecraft/inventory/IInventory;)Lnet/minecraft/item/ItemStack;", shift = At.Shift.AFTER),
	require = 0,
	locals = LocalCapture.CAPTURE_FAILSOFT)
	private static void recipeEventHook(World world, PlayerEntity player, CraftingInventoryExt inv, CraftResultInventory result, CallbackInfo callback, ItemStack stack, IRecipe<CraftingInventory> oldRecipe, IRecipe<CraftingInventory> recipe) {
		if (!world.isClientSide() && !stack.isEmpty()) {
			if (AoAEvents.firePlayerCraftingEvent(player, stack, inv, result))
				stack.setCount(0);
		}
	}
}
