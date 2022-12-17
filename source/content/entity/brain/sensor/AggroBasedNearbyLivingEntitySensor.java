package net.tslat.aoa3.content.entity.brain.sensor;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.NearestVisibleLivingEntities;
import net.tslat.aoa3.common.registration.AoAAttributes;
import net.tslat.smartbrainlib.api.core.sensor.vanilla.NearbyLivingEntitySensor;
import net.tslat.smartbrainlib.object.SquareRadius;
import net.tslat.smartbrainlib.util.BrainUtils;
import net.tslat.smartbrainlib.util.EntityRetrievalUtil;

import java.util.Comparator;
import java.util.List;

public class AggroBasedNearbyLivingEntitySensor<E extends LivingEntity> extends NearbyLivingEntitySensor<E> {
	@Override
	protected void doTick(ServerLevel level, E entity) {
		SquareRadius radius = this.radius;

		if (radius == null) {
			double dist = entity.getAttributeValue(AoAAttributes.AGGRO_RANGE.get());

			radius = new SquareRadius(dist, dist);
		}

		List<LivingEntity> entities = EntityRetrievalUtil.getEntities(level, entity.getBoundingBox().inflate(radius.xzRadius(), radius.yRadius(), radius.xzRadius()), obj -> obj instanceof LivingEntity livingEntity && predicate().test(livingEntity, entity));

		entities.sort(Comparator.comparingDouble(entity::distanceToSqr));

		BrainUtils.setMemory(entity, MemoryModuleType.NEAREST_LIVING_ENTITIES, entities);
		BrainUtils.setMemory(entity, MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES, new NearestVisibleLivingEntities(entity, entities));
	}
}
