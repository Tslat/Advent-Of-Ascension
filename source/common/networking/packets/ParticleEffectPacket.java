package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.codec.NeoForgeStreamCodecs;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.tme.api.object.builder.EntityPredicateBuilder;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public record ParticleEffectPacket(Type effectType, int senderId, int entityId) implements AoAPacket {
	private static final Predicate<Entity> TARGETABLE_ENTITIES = EntityPredicateBuilder.builder().isTargetable().build();
	public static final CustomPacketPayload.Type<ParticleEffectPacket> TYPE = new CustomPacketPayload.Type<>(AdventOfAscension.id("particle_effect"));
	public static final StreamCodec<RegistryFriendlyByteBuf, ParticleEffectPacket> CODEC = StreamCodec.composite(
			NeoForgeStreamCodecs.enumCodec(Type.class), ParticleEffectPacket::effectType,
			ByteBufCodecs.VAR_INT, ParticleEffectPacket::senderId,
			ByteBufCodecs.VAR_INT, ParticleEffectPacket::entityId,
			ParticleEffectPacket::new);

	@Override
	public CustomPacketPayload.Type<? extends ParticleEffectPacket> type() {
		return TYPE;
	}

	@Override
	public void receiveMessage(IPayloadContext context) {
		context.enqueueWork(() -> {
			Level level = context.player().level();
			Entity entity = this.entityId >= 0 ? level.getEntity(this.entityId) : null;
			Entity source = this.senderId >= 0 ? level.getEntity(this.senderId) : null;

			this.effectType.effectHandler.accept(level, entity, source);
		});
	}

	private static void doSnowflakeEffect(Level level, @Nullable Entity hitEntity, @Nullable Entity particleSource) {
		if (hitEntity != null) {
			if (particleSource instanceof AoARangedAttacker rangedAttacker) {
				rangedAttacker.doRangedAttackEntity(null, hitEntity);
			}
			else if (hitEntity.getTicksFrozen() <= hitEntity.getTicksRequiredToFreeze() * 2.5f) {
				hitEntity.setTicksFrozen(hitEntity.getTicksFrozen() + 14);
			}
		}
	}

	private static void doSandstormEffect(Level level, @Nullable Entity hitEntity, @Nullable Entity particleSource) {
		switch (hitEntity) {
			case LivingEntity livingTarget -> {
				if (TARGETABLE_ENTITIES.test(livingTarget)) {
					DamageSource source = particleSource == null ? DamageUtil.miscDamage(AoADamageTypes.SANDSTORM, livingTarget.level()) :
							DamageUtil.positionedEntityDamage(AoADamageTypes.SANDSTORM, particleSource, livingTarget.position());

					DamageUtil.safelyDealDamage(source, livingTarget, 4);
				}
			}
			case Projectile projectile -> projectile.setDeltaMovement(projectile.getDeltaMovement().multiply(-0.5f, -0.5f, -0.5f));
            case null, default -> {}
        }
	}

	private static void doBurningFlameEffect(Level level, @Nullable Entity hitEntity, @Nullable Entity particleSource) {
		if (hitEntity instanceof LivingEntity) {
			if (particleSource instanceof AoARangedAttacker rangedAttacker) {
				rangedAttacker.doRangedAttackEntity(null, hitEntity);
			}
			else {
				DamageUtil.safelyDealDamage(DamageUtil.miscPositionedDamage(AoADamageTypes.MOB_FIRE_RANGED_ATTACK, hitEntity.level(), hitEntity.position()), hitEntity, 1);
				hitEntity.igniteForSeconds((int)Math.ceil(hitEntity.getRemainingFireTicks() / 20f) + 1);
			}
		}
	}

	public enum Type {
		FREEZING_SNOWFLAKE(ParticleEffectPacket::doSnowflakeEffect),
		BURNING_FLAME(ParticleEffectPacket::doBurningFlameEffect),
		SANDSTORM(ParticleEffectPacket::doSandstormEffect);

		private final TriConsumer<Level, @Nullable Entity, @Nullable Entity> effectHandler;

		Type(TriConsumer<Level, @Nullable Entity, @Nullable Entity> effectHandler) {
			this.effectHandler = effectHandler;
		}
	}
}
