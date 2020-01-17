package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.common.containers.ContainerFrameBench;

public class PacketGuiData implements IMessage {
	private String type;
	private int intData;
	private String stringData;

	public PacketGuiData() {}

	public PacketGuiData(final String type, final String dataString) {
		this.type = type;
		this.stringData = dataString;
	}

	public void fromBytes(final ByteBuf buffer) {
		type = ByteBufUtils.readUTF8String(buffer);

		switch (type) {
			case "FrameBenchSelect":
				stringData = ByteBufUtils.readUTF8String(buffer);
				break;
			default:
				break;
		}
	}

	public void toBytes(final ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, type);

		switch (type) {
			case "FrameBenchSelect":
				ByteBufUtils.writeUTF8String(buffer, stringData);
				break;
			default:
				break;
		}
	}

	public static class Handler implements IMessageHandler<PacketGuiData, IMessage> {
		public IMessage onMessage(final PacketGuiData msg, final MessageContext ctx) {
			switch (msg.type) {
				case "FrameBenchSelect":
					if (ctx.getServerHandler().player.openContainer instanceof ContainerFrameBench)
						((ContainerFrameBench)ctx.getServerHandler().player.openContainer).changeSelection(msg.stringData);
					break;
				default:
					break;
			}

			return null;
		}
	}
}
