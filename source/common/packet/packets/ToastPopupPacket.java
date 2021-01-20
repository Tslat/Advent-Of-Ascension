package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.util.ClientOperations;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.constant.Skills;

import java.util.function.Supplier;

public class ToastPopupPacket implements AoAPacket {
	private final ToastPopupType type;
	private final Object subject;
	private final Object value;

	public ToastPopupPacket(final Skills skill, final int levelReq) {
		this.type = ToastPopupType.SKILL_REQUIREMENT;
		this.value = levelReq;
		this.subject = skill;
	}

	public ToastPopupPacket(final Resources resource, final float amount) {
		this.type = ToastPopupType.RESOURCE_REQUIREMENT;
		this.value = amount;
		this.subject = resource;
	}

	public ToastPopupPacket(final Deities deity, final int tributeAmount) {
		this.type = ToastPopupType.TRIBUTE_REQUIREMENT;
		this.subject = deity;
		this.value = tributeAmount;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeString(type.toString());

		switch (type) {
			case SKILL_REQUIREMENT:
			case TRIBUTE_REQUIREMENT:
				buffer.writeString(subject.toString());
				buffer.writeInt((Integer)value);
				break;
			case RESOURCE_REQUIREMENT:
				buffer.writeString(subject.toString());
				buffer.writeFloat((Float)value);
				break;
		}
	}

	public static ToastPopupPacket decode(PacketBuffer buffer) {
		ToastPopupType type = ToastPopupType.valueOf(buffer.readString(32767));

		switch (type) {
			case SKILL_REQUIREMENT:
				return new ToastPopupPacket(Skills.valueOf(buffer.readString(32767)), buffer.readInt());
			case RESOURCE_REQUIREMENT:
				return new ToastPopupPacket(Resources.valueOf(buffer.readString(32767)), buffer.readFloat());
			case TRIBUTE_REQUIREMENT:
				return new ToastPopupPacket(Deities.valueOf(buffer.readString(32767)), buffer.readInt());
		}

		return null;
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		ClientOperations.addToast(type, subject, value);

		context.get().setPacketHandled(true);
	}

	public enum ToastPopupType {
		SKILL_REQUIREMENT,
		RESOURCE_REQUIREMENT,
		TRIBUTE_REQUIREMENT
	}
}
