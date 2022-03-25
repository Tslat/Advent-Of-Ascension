package net.tslat.aoa3.content.entity.brain;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.Dynamic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.loading.FMLLoader;
import net.tslat.aoa3.advent.Logging;
import org.apache.commons.lang3.mutable.MutableObject;
import org.apache.commons.lang3.tuple.Triple;
import org.apache.logging.log4j.Level;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SmartBrainHandler<T extends LivingEntity & SmartBrainOwner<T>> {
	private static final HashMap<EntityType<? extends LivingEntity>, ImmutableList<MemoryModuleType<?>>> brainMemoryCache = new HashMap<>();

	private final T owner;
	private Brain<T> brain = null;

	private final boolean saveMemories;
	private final boolean hasDynamicTasks;

	public SmartBrainHandler(T owner) {
		this(owner, false);
	}

	public SmartBrainHandler(T owner, boolean hasDynamicTasks) {
		this(owner, false, hasDynamicTasks);
	}

	public SmartBrainHandler(T owner, boolean saveMemories, boolean hasDynamicTasks) {
		this.owner = owner;
		this.saveMemories = saveMemories;
		this.hasDynamicTasks = hasDynamicTasks;
	}

	public Brain.BrainCodec<T> getBrainCodec() {
		if (!this.hasDynamicTasks && brainMemoryCache.containsKey(this.owner.getType()))
			return Brain.provider(brainMemoryCache.get(this.owner.getType()), this.owner.getSensors());

		if (this.brain != null)
			return Brain.provider(ImmutableList.copyOf(this.brain.memories.keySet()), this.owner.getSensors());

		ImmutableList<SensorType<? extends Sensor<? super T>>> sensors = owner.getSensors();

		return Brain.provider(getMemoryList(compileTasks(), sensors), sensors);
	}

	public Brain<T> getBrain() {
		return this.brain;
	}

	public Brain<T> makeBrain(Dynamic<?> codecLoader) {
		if (this.brain != null && !this.saveMemories)
			return this.brain;

		ImmutableList<SensorType<? extends Sensor<? super T>>> sensors = owner.getSensors();
		Map<Activity, Triple<Integer, ImmutableList<? extends Task<? super T>>, MemoryModuleType<?>>> taskList = compileTasks();
		ImmutableList<MemoryModuleType<?>> memories;

		if (!hasDynamicTasks && brainMemoryCache.containsKey(owner.getType())) {
			memories = brainMemoryCache.get(owner.getType());
		}
		else {
			memories = getMemoryList(taskList, sensors);

			if (!this.hasDynamicTasks)
				brainMemoryCache.put((EntityType<? extends LivingEntity>)this.owner.getType(), memories);
		}

		if (!saveMemories) {
			Codec<Brain<T>> brainCodec = emptyBrainCodec();
			this.brain = new Brain<T>(memories, sensors, ImmutableList.of(), () -> brainCodec);
		}
		else {
			this.brain = Brain.provider(memories, sensors).makeBrain(codecLoader);
		}

		sanityCheckBrainState(taskList);
		applyTasks(taskList);

		return this.brain;
	}

	private Codec<Brain<T>> emptyBrainCodec() {
		MutableObject<Codec<Brain<T>>> brainCodec = new MutableObject<Codec<Brain<T>>>();

		brainCodec.setValue(Codec.unit(() -> new Brain<T>(ImmutableList.of(), ImmutableList.of(), ImmutableList.of(), brainCodec::getValue)));

		return brainCodec.getValue();
	}

	protected ImmutableList<MemoryModuleType<?>> getMemoryList(Map<Activity, Triple<Integer, ImmutableList<? extends Task<? super T>>, MemoryModuleType<?>>> taskList, ImmutableList<SensorType<? extends Sensor<? super T>>> sensors) {
		if (!hasDynamicTasks && brainMemoryCache.containsKey(owner.getType()))
			return brainMemoryCache.get(owner.getType());

		HashSet<MemoryModuleType<?>> memoryTypes = new HashSet<MemoryModuleType<?>>();

		taskList.forEach((key, value) -> value.getMiddle().forEach(task -> memoryTypes.addAll(task.entryCondition.keySet())));

		return ImmutableList.copyOf(memoryTypes);
	}

	private Map<Activity, Triple<Integer, ImmutableList<? extends Task<? super T>>, MemoryModuleType<?>>> compileTasks() {
		Map<Activity, Triple<Integer, ImmutableList<? extends Task<? super T>>, MemoryModuleType<?>>> map = new HashMap<Activity, Triple<Integer, ImmutableList<? extends Task<? super T>>, MemoryModuleType<?>>>();

		map.put(Activity.CORE, owner.getCoreTasks());
		map.put(Activity.IDLE, owner.getIdleTasks());
		map.put(Activity.FIGHT, owner.getFightTasks());
		map.putAll(owner.getAdditionalTasks());

		map.entrySet().removeIf(entry -> entry.getValue().getMiddle().isEmpty());

		return map;
	}

	private void applyTasks(Map<Activity, Triple<Integer, ImmutableList<? extends Task<? super T>>, MemoryModuleType<?>>> taskList) {
		for (Map.Entry<Activity, Triple<Integer, ImmutableList<? extends Task<? super T>>, MemoryModuleType<?>>> tasksEntry : taskList.entrySet()) {
			Triple<Integer, ImmutableList<? extends Task<? super T>>, MemoryModuleType<?>> tasks = tasksEntry.getValue();

			if (tasks.getRight() != null) {
				this.brain.addActivityAndRemoveMemoryWhenStopped(tasksEntry.getKey(), tasks.getLeft(), tasks.getMiddle(), tasks.getRight());
			}
			else {
				this.brain.addActivity(tasksEntry.getKey(), tasks.getLeft(), tasks.getMiddle());
			}
		}

		this.brain.setCoreActivities(this.owner.getAlwaysRunningActivities());
		this.brain.setDefaultActivity(this.owner.getDefaultActivity());
		this.brain.useDefaultActivity();
		this.owner.handleAdditionalBrainSetup(this.brain);
	}

	private void sanityCheckBrainState(Map<Activity, Triple<Integer, ImmutableList<? extends Task<? super T>>, MemoryModuleType<?>>> taskList) {
		if (!FMLLoader.isProduction()) {
			for (Sensor<? super T> sensor : this.brain.sensors.values()) {
				for (MemoryModuleType<?> memoryType : sensor.requires()) {
					if (!this.brain.memories.containsKey(memoryType))
						Logging.logMessage(Level.ERROR, "Required memory module not present in entity brain. " + this.owner.getType().getRegistryName().toString());
				}
			}
		}
	}

	public void tick() {
		this.owner.level.getProfiler().push("SmartBrain");
		this.brain.tick((ServerWorld)this.owner.level, this.owner);
		this.brain.setActiveActivityToFirstValid(this.owner.getActivityPriorities());
		this.owner.level.getProfiler().pop();

		if (this.owner instanceof MobEntity)
			((MobEntity)this.owner).setAggressive(((MobEntity)this.owner).getTarget() != null);
	}
}
