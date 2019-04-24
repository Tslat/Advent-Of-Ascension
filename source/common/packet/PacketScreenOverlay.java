package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.Enums;

public class PacketScreenOverlay implements IMessage {
	private int durationTicks;
	private int screenId;

	public PacketScreenOverlay() {}

	public PacketScreenOverlay(final int duration, final Enums.ScreenOverlays screen) {
		durationTicks = duration;
		screenId = screen.id;
	}

	public void fromBytes(final ByteBuf buffer) {
		durationTicks = ByteBufUtils.readVarShort(buffer);
		screenId = ByteBufUtils.readVarShort(buffer);
	}

	public void toBytes(final ByteBuf buffer) {
		ByteBufUtils.writeVarShort(buffer, durationTicks);
		ByteBufUtils.writeVarShort(buffer, screenId);
	}

	public static class Handler implements IMessageHandler<PacketScreenOverlay, IMessage> {
		public IMessage onMessage(final PacketScreenOverlay message, final MessageContext ctx) {
			AdventOfAscension.proxy.displayScreenOverlay(message.durationTicks, Enums.ScreenOverlays.getById(message.screenId));
			return null;
		}
	}
}
