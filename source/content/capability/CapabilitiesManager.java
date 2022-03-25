package net.tslat.aoa3.content.capability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.content.capability.adventplayer.AdventPlayerCapabilityProvider;
import net.tslat.aoa3.content.capability.persistentstack.PersistentStackCapabilityProvider;
import net.tslat.aoa3.content.capability.volatilestack.VolatileStackCapabilityProvider;

public final class CapabilitiesManager {
	public static void preInit() {
		MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, EventPriority.NORMAL, false, AttachCapabilitiesEvent.class, CapabilitiesManager::attachEntityCapabilities);
	}

	private static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof ServerPlayerEntity)
			event.addCapability(AdventPlayerCapabilityProvider.ID, new AdventPlayerCapabilityProvider((ServerPlayerEntity)event.getObject()));
	}

	public static void registerCapabilities() {
		VolatileStackCapabilityProvider.register();
		PersistentStackCapabilityProvider.register();
		AdventPlayerCapabilityProvider.register();
	}
}
