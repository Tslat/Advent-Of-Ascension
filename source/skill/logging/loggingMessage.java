package net.nevermine.skill.logging;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class loggingMessage implements IMessage {
	private int value;
	private int percent;

	public loggingMessage() {
	}

	public loggingMessage(final int level, final int per) {
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

	public static class Handler implements IMessageHandler<loggingMessage, IMessage> {
		public IMessage onMessage(final loggingMessage msg, final MessageContext ctx) {
			loggingRenderer.value = msg.value;
			loggingRenderer.percent = msg.percent;
			return null;
		}
	}
}
