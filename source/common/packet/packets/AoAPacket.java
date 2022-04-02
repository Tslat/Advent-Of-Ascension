package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public interface AoAPacket {
	void encode(FriendlyByteBuf buffer);
	void receiveMessage(Supplier<NetworkEvent.Context> context);
}
