package net.nevermine.skill.expedition;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.nevermine.container.PlayerContainer;

public class reverseExpeditionMessage implements IMessage {
	public void fromBytes(final ByteBuf buf) {
	}

	public void toBytes(final ByteBuf buf) {
	}

	public static class Handler implements IMessageHandler<reverseExpeditionMessage, IMessage> {
		public IMessage onMessage(final reverseExpeditionMessage msg, final MessageContext ctx) {
			PlayerContainer.getProperties(ctx.getServerHandler().playerEntity).expeditionBoostUp();
			return null;
		}
	}
}
