package net.tslat.aoa3.content.entity.brain.task.temp;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.tslat.smartbrainlib.api.core.behaviour.custom.move.FollowParent;
import net.tslat.smartbrainlib.util.BrainUtils;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiPredicate;

public class FixedFollowParent<E extends AgeableMob> extends FollowParent<E> {
    private BiPredicate<E, AgeableMob> parentPredicate = (entity, other) -> entity.getClass() == other.getClass() && other.getAge() >= 0;

    /**
     * Set the predicate that determines whether a given entity is a suitable 'parent' to follow
     */
    public FixedFollowParent<E> parentPredicate(BiPredicate<E, AgeableMob> predicate) {
        this.parentPredicate = predicate;

        return this;
    }

    @Override
    protected @Nullable AgeableMob getParent(E entity) {
        return BrainUtils.getMemory(entity, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES).findClosest(other -> other instanceof AgeableMob ageableMob && this.parentPredicate.test(entity, ageableMob)).map(AgeableMob.class::cast).orElse(null);
    }
}
