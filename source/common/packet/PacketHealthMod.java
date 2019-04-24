package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.advent.AdventOfAscension;

public class PacketHealthMod implements IMessage {
	private float health;

	public PacketHealthMod() {}

	public PacketHealthMod(final float value) {
		health = value;
	}

	public void fromBytes(final ByteBuf buffer) {
		health = buffer.readFloat();
	}

	public void toBytes(final ByteBuf buffer) {
		buffer.writeFloat(health);
	}

	public static class Handler implements IMessageHandler<PacketHealthMod, IMessage> {
		public IMessage onMessage(final PacketHealthMod msg, final MessageContext ctx) {
			AdventOfAscension.proxy.setPlayerHealth(msg.health);
			return null;
		}
	}
}
