package net.tslat.aoa3.mixin.client;

import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin {
	@Redirect(method = "Lnet/minecraft/client/renderer/entity/PlayerRenderer;getArmPose(Lnet/minecraft/client/entity/player/AbstractClientPlayerEntity;Lnet/minecraft/util/Hand;)Lnet/minecraft/client/renderer/entity/model/BipedModel$ArmPose;",
			slice = @Slice(
					from = @At(value = "FIELD", target = "Lnet/minecraft/entity/LivingEntity;swinging:Z", opcode = Opcodes.GETFIELD),
					to = @At(value = "INVOKE", target = "Lnet/minecraft/item/CrossbowItem;isCharged(Lnet/minecraft/item/ItemStack;)Z", shift = At.Shift.AFTER)
			),
			at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getItem()Lnet/minecraft/item/Item;")
	)
	private static Item isCrossbowType(ItemStack stack) {
		return stack.getItem() instanceof CrossbowItem ? Items.CROSSBOW : Items.AIR;
	}
}
