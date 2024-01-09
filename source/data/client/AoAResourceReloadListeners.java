package net.tslat.aoa3.data.client;

import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.fml.ModLoader;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.armor.AoAMiscModels;

public final class AoAResourceReloadListeners {
	public static void init() {
		AdventOfAscension.getModEventBus().addListener(EventPriority.NORMAL, false, RegisterClientReloadListenersEvent.class, AoAResourceReloadListeners::onResourceListenersRegistration);
	}

	private static void onResourceListenersRegistration(final RegisterClientReloadListenersEvent ev) {
		ev.registerReloadListener(new BestiaryReloadListener());
		ev.registerReloadListener(new MiscellaneousReloadListener());
		ev.registerReloadListener(new RealmstoneInsertsReloadListener());
		ev.registerReloadListener(new AdventGuiThemeReloadListener());
		ev.registerReloadListener((ResourceManagerReloadListener)resourceManager -> {
			if (ModLoader.isLoadingStateValid())
				AoAMiscModels.generateFactories();
		});
	}
}
