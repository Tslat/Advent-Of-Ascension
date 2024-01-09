package net.tslat.aoa3.library.object.explosion;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.EntityBasedExplosionDamageCalculator;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.EventHooks;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class ExtendedExplosion extends Explosion {
	protected final ExplosionInfo info;
	protected final RandomUtil.EasyRandom random = new RandomUtil.EasyRandom();

	protected List<Entity> affectedEntities;
	protected Object2ObjectOpenHashMap<BlockPos, BlockState> affectedBlocks = new Object2ObjectOpenHashMap<>();
	protected ObjectArrayList<Pair<ItemStack, BlockPos>> blockDrops = new ObjectArrayList<>();

	@Nullable
	protected Entity indirectExploder;
	protected Vec3 origin;

	protected boolean shouldDamageBlocks = true;
	protected int explodeTick;
	protected Predicate<Vec3> boundsPredicate = null;

	public ExtendedExplosion(ExplosionInfo explosionInfo, ServerLevel level, Entity exploder, Entity indirectExploder) {
		this(explosionInfo, level, exploder, indirectExploder, null, exploder.getX(0.5), exploder.getY(0.5), exploder.getZ(0.5));
	}

	public ExtendedExplosion(ExplosionInfo explosionInfo, ServerLevel level, Entity exploder) {
		this(explosionInfo, level, exploder, null, null, exploder.getX(0.5), exploder.getY(0.5), exploder.getZ(0.5));
	}

	public ExtendedExplosion(ExplosionInfo explosionInfo, ServerLevel level, Entity exploder, Entity indirectExploder, Vec3 position) {
		this(explosionInfo, level, exploder, indirectExploder, null, position.x, position.y, position.z);
	}

	public ExtendedExplosion(ExplosionInfo explosionInfo, ServerLevel level, Entity exploder, Vec3 position) {
		this(explosionInfo, level, exploder, null, null, position.x, position.y, position.z);
	}

	public ExtendedExplosion(ExplosionInfo explosionInfo, ServerLevel level, Entity exploder, double x, double y, double z) {
		this(explosionInfo, level, exploder, null, null, x, y, z);
	}

	public ExtendedExplosion(ExplosionInfo explosionInfo, ServerLevel level, double x, double y, double z) {
		this(explosionInfo, level, null, null, null, x, y, z);
	}

	public ExtendedExplosion(ExplosionInfo explosionInfo, ServerLevel level, DamageSource damageSource, double x, double y, double z) {
		this(explosionInfo, level, null, null, damageSource, x, y, z);
	}

	public ExtendedExplosion(ExplosionInfo explosionInfo, ServerLevel level, @Nullable Entity exploder, @Nullable Entity indirectExploder, @Nullable DamageSource damageSource, double x, double y, double z) {
		super(level, exploder, damageSource, null, x, y, z, 0, false, BlockInteraction.KEEP, ParticleTypes.EXPLOSION, ParticleTypes.EXPLOSION_EMITTER, SoundEvents.GENERIC_EXPLODE);

		this.info = explosionInfo;
		this.origin = center();
		this.indirectExploder = indirectExploder;
	}

	public ExplosionInfo getExplosionInfo() {
		return this.info;
	}

	public Level getLevel() {
		return this.level;
	}

	public RandomUtil.EasyRandom rand() {
		return this.random;
	}

	public int getExplodeTick() {
		return this.explodeTick;
	}

	public boolean shouldDamageBlocks() {
		return this.shouldDamageBlocks;
	}

	public int stepsPerTick() {
		return 1000;
	}

	/**
	 * Begin the actual explosion
	 */
	@Override
	public void explode() {
		sanityCheck();

		this.shouldDamageBlocks = griefingCheck();
		this.damageCalculator = this.source != null ? new EntityBasedExplosionDamageCalculator(this.source) : new ExplosionDamageCalculator();

		this.level.gameEvent(this.source, GameEvent.EXPLODE, this.origin);

		this.boundsPredicate = this.info.getRadius().map(
				radius -> pos -> pos.closerThan(this.origin, radius),
				squareRadius -> {
					AABB bounds = squareRadius.inflateAABB(new AABB(this.origin.x, this.origin.y, this.origin.z, this.origin.x, this.origin.y, this.origin.z));

					return bounds::contains;
				});

		doPreExplosionWork();
		this.toBlow = getAffectedBlocks();
		this.affectedEntities = getAffectedEntities();

		filterAffectedBlocksAndEntities();
		EventHooks.onExplosionDetonate(this.level, this, this.affectedEntities, (this.info.getBaseDamage() + this.info.getEffectiveRadius()) / 2f);
		this.level.playSound(null, this.origin.x, this.origin.y, this.origin.z, this.info.getExplosionSound(), SoundSource.BLOCKS, 4.0F, (1.0F + (this.level.random.nextFloat() - this.level.random.nextFloat()) * 0.2F) * 0.7F);

		if (this.info.isSingleTickExplosion()) {
			this.info.doParticles(this, -1);

			for (BlockPos pos : this.toBlow) {
				BlockState state = this.affectedBlocks.get(pos);

				if (state == null)
					continue;

				captureDropsForBlock(state, pos, this.blockDrops);
				state.onBlockExploded(this.level, pos, this);
				this.info.blockBroken(this, state, pos);
			}

			impactEntities();
			finishExplosion();
		}
		else {
			doExplosionTick(stepsPerTick());
		}
	}

	/**
	 * Safety check the explosion and its attributes prior to explosion, to prevent any unintentional problems
	 */
	protected void sanityCheck() {
		if (!NumberUtil.numberIsBetween(this.info.getEffectiveRadius(), 0, 256))
			throw new IllegalArgumentException("Invalid radius provided, must be between 1 and 255 blocks (inclusive)");

		if (!this.info.isBlockDamaging() && !this.info.isEntityDamaging() && !this.info.isKnockbackEntities())
			throw new IllegalArgumentException("Provided ExplosionInfo has all functional effects disabled. What are you even doing?");
	}

	/**
	 * Complete any pre-emptive work required for the explosion, that needs to be done before any of the actual explosion work is done
	 */
	protected void doPreExplosionWork() {}

	/**
	 * Make the necessary checks and events to determine whether the given explosion should do block damage
	 * @return Whether the explosion should do block damage
	 */
	protected boolean griefingCheck() {
		if (!this.info.isBlockDamaging())
			return false;

		if (this.indirectExploder != null) {
			if (this.indirectExploder instanceof Player)
				return AoAGameRules.checkDestructiveWeaponPhysics(this.level);

			return EventHooks.getMobGriefingEvent(this.level, this.indirectExploder);
		}
		else if (this.source != null) {
			if (this.source instanceof Player)
				return AoAGameRules.checkDestructiveWeaponPhysics(this.level);;

			if (this.source instanceof OwnableEntity ownable && ownable.getOwner() instanceof Player)
				return AoAGameRules.checkDestructiveWeaponPhysics(this.level);

			return EventHooks.getMobGriefingEvent(this.level, this.source);
		}

		return true;
	}

	/**
	 * Collect and return the block positions affected by this explosion. Must be completed prior to the actual explosion, for event handling
	 * @return The list of block positions that are to be impacted by this explosion
	 */
	protected ObjectArrayList<BlockPos> getAffectedBlocks() {
		return new ObjectArrayList<>();
	}

	/**
	 * Collect and return the entities affected by this explosion. Should be completed prior to the actual explosion
	 * @return The list of entities that are to be impacted by this explosion
	 */
	protected List<Entity> getAffectedEntities() {
		Predicate<Entity> predicate = this.info.getAffectsOwner() ? entity -> !entity.ignoreExplosion(this) && this.info.shouldAffectEntity(this, entity) : entity -> entity != this.source && entity != this.indirectExploder && !entity.ignoreExplosion(this) && this.info.shouldAffectEntity(this, entity);

		return this.info.getRadius().map(
				radius -> EntityRetrievalUtil.getEntities(this.level, AABB.ofSize(this.origin, radius * 2, radius * 2, radius * 2), predicate),
				squareRadius -> EntityRetrievalUtil.getEntities(this.level, squareRadius.inflateAABB(new AABB(this.origin.x, this.origin.y, this.origin.z, this.origin.x, this.origin.y, this.origin.z)), predicate)
		);
	}

	/**
	 * Filter out anything that shouldn't be affected by the explosion prior to the events and callbacks being called
	 */
	protected void filterAffectedBlocksAndEntities() {}

	/**
	 * Called when the explosion is complete.<br>
	 * This spawns the collected loot and handles final callbacks
	 */
	protected void finishExplosion() {
		spawnDrops(this.blockDrops);
		this.info.postExplosionCallback(this);
	}

	/**
	 * Handle a single tick's worth of functionality for the explosion, then schedule the next tick's work.<br>
	 * This is only called for multi-tick explosions.<br>
	 * <br>
	 * This method assumes you have already completed all other functional work such as filtering out irrelevant blocks or entities
	 * @param steps The number of actions (usually block-breaks) to run before stopping and scheduling the next tick's work.
	 */
	protected void doExplosionTick(int steps) {
		this.info.doParticles(this, this.explodeTick);

		if (shouldDamageBlocks()) {
			int step = 0;

			for (Iterator<BlockPos> iterator = this.toBlow.iterator(); step++ <= steps && iterator.hasNext();) {
				BlockPos pos = iterator.next();
				BlockState state = this.affectedBlocks.get(pos);

				captureDropsForBlock(state, pos, this.blockDrops);
				state.onBlockExploded(this.level, pos, this);
				this.info.blockBroken(this, state, pos);

				iterator.remove();
			}

			if (this.explodeTick++ < 600 && !this.toBlow.isEmpty()) {
				AoAScheduler.scheduleSyncronisedTask(() -> doExplosionTick(steps), 1);

				return;
			}
		}

		impactEntities();
		finishExplosion();
	}

	protected void impactEntities() {}

	protected void captureDropsForBlock(BlockState state, BlockPos pos, ObjectArrayList<Pair<ItemStack, BlockPos>> loot) {
		if (state.canDropFromExplosion(this.level, pos, this) && RandomUtil.percentChance(this.info.getBlockDropChance())) {
			ServerLevel serverLevel = (ServerLevel)level;
			LootParams.Builder params = new LootParams.Builder(serverLevel)
					.withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
					.withParameter(LootContextParams.TOOL, ItemStack.EMPTY)
					.withParameter(LootContextParams.EXPLOSION_RADIUS, (this.info.getBaseDamage() + this.info.getEffectiveRadius()) / 2f)
					.withOptionalParameter(LootContextParams.BLOCK_ENTITY, state.hasBlockEntity() ? this.level.getBlockEntity(pos) : null)
					.withOptionalParameter(LootContextParams.THIS_ENTITY, this.source);

			state.spawnAfterBreak(serverLevel, pos, ItemStack.EMPTY, this.source instanceof Player || this.indirectExploder instanceof Player);
			state.getDrops(params).forEach(stack -> addOrAppendStack(loot, stack, pos));
		}
	}

	protected void spawnDrops(List<Pair<ItemStack, BlockPos>> loot) {
		for (Pair<ItemStack, BlockPos> lootDrop : loot) {
			Block.popResource(this.level, lootDrop.getSecond(), lootDrop.getFirst());
		}
	}
}
