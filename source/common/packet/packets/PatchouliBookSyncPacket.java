package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.client.ClientOperations;

import java.util.ArrayList;
import java.util.function.Supplier;

public class PatchouliBookSyncPacket implements AoAPacket {
	private final ArrayList<ResourceLocation> books;

	public PatchouliBookSyncPacket(ArrayList<ResourceLocation> books) {
		this.books = books;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeVarInt(books.size());

		for (ResourceLocation id : books) {
			buffer.writeResourceLocation(id);
		}
	}

	public static PatchouliBookSyncPacket decode(PacketBuffer buffer) {
		int bookCount = buffer.readVarInt();
		ArrayList<ResourceLocation> books = new ArrayList<ResourceLocation>(bookCount);

		for (int i = 0; i < bookCount; i++) {
			books.add(buffer.readResourceLocation());
		}

		return new PatchouliBookSyncPacket(books);
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		ClientOperations.syncPatchouliBooks(books);

		context.get().setPacketHandled(true);
	}
}
