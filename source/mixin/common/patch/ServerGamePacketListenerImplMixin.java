package net.tslat.aoa3.mixin.common.patch;

import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.tslat.aoa3.util.EntityUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(ServerGamePacketListenerImpl.class)
public class ServerGamePacketListenerImplMixin {
	@Shadow
	private double lastGoodY;

	// Patches vanilla bug that fails to trigger jump on server when on block edge by checking if the player is in the fall state that would only occur when the player starts to fall but before ticking
	@Redirect(method = "handleMovePlayer",
			slice = @Slice(
					from = @At(value = "FIELD", target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;lastGoodZ:D", ordinal = 0),
					to = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;move(Lnet/minecraft/world/entity/MoverType;Lnet/minecraft/world/phys/Vec3;)V")
			),
			at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;onGround()Z"))
	private boolean onGround(ServerPlayer player, ServerboundMovePlayerPacket packet) {
		if (packet.getY(player.getY()) - this.lastGoodY <= 0 || packet.isOnGround())
			return false;

		return player.onGround() || (player.getDeltaMovement().y() < -player.getAttributeValue(NeoForgeMod.ENTITY_GRAVITY.value()) && packet.getY(player.getY()) == player.getY() + EntityUtil.getEntityJumpVelocity(player));
	}
}
