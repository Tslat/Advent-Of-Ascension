package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.builder.SoundBuilder;

public record AoASoundBuilderPacket(SoundBuilder soundBuilder) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("sound_builder");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		this.soundBuilder.toNetwork(buffer);
	}

	public static AoASoundBuilderPacket decode(FriendlyByteBuf buffer) {
		return new AoASoundBuilderPacket(SoundBuilder.fromNetwork(buffer));
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		context.workHandler().execute(this.soundBuilder::execute);
	}
}
