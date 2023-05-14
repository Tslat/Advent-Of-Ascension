package net.tslat.aoa3.common.registration;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.tslat.aoa3.data.server.*;

public final class AoADatapackLoaders {
	public static void init(){
		MinecraftForge.EVENT_BUS.addListener(EventPriority.NORMAL, false, AddReloadListenerEvent.class, AoADatapackLoaders::registerLoaders);
	}

	private static void registerLoaders(final AddReloadListenerEvent ev) {
		ev.addListener(new AoAResourcesReloadListener());
		ev.addListener(new AoASkillReqReloadListener());
		ev.addListener(new AoASkillsReloadListener());
		ev.addListener(new AoAHaulingFishReloadListener());
		ev.addListener(new AoANowhereBossArenaListener());
		ev.addListener(new AoANowhereParkourCourseListener());
		ev.addListener(new AoACustomSpawnersListener());
	}
}
