package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.client.gui.overlay.ScreenEffectRenderer;
import net.tslat.aoa3.library.constant.ScreenImageEffect;

import java.util.function.Supplier;

public class ScreenEffectPacket implements AoAPacket {
	private final ScreenImageEffect effect;

	public ScreenEffectPacket(final ScreenImageEffect effect) {
		this.effect = effect;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeVarInt(effect.getType().ordinal());
		buffer.writeFloat(effect.getScale());
		buffer.writeVarInt(effect.getColour());
		buffer.writeVarInt(effect.getDuration());
		buffer.writeBoolean(effect.isFullscreen());
	}

	public static ScreenEffectPacket decode(FriendlyByteBuf buffer) {
		return new ScreenEffectPacket(new ScreenImageEffect(ScreenImageEffect.Type.values()[buffer.readVarInt()])
				.scaled(buffer.readFloat())
				.coloured(buffer.readVarInt())
				.duration(buffer.readVarInt())
				.fullscreen(buffer.readBoolean()));
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		ScreenEffectRenderer.addScreenEffect(this.effect);

		context.get().setPacketHandled(true);
	}
}
