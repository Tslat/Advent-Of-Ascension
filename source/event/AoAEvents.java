package net.tslat.aoa3.event;

import net.minecraftforge.common.MinecraftForge;
import net.tslat.aoa3.event.custom.PlayerLevelChangeEvent;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.player.PlayerDataManager;

public final class AoAEvents {
	public static final void playerLevelChange(PlayerDataManager playerDataManager, int oldLevel, int newLevel, Enums.Skills skill, boolean wasNaturallyChanged) {
		MinecraftForge.EVENT_BUS.post(new PlayerLevelChangeEvent(playerDataManager, oldLevel, newLevel, skill, wasNaturallyChanged));
	}
}
