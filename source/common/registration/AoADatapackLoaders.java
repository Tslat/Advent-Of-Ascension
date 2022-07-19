package net.tslat.aoa3.common.registration;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.data.server.AoAHaulingFishReloadListener;
import net.tslat.aoa3.data.server.AoAResourcesReloadListener;
import net.tslat.aoa3.data.server.AoASkillReqReloadListener;
import net.tslat.aoa3.data.server.AoASkillsReloadListener;

public final class AoADatapackLoaders {
	public static void init(){
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, AddReloadListenerEvent.class, AoADatapackLoaders::registerLoaders);
	}

	private static void registerLoaders(final AddReloadListenerEvent ev) {
		ev.addListener(new AoAResourcesReloadListener());
		ev.addListener(new AoASkillReqReloadListener());
		ev.addListener(new AoASkillsReloadListener());
		ev.addListener(new AoAHaulingFishReloadListener());
	}
}
