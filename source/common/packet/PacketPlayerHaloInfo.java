package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.handlers.PlayerHaloHandler;
import net.tslat.aoa3.library.Enums;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PacketPlayerHaloInfo implements IMessage {
	private int mapSize;
	private HashMap<UUID, PlayerHaloHandler.PlayerHaloContainer> halosMap;

	public PacketPlayerHaloInfo() {}

	public PacketPlayerHaloInfo(UUID uuid, Enums.PlayerHaloTypes halo) {
		halosMap = new HashMap<UUID, PlayerHaloHandler.PlayerHaloContainer>();
		mapSize = 1;

		halosMap.put(uuid, new PlayerHaloHandler.PlayerHaloContainer(halo));
	}

	public PacketPlayerHaloInfo(HashMap<UUID, PlayerHaloHandler.PlayerHaloContainer> halosMap) {
		this.mapSize = halosMap.size();
		this.halosMap = halosMap;
	}

	public void fromBytes(final ByteBuf buffer) {
		mapSize = buffer.readInt();
		halosMap = new HashMap<UUID, PlayerHaloHandler.PlayerHaloContainer>();

		for (int i = 0; i < mapSize; i++) {
			try {
				halosMap.put(UUID.fromString(ByteBufUtils.readUTF8String(buffer)), new PlayerHaloHandler.PlayerHaloContainer(Enums.PlayerHaloTypes.valueOf(ByteBufUtils.readUTF8String(buffer))));
			}
			catch (Exception e) {
				i++;
			}
		}
	}

	public void toBytes(final ByteBuf buffer) {
		buffer.writeInt(mapSize);

		for (Map.Entry<UUID, PlayerHaloHandler.PlayerHaloContainer> entry : halosMap.entrySet()) {
			ByteBufUtils.writeUTF8String(buffer, entry.getKey().toString());
			ByteBufUtils.writeUTF8String(buffer, entry.getValue().getPreferredHalo().toString());
		}
	}

	public static class Handler implements IMessageHandler<PacketPlayerHaloInfo, IMessage> {
		public IMessage onMessage(final PacketPlayerHaloInfo msg, final MessageContext ctx) {
			AdventOfAscension.logOptionalMessage("Received player halos map update");

			for (Map.Entry<UUID, PlayerHaloHandler.PlayerHaloContainer> entry : msg.halosMap.entrySet()) {
				PlayerHaloHandler.setHaloChoice(entry.getKey(), entry.getValue().getPreferredHalo());
				AdventOfAscension.logOptionalMessage("UUID: " + entry.getKey() + "; Halo: " + entry.getValue().getPreferredHalo().toString());
			}

			return null;
		}
	}
}
