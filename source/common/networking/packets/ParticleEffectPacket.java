package net.tslat.aoa3.common.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.entity.AoADamageTypes;
import net.tslat.aoa3.content.entity.base.AoARangedAttacker;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.aoa3.util.DamageUtil;

public record ParticleEffectPacket(Type type, int senderId, int entityId) implements AoAPacket<PlayPayloadContext> {
	public static final ResourceLocation ID = AdventOfAscension.id("particle_effect");

	@Override
	public ResourceLocation id() {
		return ID;
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeEnum(this.type);
		buffer.writeVarInt(this.senderId);
		buffer.writeVarInt(this.entityId);
	}

	public static ParticleEffectPacket decode(FriendlyByteBuf buffer) {
		return new ParticleEffectPacket(buffer.readEnum(Type.class), buffer.readVarInt(), buffer.readVarInt());
	}

	@Override
	public void receiveMessage(PlayPayloadContext context) {
		switch (this.type) {
			case FREEZING_SNOWFLAKE -> context.workHandler().execute(() -> context.level().ifPresent(this::doSnowflakeEffect));
			case SANDSTORM -> context.workHandler().execute(() -> context.level().ifPresent(this::doSandstormEffect));
			case BURNING_FLAME -> context.workHandler().execute(() -> context.level().ifPresent(this::doBurningFlameEffect));
		}
	}

	private void doSnowflakeEffect(Level level) {
		final Entity entity = level.getEntity(this.entityId);

		if (entity != null) {
			if (this.senderId > 0 && level.getEntity(this.senderId) instanceof AoARangedAttacker rangedAttacker) {
				rangedAttacker.doRangedAttackEntity(null, entity);
			}
			else if (entity.getTicksFrozen() <= entity.getTicksRequiredToFreeze() * 2.5f) {
				entity.setTicksFrozen(entity.getTicksFrozen() + 14);
			}
		}
	}

	private void doSandstormEffect(Level level) {
		final Entity entity = level.getEntity(this.entityId);
		final Entity attacker = this.senderId >= 0 ? level.getEntity(this.senderId) : null;

		if (entity instanceof LivingEntity) {
			if (EntityPredicate.TARGETABLE_ENTITIES.test(entity)) {
				DamageSource source = attacker == null ? DamageUtil.miscDamage(DamageTypes.STING, entity.level()) :
						DamageUtil.positionedEntityDamage(DamageTypes.MOB_ATTACK_NO_AGGRO, attacker, entity.position());

				DamageUtil.safelyDealDamage(source, entity, 4);
			}
		}
		else if (entity instanceof Projectile) {
			entity.setDeltaMovement(entity.getDeltaMovement().multiply(-0.5f, -0.5f, -0.5f));
		}
	}

	private void doBurningFlameEffect(Level level) {
		final Entity entity = level.getEntity(this.entityId);

		if (entity instanceof LivingEntity) {
			if (this.senderId > 0 && level.getEntity(this.senderId) instanceof AoARangedAttacker rangedAttacker) {
				rangedAttacker.doRangedAttackEntity(null, entity);
			}
			else {
				DamageUtil.safelyDealDamage(DamageUtil.miscPositionedDamage(AoADamageTypes.MOB_FIRE_RANGED_ATTACK, entity.level(), entity.position()), entity, 1);
				entity.setSecondsOnFire((int)Math.ceil(entity.getRemainingFireTicks() / 20f) + 1);
			}
		}
	}

	public enum Type {
		FREEZING_SNOWFLAKE,
		BURNING_FLAME,
		SANDSTORM
	}
}
