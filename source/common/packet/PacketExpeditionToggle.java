package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.utils.PlayerUtil;

public class PacketExpeditionToggle implements IMessage {

	public void fromBytes(final ByteBuf buffer) {}

	public void toBytes(final ByteBuf buffer) {}

	public static class Handler implements IMessageHandler<PacketExpeditionToggle, IMessage> {
		public IMessage onMessage(final PacketExpeditionToggle msg, final MessageContext ctx) {
			PlayerUtil.getAdventPlayer(ctx.getServerHandler().player).incrementExpeditionBoost();

			return null;
		}
	}
}