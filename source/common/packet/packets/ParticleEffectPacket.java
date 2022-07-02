package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.library.builder.EntityPredicate;

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

				if (entity != null && entity.getTicksFrozen() <= entity.getTicksRequiredToFreeze() * 2.5f)
					entity.setTicksFrozen(entity.getTicksFrozen() + 14);
			}
			case SANDSTORM -> {
				Entity entity = context.get().getSender().getLevel().getEntity(this.entityId);

				if (entity instanceof LivingEntity) {
					if (EntityPredicate.TARGETABLE_ENTITIES.test(entity))
						entity.hurt(DamageSource.MAGIC, 2);
				}
				else if (entity instanceof Projectile) {
					entity.setDeltaMovement(entity.getDeltaMovement().multiply(-0.5f, -0.5f, -0.5f));
				}
			}
		}
	}

	public enum Type {
		FREEZING_SNOWFLAKE,
		SANDSTORM
	}
}
