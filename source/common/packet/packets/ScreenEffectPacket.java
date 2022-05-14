package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
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
		buffer.writeFloat(effect.getRotation());
		buffer.writeFloat(effect.getScale());
		buffer.writeVarInt(effect.getColour());
		buffer.writeVarInt(effect.getDuration());
	}

	public static ScreenEffectPacket decode(FriendlyByteBuf buffer) {
		return new ScreenEffectPacket(new ScreenImageEffect(ScreenImageEffect.Type.values()[buffer.readVarInt()])
				.rotated(buffer.readFloat())
				.scaled(buffer.readFloat())
				.coloured(buffer.readVarInt())
				.duration(buffer.readVarInt()));
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		//ScreenOverlayRenderer.addScreenEffect(this.effect);

		context.get().setPacketHandled(true);
	}
}
