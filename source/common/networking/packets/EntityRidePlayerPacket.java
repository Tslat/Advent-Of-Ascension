package net.tslat.aoa3.common.networking.packets;

import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.tme.api.util.StreamCodecUtil;

import java.util.UUID;

public record EntityRidePlayerPacket(int entity, UUID player, Operation operation) implements AoAPacket {
	public static final Type<EntityRidePlayerPacket> TYPE = new Type<>(AdventOfAscension.id("entity_ride_player"));
	public static final StreamCodec<RegistryFriendlyByteBuf, EntityRidePlayerPacket> CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, EntityRidePlayerPacket::entity,
            UUIDUtil.STREAM_CODEC, EntityRidePlayerPacket::player,
            StreamCodecUtil.enumCodec(Operation.class), EntityRidePlayerPacket::operation,
            EntityRidePlayerPacket::new);

	@Override
	public Type<? extends EntityRidePlayerPacket> type() {
		return TYPE;
	}

	@Override
	public void receiveMessage(IPayloadContext context) {
        context.enqueueWork(() -> {
            Level level = context.player().level();
            Entity passenger = level.getEntity(this.entity);

            if (passenger == null)
                return;

            Player vehicle = level.getPlayerByUUID(this.player);

            if (vehicle == null)
                return;

            if (this.operation == Operation.MOUNT) {
                passenger.startRiding(vehicle, true);
            }
            else if (this.operation == Operation.DISMOUNT && passenger.getVehicle() == vehicle) {
                passenger.stopRiding();
            }
        });
	}

    public enum Operation {
        MOUNT,
        DISMOUNT
    }
}
