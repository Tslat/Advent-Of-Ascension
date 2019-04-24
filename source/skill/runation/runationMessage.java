package net.nevermine.skill.runation;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class runationMessage implements IMessage {
	private int value;
	private int percent;

	public runationMessage() {
	}

	public runationMessage(final int level, final int per) {
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

	public static class Handler implements IMessageHandler<runationMessage, IMessage> {
		public IMessage onMessage(final runationMessage msg, final MessageContext ctx) {
			runationRenderer.value = msg.value;
			runationRenderer.percent = msg.percent;
			return null;
		}
	}
}
