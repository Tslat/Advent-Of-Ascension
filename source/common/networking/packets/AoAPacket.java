package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public interface AoAPacket<T extends IPayloadContext> extends CustomPacketPayload {
	void receiveMessage(T context);
}
