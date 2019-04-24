package net.nevermine.event.player;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.nevermine.common.nevermine;

public class HealthMessage implements IMessage {
	private float health;

	public HealthMessage() {
	}

	public HealthMessage(final float val) {
		health = val;
	}

	public void fromBytes(final ByteBuf buf) {
		health = buf.readFloat();
	}

	public void toBytes(final ByteBuf buf) {
		buf.writeFloat(health);
	}

	public static class Handler implements IMessageHandler<HealthMessage, IMessage> {
		public IMessage onMessage(final HealthMessage msg, final MessageContext ctx) {
			nevermine.proxy.setPlayerHealth(msg.health);
			return null;
		}
	}
}
