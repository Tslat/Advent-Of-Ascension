package net.tslat.aoa3.content.entity.ai.temp;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.behavior.EntityTracker;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.WalkTarget;
import net.tslat.smartbrainlib.util.BrainUtils;

public class SetWalkTargetToAttackTarget<E extends Mob> extends net.tslat.smartbrainlib.api.core.behaviour.custom.path.SetWalkTargetToAttackTarget<E> {
    @Override
    protected void start(E entity) {
        Brain<?> brain = entity.getBrain();
        LivingEntity target = BrainUtils.getTargetOfEntity(entity);

        if (entity.getSensing().hasLineOfSight(target) && BehaviorUtils.isWithinAttackRange(entity, target, 1)) {
            BrainUtils.clearMemory(brain, MemoryModuleType.WALK_TARGET);
        }
        else {
            BrainUtils.setMemory(brain, MemoryModuleType.LOOK_TARGET, new EntityTracker(target, true));
            // Track eye height for walk target for non-ground pathing
            BrainUtils.setMemory(brain, MemoryModuleType.WALK_TARGET, new WalkTarget(new EntityTracker(target, true), this.speedMod.apply(entity, target), this.closeEnoughWhen.applyAsInt(entity, target)));
        }
    }
}
