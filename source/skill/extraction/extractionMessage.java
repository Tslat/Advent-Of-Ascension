package net.nevermine.skill.extraction;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class extractionMessage implements IMessage {
	private int value;
	private int percent;

	public extractionMessage() {
	}

	public extractionMessage(final int level, final int per) {
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

	public static class Handler implements IMessageHandler<extractionMessage, IMessage> {
		public IMessage onMessage(final extractionMessage msg, final MessageContext ctx) {
			extractionRenderer.value = msg.value;
			extractionRenderer.percent = msg.percent;
			return null;
		}
	}
}
