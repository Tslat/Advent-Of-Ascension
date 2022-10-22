package net.tslat.aoa3.library.object.explosion;

import com.mojang.datafixers.util.Either;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.library.object.TriFunction;
import net.tslat.smartbrainlib.object.SquareRadius;
import net.tslat.smartbrainlib.object.TriPredicate;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class ExplosionInfo {
	protected float baseDamage = 20;
	protected float baseKnockback = 1;
	protected SquareRadius squareRadius = null;
	protected float radius = 10;
	protected float penetrationPower = 100;
	protected float blockDropChance = 1;
	protected SoundEvent explosionSound = SoundEvents.GENERIC_EXPLODE;

	protected boolean affectsOwner = false;
	protected boolean noEntityDamage = false;
	protected boolean noEntityKnockback = false;
	protected boolean noBlockDamage = false;

	protected boolean singleTickExplosion = false;

	protected BiPredicate<ExtendedExplosion, Entity> affectedEntityPredicate = (explosion, entity) -> true;
	protected TriPredicate<ExtendedExplosion, BlockState, BlockPos> affectedBlockPredicate = (explosion, blockState, pos) -> true;
	protected BiFunction<ExtendedExplosion, Entity, Float> knockbackVelocityFunction = (explosion, entity) -> 1f;
	protected BiFunction<ExtendedExplosion, Entity, Float> damageModFunction = (explosion, entity) -> 1f;
	protected TriFunction<ExtendedExplosion, BlockState, BlockPos, Float> penetrationModFunction = (explosion, blockState, pos) -> 1f;
	protected BiConsumer<ExtendedExplosion, Entity> entityEffectConsumer = (explosion, entity) -> {};
	protected TriConsumer<ExtendedExplosion, BlockState, BlockPos> blockEffectConsumer = (explosion, blockState, pos) -> {};
	protected Consumer<ExtendedExplosion> afterExplodingFunction = explosion -> {};
	protected BiConsumer<ExtendedExplosion, Integer> particleConsumer = (explosion, explodeTick) -> {
		if (explodeTick <= 0)
			AoAPackets.messageNearbyPlayers(new ServerParticlePacket().particle(getEffectiveRadius() >= 4.0F && !this.noBlockDamage ? ParticleTypes.EXPLOSION_EMITTER : ParticleTypes.EXPLOSION, explosion.origin.x, explosion.origin.y, explosion.origin.z, 0, 0, 0), (ServerLevel)explosion.level, explosion.origin, 64);
	};

	public float getBaseDamage() {
		return this.baseDamage;
	}

	public float getBaseKnockback() {
		return this.baseKnockback;
	}

	public Either<Float, SquareRadius> getRadius() {
		return this.squareRadius == null ? Either.left(this.radius) : Either.right(this.squareRadius);
	}

	public float getEffectiveRadius() {
		if (this.squareRadius != null)
			return (float)(this.squareRadius.xzRadius() + this.squareRadius.yRadius()) / 2f;

		return this.radius;
	}

	public double getPenetrationPower() {
		return this.penetrationPower;
	}

	public double getBlockDropChance() {
		return this.blockDropChance;
	}

	public boolean getAffectsOwner() {
		return this.affectsOwner;
	}

	public SoundEvent getExplosionSound() {
		return this.explosionSound;
	}

	public boolean isEntityDamaging() {
		return !this.noEntityDamage;
	}

	public boolean isKnockbackEntities() {
		return !this.noEntityKnockback;
	}

	public boolean isBlockDamaging() {
		return !this.noBlockDamage;
	}

	public boolean isSingleTickExplosion() {
		return this.singleTickExplosion;
	}

	public ExplosionInfo affectsOwner() {
		this.affectsOwner = true;

		return this;
	}

	public ExplosionInfo baseDamage(float damage) {
		this.baseDamage = damage;

		return this;
	}

	public ExplosionInfo baseKnockbackStrength(float amount) {
		this.baseKnockback = amount;

		return this;
	}

	public ExplosionInfo radius(float radius) {
		this.radius = radius;

		return this;
	}

	public ExplosionInfo radius(SquareRadius radius) {
		this.squareRadius = radius;

		return this;
	}

	public ExplosionInfo penetration(float penetrationPower) {
		this.penetrationPower = penetrationPower;

		return this;
	}

	public ExplosionInfo blocksDropChance(float chance) {
		this.blockDropChance = chance;

		return this;
	}

	public ExplosionInfo affectedEntities(BiPredicate<ExtendedExplosion, Entity> predicate) {
		this.affectedEntityPredicate = predicate;

		return this;
	}

	public ExplosionInfo affectedBlocks(TriPredicate<ExtendedExplosion, BlockState, BlockPos> predicate) {
		this.affectedBlockPredicate = predicate;

		return this;
	}

	public ExplosionInfo particles(BiConsumer<ExtendedExplosion, Integer> consumer) {
		this.particleConsumer = consumer;

		return this;
	}

	public ExplosionInfo knockback(BiFunction<ExtendedExplosion, Entity, Float> modifierFunction) {
		this.knockbackVelocityFunction = modifierFunction;

		return this;
	}

	public ExplosionInfo damage(BiFunction<ExtendedExplosion, Entity, Float> modifierFunction) {
		this.damageModFunction = modifierFunction;

		return this;
	}

	public ExplosionInfo blockDamage(TriFunction<ExtendedExplosion, BlockState, BlockPos, Float> modifierFunction) {
		this.penetrationModFunction = modifierFunction;

		return this;
	}

	public ExplosionInfo onHit(BiConsumer<ExtendedExplosion, Entity> consumer) {
		this.entityEffectConsumer = consumer;

		return this;
	}

	public ExplosionInfo onBlockHit(TriConsumer<ExtendedExplosion, BlockState, BlockPos> consumer) {
		this.blockEffectConsumer = consumer;

		return this;
	}

	public ExplosionInfo afterExplosion(Consumer<ExtendedExplosion> consumer) {
		this.afterExplodingFunction = consumer;

		return this;
	}

	public ExplosionInfo noEntityDamage() {
		this.noEntityDamage = true;

		return this;
	}

	public ExplosionInfo noEntityKnockback() {
		this.noEntityKnockback = true;

		return this;
	}

	public ExplosionInfo noBlockDamage() {
		this.noBlockDamage = true;

		return this;
	}

	public ExplosionInfo explodeInOneTick() {
		this.singleTickExplosion = true;

		return this;
	}

	public ExplosionInfo explosionSound(SoundEvent sound) {
		this.explosionSound = sound;

		return this;
	}

	public static ExplosionInfo from(ExplosionInfo otherInfo) {
		ExplosionInfo newInfo = new ExplosionInfo();

		newInfo.baseDamage = otherInfo.baseDamage;
		newInfo.baseKnockback = otherInfo.baseKnockback;
		newInfo.squareRadius = otherInfo.squareRadius;
		newInfo.radius = otherInfo.radius;
		newInfo.penetrationPower = otherInfo.penetrationPower;
		newInfo.blockDropChance = otherInfo.blockDropChance;
		newInfo.explosionSound = otherInfo.explosionSound;
		newInfo.affectsOwner = otherInfo.affectsOwner;
		newInfo.noEntityDamage = otherInfo.noEntityDamage;
		newInfo.noEntityKnockback = otherInfo.noEntityKnockback;
		newInfo.noBlockDamage = otherInfo.noBlockDamage;
		newInfo.singleTickExplosion = otherInfo.singleTickExplosion;
		newInfo.affectedEntityPredicate = otherInfo.affectedEntityPredicate;
		newInfo.affectedBlockPredicate = otherInfo.affectedBlockPredicate;
		newInfo.particleConsumer = otherInfo.particleConsumer;
		newInfo.knockbackVelocityFunction = otherInfo.knockbackVelocityFunction;
		newInfo.damageModFunction = otherInfo.damageModFunction;
		newInfo.penetrationModFunction = otherInfo.penetrationModFunction;
		newInfo.entityEffectConsumer = otherInfo.entityEffectConsumer;
		newInfo.blockEffectConsumer = otherInfo.blockEffectConsumer;
		newInfo.afterExplodingFunction = otherInfo.afterExplodingFunction;

		return newInfo;
	}
}
