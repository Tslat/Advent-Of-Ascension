package net.tslat.aoa3.common.registration;

import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.object.entity.brain.sensor.IncomingProjectilesSensor;

import java.util.function.Supplier;

public final class AoABrainSensors {
	public static final DeferredRegister<SensorType<?>> SENSORS = DeferredRegister.create(ForgeRegistries.SENSOR_TYPES, AdventOfAscension.MOD_ID);

	public static final RegistryObject<SensorType<IncomingProjectilesSensor>> INCOMING_PROJECTILES = registerSensor("incoming_projectiles", IncomingProjectilesSensor::new);

	private static <T extends Sensor<?>> RegistryObject<SensorType<T>> registerSensor(String id, Supplier<T> sensor) {
		return SENSORS.register(id, () -> new SensorType<T>(sensor));
	}
}
