package net.tslat.aoa3.library.object.explosion;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.library.object.AllDirections;
import net.tslat.aoa3.util.EntityUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.EnumMap;
import java.util.Optional;

public class StandardExplosion extends ExtendedExplosion {
	protected EnumMap<AllDirections, Float> sectorPenetrationMap;

	public StandardExplosion(ExplosionInfo explosionInfo, ServerLevel level, Entity exploder, Entity indirectExploder) {
		super(explosionInfo, level, exploder, indirectExploder);
	}

	public StandardExplosion(ExplosionInfo explosionInfo, ServerLevel level, Entity exploder) {
		super(explosionInfo, level, exploder);
	}

	public StandardExplosion(ExplosionInfo explosionInfo, ServerLevel level, Entity exploder, Vec3 position) {
		super(explosionInfo, level, exploder, position);
	}

	public StandardExplosion(ExplosionInfo explosionInfo, ServerLevel level, Entity exploder, Entity indirectExploder, Vec3 position) {
		super(explosionInfo, level, exploder, indirectExploder, position);
	}

	public StandardExplosion(ExplosionInfo explosionInfo, ServerLevel level, Entity exploder, double x, double y, double z) {
		super(explosionInfo, level, exploder, x, y, z);
	}

	public StandardExplosion(ExplosionInfo explosionInfo, ServerLevel level, double x, double y, double z) {
		super(explosionInfo, level, x, y, z);
	}

	public StandardExplosion(ExplosionInfo explosionInfo, ServerLevel level, DamageSource damageSource, double x, double y, double z) {
		super(explosionInfo, level, damageSource, x, y, z);
	}

	public StandardExplosion(ExplosionInfo explosionInfo, ServerLevel level, @Nullable Entity exploder, @Nullable Entity indirectExploder, @Nullable DamageSource damageSource, double x, double y, double z) {
		super(explosionInfo, level, exploder, indirectExploder, damageSource, x, y, z);
	}

	@Override
	public int stepsPerTick() {
		return 1000;
	}

	@Override
	protected void doPreExplosionWork() {
		if (shouldDamageBlocks()) {
			this.sectorPenetrationMap = new EnumMap<>(AllDirections.class);
			float penetration = this.info.getPenetrationPower();
			BlockPos originPos = BlockPos.containing(this.origin);
			float originFluidBlastResistance = level.getFluidState(originPos).getExplosionResistance(this.level, originPos, this);

			if (originFluidBlastResistance > 0)
				penetration *= Math.max(0, 0.6f - 0.1f * originFluidBlastResistance / 100);

			for (AllDirections direction : AllDirections.values()) {
				this.sectorPenetrationMap.put(direction, penetration * (float)this.random.randomValueBetween(0.5f, 1.5f));
			}
		}
	}

	@Override
	protected ObjectArrayList<BlockPos> getAffectedBlocks() {
		if (!shouldDamageBlocks())
			return new ObjectArrayList<>();

		ObjectArrayList<BlockPos> list = new ObjectArrayList<>((int)Math.pow(this.info.getEffectiveRadius(), 2) * 4);

		this.info.getRadius()
				.ifLeft(radius -> {
					for (int x = -(int)Math.floor(radius); x <= (int)Math.ceil(radius); x++) {
						for (int y = -(int)Math.floor(radius); y <= (int)Math.ceil(radius); y++) {
							for (int z = -(int)Math.floor(radius); z <= (int)Math.ceil(radius); z++) {
								if ((x + 0.5f) * (x + 0.5f) + (y + 0.5f) * (y + 0.5f) + (z + 0.5f) * (z + 0.5f) < radius * radius) {
									BlockPos pos = BlockPos.containing(x + this.origin.x, y + this.origin.y, z + this.origin.z);

									if (this.level.isInWorldBounds(pos))
										list.add(pos);
								}
							}
						}
					}
				})
				.ifRight(squareRadius -> {
					BlockPos originPos = BlockPos.containing(this.origin);

					for (BlockPos pos : BlockPos.betweenClosed(originPos.subtract(squareRadius.toVec3i()), originPos.offset(squareRadius.toVec3i()))) {
						if (this.level.isInWorldBounds(pos))
							list.add(pos);
					}
				});

		list.sort(Comparator.comparingDouble(pos -> pos.distToCenterSqr(this.origin)));

		return list;
	}

