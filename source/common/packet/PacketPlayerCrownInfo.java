package net.tslat.aoa3.common.packet;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.tslat.aoa3.common.handlers.PlayerCrownHandler;
import net.tslat.aoa3.library.Enums;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PacketPlayerCrownInfo implements IMessage {
	private int mapSize;
	private HashMap<UUID, PlayerCrownHandler.PlayerCrownContainer> crownsMap;

	public PacketPlayerCrownInfo() {}

	public PacketPlayerCrownInfo(UUID uuid, Enums.PlayerCrownTypes crown) {
		crownsMap = new HashMap<UUID, PlayerCrownHandler.PlayerCrownContainer>();
		mapSize = 1;

		crownsMap.put(uuid, new PlayerCrownHandler.PlayerCrownContainer(crown));
	}

	public PacketPlayerCrownInfo(HashMap<UUID, PlayerCrownHandler.PlayerCrownContainer> crownsMap) {
		this.mapSize = crownsMap.size();
		this.crownsMap = crownsMap;
	}

	public void fromBytes(final ByteBuf buffer) {
		mapSize = buffer.readInt();
		crownsMap = new HashMap<UUID, PlayerCrownHandler.PlayerCrownContainer>();

		for (int i = 0; i < mapSize; i++) {
			try {
				crownsMap.put(UUID.fromString(ByteBufUtils.readUTF8String(buffer)), new PlayerCrownHandler.PlayerCrownContainer(Enums.PlayerCrownTypes.valueOf(ByteBufUtils.readUTF8String(buffer))));
			}
			catch (Exception e) {
				i++;
			}
		}
	}

	public void toBytes(final ByteBuf buffer) {
		buffer.writeInt(mapSize);

		for (Map.Entry<UUID, PlayerCrownHandler.PlayerCrownContainer> entry : crownsMap.entrySet()) {
			ByteBufUtils.writeUTF8String(buffer, entry.getKey().toString());
			ByteBufUtils.writeUTF8String(buffer, entry.getValue().getPreferredCrown().toString());
		}
	}

	public static class Handler implements IMessageHandler<PacketPlayerCrownInfo, IMessage> {
		public IMessage onMessage(final PacketPlayerCrownInfo msg, final MessageContext ctx) {
			for (Map.Entry<UUID, PlayerCrownHandler.PlayerCrownContainer> entry : msg.crownsMap.entrySet()) {
				PlayerCrownHandler.setCrownChoice(entry.getKey(), entry.getValue().getPreferredCrown());
			}

			return null;
		}
	}
}
