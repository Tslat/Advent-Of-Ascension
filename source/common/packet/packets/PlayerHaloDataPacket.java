package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.util.AoAHaloUtil;
import org.apache.logging.log4j.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class PlayerHaloDataPacket implements AoAPacket {
	private final int mapSize;
	private final HashMap<UUID, AoAHaloUtil.PlayerHaloContainer> halosMap;

	public PlayerHaloDataPacket(UUID uuid, AoAHaloUtil.Type halo) {
		halosMap = new HashMap<UUID, AoAHaloUtil.PlayerHaloContainer>();
		mapSize = 1;

		halosMap.put(uuid, new AoAHaloUtil.PlayerHaloContainer(halo));
	}

	public PlayerHaloDataPacket(HashMap<UUID, AoAHaloUtil.PlayerHaloContainer> halosMap) {
		this.mapSize = halosMap.size();
		this.halosMap = halosMap;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeInt(mapSize);

		for (Map.Entry<UUID, AoAHaloUtil.PlayerHaloContainer> entry : halosMap.entrySet()) {
			buffer.writeUtf(entry.getKey().toString());
			buffer.writeUtf(entry.getValue().getPreferredHalo().toString());
		}
	}

	public static PlayerHaloDataPacket decode(FriendlyByteBuf buffer) {
		int mapSize = buffer.readInt();
		HashMap<UUID, AoAHaloUtil.PlayerHaloContainer> halosMap = new HashMap<UUID, AoAHaloUtil.PlayerHaloContainer>();

		for (int i = 0; i < mapSize; i++) {
			try {
				halosMap.put(UUID.fromString(buffer.readUtf(32767)), new AoAHaloUtil.PlayerHaloContainer(AoAHaloUtil.Type.valueOf(buffer.readUtf(32767))));
			}
			catch (Exception e) {
				Logging.logMessage(Level.WARN, "Invalid formatting on received player halo type. This shouldn't happen.");
			}
		}

		return new PlayerHaloDataPacket(halosMap);
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		Logging.logMessage(Level.DEBUG, "Received player halos map update");

		for (Map.Entry<UUID, AoAHaloUtil.PlayerHaloContainer> entry : halosMap.entrySet()) {
			AoAHaloUtil.setHaloChoice(entry.getKey(), entry.getValue().getPreferredHalo());
			Logging.logMessage(Level.DEBUG, "UUID: " + entry.getKey() + "; Halo: " + entry.getValue().getPreferredHalo().toString());
		}

		context.get().setPacketHandled(true);
	}
}
