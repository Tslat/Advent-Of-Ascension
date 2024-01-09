package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.player.halo.PlayerHaloManager;
import net.tslat.aoa3.player.halo.HaloTypes;

import java.util.UUID;

public record HaloChangePacket(UUID player, HaloTypes selected) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("halo_change");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeUUID(this.player);
		buffer.writeEnum(this.selected);
	}

	public static HaloChangePacket decode(FriendlyByteBuf buffer) {
		return new HaloChangePacket(buffer.readUUID(), buffer.readEnum(HaloTypes.class));
	}

	public void receiveMessage(PlayPayloadContext context) {
		PlayerHaloManager.updateSelectedHalo(this.player, this.selected, false);
	}
}
