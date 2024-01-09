package net.tslat.aoa3.common.registration.entity;

import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.tslat.aoa3.common.registration.AoARegistries;

import java.util.function.Supplier;

public final class AoABrainSensors {
	public static void init() {}

	private static <T extends Sensor<?>> DeferredHolder<SensorType<?>, SensorType<T>> registerSensor(String id, Supplier<T> sensor) {
		return AoARegistries.BRAIN_SENSORS.register(id, () -> new SensorType<T>(sensor));
	}
}
