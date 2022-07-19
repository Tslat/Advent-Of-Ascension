package net.tslat.aoa3.common.registration.entity;

import net.minecraft.world.entity.ai.sensing.Sensor;
import net.minecraft.world.entity.ai.sensing.SensorType;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;
import net.tslat.aoa3.content.entity.brain.sensor.IncomingProjectilesSensor;

import java.util.function.Supplier;

public final class AoABrainSensors {
	public static void init() {}

	public static final RegistryObject<SensorType<IncomingProjectilesSensor>> INCOMING_PROJECTILES = registerSensor("incoming_projectiles", IncomingProjectilesSensor::new);

	private static <T extends Sensor<?>> RegistryObject<SensorType<T>> registerSensor(String id, Supplier<T> sensor) {
		return AoARegistries.BRAIN_SENSORS.register(id, () -> new SensorType<T>(sensor));
	}
}
