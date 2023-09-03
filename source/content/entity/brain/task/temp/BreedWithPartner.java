package net.tslat.aoa3.content.entity.brain.task.temp;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.animal.Animal;
import net.tslat.smartbrainlib.api.core.behaviour.ExtendedBehaviour;
import net.tslat.smartbrainlib.util.BrainUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * Functional replacement for vanilla's {@link net.minecraft.world.entity.ai.behavior.AnimalMakeLove AnimalMakeLove}.
 * <p>Makes the entity find, move to, and breed with its target mate, producing offspring.</p>
 * Defaults:
 * <ul>
 *     <li>1x walk speed modifier when moving to its breeding partner</li>
 *     <li>Spend between 3 and 5.5 seconds to create the offspring</li>
 * </ul>
 */
public class BreedWithPartner<E extends Animal> extends ExtendedBehaviour<E> {
	private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = ObjectArrayList.of(Pair.of(MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED), Pair.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED), Pair.of(MemoryModuleType.BREED_TARGET, MemoryStatus.VALUE_ABSENT), Pair.of(MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryStatus.VALUE_PRESENT));

	protected BiFunction<E, Animal, Float> speedMod = (entity, partner) -> 1f;
	protected BiFunction<E, Animal, Integer> breedTime = (entity, partner) -> entity.getRandom().nextInt(60, 110);
	protected BiPredicate<E, Animal> partnerPredicate = (entity, partner) -> entity.getType() == partner.getType() && entity.canMate(partner);

	protected int childBreedTick = -1;
	protected Animal partner = null;

	public BreedWithPartner() {
		runFor(entity -> Integer.MAX_VALUE);
	}

	@Override
	protected List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
		return MEMORY_REQUIREMENTS;
	}

	/**
	 * Set the movespeed modifier for the entity when moving to their partner.
	 * @param speedModifier The movespeed modifier/multiplier
	 * @return this
	 */
	public BreedWithPartner<E> speedMod(final BiFunction<E, Animal, Float> speedModifier) {
		this.speedMod = speedModifier;

		return this;
	}

	@Override
	protected boolean checkExtraStartConditions(ServerLevel level, E entity) {
		if (!entity.isInLove())
			return false;

		this.partner = findPartner(entity);

		return this.partner != null;
	}

	@Override
	protected boolean shouldKeepRunning(E entity) {
		return this.partner != null && this.partner.isAlive() && entity.tickCount <= this.childBreedTick && BehaviorUtils.entityIsVisible(entity.getBrain(), this.partner) && this.partnerPredicate.test(entity, this.partner);
	}

	@Override
	protected void start(E entity) {
		this.childBreedTick = entity.tickCount + this.breedTime.apply(entity, this.partner);

		BrainUtils.setMemory(entity, MemoryModuleType.BREED_TARGET, this.partner);
		BrainUtils.setMemory(this.partner, MemoryModuleType.BREED_TARGET, entity);
		BehaviorUtils.lockGazeAndWalkToEachOther(entity, this.partner, this.speedMod.apply(entity, this.partner));
	}

	@Override
	protected void tick(E entity) {
		BehaviorUtils.lockGazeAndWalkToEachOther(entity, this.partner, this.speedMod.apply(entity, this.partner));

		if (entity.closerThan(this.partner, 3) && entity.tickCount == this.childBreedTick) {
			entity.spawnChildFromBreeding((ServerLevel)entity.level(), this.partner);
			BrainUtils.clearMemory(entity, MemoryModuleType.BREED_TARGET);
			BrainUtils.clearMemory(this.partner, MemoryModuleType.BREED_TARGET);
		}
	}

	@Override
	protected void stop(E entity) {
		BrainUtils.clearMemories(entity, MemoryModuleType.BREED_TARGET, MemoryModuleType.LOOK_TARGET, MemoryModuleType.WALK_TARGET);

		if (this.partner != null)
			BrainUtils.clearMemories(this.partner, MemoryModuleType.BREED_TARGET, MemoryModuleType.LOOK_TARGET, MemoryModuleType.WALK_TARGET);

		this.childBreedTick = -1;
		this.partner = null;
	}

	@Nullable
	protected Animal findPartner(E entity) {
		return BrainUtils.getMemory(entity, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES).findClosest(entity2 -> entity2 instanceof Animal partner && this.partnerPredicate.test(entity, partner)).map(Animal.class::cast).orElse(null);
	}
}
