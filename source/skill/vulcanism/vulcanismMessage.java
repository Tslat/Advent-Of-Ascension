package net.nevermine.skill.vulcanism;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class vulcanismMessage implements IMessage {
	private int value;
	private int percent;

	public vulcanismMessage() {
	}

	public vulcanismMessage(final int level, final int per) {
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

	public static class Handler implements IMessageHandler<vulcanismMessage, IMessage> {
		public IMessage onMessage(final vulcanismMessage msg, final MessageContext ctx) {
			vulcanismRenderer.value = msg.value;
			vulcanismRenderer.percent = msg.percent;
			return null;
		}
	}
}
