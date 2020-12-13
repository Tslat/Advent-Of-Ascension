package net.tslat.aoa3.common.packet.packets;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.util.player.PlayerUtil;
import org.apache.logging.log4j.Level;

import java.util.function.Supplier;

public class ExpeditionTogglePacket implements AoAPacket {
	public ExpeditionTogglePacket() {}

	@Override
	public void encode(PacketBuffer buffer) {}

	public static ExpeditionTogglePacket decode(PacketBuffer buffer) {
		return new ExpeditionTogglePacket();
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		ServerPlayerEntity player = context.get().getSender();

		if (player == null) {
			Logging.logMessage(Level.ERROR, "Received an expedition toggle packet with no assigned player, y tho?");

			return;
		}

		PlayerUtil.getAdventPlayer(player).stats().incrementExpeditionBoost();
		context.get().setPacketHandled(true);
	}
}
