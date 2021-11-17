package net.tslat.aoa3.mixin.client.patch;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
	@Redirect(method = "Lnet/minecraft/client/entity/player/ClientPlayerEntity;aiStep()V",
			slice = @Slice(
					from = @At(value = "INVOKE", target = "Lnet/minecraft/client/tutorial/Tutorial;onInput(Lnet/minecraft/util/MovementInput;)V"),
					to = @At(value = "FIELD", target = "Lnet/minecraft/client/entity/player/ClientPlayerEntity;autoJumpTime:I")
			),
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/player/ClientPlayerEntity;isPassenger()Z")
	)
	private boolean isPassenger(ClientPlayerEntity entity) {
		ItemStack heldStack = entity.getUseItem();

		return entity.isPassenger() || heldStack.getItem().getUseAnimation(heldStack) == UseAction.NONE;
	}
}
