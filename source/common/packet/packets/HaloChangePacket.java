package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.util.AoAHaloUtil;
import org.apache.logging.log4j.Level;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class HaloChangePacket implements AoAPacket {
	private final String haloChoice;

	public HaloChangePacket(@Nonnull final AoAHaloUtil.Type.Choosable haloChoice) {
		this.haloChoice = haloChoice.toString();
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeUtf(haloChoice);
	}

	public static HaloChangePacket decode(FriendlyByteBuf buffer) {
		return new HaloChangePacket(AoAHaloUtil.Type.Choosable.valueOf(buffer.readUtf(32767)));
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		AoAHaloUtil.Type preferredHalo = AoAHaloUtil.Type.Donator;

		try {
			preferredHalo = AoAHaloUtil.Type.Choosable.valueOf(haloChoice).toBaseType();
		}
		catch (IllegalArgumentException e) {
			Logging.logMessage(Level.WARN, "Error parsing halo info from client: " + haloChoice);
		}

		ServerPlayer sender = context.get().getSender();

		if (sender != null) {
			AoAHaloUtil.syncNewHaloChoice(sender.getGameProfile().getId(), preferredHalo);
		}
		else {
			Logging.logMessage(Level.WARN, "No sender assigned to received PacketChangedHalo, skipping");
		}

		context.get().setPacketHandled(true);
	}
}
