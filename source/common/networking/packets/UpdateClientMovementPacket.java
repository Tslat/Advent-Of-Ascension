package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.ClientOperations;
import net.tslat.tme.api.util.StreamCodecUtil;

import java.util.OptionalDouble;

public record UpdateClientMovementPacket(Operation operation, OptionalDouble x, OptionalDouble y, OptionalDouble z) implements AoAPacket {
	public static final Type<UpdateClientMovementPacket> TYPE = new Type<>(AdventOfAscension.id("update_client_movement"));
	public static final StreamCodec<FriendlyByteBuf, UpdateClientMovementPacket> CODEC = StreamCodec.composite(
			NeoForgeStreamCodecs.enumCodec(Operation.class), UpdateClientMovementPacket::operation,
			StreamCodecUtil.OPTIONALDOUBLE, UpdateClientMovementPacket::x,
			StreamCodecUtil.OPTIONALDOUBLE, UpdateClientMovementPacket::y,
			StreamCodecUtil.OPTIONALDOUBLE, UpdateClientMovementPacket::z,
			UpdateClientMovementPacket::new);

	public UpdateClientMovementPacket(Operation operation, double x, double y, double z) {
		this(operation, OptionalDouble.of(x), OptionalDouble.of(y), OptionalDouble.of(z));
	}

	public UpdateClientMovementPacket(Operation operation, double x, double z) {
		this(operation, OptionalDouble.of(x), OptionalDouble.empty(), OptionalDouble.of(z));
	}

	public UpdateClientMovementPacket(Operation operation, double y) {
		this(operation, OptionalDouble.empty(), OptionalDouble.of(y), OptionalDouble.empty());
	}

	@Override
	public Type<? extends UpdateClientMovementPacket> type() {
		return TYPE;
	}

	@Override
	public void receiveMessage(IPayloadContext context) {
		context.enqueueWork(() -> ClientOperations.adjustPlayerMovement(this.x, this.y, this.z, this.operation));
	}

	public enum Operation {
		SET,
		ADD,
		MULTIPLY,
		MAX,
		MIN;
	}
}
