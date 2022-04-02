package net.tslat.aoa3.content.entity.brain;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.Brain;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraft.world.entity.schedule.Activity;
import org.apache.commons.lang3.tuple.Triple;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SmartBrainOwner<T extends LivingEntity & SmartBrainOwner<T>> {
	@Nullable
	SmartBrainHandler<T> getBrainHandler();
	void setBrainHandler(SmartBrainHandler<T> handler);
	ImmutableList<SensorType<? extends Sensor<? super T>>> getSensors();

	default Triple<Integer, ImmutableList<? extends Behavior<? super T>>, MemoryModuleType<?>> getCoreTasks() {
		return Triple.of(0, ImmutableList.of(), null);
	}

	default Triple<Integer, ImmutableList<? extends Behavior<? super T>>, MemoryModuleType<?>> getIdleTasks() {
		return Triple.of(0, ImmutableList.of(), null);
	}

	default Triple<Integer, ImmutableList<? extends Behavior<? super T>>, MemoryModuleType<?>> getFightTasks() {
		return Triple.of(0, ImmutableList.of(), MemoryModuleType.ATTACK_TARGET);
	}

	default Map<Activity, Triple<Integer, ImmutableList<? extends Behavior<? super T>>, MemoryModuleType<?>>> getAdditionalTasks() {
		return new HashMap<>();
	}

	default Set<Activity> getAlwaysRunningActivities() {
		return ImmutableSet.of(Activity.CORE);
	}

	default Activity getDefaultActivity() {
		return Activity.IDLE;
	}

	default List<Activity> getActivityPriorities() {
		return ImmutableList.of(Activity.FIGHT, Activity.IDLE);
	}

	default void handleAdditionalBrainSetup(Brain<T> brain) {}
}
