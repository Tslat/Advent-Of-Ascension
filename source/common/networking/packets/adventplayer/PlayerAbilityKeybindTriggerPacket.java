package net.tslat.aoa3.common.networking.packets.adventplayer;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.packets.AoAPacket;
import net.tslat.aoa3.event.AoAPlayerEvents;

import java.util.List;

public record PlayerAbilityKeybindTriggerPacket(List<String> abilities) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("player_ability_keybind_trigger");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeCollection(this.abilities, FriendlyByteBuf::writeUtf);
	}

	public static PlayerAbilityKeybindTriggerPacket decode(FriendlyByteBuf buffer) {
		return new PlayerAbilityKeybindTriggerPacket(buffer.readList(FriendlyByteBuf::readUtf));
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		context.workHandler().execute(() -> AoAPlayerEvents.onKeyPress((ServerPlayer)context.player().get(), this.abilities));
	}
}
