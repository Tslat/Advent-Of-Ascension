package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ParticleEffectPacket implements AoAPacket {
	private final Type type;
	private final int entityId;

	public ParticleEffectPacket(Type type, int entityId) {
		this.type = type;
		this.entityId = entityId;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeVarInt(this.type.ordinal());
		buffer.writeVarInt(this.entityId);
	}

	public static ParticleEffectPacket decode(FriendlyByteBuf buffer) {
		return new ParticleEffectPacket(Type.values()[buffer.readVarInt()], buffer.readVarInt());
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		switch (this.type) {
			case FREEZING_SNOWFLAKE -> {
				Entity entity = context.get().getSender().getLevel().getEntity(this.entityId);

				if (entity != null && entity.getTicksFrozen() <= entity.getTicksRequiredToFreeze() * 2)
					entity.setTicksFrozen(entity.getTicksFrozen() + 14);
			}
		}
	}

	public enum Type {
		FREEZING_SNOWFLAKE
	}
}
