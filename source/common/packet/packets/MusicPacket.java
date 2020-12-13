package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.client.MusicPlayer;

import java.util.function.Supplier;

public class MusicPacket implements AoAPacket {
	private final boolean startingMusic;
	private final ResourceLocation id;

	public MusicPacket(boolean startingMusic, ResourceLocation id) {
		this.startingMusic = startingMusic;
		this.id = id;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeBoolean(startingMusic);
		buffer.writeString(id.toString());
	}

	public static MusicPacket decode(PacketBuffer buffer) {
		return new MusicPacket(buffer.readBoolean(), new ResourceLocation(buffer.readString()));
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		if (startingMusic) {
			MusicPlayer.playMusic(id);
		}
		else {
			MusicPlayer.stopMusic(id);
		}

		context.get().setPacketHandled(true);
	}
}
