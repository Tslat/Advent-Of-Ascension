package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.client.gui.overlay.ScreenOverlayRenderer;

import java.util.function.Supplier;

public class ScreenOverlayPacket implements AoAPacket {
	private final int durationTicks;
	private final ResourceLocation overlay;

	public ScreenOverlayPacket(final ResourceLocation overlay, final int duration) {
		this.durationTicks = duration;
		this.overlay = overlay;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeResourceLocation(overlay);
		buffer.writeInt(durationTicks);
	}

	public static ScreenOverlayPacket decode(PacketBuffer buffer) {
		return new ScreenOverlayPacket(buffer.readResourceLocation(), buffer.readInt());
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		ScreenOverlayRenderer.addOverlay(overlay, durationTicks);

		context.get().setPacketHandled(true);
	}
}
