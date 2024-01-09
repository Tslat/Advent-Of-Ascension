package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.content.entity.base.AoAMultipartEntity;

public record MultipartTogglePacket(int entityId, boolean active) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("multipart_toggle");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeVarInt(this.entityId);
		buffer.writeBoolean(this.active);
	}

	public static MultipartTogglePacket decode(FriendlyByteBuf buffer) {
		return new MultipartTogglePacket(buffer.readVarInt(), buffer.readBoolean());
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		context.workHandler().execute(() -> {
			if (ClientOperations.getWorld().getEntity(this.entityId) instanceof AoAMultipartEntity multipart) {
				multipart.toggleMultipart(this.active);
				((Entity)multipart).refreshDimensions();
			}
		});
	}
}
