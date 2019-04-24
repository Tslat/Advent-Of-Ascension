package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.client.gui.mainwindow.AdventGuiTabPlayer;

public class PacketTributeData implements IMessage {
	private int erebon;
	private int luxon;
	private int pluton;
	private int selyan;

	public PacketTributeData() {}

	public PacketTributeData(final int ere, final int lux, final int plu, final int sel) {
		erebon = ere;
		luxon = lux;
		pluton = plu;
		selyan = sel;
	}

	public void fromBytes(final ByteBuf buffer) {
		erebon = buffer.readInt();
		luxon = buffer.readInt();
		pluton = buffer.readInt();
		selyan = buffer.readInt();
	}

	public void toBytes(final ByteBuf buffer) {
		buffer.writeInt(erebon);
		buffer.writeInt(luxon);
		buffer.writeInt(pluton);
		buffer.writeInt(selyan);
	}

	public static class Handler implements IMessageHandler<PacketTributeData, IMessage> {
		public IMessage onMessage(final PacketTributeData msg, final MessageContext ctx) {
			AdventGuiTabPlayer.tributeErebon = msg.erebon;
			AdventGuiTabPlayer.tributeLuxon = msg.luxon;
			AdventGuiTabPlayer.tributePluton = msg.pluton;
			AdventGuiTabPlayer.tributeSelyan = msg.selyan;

			return null;
		}
	}
}
