package net.nevermine.skill.innervation;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class innervationMessage implements IMessage {
	private int value;
	private int percent;

	public innervationMessage() {
	}

	public innervationMessage(final int level, final int per) {
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

	public static class Handler implements IMessageHandler<innervationMessage, IMessage> {
		public IMessage onMessage(final innervationMessage msg, final MessageContext ctx) {
			innervationRenderer.value = msg.value;
			innervationRenderer.percent = msg.percent;
			return null;
		}
	}
}
