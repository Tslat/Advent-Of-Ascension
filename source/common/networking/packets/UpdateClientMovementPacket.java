package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.ClientOperations;

import java.util.OptionalDouble;

public record UpdateClientMovementPacket(Operation operation, OptionalDouble x, OptionalDouble y, OptionalDouble z) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("update_client_movement");

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
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeEnum(this.operation);
		this.x.ifPresentOrElse(x -> {
			buffer.writeBoolean(true);
			buffer.writeDouble(x);
		}, () -> buffer.writeBoolean(false));
		this.y.ifPresentOrElse(y -> {
			buffer.writeBoolean(true);
			buffer.writeDouble(y);
		}, () -> buffer.writeBoolean(false));
		this.z.ifPresentOrElse(z -> {
			buffer.writeBoolean(true);
			buffer.writeDouble(z);
		}, () -> buffer.writeBoolean(false));
	}

	public static UpdateClientMovementPacket decode(FriendlyByteBuf buffer) {
		return new UpdateClientMovementPacket(buffer.readEnum(Operation.class), buffer.readBoolean() ? OptionalDouble.of(buffer.readDouble()) : OptionalDouble.empty(), buffer.readBoolean() ? OptionalDouble.of(buffer.readDouble()) : OptionalDouble.empty(), buffer.readBoolean() ? OptionalDouble.of(buffer.readDouble()) : OptionalDouble.empty());
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		context.workHandler().execute(() -> ClientOperations.adjustPlayerMovement(this.x, this.y, this.z, this.operation));
	}

	public enum Operation {
		SET,
		ADD,
		MULTIPLY,
		MAX,
		MIN;
	}
}
