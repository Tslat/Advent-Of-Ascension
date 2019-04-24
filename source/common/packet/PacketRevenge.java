package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.client.gui.render.ResourcesRenderer;

public class PacketRevenge implements IMessage {
	private boolean activated;

	public PacketRevenge() {}

	public PacketRevenge(final boolean value) {
		activated = value;
	}

	public void fromBytes(final ByteBuf buffer) {
		activated = buffer.readBoolean();
	}

	public void toBytes(final ByteBuf buffer) {
		buffer.writeBoolean(activated);
	}

	public static class Handler implements IMessageHandler<PacketRevenge, IMessage> {
		public IMessage onMessage(final PacketRevenge msg, final MessageContext ctx) {
			ResourcesRenderer.revengeActive = msg.activated;
			return null;
		}
	}
}
