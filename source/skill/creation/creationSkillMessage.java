package net.nevermine.skill.creation;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class creationSkillMessage implements IMessage {
	private int value;
	private int percent;

	public creationSkillMessage() {
	}

	public creationSkillMessage(final int level, final int per) {
		value = level;
		percent = per;
	}

	public void fromBytes(final ByteBuf buf) {
		value = buf.readInt();
		percent = buf.readInt();
	}

	public void toBytes(final ByteBuf buf) {
		buf.writeInt(value);
		buf.writeInt(percent);
	}

	public static class Handler implements IMessageHandler<creationSkillMessage, IMessage> {
		public IMessage onMessage(final creationSkillMessage msg, final MessageContext ctx) {
			creationSkillRenderer.value = msg.value;
			creationSkillRenderer.percent = msg.percent;
			return null;
		}
	}
}
