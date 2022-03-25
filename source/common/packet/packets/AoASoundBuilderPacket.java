package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.library.builder.SoundBuilder;

import java.util.function.Supplier;

public class AoASoundBuilderPacket implements AoAPacket {
	private final SoundBuilder soundBuilder;

	public AoASoundBuilderPacket(SoundBuilder soundBuilder) {
		this.soundBuilder = soundBuilder;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		soundBuilder.toNetwork(buffer);
	}

	public static AoASoundBuilderPacket decode(PacketBuffer buffer) {
		return new AoASoundBuilderPacket(SoundBuilder.fromNetwork(buffer));
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(soundBuilder::play);
		context.get().setPacketHandled(true);
	}
}
