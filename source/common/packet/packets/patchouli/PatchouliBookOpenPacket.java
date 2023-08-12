package net.tslat.aoa3.common.packet.packets.patchouli;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.common.packet.packets.AoAPacket;
import net.tslat.aoa3.util.PlayerUtil;

import java.util.function.Supplier;

public class PatchouliBookOpenPacket implements AoAPacket {
	private final ResourceLocation book;

	public PatchouliBookOpenPacket(ResourceLocation id) {
		this.book = id;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(book);
	}

	public static PatchouliBookOpenPacket decode(FriendlyByteBuf buffer) {
		return new PatchouliBookOpenPacket(buffer.readResourceLocation());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		PlayerUtil.getAdventPlayer(context.get().getSender()).addPatchouliBook(book);

		context.get().setPacketHandled(true);
	}
}
