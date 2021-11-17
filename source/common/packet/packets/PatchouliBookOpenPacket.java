package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.function.Supplier;

public class PatchouliBookOpenPacket implements AoAPacket {
	private final ResourceLocation book;

	public PatchouliBookOpenPacket(ResourceLocation id) {
		this.book = id;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeResourceLocation(book);
	}

	public static PatchouliBookOpenPacket decode(PacketBuffer buffer) {
		return new PatchouliBookOpenPacket(buffer.readResourceLocation());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		PlayerUtil.getAdventPlayer(context.get().getSender()).addPatchouliBook(book);

		context.get().setPacketHandled(true);
	}
}
