package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.aoa3.content.entity.base.AoAMultipartEntity;

import java.util.function.Supplier;

public class MultipartTogglePacket implements AoAPacket {
	private final int entityId;
	private final boolean active;

	public MultipartTogglePacket(final int entityId, final boolean active) {
		this.entityId = entityId;
		this.active = active;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeVarInt(this.entityId);
		buffer.writeBoolean(this.active);
	}

	public static MultipartTogglePacket decode(FriendlyByteBuf buffer) {
		return new MultipartTogglePacket(buffer.readVarInt(), buffer.readBoolean());
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			if (ClientOperations.getWorld().getEntity(this.entityId) instanceof AoAMultipartEntity multipart) {
				multipart.toggleMultipart(this.active);
				((Entity)multipart).refreshDimensions();
			}
		});

		context.get().setPacketHandled(true);
	}
}
