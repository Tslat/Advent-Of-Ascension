package net.tslat.aoa3.common.packet.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraftforge.network.NetworkEvent;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.aoa3.util.DamageUtil;

import java.util.function.Supplier;

public class ParticleEffectPacket implements AoAPacket {
	private final Type type;
	private final int senderId;
	private final int entityId;

	public ParticleEffectPacket(Type type, int senderId, int entityId) {
		this.type = type;
		this.senderId = senderId;
		this.entityId = entityId;
	}

	@Override
	public void encode(FriendlyByteBuf buffer) {
		buffer.writeVarInt(this.type.ordinal());
		buffer.writeVarInt(this.senderId);
		buffer.writeVarInt(this.entityId);
	}

	public static ParticleEffectPacket decode(FriendlyByteBuf buffer) {
		return new ParticleEffectPacket(Type.values()[buffer.readVarInt()], buffer.readVarInt(), buffer.readVarInt());
	}

	@Override
	public void receiveMessage(Supplier<NetworkEvent.Context> context) {
		switch (this.type) {
			case FREEZING_SNOWFLAKE -> {
				ServerLevel level = context.get().getSender().getLevel();
				Entity entity = level.getEntity(this.entityId);
				Entity attacker = null;

				if (entity != null) {
					if (this.senderId > 0)
						attacker = level.getEntity(this.senderId);

					if (attacker instanceof AoARangedAttacker rangedAttacker) {
						rangedAttacker.doRangedAttackEntity(null, entity);
					}
					else if (entity.getTicksFrozen() <= entity.getTicksRequiredToFreeze() * 2.5f) {
						entity.setTicksFrozen(entity.getTicksFrozen() + 14);
					}
				}
			}
			case SANDSTORM -> {
				Entity entity = context.get().getSender().getLevel().getEntity(this.entityId);

				if (entity instanceof LivingEntity) {
					if (EntityPredicate.TARGETABLE_ENTITIES.test(entity))
						DamageUtil.safelyDealDamage(DamageUtil.miscDamage(DamageTypes.GENERIC, entity.level), entity, 2);
				}
				else if (entity instanceof Projectile) {
					entity.setDeltaMovement(entity.getDeltaMovement().multiply(-0.5f, -0.5f, -0.5f));
				}
			}
			case BURNING_FLAME -> {
				ServerLevel level = context.get().getSender().getLevel();
				Entity entity = level.getEntity(this.entityId);

				if (entity instanceof LivingEntity) {
					Entity attacker = null;

					if (this.senderId > 0)
						attacker = level.getEntity(this.senderId);

					if (attacker instanceof AoARangedAttacker rangedAttacker) {
						rangedAttacker.doRangedAttackEntity(null, entity);
					}
					else {
						DamageUtil.safelyDealDamage(DamageUtil.miscPositionedDamage(AoADamageTypes.BURN, entity.level, entity.position()), entity, 1);
						entity.setSecondsOnFire((int)Math.ceil(entity.getRemainingFireTicks() / 20f) + 1);
					}
				}
			}
		}
	}

	public enum Type {
		FREEZING_SNOWFLAKE,
		BURNING_FLAME,
		SANDSTORM
	}
}
