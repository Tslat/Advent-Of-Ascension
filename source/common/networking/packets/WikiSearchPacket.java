package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.command.WikiCommand;

public record WikiSearchPacket(String searchString) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("wiki_search");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeUtf(this.searchString);
	}

	public static WikiSearchPacket decode(FriendlyByteBuf buffer) {
		return new WikiSearchPacket(buffer.readUtf());
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		context.workHandler().submitAsync(() -> WikiCommand.handleSearchRequest(this.searchString));
	}
}
