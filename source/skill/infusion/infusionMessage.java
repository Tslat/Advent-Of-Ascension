package net.nevermine.skill.infusion;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class infusionMessage implements IMessage {
	private int value;
	private int percent;

	public infusionMessage() {
	}

	public infusionMessage(final int level, final int per) {
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

	public static class Handler implements IMessageHandler<infusionMessage, IMessage> {
		public IMessage onMessage(final infusionMessage msg, final MessageContext ctx) {
			infusionRenderer.value = msg.value;
			infusionRenderer.percent = msg.percent;
			return null;
		}
	}
}
