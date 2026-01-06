package net.tslat.aoa3.mixin.common.function;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.content.item.weapon.crossbow.AoACrossbow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {
    @WrapOperation(method = "getChargeDuration", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;modifyCrossbowChargingTime(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;F)F"))
    private static float aoa3$adjustAoAChargingTime(ItemStack stack, LivingEntity entity, float crossbowChargingTime, Operation<Float> original) {
        if (stack.getItem() instanceof AoACrossbow aoaCrossbow)
            crossbowChargingTime /= aoaCrossbow.getChargeSpeedModifier(stack);

        return original.call(stack, entity, crossbowChargingTime);
    }
}
