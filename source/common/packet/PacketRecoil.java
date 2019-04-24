package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.advent.AdventOfAscension;

public class PacketRecoil implements IMessage {
	private float recoilAmount;
	private int firingTime;

	public PacketRecoil() {}

	public PacketRecoil(final float recoil, final int firingDelay) {
		recoilAmount = recoil;
		firingTime = firingDelay;
	}

	public void fromBytes(final ByteBuf buffer) {
		recoilAmount = buffer.readFloat();
		firingTime = buffer.readInt();
	}

	public void toBytes(final ByteBuf buffer) {
		buffer.writeFloat(recoilAmount);
		buffer.writeInt(firingTime);
	}

	public static class Handler implements IMessageHandler<PacketRecoil, IMessage> {
		public IMessage onMessage(final PacketRecoil msg, final MessageContext ctx) {
			AdventOfAscension.proxy.addRecoil(msg.recoilAmount, msg.firingTime);
			return null;
		}
	}
}
