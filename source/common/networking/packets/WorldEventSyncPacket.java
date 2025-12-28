package net.tslat.aoa3.common.networking.packets;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.world.event.AoAWorldEvent;
import net.tslat.aoa3.content.world.event.AoAWorldEventManager;
import net.tslat.tme.api.util.StreamCodecUtil;
import org.apache.commons.lang3.tuple.Triple;

import java.util.List;

public record WorldEventSyncPacket(List<Triple<AoAWorldEvent.Type<?>, ResourceLocation, CompoundTag>> events) implements AoAPacket {
	public static final Type<WorldEventSyncPacket> TYPE = new Type<>(AdventOfAscension.id("world_event_sync"));
	public static final StreamCodec<RegistryFriendlyByteBuf, WorldEventSyncPacket> CODEC = StreamCodec.composite(
			StreamCodecUtil.triple(Triple::of, ByteBufCodecs.registry(AoARegistries.WORLD_EVENT_TYPE_REGISTRY_KEY), ResourceLocation.STREAM_CODEC, ByteBufCodecs.TRUSTED_COMPOUND_TAG).apply(ByteBufCodecs.list()), WorldEventSyncPacket::events,
			WorldEventSyncPacket::new);

	@Override
	public Type<? extends WorldEventSyncPacket> type() {
		return TYPE;
	}

	@Override
	public void receiveMessage(IPayloadContext context) {
		context.enqueueWork(() -> AoAWorldEventManager.syncFromServer(this.events));
	}
}
