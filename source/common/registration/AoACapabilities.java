package net.tslat.aoa3.common.registration;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.capability.adventplayer.AdventPlayerCapabilityHandles;
import net.tslat.aoa3.content.capability.adventplayer.AdventPlayerCapabilityProvider;
import net.tslat.aoa3.content.capability.persistentstack.PersistentStackCapabilityHandles;
import net.tslat.aoa3.content.capability.volatilestack.VolatileStackCapabilityHandles;

public final class AoACapabilities {
	public static void init(){
		AdventOfAscension.modEventBus.addListener(EventPriority.NORMAL, false, RegisterCapabilitiesEvent.class, AoACapabilities::registerCapabilities);
		MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, EventPriority.NORMAL, false, AttachCapabilitiesEvent.class, AoACapabilities::attachEntityCapabilities);
	}

	private static void registerCapabilities(final RegisterCapabilitiesEvent ev) {
		ev.register(AdventPlayerCapabilityHandles.class);
		ev.register(VolatileStackCapabilityHandles.class);
		ev.register(PersistentStackCapabilityHandles.class);
	}

	private static void attachEntityCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof ServerPlayer player)
			event.addCapability(AdventPlayerCapabilityProvider.ID, new AdventPlayerCapabilityProvider(player));
	}
}
