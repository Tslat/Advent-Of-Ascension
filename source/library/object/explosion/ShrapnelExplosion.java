package net.tslat.aoa3.library.object.explosion;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.ProtectionEnchantment;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ObjectUtil;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ShrapnelExplosion extends ExtendedExplosion {
	private static final int RAYS_PER_AXIS = 13;

	protected List<Vec3> rays;
	protected final Object2ObjectOpenHashMap<UUID, Pair<Entity, Set<Vec3>>> collatedEntityImpacts = new Object2ObjectOpenHashMap<>();

	public ShrapnelExplosion(ExplosionInfo explosionInfo, ServerLevel level, Entity exploder, Entity indirectExploder) {
		super(explosionInfo, level, exploder, indirectExploder);
	}

	public ShrapnelExplosion(ExplosionInfo explosionInfo, ServerLevel level, Entity exploder) {
		super(explosionInfo, level, exploder);
	}

	public ShrapnelExplosion(ExplosionInfo explosionInfo, ServerLevel level, Entity exploder, Vec3 position) {
		super(explosionInfo, level, exploder, position);
	}

	public ShrapnelExplosion(ExplosionInfo explosionInfo, ServerLevel level, Entity exploder, Entity indirectExploder, Vec3 position) {
		super(explosionInfo, level, exploder, indirectExploder, position);
	}

	public ShrapnelExplosion(ExplosionInfo explosionInfo, ServerLevel level, Entity exploder, double x, double y, double z) {
		super(explosionInfo, level, exploder, x, y, z);
	}

	public ShrapnelExplosion(ExplosionInfo explosionInfo, ServerLevel level, double x, double y, double z) {
		super(explosionInfo, level, x, y, z);
	}

	public ShrapnelExplosion(ExplosionInfo explosionInfo, ServerLevel level, DamageSource damageSource, double x, double y, double z) {
		super(explosionInfo, level, damageSource, x, y, z);
	}

	public ShrapnelExplosion(ExplosionInfo explosionInfo, ServerLevel level, @Nullable Entity exploder, @Nullable Entity indirectExploder, @Nullable DamageSource damageSource, double x, double y, double z) {
		super(explosionInfo, level, exploder, indirectExploder, damageSource, x, y, z);
	}

	@Override
	public int stepsPerTick() {
		return 1000;
	}

	@Override
	protected void doPreExplosionWork() {
		populateRays();
	}

	@Override
	protected void filterAffectedBlocksAndEntities() {
		ObjectOpenHashSet<BlockPos> emptyPos = new ObjectOpenHashSet<>();

		for (Vec3 ray : this.rays) {
			doRayTraversal(ray, this.info.getPenetrationPower() * (float)this.random.randomValueBetween(0.85f, 1.15f), shouldDamageBlocks(), emptyPos, this.affectedEntities, this.collatedEntityImpacts);
		}

		this.affectedEntities.removeIf(entity -> !this.collatedEntityImpacts.containsKey(entity.getUUID()));
		this.affectedBlocks.forEach((pos, state) -> this.toBlow.add(pos));
		this.toBlow.sort(Comparator.comparingDouble(pos -> pos.distToCenterSqr(this.origin)));
	}

	protected void populateRays() {
		this.rays = new ObjectArrayList<>((int)Math.pow(RAYS_PER_AXIS, 3));
		final float angleStep = (2 / (float)RAYS_PER_AXIS);

		for (float x = (float)-this.random.randomValueBetween(0.95, 1); x <= 1; x += angleStep) {
			for (float y = (float)-this.random.randomValueBetween(0.95, 1); y <= 1; y += angleStep) {
				for (float z = (float)-this.random.randomValueBetween(0.95, 1); z <= 1; z += angleStep) {
					if (Math.abs(x * y * z) > 0.00005)
						this.rays.add(new Vec3(x, y, z));
				}
			}
		}

		ObjectUtil.fastShuffleList(this.rays);
	}

	protected float doRayTraversal(Vec3 ray, float penetration, boolean blockDamage, ObjectOpenHashSet<BlockPos> emptyPos, List<Entity> nearbyEntities, Object2ObjectOpenHashMap<UUID, Pair<Entity, Set<Vec3>>> entities) {
		float stepDistance = 0;
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		BlockPos lastPos = null;


		while (stepDistance <= this.info.getEffectiveRadius()) {
			Vec3 exactPos = new Vec3(this.origin.x + stepDistance * ray.x, this.origin.y + stepDistance * ray.y, this.origin.z + stepDistance * ray.z);
			stepDistance += (1 / 3f);

			if ((pos.set(exactPos.x, exactPos.y, exactPos.z)).equals(lastPos) || emptyPos.contains(pos))
				continue;

			if (!this.boundsPredicate.test(exactPos) || !this.level.isInWorldBounds(pos)) {
				checkForEntitiesInRay(ray, exactPos, nearbyEntities, entities);

				return 0;
			}

			BlockPos immutablePos = pos.immutable();
			BlockState state = this.level.getBlockState(pos);
			lastPos = immutablePos;

			if (state.isAir())
				continue;

			if (!this.info.shouldAffectBlock(this, state, pos)) {
				checkForEntitiesInRay(ray, exactPos, nearbyEntities, entities);

				return 0;
			}

			Optional<Float> blastResistance = this.damageCalculator.getBlockExplosionResistance(this, this.level, pos, state, this.level.getFluidState(pos));

			if (blastResistance.isEmpty()) {
				emptyPos.add(immutablePos);
			}
			else if (!blockDamage || !this.damageCalculator.shouldBlockExplode(this, this.level, immutablePos, state, (this.info.getBaseDamage() + this.info.getEffectiveRadius()) / 2f)) {
				checkForEntitiesInRay(ray, exactPos, nearbyEntities, entities);

				return 0;
			}
			else {
				float specificPenetration = penetration * this.info.calculateBlockDamageModifier(this, state, immutablePos);
				float resistance = blastResistance.get();

				if (resistance > specificPenetration) {
					checkForEntitiesInRay(ray, exactPos, nearbyEntities, entities);

					return 0;
				}

				penetration -= resistance;

				this.affectedBlocks.put(immutablePos, state);
				emptyPos.add(immutablePos);
			}
		}

		return penetration;
	}

	protected void impactEntities() {
		for (Pair<Entity, Set<Vec3>> pair : this.collatedEntityImpacts.values()) {
			Entity entity = pair.getFirst();
			float distModifier;

			if (this.info.isEntityDamaging()) {
				distModifier = (float)Math.pow(EntityUtil.getEntityCenter(entity).distanceTo(this.origin), 0.5);
				float damage = this.info.getBaseDamage() * (1 / distModifier);

				damage *= this.info.calculateEntityDamageModifier(this, entity);
				damage *= 0.5 + pair.getSecond().size() * 0.5;
				damage = Math.min(this.info.getBaseDamage(), damage);

				entity.hurt(this.damageSource, damage);
			}

			if (this.info.isKnockbackEntities() && entity.isPushable()) {
				Vec3 dist = entity.position().subtract(this.origin).normalize();
				double knockback = this.info.getBaseKnockback() * (0.5 + pair.getSecond().size() * 0.5);
				knockback *= this.info.calculateKnockbackModifier(this, entity);

				if (entity instanceof LivingEntity livingEntity)
					knockback = ProtectionEnchantment.getExplosionKnockbackAfterDampener(livingEntity, knockback);

				dist.multiply(knockback, knockback, knockback);
				entity.setDeltaMovement(entity.getDeltaMovement().add(dist));
			}

			this.info.entityImpacted(this, entity);
		}
	}

	protected void checkForEntitiesInRay(Vec3 ray, Vec3 rayEndPos, List<Entity> entitiesInBlast, Object2ObjectOpenHashMap<UUID, Pair<Entity, Set<Vec3>>> hitEntityMap) {
		for (Entity entity : entitiesInBlast) {
			Pair<Entity, Set<Vec3>> collidedRaysForEntity = hitEntityMap.get(entity.getUUID());

			if (collidedRaysForEntity != null && collidedRaysForEntity.getSecond().contains(ray))
				continue;

			if (entity.getBoundingBox().clip(this.origin, rayEndPos).isPresent()) {
				if (collidedRaysForEntity == null)
					hitEntityMap.put(entity.getUUID(), (collidedRaysForEntity = new Pair<>(entity, new ObjectOpenHashSet<>())));

				collidedRaysForEntity.getSecond().add(ray);
			}
		}
	}
}
