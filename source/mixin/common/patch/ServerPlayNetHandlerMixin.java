package net.tslat.aoa3.mixin.common.patch;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.ServerPlayNetHandler;
import net.minecraft.network.play.client.CPlayerPacket;
import net.minecraftforge.common.ForgeMod;
import net.tslat.aoa3.util.EntityUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(ServerPlayNetHandler.class)
public class ServerPlayNetHandlerMixin {
	@Shadow
	private double lastGoodY;

	// Patches vanilla bug that fails to trigger jump on server when on block edge by checking if the player is in the fall state that would only occur when the player starts to fall but before ticking
	@Redirect(method = "handleMovePlayer",
			slice = @Slice(
					from = @At(value = "FIELD", target = "Lnet/minecraft/network/play/ServerPlayNetHandler;lastGoodZ:D"),
					to = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/ServerPlayerEntity;move(Lnet/minecraft/entity/MoverType;Lnet/minecraft/util/math/vector/Vector3d;)V")
			),
			at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/ServerPlayerEntity;isOnGround()Z"))
	private boolean onGround(ServerPlayerEntity player, CPlayerPacket packet) {
		if (packet.getY(player.getY()) - this.lastGoodY <= 0 || packet.isOnGround())
			return false;

		return player.isOnGround() || (player.getDeltaMovement().y() < -player.getAttributeValue(ForgeMod.ENTITY_GRAVITY.get()) && packet.getY(player.getY()) == player.getY() + EntityUtil.getEntityJumpVelocity(player));
	}
}
