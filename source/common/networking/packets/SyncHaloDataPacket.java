package net.tslat.aoa3.common.networking.packets;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.advent.Logging;
import net.tslat.aoa3.player.halo.PlayerHaloContainer;
import net.tslat.aoa3.player.halo.PlayerHaloManager;
import org.apache.logging.log4j.Level;

import java.util.Map;
import java.util.UUID;

public record SyncHaloDataPacket(Map<UUID, PlayerHaloContainer> halos) implements AoAPacket<IPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("sync_halo_data");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeMap(this.halos, FriendlyByteBuf::writeUUID, (buffer1, container) -> container.toNetwork(buffer));
	}

	public static SyncHaloDataPacket decode(FriendlyByteBuf buffer) {
		return new SyncHaloDataPacket(buffer.readMap(Object2ObjectOpenHashMap::new, FriendlyByteBuf::readUUID, PlayerHaloContainer::fromNetwork));
	}

	@Override
	public void receiveMessage(IPayloadContext context) {
		Logging.logMessage(Level.DEBUG, "Received player halos map update");
		PlayerHaloManager.syncFromServer(this.halos);
	}
}
