package net.nevermine.skill.hunter;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class hunterMessage implements IMessage {
	private int value;
	private int percent;

	public hunterMessage() {
	}

	public hunterMessage(final int level, final int per) {
		value = level;
		percent = per;
	}

	public void fromBytes(final ByteBuf buf) {
		value = buf.readInt();
		percent = buf.readInt();
	}

	public void toBytes(final ByteBuf buf) {
		buf.writeInt(value);
		buf.writeInt(percent);
	}

	public static class Handler implements IMessageHandler<hunterMessage, IMessage> {
		public IMessage onMessage(final hunterMessage msg, final MessageContext ctx) {
			hunterRenderer.value = msg.value;
			hunterRenderer.percent = msg.percent;
			return null;
		}
	}
}
