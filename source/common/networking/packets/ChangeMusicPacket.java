package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.MusicPlayer;

public record ChangeMusicPacket(ResourceLocation id, boolean startingMusic) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("change_music");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(this.id);
		buffer.writeBoolean(this.startingMusic);
	}

	public static ChangeMusicPacket decode(FriendlyByteBuf buffer) {
		return new ChangeMusicPacket(buffer.readResourceLocation(), buffer.readBoolean());
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		if (this.startingMusic) {
			MusicPlayer.playMusic(this.id);
		}
		else {
			MusicPlayer.stopMusic(this.id);
		}
	}
}
