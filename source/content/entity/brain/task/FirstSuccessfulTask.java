package net.tslat.aoa3.content.entity.brain.task;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.util.Pair;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.Behavior;
import net.minecraft.world.entity.ai.behavior.GateBehavior;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;

import java.util.List;
import java.util.Map;

public class FirstSuccessfulTask<E extends LivingEntity> extends GateBehavior<E> {
	public FirstSuccessfulTask(List<Pair<Behavior<? super E>, Integer>> taskList) {
		this(ImmutableMap.of(), taskList);
	}

	public FirstSuccessfulTask(Map<MemoryModuleType<?>, MemoryStatus> memoryRequirements, List<Pair<Behavior<? super E>, Integer>> taskList) {
		super(memoryRequirements, ImmutableSet.of(), OrderPolicy.ORDERED, RunningPolicy.RUN_ONE, taskList);
	}
}
