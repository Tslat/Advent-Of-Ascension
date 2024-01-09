package net.tslat.aoa3.common.networking.packets.setup;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.ConfigurationPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.configtask.PlayerHalosHandshakeTask;
import net.tslat.aoa3.common.networking.packets.AoAPacket;
import net.tslat.aoa3.player.halo.PlayerHaloContainer;
import net.tslat.aoa3.player.halo.PlayerHaloManager;

import java.util.Map;
import java.util.UUID;

public record PlayerHalosLoginSyncPacket(Map<UUID, PlayerHaloContainer> halosMap, boolean ack) implements AoAPacket<ConfigurationPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("player_halos_sync");

	public PlayerHalosLoginSyncPacket(Map<UUID, PlayerHaloContainer> halosMap) {
		this(halosMap, false);
	}

	private PlayerHalosLoginSyncPacket(boolean isAck) {
		this(null, true);
	}

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeBoolean(this.ack);

		if (!this.ack)
			buffer.writeMap(this.halosMap, FriendlyByteBuf::writeUUID, (buffer1, container) -> container.toNetwork(buffer));
	}

	public static PlayerHalosLoginSyncPacket decode(FriendlyByteBuf buffer) {
		final boolean ack = buffer.readBoolean();

		return new PlayerHalosLoginSyncPacket(
				!ack ? buffer.readMap(Object2ObjectOpenHashMap::new, FriendlyByteBuf::readUUID, PlayerHaloContainer::fromNetwork) : null,
				ack);
	}

	@Override
	public void receiveMessage(ConfigurationPayloadContext context) {
		if (this.ack) {
			context.taskCompletedHandler().onTaskCompleted(PlayerHalosHandshakeTask.TYPE);
		}
		else {
			PlayerHaloManager.syncFromServer(this.halosMap);
			context.replyHandler().send(new PlayerHalosLoginSyncPacket(true));
		}
	}
}
