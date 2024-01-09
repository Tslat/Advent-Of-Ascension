package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.ClientOperations;

public record GunRecoilPacket(float recoilAmount, int firingTime) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("gun_recoil");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeFloat(recoilAmount);
		buffer.writeInt(firingTime);
	}

	public static GunRecoilPacket decode(FriendlyByteBuf buffer) {
		return new GunRecoilPacket(buffer.readFloat(), buffer.readInt());
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		ClientOperations.addRecoil(this.recoilAmount, this.firingTime);
	}
}
