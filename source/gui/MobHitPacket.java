package net.nevermine.gui;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.nevermine.common.nevermine;

public class MobHitPacket implements IMessage {
	public int ticks;
	public int screen;

	public MobHitPacket() {
	}

	public MobHitPacket(final int counter, final int pict) {
		ticks = counter;
		screen = pict;
	}

	public void fromBytes(final ByteBuf buf) {
		ticks = ByteBufUtils.readVarShort(buf);
		screen = ByteBufUtils.readVarShort(buf);
	}

	public void toBytes(final ByteBuf buf) {
		ByteBufUtils.writeVarShort(buf, ticks);
		ByteBufUtils.writeVarShort(buf, screen);
	}

	public static class Handler implements IMessageHandler<MobHitPacket, IMessage> {
		public IMessage onMessage(final MobHitPacket message, final MessageContext ctx) {
			nevermine.proxy.displayMobScreen(message.ticks, message.screen);
			return null;
		}
	}
}
