package net.nevermine.event.recoil;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.nevermine.common.nevermine;

public class RecoilMessage implements IMessage {
	private float rec;

	public RecoilMessage() {
	}

	public RecoilMessage(final float recoil) {
		rec = recoil;
	}

	public void fromBytes(final ByteBuf buf) {
		rec = buf.readFloat();
	}

	public void toBytes(final ByteBuf buf) {
		buf.writeFloat(rec);
	}

	public static class Handler implements IMessageHandler<RecoilMessage, IMessage> {
		public IMessage onMessage(final RecoilMessage msg, final MessageContext ctx) {
			nevermine.proxy.recoilTicking(msg.rec);
			return null;
		}
	}
}
