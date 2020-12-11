package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.common.container.FrameBenchContainer;

import java.util.function.Supplier;

public class GuiDataPacket implements AoAPacket {
	private final Type type;
	private final String stringData;

	public GuiDataPacket(final Type type, final String dataString) {
		this.type = type;
		this.stringData = dataString;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeString(type.toString());
		buffer.writeString(stringData);
	}

	public static GuiDataPacket decode(PacketBuffer buffer) {
		return new GuiDataPacket(Type.valueOf(buffer.readString()), buffer.readString());
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		switch (type) {
			case FRAME_BENCH_SELECTION:
				if (context.get().getSender().openContainer instanceof FrameBenchContainer)
					((FrameBenchContainer)context.get().getSender().openContainer).changeSelection(stringData);
		}

		context.get().setPacketHandled(true);
	}

	public enum Type {
		FRAME_BENCH_SELECTION;
	}
}
