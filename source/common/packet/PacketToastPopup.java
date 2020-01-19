package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.Enums;

public class PacketToastPopup implements IMessage {
	private ToastPopupType type;
	private Object subject;
	private Object value;

	public PacketToastPopup() {}

	public PacketToastPopup(final Enums.Skills skill, final int levelReq) {
		this.type = ToastPopupType.SKILL_REQUIREMENT;
		this.value = levelReq;
		this.subject = skill;
	}

	public PacketToastPopup(final Enums.Resources resource, final float amount) {
		this.type = ToastPopupType.RESOURCE_REQUIREMENT;
		this.value = amount;
		this.subject = resource;
	}

	public PacketToastPopup(final Enums.Deities deity, final int tributeAmount) {
		this.type = ToastPopupType.TRIBUTE_REQUIREMENT;
		this.subject = deity;
		this.value = tributeAmount;
	}

	public void fromBytes(final ByteBuf buffer) {
		type = ToastPopupType.valueOf(ByteBufUtils.readUTF8String(buffer));

		switch (type) {
			case SKILL_REQUIREMENT:
				subject = Enums.Skills.valueOf(ByteBufUtils.readUTF8String(buffer));
				value = buffer.readInt();
				break;
			case RESOURCE_REQUIREMENT:
				subject = Enums.Resources.valueOf(ByteBufUtils.readUTF8String(buffer));
				value = buffer.readFloat();
				break;
			case TRIBUTE_REQUIREMENT:
				subject = Enums.Deities.valueOf(ByteBufUtils.readUTF8String(buffer));
				value = buffer.readInt();
				break;
		}
	}

	public void toBytes(final ByteBuf buffer) {
		ByteBufUtils.writeUTF8String(buffer, type.toString());

		switch (type) {
			case SKILL_REQUIREMENT:
			case TRIBUTE_REQUIREMENT:
				ByteBufUtils.writeUTF8String(buffer, subject.toString());
				buffer.writeInt((Integer)value);
				break;
			case RESOURCE_REQUIREMENT:
				ByteBufUtils.writeUTF8String(buffer, subject.toString());
				buffer.writeFloat((Float)value);
				break;
		}
	}

	public static class Handler implements IMessageHandler<PacketToastPopup, IMessage> {
		public IMessage onMessage(final PacketToastPopup message, final MessageContext ctx) {
			AdventOfAscension.proxy.displayToast(message.type, message.subject, message.value);

			return null;
		}
	}

	public enum ToastPopupType {
		SKILL_REQUIREMENT,
		RESOURCE_REQUIREMENT,
		TRIBUTE_REQUIREMENT
	}
}