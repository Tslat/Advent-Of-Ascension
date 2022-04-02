package net.tslat.aoa3.mixin.client.patch;

import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(GameRenderer.class)
public class GameRendererMixin { // Increases client-side entity pick potential range to 10 blocks instead of 3. Allows for Reach Attribute to work
	@ModifyConstant(method = "pick",
			slice = @Slice(
					from = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/EntityHitResult;getEntity()Lnet/minecraft/world/entity/Entity;"),
					to = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/BlockHitResult;miss(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/core/Direction;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/BlockHitResult;")
			),
			constant = @Constant(doubleValue = 9.0D))
	private static double checkMaxRange(double defaultDist) {
		return 100;
	}
}
