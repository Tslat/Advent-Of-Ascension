package net.tslat.aoa3.common.registration.entity;

import net.minecraft.world.entity.schedule.Activity;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.AoARegistries;

public class AoABrainActivities {
	public static final RegistryObject<Activity> FIGHT_2 = registerActivity("fight_2");
	public static final RegistryObject<Activity> FIGHT_3 = registerActivity("fight_3");

	public static void init() {}

	private static <T> RegistryObject<Activity> registerActivity(String id) {
		return AoARegistries.BRAIN_ACTIVITIES.register(id, () -> new Activity(id));
	}
}
