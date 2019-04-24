package net.nevermine.resource.revenge;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class revengeMessage implements IMessage {
	private boolean activated;

	public revengeMessage() {
	}

	public revengeMessage(final boolean act) {
		activated = act;
	}

	public void fromBytes(final ByteBuf buf) {
		activated = buf.readBoolean();
	}

	public void toBytes(final ByteBuf buf) {
		buf.writeBoolean(activated);
	}

	public static class Handler implements IMessageHandler<revengeMessage, IMessage> {
		public IMessage onMessage(final revengeMessage msg, final MessageContext ctx) {
			revengeRenderer.activated = msg.activated;
			return null;
		}
	}
}
