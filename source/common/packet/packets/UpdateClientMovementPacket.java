package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;
import net.tslat.aoa3.client.ClientOperations;

import java.util.function.Supplier;

public class UpdateClientMovementPacket implements AoAPacket {
	private Float x;
	private Float y;
	private Float z;
	private final Operation operation;

	public UpdateClientMovementPacket(Operation operation) {
		this.operation = operation;
	}

	public UpdateClientMovementPacket x(Float x) {
		this.x = x;

		return this;
	}

	public UpdateClientMovementPacket y(Float y) {
		this.y = y;

		return this;
	}

	public UpdateClientMovementPacket z(Float z) {
		this.z = z;

		return this;
	}

	@Override
	public void encode(PacketBuffer buffer) {
		buffer.writeBoolean(x != null);

		if (x != null)
			buffer.writeFloat(x);

		buffer.writeBoolean(y != null);

		if (y != null)
			buffer.writeFloat(y);

		buffer.writeBoolean(z != null);

		if (z != null)
			buffer.writeFloat(z);

		buffer.writeInt(operation.toOrdinal());
	}

	public static UpdateClientMovementPacket decode(PacketBuffer buffer) {
		Float x = buffer.readBoolean() ? buffer.readFloat() : null;
		Float y = buffer.readBoolean() ? buffer.readFloat() : null;
		Float z = buffer.readBoolean() ? buffer.readFloat() : null;

		return new UpdateClientMovementPacket(Operation.fromOrdinal(buffer.readInt())).x(x).y(y).z(z);
	}

	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> ClientOperations.adjustPlayerMovement(x, y, z, operation));
		context.get().setPacketHandled(true);
	}

	public enum Operation {
		SET(0),
		ADD(1),
		MULTIPLY(2),
		MAX(3),
		MIN(4);

		private final int ordinal;

		Operation(int ordinal) {
			this.ordinal = ordinal;
		}

		public static Operation fromOrdinal(int ordinal) {
			switch (ordinal) {
				case 1:
					return ADD;
				case 2:
					return MULTIPLY;
				case 3:
					return MAX;
				case 4:
					return MIN;
				case 0:
				default:
					return SET;
			}
		}

		public int toOrdinal() {
			return this.ordinal;
		}
	}
}
