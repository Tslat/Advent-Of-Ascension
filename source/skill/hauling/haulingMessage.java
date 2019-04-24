package net.nevermine.skill.hauling;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class haulingMessage implements IMessage {
	private int value;
	private int percent;

	public haulingMessage() {
	}

	public haulingMessage(final int level, final int per) {
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

	public static class Handler implements IMessageHandler<haulingMessage, IMessage> {
		public IMessage onMessage(final haulingMessage msg, final MessageContext ctx) {
			haulingRenderer.value = msg.value;
			haulingRenderer.percent = msg.percent;
			return null;
		}
	}
}
