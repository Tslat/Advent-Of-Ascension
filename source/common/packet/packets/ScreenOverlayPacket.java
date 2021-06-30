package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.client.gui.overlay.ScreenOverlayRenderer;

import java.util.function.Supplier;

public class ScreenOverlayPacket implements AoAPacket {
	private final int durationTicks;
	private final short screenId;

	public ScreenOverlayPacket(final Type screen, final int duration) {
		durationTicks = duration;
		screenId = (short)screen.id;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeInt(screenId);
		buffer.writeInt(durationTicks);
	}

	public static ScreenOverlayPacket decode(PacketBuffer buffer) {
		return new ScreenOverlayPacket(Type.getById(buffer.readInt()), buffer.readInt());
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		ScreenOverlayRenderer.overlayTicks = durationTicks;
		ScreenOverlayRenderer.screen = Type.getById(screenId);

		context.get().setPacketHandled(true);
	}

	public enum Type {
		BLOODY(0),
		CIRCLES(1),
		DARKNESS(2),
		GRILLFACE(3),
		LIGHTWALKER(4),
		PURPLE_FOG(5),
		SCRATCHES(6),
		SPIKEY_CIRCLES(7),
		STATIC(8);

		public final int id;

		Type(final int id) {
			this.id = id;
		}

		public static Type getById(final int id) {
			switch(id) {
				case 0:
					return BLOODY;
				case 1:
					return CIRCLES;
				case 2:
					return DARKNESS;
				case 3:
					return GRILLFACE;
				case 4:
					return LIGHTWALKER;
				case 5:
					return PURPLE_FOG;
				case 6:
					return SCRATCHES;
				case 7:
					return SPIKEY_CIRCLES;
				case 8:
					return STATIC;
				default:
					return null;
			}
		}
	}
}
