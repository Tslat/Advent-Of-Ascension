package net.tslat.aoa3.common.packet.packets;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.config.AoAConfig;
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
	public void encode(PacketBuffer buffer) {
		buffer.writeUtf(haloChoice);
	}

	public static HaloChangePacket decode(PacketBuffer buffer) {
		return new HaloChangePacket(AoAHaloUtil.Type.Choosable.valueOf(buffer.readUtf(32767)));
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		AoAHaloUtil.Type preferredHalo = AoAHaloUtil.Type.Donator;

		try {
			preferredHalo = AoAHaloUtil.Type.Choosable.valueOf(haloChoice).toBaseType();
		}
		catch (IllegalArgumentException e) {
			if (AoAConfig.COMMON.doVerboseDebugging.get()) {
				Logging.logMessage(Level.WARN, "Error parsing halo info from client: " + haloChoice);
				e.printStackTrace();
			}
		}

		ServerPlayerEntity sender = context.get().getSender();

		if (sender != null) {
			AoAHaloUtil.syncNewHaloChoice(sender.getGameProfile().getId(), preferredHalo);
		}
		else {
			Logging.logMessage(Level.WARN, "No sender assigned to received PacketChangedHalo, skipping");
		}

		context.get().setPacketHandled(true);
	}
}
