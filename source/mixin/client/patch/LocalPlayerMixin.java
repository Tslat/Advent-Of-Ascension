package net.tslat.aoa3.mixin.client.patch;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin { // Prevents the movement slowdown from affecting the player when using an item with no use animation
	@Redirect(method = "aiStep",
			slice = @Slice(
					from = @At(value = "INVOKE", target = "Lnet/minecraft/client/tutorial/Tutorial;onInput(Lnet/minecraft/client/player/Input;)V"),
					to = @At(value = "FIELD", target = "Lnet/minecraft/client/player/LocalPlayer;autoJumpTime:I")
			),
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;isPassenger()Z")
	)
	private boolean isPassenger(LocalPlayer entity) {
		ItemStack heldStack = entity.getUseItem();

		return entity.isPassenger() || heldStack.getItem().getUseAnimation(heldStack) == UseAnim.NONE;
	}
}
