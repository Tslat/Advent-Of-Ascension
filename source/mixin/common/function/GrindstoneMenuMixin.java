package net.tslat.aoa3.mixin.common.function;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.GrindstoneMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.event.custom.AoAEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GrindstoneMenu.class)
public class GrindstoneMenuMixin {
    @Shadow
    @Final
    Container repairSlots;

    @WrapOperation(method = "createResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/inventory/GrindstoneMenu;removeNonCurses(Lnet/minecraft/world/item/ItemStack;II)Lnet/minecraft/world/item/ItemStack;"))
    private ItemStack aoa3$ModifyResult(GrindstoneMenu instance, ItemStack stack, int count, int damage, Operation<ItemStack> original) {
        Player player = null;

        for (Slot slot : instance.slots) {
            if (slot.container instanceof Inventory inventory) {
                player = inventory.player;

                break;
            }
        }

        ItemStack output = original.call(instance, stack, count, damage);

        if (player == null || !AoAEvents.firePlayerGrindstoneEvent(player, output, this.repairSlots))
            return output;

        return ItemStack.EMPTY;
    }
}
