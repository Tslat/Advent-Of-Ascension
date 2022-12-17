package net.tslat.aoa3.library.object.explosion;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.aoa3.library.object.AllDirections;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.smartbrainlib.object.SquareRadius;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class StandardExplosion extends ExtendedExplosion {
	private static final int MAX_STEPS_PER_TICK = 1000;
	protected EnumMap<AllDirections, Float> sectorPenetrationMap;
	protected List<BlockPos> blockPositions;
	protected BlockPos originPos;

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
	public void explode() {
		super.explode();

		this.originPos = new BlockPos(this.origin);
		boolean blockDamage = shouldDamageBlocks();

		if (blockDamage) {
			this.sectorPenetrationMap = new EnumMap<>(AllDirections.class);
			float penetration = this.info.penetrationPower;
			float originFluidBlastResistance = level.getFluidState(this.originPos).getExplosionResistance(level, this.originPos, this);

			if (originFluidBlastResistance > 0)
				penetration *= Math.max(0, 0.6f - 0.1f * originFluidBlastResistance / 100);

			for (AllDirections direction : AllDirections.values()) {
				this.sectorPenetrationMap.put(direction, penetration * (float)RandomUtil.randomValueBetween(0.5f, 1.5f));
			}
		}

		ObjectArrayList<Pair<ItemStack, BlockPos>> loot = new ObjectArrayList<>();
		List<Entity> nearbyEntities = getEntitiesInBlastRadius();

		ForgeEventFactory.onExplosionDetonate(this.level, this, nearbyEntities, (this.info.baseDamage + this.info.getEffectiveRadius()) / 2f);
		this.level.playSound(null, this.origin.x, this.origin.y, this.origin.z, this.info.explosionSound, SoundSource.BLOCKS, 4.0F, (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F);
		collectBlocksInRadius();

		if (this.info.singleTickExplosion) {
			this.info.particleConsumer.accept(this, -1);

			if (blockDamage) {
				for (BlockPos pos : this.blockPositions) {
					if (!doBlockDamage(pos, loot))
						break;
				}
			}

			impactEntities(nearbyEntities);
			spawnDrops(loot);
			this.info.afterExplodingFunction.accept(this);
		}
		else {
			doExplosionStep(loot, nearbyEntities);
		}
	}

	protected void collectBlocksInRadius() {
		blockPositions = new ObjectArrayList<>((int)Math.pow(this.info.getEffectiveRadius(), 2) * 4);

		if (this.info.squareRadius == null) {
			for (int x = -(int)Math.floor(this.info.radius); x <= (int)Math.ceil(this.info.radius); x++) {
				for (int y = -(int)Math.floor(this.info.radius); y <= (int)Math.ceil(this.info.radius); y++) {
					for (int z = -(int)Math.floor(this.info.radius); z <= (int)Math.ceil(this.info.radius); z++) {
						if ((x + 0.5f) * (x + 0.5f) + (y + 0.5f) * (y + 0.5f) + (z + 0.5f) * (z + 0.5f) < this.info.radius * this.info.radius) {
							BlockPos pos = new BlockPos(x + this.origin.x, y + this.origin.y, z + this.origin.z);

							if (this.level.isInWorldBounds(pos))
								this.blockPositions.add(pos);
						}
					}
				}
			}
		}
		else {
			SquareRadius radius = this.info.squareRadius;
			BlockPos originPos = this.originPos;

			for (BlockPos pos : BlockPos.betweenClosed(originPos.offset(-radius.xzRadius(), -radius.yRadius(), -radius.xzRadius()), originPos.offset(radius.xzRadius(), radius.yRadius(), radius.xzRadius()))) {
				if (this.level.isInWorldBounds(pos))
					this.blockPositions.add(pos);
			}
		}

		this.blockPositions.sort(Comparator.comparingDouble(pos -> pos.distToCenterSqr(this.origin)));
	}

	protected void doExplosionStep(ObjectArrayList<Pair<ItemStack, BlockPos>> loot, List<Entity> entities) {
		if (!shouldDamageBlocks()) {
			int step = 0;

			for (Iterator<BlockPos> iterator = this.blockPositions.iterator(); step++ <= MAX_STEPS_PER_TICK && iterator.hasNext();) {
				if (!doBlockDamage(iterator.next(), loot))
					break;

				iterator.remove();
			}

			if (this.explodeTick++ < 600 && !this.blockPositions.isEmpty()) {
				AoAScheduler.scheduleSyncronisedTask(() -> doExplosionStep(loot, entities), 1);

				return;
			}
		}

		impactEntities(entities);
		spawnDrops(loot);
		this.info.afterExplodingFunction.accept(this);
	}

	protected void impactEntities(List<Entity> entities) {
		for (Entity entity : entities) {
			if (!this.info.noEntityDamage || !this.info.noEntityKnockback) {
				double impactPercent = getSeenPercent(this.origin, entity);

				if (!this.info.noEntityDamage) {
					float distModifier = (float)Math.pow(EntityUtil.getEntityCenter(entity).distanceTo(this.origin), 0.5);
					float damage = this.info.baseDamage * (1 / distModifier);

					damage *= this.info.damageModFunction.apply(this, entity);
					damage *= impactPercent;
					damage = Math.min(this.info.baseDamage, damage);

					entity.hurt(this.damageSource, damage);
				}

				if (!this.info.noEntityKnockback) {
					Vec3 dist = entity.position().subtract(this.origin).normalize();
					double knockback = this.info.baseKnockback * impactPercent;
					knockback *= this.info.knockbackVelocityFunction.apply(this, entity);

					if (entity instanceof LivingEntity livingEntity)
						knockback = ProtectionEnchantment.getExplosionKnockbackAfterDampener(livingEntity, knockback);

					dist.multiply(knockback, knockback, knockback);
					entity.setDeltaMovement(entity.getDeltaMovement().add(dist));
				}
			}

			this.info.entityEffectConsumer.accept(this, entity);
		}
	}

	protected boolean doBlockDamage(BlockPos pos, ObjectArrayList<Pair<ItemStack, BlockPos>> loot) {
		Vec3 posCenter = Vec3.atCenterOf(pos);
		AllDirections sector = AllDirections.byAngle(this.origin.subtract(posCenter).normalize());
		float sectorPenetration = this.sectorPenetrationMap.get(sector);

		if (sectorPenetration <= 0)
			return true;

		BlockState state = this.level.getBlockState(pos);
		Optional<Float> blastResistance = this.damageCalculator.getBlockExplosionResistance(this, this.level, pos, state, this.level.getFluidState(pos));

		if (blastResistance.isEmpty())
			return true;

		if (!this.info.affectedBlockPredicate.test(this, state, pos) || !this.damageCalculator.shouldBlockExplode(this, this.level, pos, state, (this.info.baseDamage + this.info.getEffectiveRadius()) / 2f)) {
			float remainingPenetration = Math.max(0, sectorPenetration - blastResistance.get() / 5);

			this.sectorPenetrationMap.put(sector, remainingPenetration);

			return remainingPenetration > 0;
		}

		float specificPenetration = sectorPenetration * this.info.penetrationModFunction.apply(this, state, pos);
		float remainingPenetration = sectorPenetration;
		BlockPos immutablePos = pos.immutable();

		if (specificPenetration <= blastResistance.get()) {
			if (RandomUtil.fiftyFifty())
				remainingPenetration *= 0.5f;
		}
		else {
			remainingPenetration = sectorPenetration - blastResistance.get();

			captureBlockDrops(state, immutablePos, loot);
			state.onBlockExploded(this.level, immutablePos, this);
			this.info.blockEffectConsumer.accept(this, state, immutablePos);
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
