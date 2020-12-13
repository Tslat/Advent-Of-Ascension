package net.tslat.aoa3.event.custom;

import net.minecraftforge.common.MinecraftForge;
import net.tslat.aoa3.event.custom.events.PlayerLevelChangeEvent;
import net.tslat.aoa3.util.constant.Skills;
import net.tslat.aoa3.util.player.PlayerDataManager;

public final class AoAEvents {
	public static final void playerLevelChange(PlayerDataManager playerDataManager, int oldLevel, int newLevel, Skills skill, boolean wasNaturallyChanged) {
		MinecraftForge.EVENT_BUS.post(new PlayerLevelChangeEvent(playerDataManager, oldLevel, newLevel, skill, wasNaturallyChanged));
	}
}
