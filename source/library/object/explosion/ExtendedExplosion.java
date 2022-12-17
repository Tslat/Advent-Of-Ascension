package net.tslat.aoa3.library.object.explosion;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
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
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.aoa3.common.registration.AoAGameRules;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Predicate;

public class ExtendedExplosion extends Explosion {
	protected final ExplosionInfo info;

	@Nullable
	protected Entity indirectExploder;
	protected Vec3 origin;

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
		super(level, exploder, damageSource, null, x, y, z, 0, false, BlockInteraction.KEEP);

		this.info = explosionInfo;
		this.origin = getPosition();
		this.indirectExploder = indirectExploder;
	}

	@Override
	public void explode() {
		sanityCheck();

		this.level.gameEvent(this.source, GameEvent.EXPLODE, this.origin);

		this.damageCalculator = this.source != null ? new EntityBasedExplosionDamageCalculator(this.source) : new ExplosionDamageCalculator();

		if (this.info.squareRadius != null) {
			AABB bounds = this.info.squareRadius.inflateAABB(new AABB(this.origin.x, this.origin.y, this.origin.z, this.origin.x, this.origin.y, this.origin.z));
			this.boundsPredicate = bounds::contains;
		}
		else {
			this.boundsPredicate = pos -> pos.closerThan(this.origin, this.info.radius);
		}
	}

	protected void sanityCheck() {
		if (this.info.radius <= 0 || this.info.radius > 256)
			throw new IllegalArgumentException("Invalid radius provided, must be between 1 and 255 blocks (inclusive)");

		if (this.info.squareRadius != null) {
			if (this.info.squareRadius.xzRadius() <= 0 || this.info.squareRadius.xzRadius() > 256)
				throw new IllegalArgumentException("Invalid lateral radius provided, must be between 1 and 255 blocks (inclusive)");

			if (this.info.squareRadius.yRadius() <= 0 || this.info.squareRadius.yRadius() > this.level.getMaxBuildHeight() - this.level.getMinBuildHeight())
				throw new IllegalArgumentException("Invalid vertical radius provided, must be between 1 and " + (this.level.getMaxBuildHeight() - this.level.getMinBuildHeight()) + " blocks (inclusive)");
		}
	}

	protected List<Entity> getEntitiesInBlastRadius() {
		Predicate<Entity> predicate = this.info.affectsOwner ? entity -> !entity.ignoreExplosion() && this.info.affectedEntityPredicate.test(this, entity) : entity -> entity != this.source && entity != this.indirectExploder && !entity.ignoreExplosion() && this.info.affectedEntityPredicate.test(this, entity);

		if (this.info.squareRadius != null) {
			return EntityRetrievalUtil.getEntities(this.level, this.info.squareRadius.inflateAABB(new AABB(this.origin.x, this.origin.y, this.origin.z, this.origin.x, this.origin.y, this.origin.z)), predicate);
		}
		else {
			return EntityRetrievalUtil.getEntities(this.level, new AABB(this.origin.x - this.info.radius, this.origin.y - this.info.radius, this.origin.z - this.info.radius, this.origin.x + this.info.radius, this.origin.y + this.info.radius, this.origin.z + this.info.radius), predicate);
		}
	}

	protected void captureBlockDrops(BlockState state, BlockPos pos, ObjectArrayList<Pair<ItemStack, BlockPos>> loot) {
		if (state.canDropFromExplosion(this.level, pos, this) && RandomUtil.percentChance(this.info.blockDropChance)) {
			ServerLevel serverLevel = (ServerLevel)level;
			LootContext.Builder lootContext = new LootContext.Builder(serverLevel)
					.withRandom(this.level.random)
					.withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
					.withParameter(LootContextParams.TOOL, ItemStack.EMPTY)
					.withParameter(LootContextParams.EXPLOSION_RADIUS, (this.info.baseDamage + this.info.radius) / 2f)
					.withOptionalParameter(LootContextParams.BLOCK_ENTITY, state.hasBlockEntity() ? this.level.getBlockEntity(pos) : null)
					.withOptionalParameter(LootContextParams.THIS_ENTITY, this.source);

			state.spawnAfterBreak(serverLevel, pos, ItemStack.EMPTY, this.source instanceof Player || this.indirectExploder instanceof Player);
			state.getDrops(lootContext).forEach(stack -> addBlockDrops(loot, stack, pos));
		}
	}

	protected void spawnDrops(List<Pair<ItemStack, BlockPos>> loot) {
		for (Pair<ItemStack, BlockPos> lootDrop : loot) {
			Block.popResource(this.level, lootDrop.getSecond(), lootDrop.getFirst());
		}
	}

	public ExplosionInfo getExplosionInfo() {
		return this.info;
	}

	public Level getLevel() {
		return this.level;
	}

	public int getExplodeTick() {
		return this.explodeTick;
	}

	public boolean shouldDamageBlocks() {
		if (this.info.noBlockDamage)
			return false;

		if (this.indirectExploder != null) {
			if (this.indirectExploder instanceof Player)
				return AoAGameRules.checkDestructiveWeaponPhysics(this.level);

			return ForgeEventFactory.getMobGriefingEvent(this.level, this.indirectExploder);
		}
		else if (this.source != null) {
			if (this.source instanceof Player)
				return AoAGameRules.checkDestructiveWeaponPhysics(this.level);;

			if (this.source instanceof OwnableEntity ownable && ownable.getOwner() instanceof Player)
				return AoAGameRules.checkDestructiveWeaponPhysics(this.level);

			return ForgeEventFactory.getMobGriefingEvent(this.level, this.source);
		}

		return true;
	}
}
