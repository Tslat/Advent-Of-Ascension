package net.tslat.aoa3.mixin.client.patch;

import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(PlayerRenderer.class)
public class PlayerRendererMixin { // Patches crossbow arm pose check to allow for extending classes to animate properly
	@Redirect(method = "getArmPose",
			slice = @Slice(
					from = @At(value = "FIELD", target = "Lnet/minecraft/client/player/AbstractClientPlayer;swinging:Z", opcode = Opcodes.GETFIELD)
			),
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z")
	)
	private static boolean isCrossbowType(ItemStack instance, Item item) {
		return instance.getItem() instanceof CrossbowItem;
	}
}
