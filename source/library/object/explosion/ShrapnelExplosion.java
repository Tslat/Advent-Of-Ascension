package net.tslat.aoa3.library.object.explosion;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
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
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ObjectUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.apache.commons.lang3.mutable.MutableFloat;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ShrapnelExplosion extends ExtendedExplosion {
	protected static final int MAX_STEPS_PER_TICK = 15;
	private static final int RAYS_PER_AXIS = 13;

	protected List<Vec3> rays;

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
	public void explode() {
		super.explode();

		populateRays();
		doRayCollisions();
	}

	protected void populateRays() {
		this.rays = new ObjectArrayList<>((int)Math.pow(RAYS_PER_AXIS, 3));
		final float angleStep = (2 / (float)RAYS_PER_AXIS);

		for (float x = (float)-RandomUtil.randomValueBetween(0.95, 1); x <= 1; x += angleStep) {
			for (float y = (float)-RandomUtil.randomValueBetween(0.95, 1); y <= 1; y += angleStep) {
				for (float z = (float)-RandomUtil.randomValueBetween(0.95, 1); z <= 1; z += angleStep) {
					if (Math.abs(x * y * z) > 0.00005)
						this.rays.add(new Vec3(x, y, z));
				}
			}
		}

		ObjectUtil.fastShuffleList(this.rays);
	}

	protected void doRayCollisions() {
		ObjectOpenHashSet<BlockPos> emptyPos = new ObjectOpenHashSet<>();
		ObjectArrayList<Pair<ItemStack, BlockPos>> loot = new ObjectArrayList<>();
		List<Entity> nearbyEntities = getEntitiesInBlastRadius();
		Object2ObjectOpenHashMap<UUID, Pair<Entity, Set<Vec3>>> entities = new Object2ObjectOpenHashMap<>();
		ForgeEventFactory.onExplosionDetonate(this.level, this, nearbyEntities, (this.info.baseDamage + this.info.getEffectiveRadius()) / 2f);
		this.level.playSound(null, this.origin.x, this.origin.y, this.origin.z, this.info.explosionSound, SoundSource.BLOCKS, 4.0F, (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F);

		if (this.info.singleTickExplosion) {
			this.info.particleConsumer.accept(this, -1);

			boolean blockDamage = shouldDamageBlocks();

			for (Vec3 ray : this.rays) {
				doRayTraversal(ray, this.info.penetrationPower * (float)RandomUtil.randomValueBetween(0.85f, 1.15f), 800, blockDamage, emptyPos, loot, nearbyEntities, entities);
			}

			impactEntities(entities);
			spawnDrops(loot);
			this.info.afterExplodingFunction.accept(this);
		}
		else {
			List<Pair<Vec3, MutableFloat>> rays = new ObjectArrayList<>(this.rays.size());

			for (Vec3 ray : this.rays) {
				rays.add(Pair.of(ray, new MutableFloat(this.info.penetrationPower * (float)RandomUtil.randomValueBetween(0.5f, 1.5f))));
			}

			doExplosionStep(this, rays, emptyPos, loot, nearbyEntities, entities);
		}
	}

	protected void doExplosionStep(ShrapnelExplosion explosion, List<Pair<Vec3, MutableFloat>> rays, ObjectOpenHashSet<BlockPos> emptyPos, ObjectArrayList<Pair<ItemStack, BlockPos>> loot, List<Entity> nearbyEntities, Object2ObjectOpenHashMap<UUID, Pair<Entity, Set<Vec3>>> entities) {
		boolean blockDamage = shouldDamageBlocks();

		this.info.particleConsumer.accept(this, this.explodeTick);

		for (Iterator<Pair<Vec3, MutableFloat>> iterator = rays.iterator(); iterator.hasNext();) {
			Pair<Vec3, MutableFloat> rayPair = iterator.next();
			float remainingPenetration = doRayTraversal(rayPair.getFirst(), rayPair.getSecond().getValue(), MAX_STEPS_PER_TICK, blockDamage, emptyPos, loot, nearbyEntities, entities);

			if (remainingPenetration <= 0) {
				iterator.remove();
			}
			else {
				rayPair.getSecond().setValue(remainingPenetration);
			}
		}

		if (rays.isEmpty() || this.explodeTick > 60) {
			impactEntities(entities);
			spawnDrops(loot);
			this.info.afterExplodingFunction.accept(this);
		}
		else {
			this.explodeTick++;
			AoAScheduler.scheduleSyncronisedTask(() -> doExplosionStep(explosion, rays, emptyPos, loot, nearbyEntities, entities), 1);
		}
	}

	protected float doRayTraversal(Vec3 ray, float penetration, int maxLoops, boolean blockDamage, ObjectOpenHashSet<BlockPos> emptyPos, ObjectArrayList<Pair<ItemStack, BlockPos>> loot, List<Entity> nearbyEntities, Object2ObjectOpenHashMap<UUID, Pair<Entity, Set<Vec3>>> entities) {
		float dist = this.explodeTick * (1 / 3f) * MAX_STEPS_PER_TICK;;
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
		BlockPos lastPos = null;

		while (maxLoops-- >= 0) {
			Vec3 exactPos = new Vec3(this.origin.x + dist * ray.x, this.origin.y + dist * ray.y, this.origin.z + dist * ray.z);
			dist += (1 / 3f);

			if (!this.boundsPredicate.test(exactPos)) {
				checkForEntitiesInRay(ray, exactPos, nearbyEntities, entities);

				return 0;
			}

			pos.set(exactPos.x, exactPos.y, exactPos.z);

			if (!this.level.isInWorldBounds(pos)) {
				checkForEntitiesInRay(ray, exactPos, nearbyEntities, entities);

				return 0;
			}

			if (pos.equals(lastPos) || emptyPos.contains(pos))
				continue;

			BlockPos immutablePos = pos.immutable();
			BlockState state = this.level.getBlockState(pos);
			lastPos = immutablePos;

			if (!this.info.affectedBlockPredicate.test(this, state, pos)) {
				checkForEntitiesInRay(ray, exactPos, nearbyEntities, entities);

				return 0;
			}

			Optional<Float> blastResistance = this.damageCalculator.getBlockExplosionResistance(this, this.level, pos, state, this.level.getFluidState(pos));

			if (blastResistance.isEmpty()) {
				emptyPos.add(immutablePos);
			}
			else if (!blockDamage || !this.damageCalculator.shouldBlockExplode(this, this.level, immutablePos, state, (this.info.baseDamage + this.info.getEffectiveRadius()) / 2f)) {
				checkForEntitiesInRay(ray, exactPos, nearbyEntities, entities);

				return 0;
			}
			else {
				float specificPenetration = penetration * this.info.penetrationModFunction.apply(this, state, immutablePos);
				float resistance = blastResistance.get();

				if (resistance > specificPenetration) {
					checkForEntitiesInRay(ray, exactPos, nearbyEntities, entities);

					return 0;
				}

				penetration -= resistance;

				captureBlockDrops(state, immutablePos, loot);
				state.onBlockExploded(this.level, immutablePos, this);
				this.info.blockEffectConsumer.accept(this, state, immutablePos);
			}
		}

		return penetration;
	}

	protected void impactEntities(Object2ObjectOpenHashMap<UUID, Pair<Entity, Set<Vec3>>> affectedEntities) {
		for (Pair<Entity, Set<Vec3>> pair : affectedEntities.values()) {
			Entity entity = pair.getFirst();
			float distModifier;

			if (!this.info.noEntityDamage) {
				distModifier = (float)Math.pow(EntityUtil.getEntityCenter(entity).distanceTo(this.origin), 0.5);
				float damage = this.info.baseDamage * (1 / distModifier);

				damage *= this.info.damageModFunction.apply(this, entity);
				damage *= 0.5 + pair.getSecond().size() * 0.5;
				damage = Math.min(this.info.baseDamage, damage);

				entity.hurt(this.damageSource, damage);
			}

			if (!this.info.noEntityKnockback) {
				Vec3 dist = entity.position().subtract(this.origin).normalize();
				double knockback = this.info.baseKnockback * (0.5 + pair.getSecond().size() * 0.5);
				knockback *= this.info.knockbackVelocityFunction.apply(this, entity);

				if (entity instanceof LivingEntity livingEntity)
					knockback = ProtectionEnchantment.getExplosionKnockbackAfterDampener(livingEntity, knockback);

				dist.multiply(knockback, knockback, knockback);
				entity.setDeltaMovement(entity.getDeltaMovement().add(dist));
			}

			this.info.entityEffectConsumer.accept(this, entity);
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
