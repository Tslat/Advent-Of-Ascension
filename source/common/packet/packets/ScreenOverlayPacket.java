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
		buffer.writeInt(durationTicks);
		buffer.writeShort(screenId);
	}

	public static ScreenOverlayPacket decode(PacketBuffer buffer) {
		return new ScreenOverlayPacket(Type.getById(buffer.readShort()), buffer.readInt());
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
		CONIFERON_VINES(2),
		DARKNESS(3),
		EILOSAPIEN(4),
		GRILLFACE(5),
		LIGHTWALKER(6),
		PURPLE_FOG(7),
		SCRATCHES(8),
		SHYRELANDS_BLIND(9),
		SHYRELANDS_DIZZY(10),
		SPIKEY_CIRCLES(11),
		STATIC(12);

		public final int id;

		Type(final int id) {
			this.id = id;
		}

		public static Type getById(final int id) {
			switch(id) {
				case 0:
					return SCRATCHES;
				case 1:
					return BLOODY;
				case 2:
					return STATIC;
				case 3:
					return GRILLFACE;
				case 4:
					return DARKNESS;
				case 5:
					return EILOSAPIEN;
				case 6:
					return PURPLE_FOG;
				case 7:
					return CIRCLES;
				case 8:
					return CONIFERON_VINES;
				case 9:
					return SPIKEY_CIRCLES;
				case 10:
					return SHYRELANDS_DIZZY;
				case 11:
					return SHYRELANDS_BLIND;
				case 12:
					return LIGHTWALKER;
				default:
					return null;
			}
		}
	}
}
