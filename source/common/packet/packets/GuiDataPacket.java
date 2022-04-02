package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
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
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeUtf(type.toString());
		buffer.writeUtf(stringData);
	}

	public static GuiDataPacket decode(FriendlyByteBuf buffer) {
		return new GuiDataPacket(Type.valueOf(buffer.readUtf(32767)), buffer.readUtf(32767));
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		switch (type) {
			case FRAME_BENCH_SELECTION:
				if (context.get().getSender().containerMenu instanceof FrameBenchContainer)
					((FrameBenchContainer)context.get().getSender().containerMenu).changeSelection(stringData);
		}

		context.get().setPacketHandled(true);
	}

	public enum Type {
		FRAME_BENCH_SELECTION;
	}
}
