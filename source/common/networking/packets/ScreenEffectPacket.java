package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.gui.overlay.ScreenEffectRenderer;
import net.tslat.aoa3.library.constant.ScreenImageEffect;

public record ScreenEffectPacket(ScreenImageEffect effect) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("screen_effect");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeVarInt(this.effect.getType().ordinal());
		buffer.writeFloat(this.effect.getScale());
		buffer.writeVarInt(this.effect.getColour());
		buffer.writeVarInt(this.effect.getDuration());
		buffer.writeBoolean(this.effect.isFullscreen());
	}

	public static ScreenEffectPacket decode(FriendlyByteBuf buffer) {
		return new ScreenEffectPacket(new ScreenImageEffect(ScreenImageEffect.Type.values()[buffer.readVarInt()])
				.scaled(buffer.readFloat())
				.coloured(buffer.readVarInt())
				.duration(buffer.readVarInt())
				.fullscreen(buffer.readBoolean()));
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		ScreenEffectRenderer.addScreenEffect(this.effect);
	}
}
