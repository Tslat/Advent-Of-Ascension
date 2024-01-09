package net.tslat.aoa3.common.networking.packets.patchouli;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.common.networking.packets.AoAPacket;

import java.util.List;

public record PatchouliBookSyncPacket(List<ResourceLocation> books) implements AoAPacket<IPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("patchouli_book_sync");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeCollection(this.books, FriendlyByteBuf::writeResourceLocation);
	}

	public static PatchouliBookSyncPacket decode(FriendlyByteBuf buffer) {
		return new PatchouliBookSyncPacket(buffer.readList(FriendlyByteBuf::readResourceLocation));
	}

	@Override
	public void receiveMessage(IPayloadContext context) {
		ClientOperations.syncModonomiconBooks(books);
	}
}
