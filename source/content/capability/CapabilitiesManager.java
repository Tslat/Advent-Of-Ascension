package net.tslat.aoa3.content.capability;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.content.capability.adventplayer.AdventPlayerCapabilityProvider;

public final class CapabilitiesManager {
	public static void preInit() {
		MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, EventPriority.NORMAL, false, AttachCapabilitiesEvent.class, CapabilitiesManager::attachEntityCapabilities);
	}

	private static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof ServerPlayer)
			event.addCapability(AdventPlayerCapabilityProvider.ID, new AdventPlayerCapabilityProvider((ServerPlayer)event.getObject()));
	}
}
