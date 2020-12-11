package net.tslat.aoa3.capabilities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.capabilities.adventplayer.AdventPlayerCapabilityProvider;
import net.tslat.aoa3.capabilities.persistentstack.PersistentStackCapabilityProvider;
import net.tslat.aoa3.capabilities.volatilestack.VolatileStackCapabilityProvider;

@Mod.EventBusSubscriber(modid = AdventOfAscension.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CapabilitiesManager {
	@SubscribeEvent
	public static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof ServerPlayerEntity)
			event.addCapability(AdventPlayerCapabilityProvider.ID, new AdventPlayerCapabilityProvider((ServerPlayerEntity)event.getObject()));
	}

	public static void registerCapabilities() {
		VolatileStackCapabilityProvider.register();
		PersistentStackCapabilityProvider.register();
		AdventPlayerCapabilityProvider.register();
	}
}
