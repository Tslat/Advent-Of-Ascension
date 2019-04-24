package net.nevermine.resource.tribute;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class TributeMessage implements IMessage {
	private int selyan;
	private int luxon;
	private int erebon;
	private int pluton;

	public TributeMessage() {
	}

	public TributeMessage(final int sel, final int lux, final int ere, final int plu) {
		selyan = sel;
		luxon = lux;
		erebon = ere;
		pluton = plu;
	}

	public void fromBytes(final ByteBuf buf) {
		selyan = buf.readInt();
		luxon = buf.readInt();
		erebon = buf.readInt();
		pluton = buf.readInt();
	}

	public void toBytes(final ByteBuf buf) {
		buf.writeInt(selyan);
		buf.writeInt(luxon);
		buf.writeInt(erebon);
		buf.writeInt(pluton);
	}

	public static class Handler implements IMessageHandler<TributeMessage, IMessage> {
		public IMessage onMessage(final TributeMessage msg, final MessageContext ctx) {
			tributeRenderer.selyan = msg.selyan;
			tributeRenderer.luxon = msg.luxon;
			tributeRenderer.erebon = msg.erebon;
			tributeRenderer.pluton = msg.pluton;
			return null;
		}
	}
}
