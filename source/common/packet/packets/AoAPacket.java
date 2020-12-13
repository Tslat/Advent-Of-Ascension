package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public interface AoAPacket {
	void encode(PacketBuffer buffer);
	void receiveMessage(Supplier<NetworkEvent.Context> context);
}
