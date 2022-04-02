package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
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
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeBoolean(startingMusic);
		buffer.writeUtf(id.toString());
	}

	public static MusicPacket decode(FriendlyByteBuf buffer) {
		return new MusicPacket(buffer.readBoolean(), new ResourceLocation(buffer.readUtf(32767)));
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