	@Override
	protected void filterAffectedBlocksAndEntities() {
		for (BlockPos pos : this.toBlow) {
			if (!doBlockDamage(pos))
				break;
		}

		this.toBlow.removeIf(pos -> !this.affectedBlocks.containsKey(pos));
	}

	protected void impactEntities() {
		for (Entity entity : this.affectedEntities) {
			if (this.info.isEntityDamaging() || this.info.isKnockbackEntities()) {
				double impactPercent = getSeenPercent(this.origin, entity);

				if (this.info.isEntityDamaging()) {
					float distModifier = (float)Math.pow(EntityUtil.getEntityCenter(entity).distanceTo(this.origin), 0.5);
					float damage = this.info.getBaseDamage() * (1 / distModifier);

					damage *= this.info.calculateEntityDamageModifier(this, entity);
					damage *= impactPercent;
					damage = Math.min(this.info.getBaseDamage(), damage);

					entity.hurt(this.damageSource, damage);
				}

				if (this.info.isKnockbackEntities() && entity.isPushable()) {
					Vec3 dist = entity.position().subtract(this.origin).normalize();
					double knockback = this.info.getBaseKnockback() * impactPercent;
					knockback *= this.info.calculateKnockbackModifier(this, entity);

					if (entity instanceof LivingEntity livingEntity)
						knockback = ProtectionEnchantment.getExplosionKnockbackAfterDampener(livingEntity, knockback);

					dist.multiply(knockback, knockback, knockback);
					entity.setDeltaMovement(entity.getDeltaMovement().add(dist));
				}
			}

			this.info.entityImpacted(this, entity);
		}
	}

	protected boolean doBlockDamage(BlockPos pos) {
		Vec3 posCenter = Vec3.atCenterOf(pos);
		AllDirections sector = AllDirections.byAngle(this.origin.subtract(posCenter).normalize());
		float sectorPenetration = this.sectorPenetrationMap.get(sector);

		if (sectorPenetration <= 0)
			return true;

		BlockState state = this.level.getBlockState(pos);
		Optional<Float> blastResistance = this.damageCalculator.getBlockExplosionResistance(this, this.level, pos, state, this.level.getFluidState(pos));

		if (blastResistance.isEmpty())
			return true;

		if (!this.info.shouldAffectBlock(this, state, pos) || !this.damageCalculator.shouldBlockExplode(this, this.level, pos, state, (this.info.getBaseDamage() + this.info.getEffectiveRadius()) / 2f)) {
			float remainingPenetration = Math.max(0, sectorPenetration - blastResistance.get() / 5);

			this.sectorPenetrationMap.put(sector, remainingPenetration);

			if (remainingPenetration > 0)
				return true;

			for (float value : this.sectorPenetrationMap.values()) {
				if (value > 0)
					return true;
			}

			return false;
		}

		float specificPenetration = sectorPenetration * this.info.calculateBlockDamageModifier(this, state, pos);
		float remainingPenetration = sectorPenetration;
		BlockPos immutablePos = pos.immutable();

		if (specificPenetration <= blastResistance.get()) {
			if (this.random.fiftyFifty())
				remainingPenetration *= 0.5f;
		}
		else {
			remainingPenetration = sectorPenetration - blastResistance.get();

			this.affectedBlocks.put(immutablePos, state);
		}

		this.sectorPenetrationMap.put(sector, Math.max(0, remainingPenetration));

		if (remainingPenetration > 0)
			return true;

		for (float value : this.sectorPenetrationMap.values()) {
			if (value > 0)
				return true;
		}

		return false;
	}
}
