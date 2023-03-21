package net.tslat.aoa3.content.entity.brain.task.temp;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.phys.Vec3;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;
import net.tslat.smartbrainlib.object.SquareRadius;
import net.tslat.smartbrainlib.util.BrainUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * Set a random position to fly to, taking into account the entity's current heading.<br>
 * Keeps the entity roughly near ground level, encouraging hover-flight rather than floating off into the sky.<br>
 * Defaults:
 * <ul>
 *     <li>1x movespeed modifier</li>
 *     <li>10-block lateral radius</li>
 *     <li>7-block vertical radius</li>
 * </ul>
 * @param <E>
 */
public class SetRandomHoverTarget<E extends PathfinderMob> extends ExtendedBehaviour<E> {
	private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = ObjectArrayList.of(Pair.of(MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT));

	protected BiFunction<E, Vec3, Float> speedModifier = (entity, targetPos) -> 1f;
	protected SquareRadius radius = new SquareRadius(10, 7);
	protected BiPredicate<E, Vec3> positionPredicate = (entity, pos) -> true;

	@Override
	protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
		return MEMORY_REQUIREMENTS;
	}

	/**
	 * Set the radius in which to look for flight positions.
	 * @param radius The coordinate radius, in blocks
	 * @return this
	 */
	public SetRandomHoverTarget<E> setRadius(double radius) {
		return setRadius(radius, radius);
	}

	/**
	 * Set the radius in which to look for flight positions.
	 * @param xz The X/Z coordinate radius, in blocks
	 * @param y The Y coordinate radius, in blocks
	 * @return this
	 */
	public SetRandomHoverTarget<E> setRadius(double xz, double y) {
		this.radius = new SquareRadius(xz, y);

		return this;
	}

	/**
	 * Set the movespeed modifier for the path when chosen.
	 * @param modifier The movespeed modifier/multiplier
	 * @return this
	 */
	public SetRandomHoverTarget<E> speedModifier(float modifier) {
		return speedModifier((entity, targetPos) -> modifier);
	}

	/**
	 * Set the movespeed modifier for the path when chosen.
	 * @param function The movespeed modifier/multiplier function
	 * @return this
	 */
	public SetRandomHoverTarget<E> speedModifier(BiFunction<E, Vec3, Float> function) {
		this.speedModifier = function;

		return this;
	}

	/**
	 * Sets a predicate to check whether the target movement position is valid or not
	 * @param predicate The predicate
	 * @return this
	 */
	public SetRandomHoverTarget<E> flightTargetPredicate(BiPredicate<E, Vec3> predicate) {
		this.positionPredicate = predicate;

		return this;
	}

	@Override
	protected void start(E entity) {
		Vec3 targetPos = getTargetPos(entity);

		if (!this.positionPredicate.test(entity, targetPos))
			targetPos = null;

		if (targetPos == null) {
			BrainUtils.clearMemory(entity, MemoryModuleType.WALK_TARGET);
		}
		else {
			BrainUtils.setMemory(entity, MemoryModuleType.WALK_TARGET, new WalkTarget(targetPos, this.speedModifier.apply(entity, targetPos), 0));
		}
	}

	@Nullable
	protected Vec3 getTargetPos(E entity) {
		Vec3 entityFacing = entity.getViewVector(0);
		Vec3 hoverPos = HoverRandomPos.getPos(entity, (int)(Math.ceil(this.radius.xzRadius())), (int)Math.ceil(this.radius.yRadius()), entityFacing.x, entityFacing.z, Mth.HALF_PI, 3, 1);

		if (hoverPos != null)
			return hoverPos;

		return AirAndWaterRandomPos.getPos(entity, (int)(Math.ceil(this.radius.xzRadius())), (int)Math.ceil(this.radius.yRadius()), -2, entityFacing.x, entityFacing.z, Mth.HALF_PI);
	}
}
