package net.nevermine.skill.augury;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class auguryMessage implements IMessage {
	private int value;
	private int percent;

	public auguryMessage() {
	}

	public auguryMessage(final int level, final int per) {
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

	public static class Handler implements IMessageHandler<auguryMessage, IMessage> {
		public IMessage onMessage(final auguryMessage msg, final MessageContext ctx) {
			auguryRenderer.value = msg.value;
			auguryRenderer.percent = msg.percent;
			return null;
		}
	}
}
