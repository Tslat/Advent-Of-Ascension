package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.command.WikiCommand;

import java.util.function.Supplier;

public class WikiSearchPacket implements AoAPacket {
	private final String searchString;

	public WikiSearchPacket(String search) {
		this.searchString = search;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeUtf(searchString);
	}

	public static WikiSearchPacket decode(PacketBuffer buffer) {
		return new WikiSearchPacket(buffer.readUtf(32767));
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		WikiCommand.handleSearchRequest(searchString);

		context.get().setPacketHandled(true);
	}
}
