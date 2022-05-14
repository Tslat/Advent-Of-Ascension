package net.tslat.aoa3.mixin.client.patch;

import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
	@ModifyConstant(method = "Lnet/minecraft/client/renderer/GameRenderer;pick(F)V",
			slice = @Slice(
					from = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/EntityRayTraceResult;getEntity()Lnet/minecraft/entity/Entity;"),
					to = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockRayTraceResult;miss(Lnet/minecraft/util/math/vector/Vector3d;Lnet/minecraft/util/Direction;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/util/math/BlockRayTraceResult;")
			),
			constant = @Constant(doubleValue = 9.0D),
			require = 0)
	private static double checkMaxRange(double defaultDist) {
		return 100;
	}
}
