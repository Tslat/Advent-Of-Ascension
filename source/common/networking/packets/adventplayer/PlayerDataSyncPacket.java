package net.tslat.aoa3.common.networking.packets.adventplayer;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.networking.packets.AoAPacket;
import net.tslat.aoa3.player.ClientPlayerDataManager;

public record PlayerDataSyncPacket(CompoundTag data) implements AoAPacket<PlayPayloadContext> {
    public static final ResourceLocation ID = AdventOfAscension.id("player_data_sync");

    @Override
    public ResourceLocation id() {
        return ID;
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeNbt(this.data);
    }

    public static PlayerDataSyncPacket decode(FriendlyByteBuf buffer) {
        return new PlayerDataSyncPacket(buffer.readNbt());
    }

    @Override
    public void receiveMessage(PlayPayloadContext context) {
        ClientPlayerDataManager.get().loadFromNbt(this.data);
    }
}
