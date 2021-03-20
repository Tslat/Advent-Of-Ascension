package net.tslat.aoa3.worldgen;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID)
public class VanillaWorldgen {
	@SubscribeEvent
	public static void biomeLoading(BiomeLoadingEvent ev) {
		if (ev.getName() == null)
			return;

		RegistryKey<Biome> biomeKey = RegistryKey.create(Registry.BIOME_REGISTRY, ev.getName());
	}
}
