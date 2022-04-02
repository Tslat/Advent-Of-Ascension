package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class WikiSearchPacket implements AoAPacket {
	private final String searchString;

	public WikiSearchPacket(String search) {
		this.searchString = search;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeUtf(searchString);
	}

	public static WikiSearchPacket decode(FriendlyByteBuf buffer) {
		return new WikiSearchPacket(buffer.readUtf(32767));
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		//WikiCommand.handleSearchRequest(searchString);

		context.get().setPacketHandled(true);
	}
}
