package net.tslat.aoa3.content.entity.brain.task.temp;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FollowEntity;
import net.tslat.smartbrainlib.util.BrainUtils;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * A movement behaviour for automatically following the parent of an {@link net.minecraft.world.entity.AgeableMob AgeableMob}.
 * <p>Note that because vanilla animals do not store a reference to their parent or child, by default this behaviour just grabs the nearest
 * animal of the same class and presumes it is the parent.</p>
 */
public class FollowParent<E extends AgeableMob> extends FollowEntity<E, AgeableMob> {
	private static final List<Pair<MemoryModuleType<?>, MemoryStatus>> MEMORY_REQUIREMENTS = ObjectArrayList.of(Pair.of(MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, MemoryStatus.VALUE_PRESENT));

	public FollowParent() {
		following(this::getParent);
		stopFollowingWithin(2);
	}

	@Override
	protected boolean checkExtraStartConditions(ServerLevel level, E entity) {
		return entity.getAge() < 0 && super.checkExtraStartConditions(level, entity);
	}

	@Override
	public List<Pair<MemoryModuleType<?>, MemoryStatus>> getMemoryRequirements() {
		return MEMORY_REQUIREMENTS;
	}

	@Nullable
	protected AgeableMob getParent(E entity) {
		return BrainUtils.getMemory(entity, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES).findClosest(other -> other instanceof AgeableMob mob && mob.getAge() >= 0).map(AgeableMob.class::cast).orElse(null);
	}
}
