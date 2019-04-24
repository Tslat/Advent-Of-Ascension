package net.nevermine.skill.expedition;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class expeditionMessage implements IMessage {
	private int value;
	private int percent;
	private int powerUp;

	public expeditionMessage() {
	}

	public expeditionMessage(final int level, final int per, final int pU) {
		value = level;
		percent = per;
		powerUp = pU;
	}

	public void fromBytes(final ByteBuf buf) {
		value = buf.readInt();
		percent = buf.readInt();
		powerUp = buf.readInt();
	}

	public void toBytes(final ByteBuf buf) {
		buf.writeInt(value);
		buf.writeInt(percent);
		buf.writeInt(powerUp);
	}

	public static class Handler implements IMessageHandler<expeditionMessage, IMessage> {
		public IMessage onMessage(final expeditionMessage msg, final MessageContext ctx) {
			expeditionRenderer.value = msg.value;
			expeditionRenderer.percent = msg.percent;
			expeditionRenderer.power = msg.powerUp;
			return null;
		}
	}
}
