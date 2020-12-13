package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.client.gui.adventgui.AdventGuiTabPlayer;

import java.util.function.Supplier;

public class TributeDataPacket implements AoAPacket {
	private final int erebon;
	private final int luxon;
	private final int pluton;
	private final int selyan;

	public TributeDataPacket(final int ere, final int lux, final int plu, final int sel) {
		erebon = ere;
		luxon = lux;
		pluton = plu;
		selyan = sel;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeInt(erebon);
		buffer.writeInt(luxon);
		buffer.writeInt(pluton);
		buffer.writeInt(selyan);
	}

	public static TributeDataPacket decode(PacketBuffer buffer) {
		return new TributeDataPacket(buffer.readInt(), buffer.readInt(), buffer.readInt(), buffer.readInt());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		AdventGuiTabPlayer.tributeErebon = erebon;
		AdventGuiTabPlayer.tributeLuxon = luxon;
		AdventGuiTabPlayer.tributePluton = pluton;
		AdventGuiTabPlayer.tributeSelyan = selyan;

		context.get().setPacketHandled(true);
	}
}
